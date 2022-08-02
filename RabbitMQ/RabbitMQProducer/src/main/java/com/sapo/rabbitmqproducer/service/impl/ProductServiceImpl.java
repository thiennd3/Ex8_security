package com.sapo.rabbitmqproducer.service.impl;


import com.sapo.rabbitmqproducer.model.entity.Product;
import com.sapo.rabbitmqproducer.repository.*;
import com.sapo.rabbitmqproducer.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ProductServiceImpl implements ProductService {


    ProductRepository productRepos;

    @Override
    public List<Product> getAll(String name, Integer page, Integer size) {
        return productRepos.getByName(name, PageRequest.of(page, size, Sort.Direction.ASC, "name")).toList();
    }

    @Override
    public String getNewCode() {
        String newCode = "P";
        Product product = productRepos.getTopById();
        newCode = newCode + String.valueOf(product.getId() + 1);
        return newCode;
    }

    @Override
    @Query("select count(p) from Product p where p.storageId = :storageId ")
    public Integer countProductById(Integer storageId) {
        return productRepos.countProductById(storageId);
    }

    @Override
    public List<Product> findAll() {
        return productRepos.findAll();
    }

    @Override
    public List<Product> findAll(Sort sort) {
        return productRepos.findAll(sort);
    }

    @Override
    public <S extends Product> S saveAndFlush(S entity) {
        return productRepos.saveAndFlush(entity);
    }

    @Override
    public <S extends Product> S save(S entity) {
        return productRepos.save(entity);
    }

    @Override
    public Optional<Product> findById(Integer integer) {
        return productRepos.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return productRepos.existsById(integer);
    }

    @Override
    @Query("select p from Product p where p.name like ?1")
    public List<Product> findAllByTenLike(String ten) {
        return productRepos.findAllByTenLike(ten);
    }

    @Override
    @Query("select p from Product p where lower(p.name)  like %:name%")
    public Page<Product> getByName(String name, Pageable pageable) {
        return productRepos.getByName(name, pageable);
    }

    @Override
    @Query(value = "select * from  product  order by id DESC limit 1", nativeQuery = true)
    public Product getTopById() {
        return productRepos.getTopById();
    }

    @Override
    public void deleteById(Integer integer) {
        productRepos.deleteById(integer);
    }

}
