package com.edu.sapo.ex9_kafka.service;



import com.edu.sapo.ex9_kafka.model.entity.Statistical;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface StatisticalService {


    Statistical getByStorageId(Integer storageId);

    List<Statistical> getStatisticals();
}
