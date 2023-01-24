package ru.tfoms.applgar.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.tfoms.applgar.entity.Fsmo;
import ru.tfoms.applgar.entity.FsmoId;

public interface FsmoRepository extends JpaRepository<Fsmo, FsmoId> {

	Collection<Fsmo> findByCdSmo(Integer smo);

	Fsmo getByCdSmoAndCdFsmo(Integer smo, Integer fSmo);

	Collection<Fsmo> findByCdSmoOrderByName(Integer smo);

}
