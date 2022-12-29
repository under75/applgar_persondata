package ru.tfoms.applgar.controller;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ru.tfoms.applgar.entity.PersDataError;
import ru.tfoms.applgar.entity.Person;
import ru.tfoms.applgar.service.PersDataService;

@Controller
public class PersonDataController {
	private final PersDataService service;

	public PersonDataController(PersDataService service) {
		super();
		this.service = service;
	}

	@GetMapping("/pers")
	public String index(Model model) {

		Long rid = 84L;

		Collection<PersDataError> errors = service.getErrorsByRid(rid);
		Collection<Person> persons = service.getPersonsByRid(rid);

		return "pers-form";
	}
}
