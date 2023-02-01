package ru.tfoms.applgar.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import ru.tfoms.applgar.entity.Okato;
import ru.tfoms.applgar.repository.OkatoRepository;

@Service
public class PersCritService {
	private final OkatoRepository okatoRepository;

	public PersCritService(OkatoRepository okatoRepository) {
		super();
		this.okatoRepository = okatoRepository;
	}
	
	public Collection<Okato> findAll() {
		return okatoRepository.findByOrderByTitle();
	}
}
