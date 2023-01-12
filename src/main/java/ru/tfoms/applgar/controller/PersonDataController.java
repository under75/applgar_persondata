package ru.tfoms.applgar.controller;

import static ru.tfoms.applgar.service.PersDataService.policyType;
import static ru.tfoms.applgar.service.PersDataService.resultType;

import java.text.ParseException;
import java.util.Collection;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ru.tfoms.applgar.entity.Dudl;
import ru.tfoms.applgar.entity.OmsPolicy;
import ru.tfoms.applgar.entity.PersDataError;
import ru.tfoms.applgar.entity.Person;
import ru.tfoms.applgar.entity.PersonData;
import ru.tfoms.applgar.entity.User;
import ru.tfoms.applgar.model.PersSearchParameters;
import ru.tfoms.applgar.service.PersDataService;
import ru.tfoms.applgar.service.PersDataService.Show;

@Controller
public class PersonDataController {
	private final PersDataService service;

	public PersonDataController(PersDataService service) {
		super();
		this.service = service;
	}

	@GetMapping("/pers")
	public String index(Model model) {
		model.addAttribute("persSParam", new PersSearchParameters());
		model.addAttribute("policyTypes", PersDataService.policyType);
		model.addAttribute("dudlTypes", service.getDudlTypes());
		model.addAttribute("resultTypes", PersDataService.resultType);

		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		Optional<Integer> page = Optional.of(1);
		Page<PersonData> persDataPage = service.getPersDataPage((User) session.getAttribute("user"), page);
		model.addAttribute("persDataPage", persDataPage);

		return "pers-form";
	}

	@PostMapping("/pers")
	public String index(Model model, @ModelAttribute("persSParam") @Valid PersSearchParameters persSParam,
			BindingResult bindingResult, @RequestParam("page") Optional<Integer> page) throws ParseException {

		model.addAttribute("policyTypes", policyType);
		model.addAttribute("dudlTypes", service.getDudlTypes());
		model.addAttribute("resultTypes", resultType);

		if (!page.isPresent())
			service.validate(persSParam, bindingResult);

		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();

		if (!bindingResult.hasErrors() && !page.isPresent())
			service.saveRequest(persSParam, (User) session.getAttribute("user"));

		Page<PersonData> persDataPage = service.getPersDataPage((User) session.getAttribute("user"), page);
		model.addAttribute("persDataPage", persDataPage);

		return "pers-form";
	}

	@PostMapping("/pers/res/{show}")
	public String result(Model model, @RequestParam("rid") Long rid, @PathVariable String show) {

		Collection<PersDataError> errors = service.getErrorsByRid(rid);
		if (errors.size() > 0) {
			model.addAttribute("errors", errors);
			return "pers-err";
		}

		PersonData personData = service.getPersonDataByRid(rid);
		if (!service.isRequestValid(personData, show)) 
			return "404code";
		
		Collection<Person> persons = service.getPersonsByRid(rid);
		Collection<OmsPolicy> policies = service.getPoliciesByRid(rid);
		Collection<Dudl> dudls  = service.getDudlsByRid(rid);
		
		model.addAttribute("show", Show.valueOf(show));
		model.addAttribute("resultTypes", resultType);
		model.addAttribute("personData", personData);
		model.addAttribute("persons", persons);
		model.addAttribute("policies", policies);
		model.addAttribute("dudls", dudls);

		return "pers-res";
	}
	
}
