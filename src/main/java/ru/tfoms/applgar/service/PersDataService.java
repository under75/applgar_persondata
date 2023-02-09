package ru.tfoms.applgar.service;

import static ru.tfoms.applgar.util.Constants.DATE_FORMAT;

import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import ru.tfoms.applgar.entity.AddrGar;
import ru.tfoms.applgar.entity.Attach;
import ru.tfoms.applgar.entity.Contact;
import ru.tfoms.applgar.entity.Dudl;
import ru.tfoms.applgar.entity.DudlType;
import ru.tfoms.applgar.entity.HouseGar;
import ru.tfoms.applgar.entity.OmsPolicy;
import ru.tfoms.applgar.entity.PersAddress;
import ru.tfoms.applgar.entity.PersDataError;
import ru.tfoms.applgar.entity.Person;
import ru.tfoms.applgar.entity.PersonData;
import ru.tfoms.applgar.entity.Snils;
import ru.tfoms.applgar.entity.SocialStatus;
import ru.tfoms.applgar.entity.User;
import ru.tfoms.applgar.model.PersSearchParameters;
import ru.tfoms.applgar.repository.AddrGarRepository;
import ru.tfoms.applgar.repository.AttachRepository;
import ru.tfoms.applgar.repository.ContactRepository;
import ru.tfoms.applgar.repository.DudlRepository;
import ru.tfoms.applgar.repository.DudlTypeRepository;
import ru.tfoms.applgar.repository.HouseGarRepository;
import ru.tfoms.applgar.repository.OmsPolicyRepository;
import ru.tfoms.applgar.repository.PersAddressRepository;
import ru.tfoms.applgar.repository.PersDataErrorRepository;
import ru.tfoms.applgar.repository.PersonDataRepository;
import ru.tfoms.applgar.repository.PersonRepository;
import ru.tfoms.applgar.repository.SnilsRepository;
import ru.tfoms.applgar.repository.SocialStatusRepository;
import ru.tfoms.applgar.util.DateValidator;

@Service
public class PersDataService {
	private static final Integer PAGE_SIZE = 10;

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
	private final PersAddressRepository addressRepository;
	private final HouseGarRepository houseRepository;
	private final AddrGarRepository addrGarRepository;

	public enum Show {
		Person, OmsPolicy, Dudl, Address, Attach, Contact, Snils, SocialStatus, All
	}

