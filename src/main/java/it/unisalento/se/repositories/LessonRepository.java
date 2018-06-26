package it.unisalento.se.repositories;


import it.unisalento.se.dao.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer>  {
}
