package ru.tfoms.applgar.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ru.tfoms.applgar.entity.User;
import ru.tfoms.applgar.service.UserService;

@Controller
public class UserController {
	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping("/")
	public String editUserById(Model model) {
		model.addAttribute("user", new User());
		return "login-form";
	}

	@PostMapping("/")
	public String login(@ModelAttribute("user") User user, BindingResult bindingResult) {
		user = service.findByNameAndPasswd(user.getName(), user.getPasswd());
		if (bindingResult.hasErrors() || user == null) {
			return "login-form";
		}
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true);
		session.setAttribute("user", user);

		return "redirect:appl";
	}
}
