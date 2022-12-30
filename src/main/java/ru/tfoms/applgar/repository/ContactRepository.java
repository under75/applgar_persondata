package ru.tfoms.applgar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.tfoms.applgar.entity.Contact;
import ru.tfoms.applgar.entity.PersDataId;

public interface ContactRepository extends JpaRepository<Contact, PersDataId> {

	 Collection<Contact> getByRid(Long rid);
}
