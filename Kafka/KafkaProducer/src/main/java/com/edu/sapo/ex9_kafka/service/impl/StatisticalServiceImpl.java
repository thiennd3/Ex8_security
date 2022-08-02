package com.edu.sapo.ex9_kafka.service.impl;

import com.edu.sapo.ex9_kafka.model.entity.Statistical;
import com.edu.sapo.ex9_kafka.repository.StatisticalRepository;
import com.edu.sapo.ex9_kafka.service.StatisticalService;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class StatisticalServiceImpl implements StatisticalService {
    StatisticalRepository statisticalRepos;
    JdbcTemplate jdbctemplate;

    @Override
    public Statistical getByStorageId(Integer storageId) {
        String query = "Call get_statistical_by_storage_id(?);";
        Statistical statistical = jdbctemplate.queryForObject(query,new BeanPropertyRowMapper<>(Statistical.class),storageId);
        return  statistical;
    }

    @Override
    public List<Statistical> getStatisticals() {
        String query = "Call get_statisticals()";
        List<Statistical> statisticals = jdbctemplate.query(query, new BeanPropertyRowMapper<>(Statistical.class));
        return statisticals;
    }
}
