package ru.tfoms.applgar.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.tfoms.applgar.entity.PersonData;

public interface PersonDataRepository extends JpaRepository<PersonData, Long> {

	Page<PersonData> findByUserOrderByDtInsDesc(String name, Pageable pageable);

}
