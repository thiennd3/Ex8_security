package com.sapo.rabbitmqproducer.repository;




import com.sapo.rabbitmqproducer.model.entity.Statistical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticalRepository extends JpaRepository<Statistical,Integer> {


}
