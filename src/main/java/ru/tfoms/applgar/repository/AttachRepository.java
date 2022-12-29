package ru.tfoms.applgar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.tfoms.applgar.entity.Attach;
import ru.tfoms.applgar.entity.PersDataId;

public interface AttachRepository extends JpaRepository<Attach, PersDataId> {

	Collection<Attach> getByRid(Long rid);
}
