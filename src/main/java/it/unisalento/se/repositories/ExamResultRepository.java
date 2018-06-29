package it.unisalento.se.repositories;


import it.unisalento.se.dao.ExamResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResults, Integer> {
}
