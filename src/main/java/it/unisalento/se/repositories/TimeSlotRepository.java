package it.unisalento.se.repositories;

import it.unisalento.se.dao.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSlotRepository extends JpaRepository<Timeslot, Integer> {

}
