package ru.tfoms.applgar.service;

import static ru.tfoms.applgar.util.Constants.DATE_FORMAT;
import static ru.tfoms.applgar.util.Constants.DATE_TIME_FORMATTER;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import ru.tfoms.applgar.entity.Okato;
import ru.tfoms.applgar.entity.Oksm;
import ru.tfoms.applgar.entity.PersonCritData;
import ru.tfoms.applgar.entity.PersonCritDataResp;
import ru.tfoms.applgar.entity.User;
import ru.tfoms.applgar.model.PersCritSearchParameters;
import ru.tfoms.applgar.repository.OkatoRepository;
import ru.tfoms.applgar.repository.OksmRepository;
import ru.tfoms.applgar.repository.PersonCritDataRepository;
import ru.tfoms.applgar.repository.PersonCritDataRespRepository;
import ru.tfoms.applgar.util.DateValidator;

@Service
public class PersCritService {
	private static final Integer PAGE_SIZE = 10;
	
	private final OkatoRepository okatoRepository;
	private final OksmRepository oksmRepository;
	private final PersonCritDataRepository critDataRepository;
	private final PersonCritDataRespRepository critDataRespRepository;

	public PersCritService(OkatoRepository okatoRepository, OksmRepository oksmRepository,
			PersonCritDataRepository critDataRepository, PersonCritDataRespRepository critDataRespRepository) {
		super();
		this.okatoRepository = okatoRepository;
		this.oksmRepository = oksmRepository;
		this.critDataRepository = critDataRepository;
		this.critDataRespRepository = critDataRespRepository;
	}

	public Collection<Okato> findOkatos() {
		return okatoRepository.findByOrderByTitle();
	}

	public Collection<Oksm> findOksms() {
		return oksmRepository.findByOrderByName1();
	}

	public void validate(@Valid PersCritSearchParameters persCritSParam, BindingResult bindingResult) {
		String lastName = persCritSParam.getLastName().trim();
		String firstName = persCritSParam.getFirstName().trim();
		String patronymic = persCritSParam.getPatronymic().trim();
		final Long maxPeriod = 11L;
		if (!lastName.isEmpty() || !firstName.isEmpty() || !patronymic.isEmpty()) {
			final Integer minLength = 3;
			if (persCritSParam.getTerrOkato().isEmpty())
				bindingResult.rejectValue("terrOkato", null);
			if (!lastName.isEmpty() && lastName.length() < minLength)
				bindingResult.rejectValue("lastName", null);
			if (!firstName.isEmpty() && firstName.length() < minLength)
				bindingResult.rejectValue("firstName", null);
			if (!patronymic.isEmpty() && patronymic.length() < minLength)
				bindingResult.rejectValue("patronymic", null);
		} else if (!persCritSParam.getTerrOkato().isEmpty()) {
			bindingResult.addError(new ObjectError("globalError",
					"(часть Фамилии или/и часть Имени или/и часть Отчества) и ОКАТО - обязательное сочетание"));
		} else if (!persCritSParam.getBirthDayFrom().isEmpty()) {
			bindingResult.addError(new ObjectError("globalError",
					"(часть Фамилии или/и часть Имени или/и часть Отчества) и ДР (с... по...) - обязательное сочетание"));
		} else if (!persCritSParam.getOksm().isEmpty()) {
			bindingResult.addError(new ObjectError("globalError", "Гражданство и ОКАТО - обязательное сочетание"));
		} else if (!persCritSParam.getDeathDateFrom().isEmpty()) {
			bindingResult.addError(
					new ObjectError("globalError", "Дата смерти (с... по...) и ОКАТО  - обязательное сочетание"));
		}
		if (areDatesValid(persCritSParam, bindingResult)) {
			if (!persCritSParam.getBirthDayFrom().isEmpty() && !persCritSParam.getBirthDayTo().isEmpty()) {
				if (howManyMonthsBetweenTwoDates(persCritSParam.getBirthDayFrom(),
						persCritSParam.getBirthDayTo()) > maxPeriod)
					bindingResult.rejectValue("birthDayTo", null);
			}
			if (!persCritSParam.getDeathDateFrom().isEmpty() && !persCritSParam.getDeathDateTo().isEmpty()) {
				if (howManyMonthsBetweenTwoDates(persCritSParam.getDeathDateFrom(),
						persCritSParam.getDeathDateTo()) > maxPeriod)
					bindingResult.rejectValue("deathDateTo", null);
			}
		}
	}

	public long howManyMonthsBetweenTwoDates(String effectiveStr, String expirationStr) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		LocalDate effective = LocalDate.parse(effectiveStr, dateTimeFormatter);
		LocalDate expiration = LocalDate.parse(expirationStr, dateTimeFormatter);
		Period period = Period.between(effective, expiration);

