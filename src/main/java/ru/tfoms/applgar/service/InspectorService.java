package ru.tfoms.applgar.service;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import ru.tfoms.applgar.entity.Inspector;
import ru.tfoms.applgar.entity.User;
import ru.tfoms.applgar.repository.InspectorRepository;
import ru.tfoms.applgar.util.Constants;

@Service
public class InspectorService {
	private final InspectorRepository inspectorRepository;

	public InspectorService(InspectorRepository inspectorRepository) {
		this.inspectorRepository = inspectorRepository;
	}

	public Collection<Inspector> findInspectors(HttpSession session) {
		User user = (User) session.getAttribute("user");
		boolean userHasHsmoRole = user.getRoles().stream().filter(t -> t.getRole_name().equals(Constants.HSMO_ROLE))
				.collect(Collectors.toList()).size() > 0;

		Collection<Inspector> result;
		if (userHasHsmoRole) {
			result = inspectorRepository.findByCdSmoAndLoginIsNotNullOrderByFioInsp(user.getSmo() + Constants.SMO_ADD_CODE);
		} else {
			result = inspectorRepository.findByCdSmoAndCdFsmoAndLoginIsNotNullOrderByFioInsp(user.getSmo() + Constants.SMO_ADD_CODE, user.getfSmo());
		}

		return result;
	}

}
