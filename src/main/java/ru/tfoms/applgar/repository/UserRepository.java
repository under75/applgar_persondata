package ru.tfoms.applgar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.tfoms.applgar.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	User findByNameAndPasswd(String uname, String passwd);

}