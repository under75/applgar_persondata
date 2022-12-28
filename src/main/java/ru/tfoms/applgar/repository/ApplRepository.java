package ru.tfoms.applgar.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.tfoms.applgar.entity.Appl;
import ru.tfoms.applgar.entity.Inspector;

@Repository
public interface ApplRepository extends JpaRepository<Appl, Long> {

	Page<Appl> findByDtApplBetweenAndCdSmo(Date start, Date end, Integer cdSmo, Pageable pageable);

	Page<Appl> findByDtApplBetweenAndCdSmoAndSerDoc(Date start, Date end, Integer cdSmo, String serDoc,
			Pageable pageable);

	Page<Appl> findByDtApplBetweenAndCdSmoAndSerDocAndNumDoc(Date start, Date end, Integer cdSmo,
			String serDoc, String numDoc, Pageable pageable);

	Page<Appl> findByDtApplBetweenAndCdSmoAndCdFsmo(Date start, Date end, Integer cdSmo, Integer cdFsmo,
			Pageable pageable);

	Page<Appl> findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDoc(Date start, Date end, Integer cdSmo,
			Integer cdFsmo, String serDoc, Pageable pageable);

	Page<Appl> findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDocAndNumDoc(Date start, Date end, Integer cdSmo,
			Integer cdFsmo, String serDoc, String numDoc, Pageable pageable);

	Page<Appl> findByDtApplBetweenAndCdSmoAndInspector(Date start, Date end, Integer cdSmo,
			Inspector inspector, Pageable pageable);

	Page<Appl> findByDtApplBetweenAndCdSmoAndSerDocAndInspector(Date start, Date end, Integer cdSmo,
			String serDoc, Inspector inspector, Pageable pageable);

	Page<Appl> findByDtApplBetweenAndCdSmoAndSerDocAndNumDocAndInspector(Date start, Date end,
			Integer cdSmo, String serDoc, String numDoc, Inspector inspector, Pageable pageable);

	Page<Appl> findByDtApplBetweenAndCdSmoAndCdFsmoAndInspector(Date start, Date end, Integer cdSmo,
			Integer cdFsmo, Inspector inspectorp, Pageable pageable);

	Page<Appl> findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDocAndInspector(Date start, Date end,
			Integer cdSmo, Integer cdFsmo, String serDoc, Inspector inspector, Pageable pageable);

	Page<Appl> findByDtApplBetweenAndCdSmoAndCdFsmoAndSerDocAndNumDocAndInspector(Date start, Date end,
			Integer cdSmo, Integer cdFsmo, String serDoc, String numDoc, Inspector inspector, Pageable pageable);

}
