package ru.tfoms.applgar.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import ru.tfoms.applgar.entity.Fsmo;
import ru.tfoms.applgar.entity.User;
import ru.tfoms.applgar.repository.FsmoRepository;
import static ru.tfoms.applgar.util.Constants.*;

@Service
public class SmoService {
	private final FsmoRepository fsmoRepository;
	
	public SmoService(FsmoRepository fsmoRepository) {
		super();
		this.fsmoRepository = fsmoRepository;
	}

	public Collection<Fsmo> findBranches(HttpSession session) {
		User user = (User) session.getAttribute("user");
		Collection<Fsmo> result = null;
		
		if (user.getRoles().stream().filter(t->t.getRole_name().equals(HSMO_ROLE)).count() > 0) {
			result = fsmoRepository.findByCdSmoOrderByName(user.getSmo() + SMO_ADD_CODE);
		} else {
			result = new ArrayList<>();
			result.add(fsmoRepository.getByCdSmoAndCdFsmo(user.getSmo() + SMO_ADD_CODE, user.getfSmo()));
		}
		
		return result;
	}

}
