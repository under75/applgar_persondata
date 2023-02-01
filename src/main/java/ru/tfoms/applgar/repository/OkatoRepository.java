package ru.tfoms.applgar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.tfoms.applgar.entity.Okato;

public interface OkatoRepository extends JpaRepository<Okato, String> {
	
	Collection<Okato> findByOrderByTitle();

}
