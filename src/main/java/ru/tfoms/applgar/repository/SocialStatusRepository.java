package ru.tfoms.applgar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.tfoms.applgar.entity.PersDataId;
import ru.tfoms.applgar.entity.SocialStatus;

public interface SocialStatusRepository extends JpaRepository<SocialStatus, PersDataId> {
	
	Collection<SocialStatus> getByRid(Long rid);
	
}
