package ru.tfoms.applgar.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import ru.tfoms.applgar.entity.Okato;
import ru.tfoms.applgar.entity.Oksm;
import ru.tfoms.applgar.model.PersCritSearchParameters;
import ru.tfoms.applgar.repository.OkatoRepository;
import ru.tfoms.applgar.repository.OksmRepository;
import ru.tfoms.applgar.util.DateValidator;

@Service
public class PersCritService {
	private final OkatoRepository okatoRepository;
	private final OksmRepository oksmRepository;

	public PersCritService(OkatoRepository okatoRepository, OksmRepository oksmRepository) {
		super();
		this.okatoRepository = okatoRepository;
		this.oksmRepository = oksmRepository;
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
}