		return period.toTotalMonths();
	}

	private boolean areDatesValid(@Valid PersCritSearchParameters persCritSParam, BindingResult bindingResult) {
		boolean res = true;
		if (!persCritSParam.getBirthDayFrom().isEmpty() && !DateValidator.isValid(persCritSParam.getBirthDayFrom())) {
			bindingResult.rejectValue("birthDayFrom", null);
			res = false;
		} else if (!persCritSParam.getBirthDayTo().isEmpty()
				&& !DateValidator.isValid(persCritSParam.getBirthDayTo())) {
			bindingResult.rejectValue("birthDayTo", null);
			res = false;
		} else if (!persCritSParam.getDeathDateFrom().isEmpty()
				&& !DateValidator.isValid(persCritSParam.getDeathDateFrom())) {
			bindingResult.rejectValue("deathDateFrom", null);
			res = false;
		} else if (!persCritSParam.getDeathDateTo().isEmpty()
				&& !DateValidator.isValid(persCritSParam.getDeathDateTo())) {
			bindingResult.rejectValue("deathDateTo", null);
			res = false;
		} else if (!persCritSParam.getBirthDay().isEmpty() && !DateValidator.isValid(persCritSParam.getBirthDay())) {
			bindingResult.rejectValue("birthDay", null);
			res = false;
		} else if (!persCritSParam.getDt().isEmpty() && !DateValidator.isValid(persCritSParam.getDt())) {
			bindingResult.rejectValue("dt", null);
			res = false;
		}

		return res;
	}

	public void saveRequest(@Valid PersCritSearchParameters persCritSParam, User user) {
		PersonCritData critData = new PersonCritData();
		critData.setDtIns(new Date());
		critData.setUser(user.getName());
		critData.setTerrOkato(persCritSParam.getTerrOkato());
		critData.setLastName(persCritSParam.getLastName().trim());
		critData.setFirstName(persCritSParam.getFirstName().trim());
		critData.setPatronymic(persCritSParam.getPatronymic().trim());
		critData.setOldsfp(persCritSParam.getOldsfp());
		critData.setDost(persCritSParam.getDost());
		critData.setOksm(persCritSParam.getOksm());
		critData.setNoCitizenship(persCritSParam.getNoCitizenship());
		critData.setBirthDayFrom(!persCritSParam.getBirthDayFrom().isEmpty() ? LocalDate.parse(persCritSParam.getBirthDayFrom(), DATE_TIME_FORMATTER) : null);
		critData.setBirthDayTo(!persCritSParam.getBirthDayTo().isEmpty() ? LocalDate.parse(persCritSParam.getBirthDayTo(), DATE_TIME_FORMATTER) : null);
		critData.setDeathDateFrom(!persCritSParam.getDeathDateFrom().isEmpty() ? LocalDate.parse(persCritSParam.getDeathDateFrom(), DATE_TIME_FORMATTER) : null);
		critData.setDeathDateTo(!persCritSParam.getDeathDateTo().isEmpty() ? LocalDate.parse(persCritSParam.getDeathDateTo(), DATE_TIME_FORMATTER) : null);
		critData.setOip(persCritSParam.getOip().trim());
		critData.setPcyType(persCritSParam.getPolicyType());
		critData.setPcySer(persCritSParam.getPcySer().trim());
		critData.setPcyNum(persCritSParam.getPcyNum().trim());
		critData.setDudlType(persCritSParam.getDudlType());
		critData.setDudlSer(persCritSParam.getDudlSer().trim());
		critData.setDudlNum(persCritSParam.getDudlNum().trim());
		critData.setSnils(persCritSParam.getSnils().trim());
		critData.setBirthDay(!persCritSParam.getBirthDay().isEmpty() ? LocalDate.parse(persCritSParam.getBirthDay(), DATE_TIME_FORMATTER) : null);
		critData.setErn(persCritSParam.getErn().trim());
		critData.setDt(!persCritSParam.getDt().isEmpty() ? LocalDate.parse(persCritSParam.getDt(), DATE_TIME_FORMATTER) : null);

		critDataRepository.save(critData);
	}

	public Page<PersonCritData> getPersDataPage(@Valid PersCritSearchParameters persCritSParam, User user,
			Optional<Integer> page) throws ParseException {
		int currentPage = page.orElse(1);
		Page<PersonCritData> dataPage;
		PageRequest pageRequest = PageRequest.of(currentPage - 1, PAGE_SIZE);
		if (persCritSParam.getDateFrom() != null && !persCritSParam.getDateFrom().isEmpty() && persCritSParam.getDateTo() != null
				&& !persCritSParam.getDateTo().isEmpty() && DateValidator.isValid(persCritSParam.getDateFrom())
				&& DateValidator.isValid(persCritSParam.getDateTo())) {
			Date start = DATE_FORMAT.parse(persCritSParam.getDateFrom());
			Date end = Date.from(DATE_FORMAT.parse(persCritSParam.getDateTo()).toInstant().plus(1, ChronoUnit.DAYS));
			dataPage = critDataRepository.findByUserAndDtInsBetweenOrderByDtInsDesc(user.getName(), start, end,
					pageRequest);
		} else if (persCritSParam.getDateFrom() != null && !persCritSParam.getDateFrom().isEmpty()
				&& DateValidator.isValid(persCritSParam.getDateFrom())) {
			Date start = DATE_FORMAT.parse(persCritSParam.getDateFrom());
			dataPage = critDataRepository.findByUserAndDtInsAfterOrderByDtInsDesc(user.getName(), start, pageRequest);
		} else if (persCritSParam.getDateTo() != null && !persCritSParam.getDateTo().isEmpty()
				&& DateValidator.isValid(persCritSParam.getDateTo())) {
			Date end = Date.from(DATE_FORMAT.parse(persCritSParam.getDateTo()).toInstant().plus(1, ChronoUnit.DAYS));
			dataPage = critDataRepository.findByUserAndDtInsBeforeOrderByDtInsDesc(user.getName(), end, pageRequest);
		} else {
			dataPage = critDataRepository.findByUserOrderByDtInsDesc(user.getName(), pageRequest);
		}

		return dataPage;
	}

	public Collection<PersonCritDataResp> findRespByRid(Long rid) {
		return critDataRespRepository.findByRid(rid);
	}
}
