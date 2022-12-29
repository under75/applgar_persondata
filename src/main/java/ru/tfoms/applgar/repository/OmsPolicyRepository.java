package ru.tfoms.applgar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.tfoms.applgar.entity.OmsPolicy;
import ru.tfoms.applgar.entity.PersDataId;

public interface OmsPolicyRepository extends JpaRepository<OmsPolicy, PersDataId> {

	Collection<OmsPolicy> getByRid(Long rid);
}
