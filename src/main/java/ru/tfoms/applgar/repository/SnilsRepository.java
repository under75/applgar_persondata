package ru.tfoms.applgar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.tfoms.applgar.entity.PersDataId;
import ru.tfoms.applgar.entity.Snils;

public interface SnilsRepository extends JpaRepository<Snils, PersDataId> {
	Collection<Snils> getByRid(Long rid);
}
