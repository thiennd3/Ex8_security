package com.sapo.kafkaconsumer.repository;

import com.sapo.kafkaconsumer.model.entity.Statistical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Statement;
import java.util.Optional;

public interface StatisticalRepository extends JpaRepository<Statistical, Integer> {


}
