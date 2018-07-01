package it.unisalento.se.repositories;

import it.unisalento.se.dao.SupportDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportDeviceRepository extends JpaRepository<SupportDevice, Integer> {
}

