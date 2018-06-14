package it.unisalento.se.repositories;

import it.unisalento.se.dao.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserTypeRepository extends JpaRepository<UserType, Integer> {

    @Query("Select t from UserType t where t.name=:typeName")
    UserType getUserTypeByName(@Param("typeName") String name);
}
