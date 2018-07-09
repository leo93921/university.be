package it.unisalento.se.repositories;

import it.unisalento.se.dao.Reporting;
import it.unisalento.se.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportingRepository extends JpaRepository<Reporting, Integer> {
    List<Reporting> findAllByUser(User profDao);
}
