package ru.tfoms.applgar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.tfoms.applgar.entity.PersDataId;
import ru.tfoms.applgar.entity.PersonCritDataResp;

public interface PersonCritDataRespRepository extends JpaRepository<PersonCritDataResp, PersDataId>{

	Collection<PersonCritDataResp> findByRid(Long rid);

}
