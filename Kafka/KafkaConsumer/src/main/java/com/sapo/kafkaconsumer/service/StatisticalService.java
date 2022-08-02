package com.sapo.kafkaconsumer.service;

import com.sapo.kafkaconsumer.model.entity.Statistical;

import java.util.List;
import java.util.Optional;

public interface StatisticalService {
    List<Statistical> findAll();

    <S extends Statistical> S save(S entity);

    Optional<Statistical> findById(Integer integer);

    boolean existsById(Integer integer);

    void deleteById(Integer integer);

    void save(List<Statistical> statisticals);
}
