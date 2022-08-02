package com.sapo.rabbitmqproducer.service;


import com.sapo.rabbitmqproducer.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService{




    List<Product> getAll(String name, Integer page, Integer size);


    String getNewCode();



    @Query("select count(p) from Product p where p.storageId = :storageId ")
    Integer countProductById(Integer storageId);

    List<Product> findAll();

    List<Product> findAll(Sort sort);

    <S extends Product> S saveAndFlush(S entity);

    <S extends Product> S save(S entity);

    Optional<Product> findById(Integer integer);

    boolean existsById(Integer integer);

    @Query("select p from Product p where p.name like ?1")
    List<Product> findAllByTenLike(String ten);

    @Query("select p from Product p where lower(p.name)  like %:name%")
    Page<Product> getByName(String name, Pageable pageable);

    @Query(value = "select * from  product  order by id DESC limit 1", nativeQuery = true)
    Product getTopById();

    void deleteById(Integer integer);
}
