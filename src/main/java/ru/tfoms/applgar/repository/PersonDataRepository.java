package ru.tfoms.applgar.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.tfoms.applgar.entity.PersonData;

public interface PersonDataRepository extends JpaRepository<PersonData, Long> {

	Page<PersonData> findByUserOrderByDtInsDesc(String name, Pageable pageable);

	Page<PersonData> findByUserAndDtInsBetweenOrderByDtInsDesc(String name, Date start, Date end, Pageable pageable);

	Page<PersonData> findByUserAndDtInsAfterOrderByDtInsDesc(String name, Date start, Pageable pageable);

	Page<PersonData> findByUserAndDtInsBeforeOrderByDtInsDesc(String name, Date end, Pageable pageable);

}
