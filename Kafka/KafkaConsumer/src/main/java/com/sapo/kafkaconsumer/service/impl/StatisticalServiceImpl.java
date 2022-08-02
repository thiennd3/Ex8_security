package com.sapo.kafkaconsumer.service.impl;

import com.sapo.kafkaconsumer.model.entity.Statistical;
import com.sapo.kafkaconsumer.repository.StatisticalRepository;
import com.sapo.kafkaconsumer.service.StatisticalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StatisticalServiceImpl implements StatisticalService {
    StatisticalRepository statisticalRepos;

    @Override
    public List<Statistical> findAll() {
        return statisticalRepos.findAll();
    }

    @Override
    public <S extends Statistical> S save(S entity) {
        return statisticalRepos.save(entity);
    }

    public <S extends Statistical> List<S> saveAll(Iterable<S> entities) {
        return statisticalRepos.saveAll(entities);
    }

    @Override
    public Optional<Statistical> findById(Integer integer) {
        return statisticalRepos.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return statisticalRepos.existsById(integer);
    }

    @Override
    public void deleteById(Integer integer) {
        statisticalRepos.deleteById(integer);
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, SQLException.class})
    public void save(List<Statistical> statisticals) {
        statisticals.forEach(v -> {
            save(v);
        });
    }
}
