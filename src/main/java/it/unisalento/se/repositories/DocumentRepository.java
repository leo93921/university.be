package it.unisalento.se.repositories;

import it.unisalento.se.dao.Document;
import it.unisalento.se.dao.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
    List<Document> findByLesson(Lesson lesson);
}
