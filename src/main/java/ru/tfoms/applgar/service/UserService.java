package ru.tfoms.applgar.service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.core.io.ClassPathResource;
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

	/** FOR TESTING ONLY!!! **/
	public void serializeUser(User user) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(
					new ClassPathResource("files/" + user.getName(), this.getClass().getClassLoader()).getFilename());

			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			//the file will appear in the root directory of the project
			objectOutputStream.writeObject(user);
			objectOutputStream.flush();
			objectOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** FOR TESTING ONLY!!! VERY CAREFULLY!!!**/
	public User deserializeUser(String uname) {
		User user = null;
		try {
			InputStream fileInputStream = new ClassPathResource("files/" + uname + ".txt",
					this.getClass().getClassLoader()).getInputStream();
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			user = (User) objectInputStream.readObject();
			objectInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}
}
