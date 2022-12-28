package ru.tfoms.applgar.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import ru.tfoms.applgar.entity.User;
import ru.tfoms.applgar.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User findByNameAndPasswd(String name, String passwd) {
		return userRepository.findByNameAndPasswd(name, DigestUtils.sha256Hex(passwd));
	}
}
