package ru.tfoms.applgar.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ru.tfoms.applgar.dao.ApplDAO;
import ru.tfoms.applgar.entity.Appl;
import ru.tfoms.applgar.entity.Inspector;
import ru.tfoms.applgar.entity.User;
import ru.tfoms.applgar.exception.ExcelGeneratorException;
import ru.tfoms.applgar.model.ApplSearchParameters;
import ru.tfoms.applgar.model.RowData;
import ru.tfoms.applgar.repository.ApplRepository;
import ru.tfoms.applgar.util.Constants;

@Service
public class ApplService {
	private final ApplRepository applRepository;
	private final ApplDAO applDAO;

	public ApplService(ApplRepository applRepository, ApplDAO applDAO) {
		this.applRepository = applRepository;
		this.applDAO = applDAO;
	}

	public Page<Appl> findByDtApplBetweenAndCdSmo(Date start, Date end, Integer cdSmo, Pageable pageable) {
		return applRepository.findByDtApplBetweenAndCdSmo(start, end, cdSmo, pageable);
	}

	public Page<Appl> findByDtApplBetweenAndCdSmoAndSerDoc(Date start, Date end, Integer cdSmo, String serDoc,
			Pageable pageable) {
		return applRepository.findByDtApplBetweenAndCdSmoAndSerDoc(start, end, cdSmo, serDoc, pageable);
	}

	public Page<Appl> findByDtApplBetweenAndCdSmoAndSerDocAndNumDoc(Date start, Date end, Integer cdSmo, String serDoc,
			String numDoc, Pageable pageable) {
		return applRepository.findByDtApplBetweenAndCdSmoAndSerDocAndNumDoc(start, end, cdSmo, serDoc, numDoc,
				pageable);
	}

	public Page<Appl> findByDtApplBetweenAndCdSmoAndCdFsmo(Date start, Date end, Integer cdSmo, Integer cdFsmo,
			Pageable pageable) {
		return applRepository.findByDtApplBetweenAndCdSmoAndCdFsmo(start, end, cdSmo, cdFsmo, pageable);
	}

	public Page<Appl> findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDoc(Date start, Date end, Integer cdSmo, Integer cdFsmo,
			String serDoc, Pageable pageable) {
		return applRepository.findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDoc(start, end, cdSmo, cdFsmo, serDoc,
				pageable);
	}

	public Page<Appl> findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDocAndNumDoc(Date start, Date end, Integer cdSmo,
			Integer cdFsmo, String serDoc, String numDoc, Pageable pageable) {
		return applRepository.findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDocAndNumDoc(start, end, cdSmo, cdFsmo, serDoc,
				numDoc, pageable);
	}

	public Page<Appl> findByDtApplBetweenAndCdSmoAndInspector(Date start, Date end, Integer cdSmo, Inspector inspector,
			Pageable pageable) {
		return applRepository.findByDtApplBetweenAndCdSmoAndInspector(start, end, cdSmo, inspector, pageable);
	}

	public Page<Appl> findByDtApplBetweenAndCdSmoAndSerDocAndInspector(Date start, Date end, Integer cdSmo,
			String serDoc, Inspector inspector, Pageable pageable) {
		return applRepository.findByDtApplBetweenAndCdSmoAndSerDocAndInspector(start, end, cdSmo, serDoc, inspector,
				pageable);
	}

	public Page<Appl> findByDtApplBetweenAndCdSmoAndSerDocAndNumDocAndInspector(Date start, Date end, Integer cdSmo,
			String serDoc, String numDoc, Inspector inspector, Pageable pageable) {
		return applRepository.findByDtApplBetweenAndCdSmoAndSerDocAndNumDocAndInspector(start, end, cdSmo, serDoc,
				numDoc, inspector, pageable);
	}

	public Page<Appl> findByDtApplBetweenAndCdSmoAndCdFsmoAndInspector(Date start, Date end, Integer cdSmo,
			Integer cdFsmo, Inspector inspector, Pageable pageable) {
		return applRepository.findByDtApplBetweenAndCdSmoAndCdFsmoAndInspector(start, end, cdSmo, cdFsmo, inspector,
				pageable);
	}

	public Page<Appl> findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDocAndInspector(Date start, Date end, Integer cdSmo,
			Integer cdFsmo, String serDoc, Inspector inspector, Pageable pageable) {
		return applRepository.findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDocAndInspector(start, end, cdSmo, cdFsmo,
				serDoc, inspector, pageable);
	}

	public Page<Appl> findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDocAndNumDocAndInspector(Date start, Date end,
			Integer cdSmo, Integer cdFsmo, String serDoc, String numDoc, Inspector inspector, Pageable pageable) {
		return applRepository.findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDocAndNumDocAndInspector(start, end, cdSmo,
				cdFsmo, serDoc, numDoc, inspector, pageable);
	}

	public Optional<Appl> findById(Long applId) {
		return applRepository.findById(applId);
	}

