package ru.tfoms.applgar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.tfoms.applgar.entity.PersAddress;

public interface PersAddressRepository extends JpaRepository<PersAddress, Long> {

	Collection<PersAddress> getByRid(Long rid);

}
