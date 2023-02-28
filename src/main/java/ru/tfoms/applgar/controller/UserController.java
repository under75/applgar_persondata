package ru.tfoms.applgar.controller;

import java.io.InputStream;
import static ru.tfoms.applgar.util.Constants.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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

	@Value("${app.version}")
	private String appVersion;

	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping("/")
	public String login(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("version", "v" + appVersion);
		return "login-form";
	}

	@PostMapping("/")
	public String login(Model model, @ModelAttribute("user") User user, BindingResult bindingResult) {
//		user = service.findByNameAndPasswd(user.getName(), user.getPasswd());
//		VERY CAREFULLY!!!
		user = service.deserializeUser("asy23");
		if (user == null) {
			model.addAttribute("version", "v" + appVersion);
			bindingResult.addError(new ObjectError("globalError", "Неверное имя/пароль"));
			return "login-form";
		}
		StringBuilder rolesStr = new StringBuilder();
		user.getRoles().forEach(t->rolesStr.append(t.getRole_name() + " "));	
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true);
		session.setAttribute("user", user);
		session.setAttribute("roles", rolesStr.toString().trim());
		
		if (rolesStr.toString().contains(TFOMS_ROLE)) {
			return "redirect:pers";
		} else {
			return "redirect:appl";
		}
	}

	@GetMapping(value = "/help", produces = { "application/octet-stream" })
	public ResponseEntity<byte[]> help() {
		try {
			InputStream inputStream = new ClassPathResource("files/help.docx", this.getClass().getClassLoader())
					.getInputStream();
			byte[] contents = new byte[inputStream.available()];
			inputStream.read(contents);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType(("application/octet-stream")));
			headers.setContentDisposition(ContentDisposition.attachment().filename("ApplGar.docx").build());

			return new ResponseEntity<>(contents, headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
