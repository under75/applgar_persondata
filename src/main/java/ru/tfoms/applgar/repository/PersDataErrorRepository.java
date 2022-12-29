package ru.tfoms.applgar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.tfoms.applgar.entity.PersDataError;
import ru.tfoms.applgar.entity.PersDataId;

@Repository
public interface PersDataErrorRepository extends JpaRepository<PersDataError, PersDataId> {
	
	Collection<PersDataError> getByRid(Long rid);

}
