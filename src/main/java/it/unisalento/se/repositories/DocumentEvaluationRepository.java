package it.unisalento.se.repositories;

import it.unisalento.se.dao.Document;
import it.unisalento.se.dao.DocumentEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentEvaluationRepository extends JpaRepository<DocumentEvaluation, Integer> {

    List<DocumentEvaluation> findByDocument(Document document);

    @Query("SELECT case when count(e) > 0 then true else false end from DocumentEvaluation e WHERE e.user.id = :userID AND e.document.id = :documentID")
    boolean checkDocumentEvaluation(@Param("userID") Integer userID, @Param("documentID") Integer documentID);


}


