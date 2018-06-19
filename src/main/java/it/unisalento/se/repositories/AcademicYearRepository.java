package it.unisalento.se.repositories;

import it.unisalento.se.dao.AcademicYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicYearRepository extends JpaRepository<AcademicYear, Integer> {

}
