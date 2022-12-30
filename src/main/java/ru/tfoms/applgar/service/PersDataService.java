package ru.tfoms.applgar.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import ru.tfoms.applgar.entity.Attach;
import ru.tfoms.applgar.entity.Contact;
import ru.tfoms.applgar.entity.Dudl;
import ru.tfoms.applgar.entity.OmsPolicy;
import ru.tfoms.applgar.entity.PersDataError;
import ru.tfoms.applgar.entity.Person;
import ru.tfoms.applgar.entity.Snils;
import ru.tfoms.applgar.entity.SocialStatus;
import ru.tfoms.applgar.repository.AttachRepository;
import ru.tfoms.applgar.repository.ContactRepository;
import ru.tfoms.applgar.repository.DudlRepository;
import ru.tfoms.applgar.repository.OmsPolicyRepository;
import ru.tfoms.applgar.repository.PersDataErrorRepository;
import ru.tfoms.applgar.repository.PersonRepository;
import ru.tfoms.applgar.repository.SnilsRepository;
import ru.tfoms.applgar.repository.SocialStatusRepository;

@Service
public class PersDataService {
	private final PersDataErrorRepository errorRepository;
	private final PersonRepository personRepository;
	private final OmsPolicyRepository policyRepository;
	private final DudlRepository dudlRepository;
	private final AttachRepository attachRepository;
	private final ContactRepository contactRepository;
	private final SnilsRepository snilsRepository;
	private final SocialStatusRepository socialStatusRepository;

	public enum Show {
		PERSON, OMS_POLICY, DUDL, ADDRESS, ATTACH, CONTACT, SNILS, SOCIAL_STATUS
	}

	public PersDataService(PersDataErrorRepository errorRepository, PersonRepository personRepository,
			OmsPolicyRepository policyRepository, DudlRepository dudlRepository, AttachRepository attachRepository,
			ContactRepository contactRepository, SnilsRepository snilsRepository,
			SocialStatusRepository socialStatusRepository) {
		super();
		this.errorRepository = errorRepository;
		this.personRepository = personRepository;
		this.policyRepository = policyRepository;
		this.dudlRepository = dudlRepository;
		this.attachRepository = attachRepository;
		this.contactRepository = contactRepository;
		this.snilsRepository = snilsRepository;
		this.socialStatusRepository = socialStatusRepository;
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

	public Collection<Contact> getContactsByRid(Long rid) {
		return contactRepository.getByRid(rid);
	}

	public Collection<Snils> getSnilsesByRid(Long rid) {
		return snilsRepository.getByRid(rid);
	}

	public Collection<SocialStatus> getSocialStatusesByRid(Long rid) {
		return socialStatusRepository.getByRid(rid);
	}
}
