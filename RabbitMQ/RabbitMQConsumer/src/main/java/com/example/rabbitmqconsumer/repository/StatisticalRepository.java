package com.example.rabbitmqconsumer.repository;




import com.example.rabbitmqconsumer.model.entity.Statistical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticalRepository extends JpaRepository<Statistical,Integer> {


}
