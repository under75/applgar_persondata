package ru.tfoms.applgar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.tfoms.applgar.entity.PersDataId;
import ru.tfoms.applgar.entity.Person;

public interface PersonRepository extends JpaRepository<Person, PersDataId> {

	Collection<Person> getByRid(Long rid);
}
