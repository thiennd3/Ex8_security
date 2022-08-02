package com.sapo.ex8_spring_security.repository;

import com.sapo.ex8_spring_security.model.entity.Storage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Integer> {
    @Query("select p from Storage p where lower(p.name)  like %:name%")
    Page<Storage> getByName(@Param("name") String name, Pageable pageable);

    @Query("delete from Storage s where s.id = ?1")
    void deleteStoreById(Integer Id);
    @Query(value = "select * from  storage  order by id DESC limit 1",nativeQuery = true)
    Storage getTopById();
}