	public Page<Appl> getPage(ApplSearchParameters applSParam, Optional<Integer> page, Optional<Integer> size,
			HttpSession session) throws ParseException {
		User user = (User) session.getAttribute("user");

		Date start = new SimpleDateFormat("yyyy-MM-dd").parse(applSParam.getDtReg1());
		Date end = new SimpleDateFormat("yyyy-MM-dd").parse(applSParam.getDtReg2());
		String serDoc = applSParam.getSerDoc().trim();
		String numDoc = applSParam.getNumDoc().trim();
		Integer cdInsp = applSParam.getCdInsp();
		Inspector inspector = new Inspector();
		inspector.setCdInsp(cdInsp);
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		boolean userHasHsmoRole = user.getRoles().stream().filter(t -> t.getRole_name().equals(Constants.HSMO_ROLE))
				.collect(Collectors.toList()).size() > 0;

		Page<Appl> applPage;
		if (userHasHsmoRole) {
			if (applSParam.getCdInsp() != null) {
				if (!serDoc.isEmpty() && !numDoc.isEmpty()) {
					applPage = findByDtApplBetweenAndCdSmoAndSerDocAndNumDocAndInspector(start, end,
							user.getSmo() + Constants.SMO_ADD_CODE, serDoc, numDoc, inspector,
							PageRequest.of(currentPage - 1, pageSize));
				} else if (!serDoc.isEmpty()) {
					applPage = findByDtApplBetweenAndCdSmoAndSerDocAndInspector(start, end,
							user.getSmo() + Constants.SMO_ADD_CODE, serDoc, inspector,
							PageRequest.of(currentPage - 1, pageSize));
				} else {
					applPage = findByDtApplBetweenAndCdSmoAndInspector(start, end,
							user.getSmo() + Constants.SMO_ADD_CODE, inspector,
							PageRequest.of(currentPage - 1, pageSize));
				}
			} else {
				if (!serDoc.isEmpty() && !numDoc.isEmpty()) {
					applPage = findByDtApplBetweenAndCdSmoAndSerDocAndNumDoc(start, end,
							user.getSmo() + Constants.SMO_ADD_CODE, serDoc, numDoc,
							PageRequest.of(currentPage - 1, pageSize));
				} else if (!serDoc.isEmpty()) {
					applPage = findByDtApplBetweenAndCdSmoAndSerDoc(start, end, user.getSmo() + Constants.SMO_ADD_CODE,
							serDoc, PageRequest.of(currentPage - 1, pageSize));
				} else {
					applPage = findByDtApplBetweenAndCdSmo(start, end, user.getSmo() + Constants.SMO_ADD_CODE,
							PageRequest.of(currentPage - 1, pageSize));
				}
			}
		} else {
			if (applSParam.getCdInsp() != null) {
				if (!serDoc.isEmpty() && !numDoc.isEmpty()) {
					applPage = findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDocAndNumDocAndInspector(start, end,
							user.getSmo() + Constants.SMO_ADD_CODE, user.getfSmo(), serDoc, numDoc, inspector,
							PageRequest.of(currentPage - 1, pageSize));
				} else if (!serDoc.isEmpty()) {
					applPage = findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDocAndInspector(start, end,
							user.getSmo() + Constants.SMO_ADD_CODE, user.getfSmo(), serDoc, inspector,
							PageRequest.of(currentPage - 1, pageSize));
				} else {
					applPage = findByDtApplBetweenAndCdSmoAndCdFsmoAndInspector(start, end,
							user.getSmo() + Constants.SMO_ADD_CODE, user.getfSmo(), inspector,
							PageRequest.of(currentPage - 1, pageSize));
				}
			} else {
				if (!serDoc.isEmpty() && !numDoc.isEmpty()) {
					applPage = findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDocAndNumDoc(start, end,
							user.getSmo() + Constants.SMO_ADD_CODE, user.getfSmo(), serDoc, numDoc,
							PageRequest.of(currentPage - 1, pageSize));
				} else if (!serDoc.isEmpty()) {
					applPage = findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDoc(start, end,
							user.getSmo() + Constants.SMO_ADD_CODE, user.getfSmo(), serDoc,
							PageRequest.of(currentPage - 1, pageSize));
				} else {
					applPage = findByDtApplBetweenAndCdSmoAndCdFsmo(start, end, user.getSmo() + Constants.SMO_ADD_CODE,
							user.getfSmo(), PageRequest.of(currentPage - 1, pageSize));
				}
			}
		}

		return applPage;
	}

	public void saveSParams(ApplSearchParameters applSParam, HttpSession session) {
		session.setAttribute("applSParam", applSParam);

	}

	public Optional<Object> getSParams(HttpSession session) {
		return Optional.ofNullable(session.getAttribute("applSParam"));

	}

	public ByteArrayInputStream createExcel(ApplSearchParameters applSParam, HttpSession session)
			throws ExcelGeneratorException, IOException {
		
		User user = (User) session.getAttribute("user");
			return new ExcelGenerator(getDataForExcel(applSParam, user)).toExcel();
	
	}

	private Collection<RowData> getDataForExcel(ApplSearchParameters applSParam, User user) {

		return applDAO.getDataForExcel(user, applSParam);
		
	}
}
