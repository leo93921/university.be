package it.unisalento.se.repositories;

import it.unisalento.se.dao.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
}
