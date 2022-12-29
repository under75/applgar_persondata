package ru.tfoms.applgar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.tfoms.applgar.entity.Dudl;
import ru.tfoms.applgar.entity.PersDataId;

public interface DudlRepository extends JpaRepository<Dudl, PersDataId> {

	Collection<Dudl> getByRid(Long rid);
}
