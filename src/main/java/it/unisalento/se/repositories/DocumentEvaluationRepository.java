package it.unisalento.se.repositories;

import it.unisalento.se.dao.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentEvaluationRepository extends JpaRepository<DocumentEvaluation, Integer> {

    List<DocumentEvaluation> findByDocument(Document document);


}


