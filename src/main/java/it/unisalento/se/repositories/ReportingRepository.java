package it.unisalento.se.repositories;

import it.unisalento.se.dao.Reporting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportingRepository extends JpaRepository<Reporting, Integer> {
}
