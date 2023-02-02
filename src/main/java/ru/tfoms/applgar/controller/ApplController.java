package ru.tfoms.applgar.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ru.tfoms.applgar.entity.Appl;
import ru.tfoms.applgar.exception.ExcelGeneratorException;
import ru.tfoms.applgar.model.ApplSearchParameters;
import ru.tfoms.applgar.model.FilterWord;
import ru.tfoms.applgar.model.Gar;
import ru.tfoms.applgar.model.SelectedAddress;
import ru.tfoms.applgar.service.AddressService;
import ru.tfoms.applgar.service.ApplService;
import ru.tfoms.applgar.service.InspectorService;
import ru.tfoms.applgar.service.SmoService;
import ru.tfoms.applgar.util.DateValidator;

@Controller
public class ApplController {
	private final ApplService applService;
	private final AddressService<?> addrService;
	private final InspectorService inspectorService;
	private final SmoService smoService;

	public ApplController(ApplService service, AddressService<?> addrService, InspectorService inspectorService, SmoService smoService) {
		this.applService = service;
		this.addrService = addrService;
		this.inspectorService = inspectorService;
		this.smoService = smoService;
	}

	@GetMapping("appl")
	public String index(Model model, Optional<Integer> clear) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();

		if (clear.orElse(0) == 1) {
			session.removeAttribute("applSParam");
		}

		ApplSearchParameters applSParam = (ApplSearchParameters) applService.getSParams(session)
				.orElse(new ApplSearchParameters());
		model.addAttribute("applSParam", applSParam);
		model.addAttribute("applPage", new PageImpl<Appl>(new ArrayList<>()));
		model.addAttribute("inspectors", inspectorService.findInspectors(session));
		model.addAttribute("branches", smoService.findBranches(session));

		return "appl-form";
	}

	@PostMapping("appl")
	public String index(Model model, @ModelAttribute("applSParam") @Valid ApplSearchParameters applSParam,
			BindingResult bindingResult, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) throws ParseException {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();

		model.addAttribute("applPage", new PageImpl<Appl>(new ArrayList<>()));
		model.addAttribute("inspectors", inspectorService.findInspectors(session));
		model.addAttribute("branches", smoService.findBranches(session));
		if (!DateValidator.isValid(applSParam.getDtReg1())) {
			bindingResult.rejectValue("dtReg1", "Invalid date");
		}
		if (!DateValidator.isValid(applSParam.getDtReg2())) {
			bindingResult.rejectValue("dtReg2", "Invalid date");
		}
		if (bindingResult.hasErrors())
			return "appl-form";

		Page<Appl> applPage = applService.getPage(applSParam, page, size, session);
		model.addAttribute("applPage", applPage);
		

		int totalPages = applPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		applService.saveSParams(applSParam, session);

		return "appl-form";
	}

	@PostMapping("/appl/addr")
	public String address(Model model, @RequestParam("idAppl") Long applId, @ModelAttribute("gar") Gar gar,
			@ModelAttribute("selAddress") SelectedAddress selAddress, @ModelAttribute("filter") FilterWord filter,
			@RequestParam("save") Optional<Integer> save) {

		Appl appl = applService.findById(applId).orElse(null);
		if (appl == null) {
			return "redirect:/";
		}
		model.addAttribute("appl", appl);
		
		String okato_reg = "-";
		String okato_pr = "-";
		if (appl.getId_adrreg() != null) {
			okato_reg = addrService.findOkato(appl.getId_adrreg());
		}
		if (appl.getId_adrpr() != null) {
			okato_pr = addrService.findOkato(appl.getId_adrpr());
		}
		model.addAttribute("okato_reg", okato_reg);
		model.addAttribute("okato_pr", okato_pr);

		if (selAddress.getIdlev1Reg() == null && selAddress.getIdlev1Pr() == null
				&& addrService.isRecordExist(appl.getId_appl())) {
			addrService.initFromDB(selAddress, appl.getId_appl());
		}

		addrService.fillPrIfSameWithReg(appl, selAddress);
		addrService.setGar(appl, gar, filter, selAddress);

		/** Save result **/
		if (save.orElse(0) == 1) {
			model.addAttribute("saved", addrService.saveResult(appl, selAddress, gar));
		}

		return "addr-form";
	}

	@PostMapping("/appl/report")
	@ResponseBody
	public ResponseEntity<?> download(@ModelAttribute("applSParam") @Valid ApplSearchParameters applSParam,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors())
			return ResponseEntity.badRequest().build();

		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();

		try {
			session.setAttribute("status", "busy");
			ResponseEntity<?> resource = ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.xls")
					.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
					.body(new InputStreamResource(applService.createExcel(applSParam, session)));
			session.setAttribute("status", "done");
			return resource;
		} catch (ExcelGeneratorException | IOException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/appl/report/status")
	@ResponseBody
	public String checkDStatus() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return (String) attr.getRequest().getSession().getAttribute("status");
	}
}
