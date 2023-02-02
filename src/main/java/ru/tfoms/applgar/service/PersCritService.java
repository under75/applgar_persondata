package ru.tfoms.applgar.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import ru.tfoms.applgar.entity.Okato;
import ru.tfoms.applgar.entity.Oksm;
import ru.tfoms.applgar.repository.OkatoRepository;
import ru.tfoms.applgar.repository.OksmRepository;

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
}
