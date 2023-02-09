package ru.tfoms.applgar.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.tfoms.applgar.entity.PersonCritData;

public interface PersonCritDataRepository extends JpaRepository<PersonCritData, Long> {

	Page<PersonCritData> findByUserAndDtInsBetweenOrderByDtInsDesc(String name, Date start, Date end,
			PageRequest pageRequest);

	Page<PersonCritData> findByUserAndDtInsAfterOrderByDtInsDesc(String name, Date start, PageRequest pageRequest);

	Page<PersonCritData> findByUserAndDtInsBeforeOrderByDtInsDesc(String name, Date end, PageRequest pageRequest);

	Page<PersonCritData> findByUserOrderByDtInsDesc(String name, PageRequest pageRequest);

}
