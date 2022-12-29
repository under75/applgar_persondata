package ru.tfoms.applgar.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import ru.tfoms.applgar.entity.Attach;
import ru.tfoms.applgar.entity.Dudl;
import ru.tfoms.applgar.entity.OmsPolicy;
import ru.tfoms.applgar.entity.PersDataError;
import ru.tfoms.applgar.entity.Person;
import ru.tfoms.applgar.repository.AttachRepository;
import ru.tfoms.applgar.repository.DudlRepository;
import ru.tfoms.applgar.repository.OmsPolicyRepository;
import ru.tfoms.applgar.repository.PersDataErrorRepository;
import ru.tfoms.applgar.repository.PersonRepository;

@Service
public class PersDataService {
	private final PersDataErrorRepository errorRepository;
	private final PersonRepository personRepository;
	private final OmsPolicyRepository policyRepository;
	private final DudlRepository dudlRepository;
	private final AttachRepository attachRepository;

	public enum Show {
		PERSON, OMS_POLICY, DUDL, ADDRESS, ATTACH, CONTACT, SNILS, SOCIAL_STATUS
	}

	public PersDataService(PersDataErrorRepository errorRepository, PersonRepository personRepository,
			OmsPolicyRepository policyRepository, DudlRepository dudlRepository, AttachRepository attachRepository) {
		super();
		this.errorRepository = errorRepository;
		this.personRepository = personRepository;
		this.policyRepository = policyRepository;
		this.dudlRepository = dudlRepository;
		this.attachRepository = attachRepository;
	}

	public Collection<PersDataError> getErrorsByRid(Long rid) {
		return errorRepository.getByRid(rid);
	}

	public Collection<Person> getPersonsByRid(Long rid) {
		return personRepository.getByRid(rid);
	}

	public Collection<OmsPolicy> getPoliciesByRid(Long rid) {
		return policyRepository.getByRid(rid);
	}

	public Collection<Dudl> getDudlsByRid(Long rid) {
		return dudlRepository.getByRid(rid);
	}

	public Collection<Attach> getAttachesByRid(Long rid) {
		return attachRepository.getByRid(rid);
	}
}
