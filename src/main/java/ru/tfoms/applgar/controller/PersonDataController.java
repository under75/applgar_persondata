package ru.tfoms.applgar.controller;

import java.text.ParseException;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ru.tfoms.applgar.entity.PersonData;
import ru.tfoms.applgar.entity.User;
import ru.tfoms.applgar.model.PersSearchParameters;
import ru.tfoms.applgar.service.PersDataService;

@Controller
public class PersonDataController {
	private final PersDataService service;

	public PersonDataController(PersDataService service) {
		super();
		this.service = service;
	}

	@GetMapping("/pers")
	public String index(Model model, @RequestParam("page") Optional<Integer> page) {
		model.addAttribute("persSParam", new PersSearchParameters());
		model.addAttribute("policyTypes", PersDataService.policyType);
		model.addAttribute("dudlTypes", service.getDudlTypes());
		model.addAttribute("resultTypes", PersDataService.resultType);

		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		Page<PersonData> persDataPage = service.getPersDataPage((User) session.getAttribute("user"), page);
		model.addAttribute("persDataPage", persDataPage);

		return "pers-form";
	}

	@PostMapping("/pers")
	public String index(Model model, @ModelAttribute("persSParam") @Valid PersSearchParameters persSParam,
			BindingResult bindingResult, @RequestParam("page") Optional<Integer> page) throws ParseException {

		model.addAttribute("policyTypes", PersDataService.policyType);
		model.addAttribute("dudlTypes", service.getDudlTypes());
		model.addAttribute("resultTypes", PersDataService.resultType);

		if (persSParam.getDudlType() != null && persSParam.getDudlNum().trim().isEmpty()) {
			bindingResult.rejectValue("dudlNum", "");
		} else if (!persSParam.getDudlNum().trim().isEmpty() && persSParam.getDudlType() == null) {
			bindingResult.rejectValue("dudlType", "");
		}
		if (bindingResult.hasErrors())
			return "pers-form";

		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		service.saveRequest(persSParam, (User) session.getAttribute("user"));

		Page<PersonData> persDataPage = service.getPersDataPage((User) session.getAttribute("user"), page);
		model.addAttribute("persDataPage", persDataPage);

		return "pers-form";
	}
}
