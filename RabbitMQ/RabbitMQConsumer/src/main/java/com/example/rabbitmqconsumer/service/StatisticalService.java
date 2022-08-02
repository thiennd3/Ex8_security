package com.example.rabbitmqconsumer.service;







import com.example.rabbitmqconsumer.model.entity.Statistical;

import java.util.List;

public interface StatisticalService {


    <S extends Statistical> List<S> saveAll(Iterable<S> entities);

    <S extends Statistical> S save(S entity);

    boolean existsById(Integer integer);
}
