package ru.tfoms.applgar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.tfoms.applgar.entity.Oksm;

public interface OksmRepository extends JpaRepository<Oksm, String> {

	Collection<Oksm> findByOrderByName1();

}
