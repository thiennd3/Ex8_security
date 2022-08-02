package com.sapo.ex8_spring_security.sevice.impl;

import com.sapo.ex8_spring_security.model.entity.Storage;
import com.sapo.ex8_spring_security.repository.ProductRepository;
import com.sapo.ex8_spring_security.repository.StorageRepository;
import com.sapo.ex8_spring_security.sevice.BaseService;
import com.sapo.ex8_spring_security.sevice.StorageService;
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
public class StorageServiceImpl implements StorageService {
    StorageRepository storeRepos;
    ProductRepository productRepos;


    @Override
    public List<Storage> getAll(String name, Integer page, Integer size) {
        return storeRepos.getByName(name, PageRequest.of(page, size, Sort.Direction.ASC, "name")).toList();
    }

    @Override
    public String getNewCode() {
        String newCode = "S";
        Storage storage = storeRepos.getTopById();
        newCode = newCode + String.valueOf(storage.getId() + 1);
        return newCode;
    }

    @Override
    public Storage update(Integer id, Storage entity) {
        Storage storage = storeRepos.findById(id).orElseThrow(()->{   throw new RuntimeException("Id is not exist"); });

            storage.setUpdateAt(Timestamp.valueOf(LocalDateTime.now()));
            storage.setName(entity.getName());
            storage.setAddress(entity.getAddress());
            return storeRepos.save(storage);


    }

    @Override
    public Integer delete(Integer id) {
        Storage storage = storeRepos.findById(id).get();
        if (storage == null) {
            throw new RuntimeException("Id is not exist");

        } else {
            storeRepos.deleteById(id);
            return id;

        }
    }

    @Override
    public Storage add(Storage entity) {

        entity.setCode(getNewCode());
        entity.setCreateAt(Timestamp.valueOf(LocalDateTime.now()));
        entity.setUpdateAt(entity.getCreateAt());
        return storeRepos.save(entity);
    }


}
