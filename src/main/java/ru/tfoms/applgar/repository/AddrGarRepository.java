package ru.tfoms.applgar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.tfoms.applgar.entity.AddrGar;

public interface AddrGarRepository extends JpaRepository<AddrGar, Long> {

	AddrGar findByObjectguidAndIsActualAndIsActive(String objectguid, boolean b, boolean c);

}
