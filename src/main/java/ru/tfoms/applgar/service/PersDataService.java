package ru.tfoms.applgar.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ru.tfoms.applgar.entity.Attach;
import ru.tfoms.applgar.entity.Contact;
import ru.tfoms.applgar.entity.Dudl;
import ru.tfoms.applgar.entity.DudlType;
import ru.tfoms.applgar.entity.OmsPolicy;
import ru.tfoms.applgar.entity.PersDataError;
import ru.tfoms.applgar.entity.Person;
import ru.tfoms.applgar.entity.PersonData;
import ru.tfoms.applgar.entity.Snils;
import ru.tfoms.applgar.entity.SocialStatus;
import ru.tfoms.applgar.entity.User;
import ru.tfoms.applgar.model.PersSearchParameters;
import ru.tfoms.applgar.repository.AttachRepository;
import ru.tfoms.applgar.repository.ContactRepository;
import ru.tfoms.applgar.repository.DudlRepository;
import ru.tfoms.applgar.repository.DudlTypeRepository;
import ru.tfoms.applgar.repository.OmsPolicyRepository;
import ru.tfoms.applgar.repository.PersDataErrorRepository;
import ru.tfoms.applgar.repository.PersonDataRepository;
import ru.tfoms.applgar.repository.PersonRepository;
import ru.tfoms.applgar.repository.SnilsRepository;
import ru.tfoms.applgar.repository.SocialStatusRepository;

@Service
public class PersDataService {
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private static final Integer PAGE_SIZE = 15;

	private final PersDataErrorRepository errorRepository;
	private final PersonRepository personRepository;
	private final OmsPolicyRepository policyRepository;
	private final DudlRepository dudlRepository;
	private final AttachRepository attachRepository;
	private final ContactRepository contactRepository;
	private final SnilsRepository snilsRepository;
	private final SocialStatusRepository socialStatusRepository;
	private final DudlTypeRepository dudlTypeRepository;
	private final PersonDataRepository personDataRepository;

	public final static Map<String, String> resultType = new HashMap<>();
	{
		resultType.put("PERSON", "Информация о персоне");
		resultType.put("OMS_POLICY", "Информация о полисе");
		resultType.put("DUDL", "ДУДЛ");
		resultType.put("ADDRESS", "Адрес");
		resultType.put("ATTACH", "Прикрепление");
		resultType.put("CONTACT", "Данные контакта");
		resultType.put("SNILS", "Данные СНИЛС");
		resultType.put("SOCIAL_STATUS", "Данные соц. статуса");
	}

	public final static Map<String, String> policyType = new HashMap<>();
	{
		policyType.put("C", "Полис ОМС старого образца");
		policyType.put("В", "Временное свидетельство в форме бумажного бланка");
		policyType.put("Е", "Временное свидетельство в форме электронного документа");
		policyType.put("П", "Бумажный полис ОМС единого образца");
		policyType.put("Э", "Электронный полис ОМС единого образца");
		policyType.put("К", "Полис ОМС в составе универсальной электронной карты");
		policyType.put("Ц", "Цифровой полис ОМС");
		policyType.put("Х", "Состояние на учёте без полиса ОМС");
		policyType.put("М", "Состояние на учёте МФЦ");
	}

	public PersDataService(PersDataErrorRepository errorRepository, PersonRepository personRepository,
			OmsPolicyRepository policyRepository, DudlRepository dudlRepository, AttachRepository attachRepository,
			ContactRepository contactRepository, SnilsRepository snilsRepository,
			SocialStatusRepository socialStatusRepository, DudlTypeRepository dudlTypeRepository,
			PersonDataRepository personDataRepository) {
		super();
		this.errorRepository = errorRepository;
		this.personRepository = personRepository;
		this.policyRepository = policyRepository;
		this.dudlRepository = dudlRepository;
		this.attachRepository = attachRepository;
		this.contactRepository = contactRepository;
		this.snilsRepository = snilsRepository;
		this.socialStatusRepository = socialStatusRepository;
		this.dudlTypeRepository = dudlTypeRepository;
		this.personDataRepository = personDataRepository;

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

	public Collection<DudlType> getDudlTypes() {
		return dudlTypeRepository.findAll();
	}

	public PersonData saveRequest(PersSearchParameters persSParam, User user) throws ParseException {
		PersonData personData = new PersonData();
		personData.setPcyType(persSParam.getPolicyType());
		personData.setPcy(persSParam.getPolicyNum());
		if (persSParam.getDudlType() != null)
			personData.setDudlType(dudlTypeRepository.findById(persSParam.getDudlType()).get());
		personData.setDudlSer(persSParam.getDudlSer());
		personData.setDudlNum(persSParam.getDudlNum());
		personData.setFirstName(persSParam.getFirstName());
		personData.setLastName(persSParam.getLastName());
		personData.setPatronymic(persSParam.getPatronymic());
		personData.setSnils(persSParam.getSnils());
		if (!persSParam.getBirthDay().isEmpty())
			personData.setBirthDay(DATE_FORMAT.parse(persSParam.getBirthDay()));
		personData.setHistorical(persSParam.getHistorical() == true ? persSParam.getHistorical() : null);
		if (persSParam.getHistorical()) {
			if (!persSParam.getDtFrom().isEmpty())
				personData.setDtFrom(DATE_FORMAT.parse(persSParam.getDtFrom()));
			if (!persSParam.getDtTo().isEmpty())
				personData.setDtTo(DATE_FORMAT.parse(persSParam.getDtTo()));
		} else if (!persSParam.getDt().isEmpty()) {
			personData.setDt(DATE_FORMAT.parse(persSParam.getDt()));
		}
		personData.setShow(persSParam.getShow());
		personData.setUser(user.getName());

		return personDataRepository.save(personData);
	}

	public Page<PersonData> getPersDataPage(User user, Optional<Integer> page) {
		int currentPage = page.orElse(1);
		return personDataRepository.findByUserOrderByDtIns(user.getName(), PageRequest.of(currentPage - 1, PAGE_SIZE));
	}
}
