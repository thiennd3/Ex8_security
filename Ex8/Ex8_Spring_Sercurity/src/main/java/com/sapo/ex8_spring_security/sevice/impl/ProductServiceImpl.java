package com.sapo.ex8_spring_security.sevice.impl;

import com.sapo.ex8_spring_security.model.entity.Category;
import com.sapo.ex8_spring_security.model.entity.Product;
import com.sapo.ex8_spring_security.repository.CategoryRepository;
import com.sapo.ex8_spring_security.repository.ProductRepository;
import com.sapo.ex8_spring_security.repository.StorageRepository;
import com.sapo.ex8_spring_security.sevice.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements  ProductService {

    ProductRepository productRepos;
    StorageRepository storeRepos;
    CategoryRepository categoryRepos;


    @Override
    public List<Product> getAll(String name, Integer page, Integer size) {
        return productRepos.getByName(name, PageRequest.of(page,size, Sort.Direction.DESC,"name")).toList();
    }

    @Override
    public String getNewCode() {
        String newCode = "P";
        Product product = productRepos.getTopById();
        newCode = newCode + String.valueOf(product.getId() + 1);
        return newCode;
    }

    @Override
    public Product update(Integer id, Product entity) {



        //lưu vào CSDL
        Product product = productRepos.findById(id).orElseThrow(()->{   throw new RuntimeException("Id is not exist"); });



            // kiểm tra mã danh mục
            if (!categoryRepos.existsById(entity.getCategoryId()))
                throw new RuntimeException("category_id is not exist");
            // kiểm tra mã kho
            if (!categoryRepos.existsById(entity.getStorageId())) {
                throw new RuntimeException("storage_id is not exist");

            }
            entity.setCreateAt(product.getCreateAt());
            entity.setUpdateAt(Timestamp.valueOf(LocalDateTime.now()));
            entity.setCode(product.getCode());
            entity.setId(product.getId());

            return productRepos.save(entity);

    }

    @Override
    public Integer delete(Integer id) {
        if (!productRepos.existsById(id)) {
            throw new RuntimeException("Id is not exist");
        } else {
            productRepos.deleteById(id);
            return id;

        }
    }

    @Override
    public Product add(Product entity) {
        Product product = entity;
        // thêm ngày tạo
        product.setCreateAt(Timestamp.valueOf(LocalDateTime.now()));
        product.setUpdateAt(product.getCreateAt());
        // tạo mã mới
        product.setCode(getNewCode());

        return productRepos.save(product);
    }

}
