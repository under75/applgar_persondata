package ru.tfoms.applgar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.tfoms.applgar.entity.HouseGar;
import ru.tfoms.applgar.entity.HouseId;

public interface HouseGarRepository extends JpaRepository<HouseGar, HouseId> {

	HouseGar findByObjectguidAndIsActualAndIsActive(String objectguid, boolean b, boolean c);

}
