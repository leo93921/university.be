package it.unisalento.se.repositories;

import it.unisalento.se.dao.CourseOfStudy;
import it.unisalento.se.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.email = :username AND u.password = :password")
    User findByCredentials(@Param("username") String username, @Param("password") String password);

    @Query("SELECT u FROM User u WHERE u.userType.id = :typeId")
    List<User> findByUserType(@Param("typeId") Integer type);

    List<User> findByCourseOfStudy(CourseOfStudy courseOfStudy);
}
