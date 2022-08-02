package com.edu.sapo.ex9_kafka.repository;



import com.edu.sapo.ex9_kafka.model.entity.Statistical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StatisticalRepository extends JpaRepository<Statistical,Integer> {


}
