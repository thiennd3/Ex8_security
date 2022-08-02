package com.example.rabbitmqconsumer.service.impl;



import com.example.rabbitmqconsumer.model.entity.Statistical;
import com.example.rabbitmqconsumer.repository.StatisticalRepository;
import com.example.rabbitmqconsumer.service.StatisticalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatisticalServiceImpl implements StatisticalService {
    StatisticalRepository statisticalRepos;




    @Override
    public <S extends Statistical> List<S> saveAll(Iterable<S> entities) {
        return statisticalRepos.saveAll(entities);
    }

    @Override
    public <S extends Statistical> S save(S entity) {
        return statisticalRepos.save(entity);
    }

    @Override
    public boolean existsById(Integer integer) {
        return statisticalRepos.existsById(integer);
    }
}
