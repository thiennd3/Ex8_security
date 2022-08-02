package com.sapo.ex8_spring_security.sevice;

import com.sapo.ex8_spring_security.model.entity.Account;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
public interface AccountService {
    @Query("select p from Account p where p.username=:username and p.password=:password")
    Account getUser(String username, String password);

    List<Account> findAll();

    List<Account> findAll(Sort sort);

    <S extends Account> S saveAndFlush(S entity);

    <S extends Account> S save(S entity);

    Optional<Account> findById(Integer integer);

    boolean existsById(Integer integer);

    void deleteById(Integer integer);
}
