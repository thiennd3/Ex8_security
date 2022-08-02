package com.sapo.rabbitmqproducer.service.impl;


import com.sapo.rabbitmqproducer.model.entity.Statistical;
import com.sapo.rabbitmqproducer.service.StatisticalService;
import com.sapo.rabbitmqproducer.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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
