package ru.tfoms.applgar.controller;

import static ru.tfoms.applgar.service.PersDataService.policyType;
import static ru.tfoms.applgar.service.PersDataService.resultType;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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

import ru.tfoms.applgar.entity.AddrGar;
import ru.tfoms.applgar.entity.Attach;
import ru.tfoms.applgar.entity.Contact;
import ru.tfoms.applgar.entity.Dudl;
import ru.tfoms.applgar.entity.HouseGar;
import ru.tfoms.applgar.entity.OmsPolicy;
import ru.tfoms.applgar.entity.PersAddress;
import ru.tfoms.applgar.entity.PersDataError;
import ru.tfoms.applgar.entity.Person;
import ru.tfoms.applgar.entity.PersonCritData;
import ru.tfoms.applgar.entity.PersonData;
import ru.tfoms.applgar.entity.Snils;
import ru.tfoms.applgar.entity.SocialStatus;
import ru.tfoms.applgar.entity.User;
import ru.tfoms.applgar.model.PersCritSearchParameters;
import ru.tfoms.applgar.model.PersSearchParameters;
import ru.tfoms.applgar.service.PersCritService;
import ru.tfoms.applgar.service.PersDataService;

@Controller
public class PersonDataController {
	private final PersDataService service;
	private final PersCritService critService;

	public PersonDataController(PersDataService service, PersCritService critService) {
		super();
		this.service = service;
		this.critService = critService;
	}

	@GetMapping("/pers")
	public String index(Model model) throws ParseException {
		PersSearchParameters persSParam = new PersSearchParameters();
		model.addAttribute("persSParam", persSParam);
		model.addAttribute("policyTypes", policyType);
		model.addAttribute("dudlTypes", service.getDudlTypes());
		model.addAttribute("resultTypes", PersDataService.resultType);

		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		Optional<Integer> page = Optional.of(1);
		Page<PersonData> persDataPage = service.getPersDataPage(persSParam, (User) session.getAttribute("user"), page);
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

		Page<PersonData> persDataPage = service.getPersDataPage(persSParam, (User) session.getAttribute("user"), page);
		model.addAttribute("persDataPage", persDataPage);

		return "pers-form";
	}

	@PostMapping("/pers/res")
	public String result(Model model, @RequestParam("rid") Long rid) {

		PersonData personData = service.getPersonDataByRid(rid);
		if (personData == null)
			return "404code";

		Collection<PersDataError> errors = service.getErrorsByRid(rid);
		if (errors.size() > 0) {
			model.addAttribute("errors", errors);
			return "pers-err";
		}

		Collection<String> showList = Arrays
				.asList(personData.getShow() != null ? personData.getShow().split(" ") : new String[0]);
		Collection<Person> persons = service.getPersonsByRid(rid);
		Collection<OmsPolicy> policies = service.getPoliciesByRid(rid);
		Collection<Dudl> dudls = service.getDudlsByRid(rid);

		Collection<PersAddress> addresses = service.getAddressesByRid(rid);
		Map<Integer, HouseGar> houses = new HashMap<>();
		Map<Integer, AddrGar> streets = new HashMap<>();
		addresses.forEach(t -> {
			if (t.getHsguid() != null) {
				houses.put(t.getNr(), service.getHouseByObjectguid(t.getHsguid()));
			}
			if (t.getAoguid() != null) {
				streets.put(t.getNr(), service.getAddrByObjectguid(t.getAoguid()));
			}
		});

		Collection<Attach> attachies = service.getAttachiesByRid(rid);
		Collection<Contact> contacts = service.getContactsByRid(rid);
		Collection<Snils> snilses = service.getSnilsesByRid(rid);
		Collection<SocialStatus> statuses = service.getSocialStatusesByRid(rid);

		model.addAttribute("showList", showList);
		model.addAttribute("resultTypes", resultType);
		model.addAttribute("personData", personData);
		model.addAttribute("persons", persons);
		model.addAttribute("policies", policies);
		model.addAttribute("dudls", dudls);
		model.addAttribute("addresses", addresses);
		model.addAttribute("houses", houses);
		model.addAttribute("streets", streets);
		model.addAttribute("attachies", attachies);
		model.addAttribute("contacts", contacts);
		model.addAttribute("snilses", snilses);
		model.addAttribute("statuses", statuses);

		return "pers-res";
	}

	@GetMapping("/pers/crit")
	public String criteria(Model model) throws ParseException {
		PersCritSearchParameters persCritSParam = new PersCritSearchParameters();
		model.addAttribute("persCritSParam", persCritSParam);
		model.addAttribute("okatos", critService.findOkatos());
		model.addAttribute("oksms", critService.findOksms());
		model.addAttribute("policyTypes", policyType);
		model.addAttribute("dudlTypes", service.getDudlTypes());

		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();

		Page<PersonCritData> persCritDataPage = critService.getPersDataPage(persCritSParam,
				(User) session.getAttribute("user"), Optional.of(1));
		model.addAttribute("persCritDataPage", persCritDataPage);

		return "pers-crit-form";
	}

	@PostMapping("/pers/crit")
	public String criteria(Model model,
			@ModelAttribute("persCritSParam") @Valid PersCritSearchParameters persCritSParam,
			BindingResult bindingResult, @RequestParam("page") Optional<Integer> page) throws ParseException {

		model.addAttribute("okatos", critService.findOkatos());
		model.addAttribute("oksms", critService.findOksms());
		model.addAttribute("policyTypes", policyType);
		model.addAttribute("dudlTypes", service.getDudlTypes());

		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();

		critService.validate(persCritSParam, bindingResult);
		if (!bindingResult.hasErrors() && !page.isPresent()) {
			critService.saveRequest(persCritSParam, (User) session.getAttribute("user"));
		}

		Page<PersonCritData> persCritDataPage = critService.getPersDataPage(persCritSParam,
				(User) session.getAttribute("user"), page);
		model.addAttribute("persCritDataPage", persCritDataPage);

		return "pers-crit-form";
	}

}