	public final static Map<Show, String> resultType = new LinkedHashMap<>();
	{
		resultType.put(Show.Person, "Информация о персоне");
		resultType.put(Show.OmsPolicy, "Информация о полисе");
		resultType.put(Show.Dudl, "ДУДЛ");
		resultType.put(Show.Address, "Адрес");
		resultType.put(Show.Attach, "Прикрепление");
		resultType.put(Show.Contact, "Данные контакта");
		resultType.put(Show.Snils, "Данные СНИЛС");
		resultType.put(Show.SocialStatus, "Данные соц. статуса");
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
			PersonDataRepository personDataRepository, PersAddressRepository addressRepository,
			HouseGarRepository houseRepository, AddrGarRepository addrGarRepository) {
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
		this.addressRepository = addressRepository;
		this.houseRepository = houseRepository;
		this.addrGarRepository = addrGarRepository;

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

	public Collection<Attach> getAttachiesByRid(Long rid) {
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
		personData.setOip(persSParam.getOip().trim());
		personData.setPcyType(persSParam.getPolicyType());
		personData.setPcy(persSParam.getPolicyNum().trim());
		if (persSParam.getDudlType() != null)
			personData.setDudlType(persSParam.getDudlType());
		personData.setDudlSer(persSParam.getDudlSer().trim());
		personData.setDudlNum(persSParam.getDudlNum().trim());
		personData.setFirstName(persSParam.getFirstName().trim());
		personData.setLastName(persSParam.getLastName().trim());
		personData.setPatronymic(persSParam.getPatronymic().trim());
		personData.setSnils(persSParam.getSnils().trim());
		if (!persSParam.getBirthDay().isEmpty())
			personData.setBirthDay(DATE_FORMAT.parse(persSParam.getBirthDay()));
		personData.setHistorical(persSParam.getHistorical() == true ? persSParam.getHistorical() : null);
		if (persSParam.getHistorical()) {
			if (!persSParam.getDtFrom().isEmpty())
				personData.setDtFrom(DATE_FORMAT.parse(persSParam.getDtFrom()));
			if (!persSParam.getDtTo().isEmpty())
				personData.setDtTo(
						Date.from(DATE_FORMAT.parse(persSParam.getDtTo()).toInstant().plus(1, ChronoUnit.DAYS)));
		} else if (!persSParam.getDt().isEmpty()) {
			personData.setDt(DATE_FORMAT.parse(persSParam.getDt()));
		}
		personData.setShow(persSParam.getShow() != null ? persSParam.getShow().replaceAll(",", " ") : "");
		personData.setUser(user.getName());

		return personDataRepository.save(personData);
	}

	public Page<PersonData> getPersDataPage(PersSearchParameters persSParam, User user, Optional<Integer> page)
			throws ParseException {
		int currentPage = page.orElse(1);
		Page<PersonData> dataPage;
		PageRequest pageRequest = PageRequest.of(currentPage - 1, PAGE_SIZE);
		if (persSParam.getDateFrom() != null && !persSParam.getDateFrom().isEmpty() && persSParam.getDateTo() != null
				&& !persSParam.getDateTo().isEmpty() && DateValidator.isValid(persSParam.getDateFrom())
				&& DateValidator.isValid(persSParam.getDateTo())) {
			Date start = DATE_FORMAT.parse(persSParam.getDateFrom());
			Date end = Date.from(DATE_FORMAT.parse(persSParam.getDateTo()).toInstant().plus(1, ChronoUnit.DAYS));
			dataPage = personDataRepository.findByUserAndDtInsBetweenOrderByDtInsDesc(user.getName(), start, end,
					pageRequest);
		} else if (persSParam.getDateFrom() != null && !persSParam.getDateFrom().isEmpty()
				&& DateValidator.isValid(persSParam.getDateFrom())) {
			Date start = DATE_FORMAT.parse(persSParam.getDateFrom());
			dataPage = personDataRepository.findByUserAndDtInsAfterOrderByDtInsDesc(user.getName(), start, pageRequest);
		} else if (persSParam.getDateTo() != null && !persSParam.getDateTo().isEmpty()
				&& DateValidator.isValid(persSParam.getDateTo())) {
			Date end = Date.from(DATE_FORMAT.parse(persSParam.getDateTo()).toInstant().plus(1, ChronoUnit.DAYS));
			dataPage = personDataRepository.findByUserAndDtInsBeforeOrderByDtInsDesc(user.getName(), end, pageRequest);
		} else {
			dataPage = personDataRepository.findByUserOrderByDtInsDesc(user.getName(), pageRequest);
		}

		return dataPage;
	}

	public void validate(PersSearchParameters persSParam, BindingResult bindingResult) {
		if (persSParam.getOip().trim().isEmpty() && persSParam.getPolicyNum().trim().isEmpty()
				&& persSParam.getDudlNum().trim().isEmpty() && persSParam.getSnils().trim().isEmpty()) {
			bindingResult.addError(new ObjectError("globalError",
					"Для успешного поиска, необходимо ввести обезличенный идентификатор персоны(ОИП) либо данные одного из документов(Полис, ДУдЛ, СНИЛС)"));
		} else if (persSParam.getDudlType() != null && persSParam.getDudlNum().trim().isEmpty()) {
			bindingResult.rejectValue("dudlNum", "");
		} else if (!persSParam.getDudlNum().trim().isEmpty() && persSParam.getDudlType() == null) {
			bindingResult.rejectValue("dudlType", "");
		}
		if (persSParam.getBirthDay() != null && !persSParam.getBirthDay().isEmpty()
				&& !DateValidator.isValid(persSParam.getBirthDay())) {
			bindingResult.rejectValue("birthDay", "Invalid date");
		}
		if (persSParam.getDt() != null && !persSParam.getDt().isEmpty() && !DateValidator.isValid(persSParam.getDt())) {
			bindingResult.rejectValue("dt", "Invalid date");
		}
		if (persSParam.getDtFrom() != null && !persSParam.getDtFrom().isEmpty()
				&& !DateValidator.isValid(persSParam.getDtFrom())) {
			bindingResult.rejectValue("dtFrom", "Invalid date");
		}
		if (persSParam.getDtTo() != null && !persSParam.getDtTo().isEmpty()
				&& !DateValidator.isValid(persSParam.getDtTo())) {
			bindingResult.rejectValue("dtTo", "Invalid date");
		}
	}

	public PersonData getPersonDataByRid(Long rid) {
		return personDataRepository.getReferenceById(rid);
	}

	public Collection<PersAddress> getAddressesByRid(Long rid) {
		return addressRepository.getByRid(rid);
	}

	public HouseGar getHouseByObjectguid(String objectguid) {
		return houseRepository.findByObjectguidAndIsActualAndIsActive(objectguid, true, true);
	}

	public AddrGar getAddrByObjectguid(String objectguid) {
		return addrGarRepository.findByObjectguidAndIsActualAndIsActive(objectguid, true, true);
	}
}
