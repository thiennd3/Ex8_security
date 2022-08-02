package com.example.rabbitmqconsumer.repository;


import com.example.rabbitmqconsumer.model.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


    @Query(value = "select p from Product p where p.name like ?1")
    List<Product> findAllByTenLike(String ten);

    @Query("select p from Product p where lower(p.name)  like %:name%")
    Page<Product> getByName(@Param("name") String name, Pageable pageable);

    @Query(value = "select * from  product  order by id DESC limit 1", nativeQuery = true)
    Product getTopById();

    @Query("select count(p) from Product p where p.storageId = :storageId ")
    Integer countProductById(@Param("storageId") Integer storageId);
}
