package it.unisalento.se.repositories;

import it.unisalento.se.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.email = :username AND u.password = :password")
    User findByCredentials(@Param("username") String username, @Param("password") String password);

}
