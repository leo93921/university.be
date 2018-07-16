package it.unisalento.se.repositories;

import it.unisalento.se.dao.PublicChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicChatMessageRepository extends JpaRepository<PublicChatMessage, Integer> {

    PublicChatMessage findByUuid(String UUID);
}
