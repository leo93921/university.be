package it.unisalento.se.repositories;

import it.unisalento.se.dao.ReportingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ReportingStatusRepository extends JpaRepository<ReportingStatus, Integer> {
    @Query("Select r from ReportingStatus r where r.name=:statusName")
    ReportingStatus getReportingStatusByName(@Param("statusName") String name);
}

