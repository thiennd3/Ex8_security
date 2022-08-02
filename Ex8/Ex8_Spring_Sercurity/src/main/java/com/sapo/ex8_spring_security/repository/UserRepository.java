package com.sapo.ex8_spring_security.repository;

import com.sapo.ex8_spring_security.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Account, Integer> {
    @Query(value = "select p from Account p where p.username=:username and p.password=:password")
    Account getUser(@Param("username") String username, @Param("password") String password);

    @Query("select u from Account u where u.username = :username")
    Account getUserByUsername(@Param("username") String username);
}