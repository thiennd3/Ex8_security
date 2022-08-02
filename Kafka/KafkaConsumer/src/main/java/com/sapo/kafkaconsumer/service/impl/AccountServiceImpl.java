package com.sapo.kafkaconsumer.service.impl;

import com.sapo.kafkaconsumer.model.entity.Account;
import com.sapo.kafkaconsumer.repository.UserRepository;
import com.sapo.kafkaconsumer.service.AccountService;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    UserRepository userRepository;

    public AccountServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Query("select p from Account p where p.username=:username and p.password=:password")
    public Account getUser(String username, String password) {
        return userRepository.getUser(username, password);
    }

    @Override
    public List<Account> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<Account> findAll(Sort sort) {
        return userRepository.findAll(sort);
    }

    @Override
    public <S extends Account> S saveAndFlush(S entity) {
        return userRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends Account> S save(S entity) {
        return userRepository.save(entity);
    }

    @Override
    public Optional<Account> findById(Integer integer) {
        return userRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return userRepository.existsById(integer);
    }

    @Override
    public void deleteById(Integer integer) {
        userRepository.deleteById(integer);
    }
}
