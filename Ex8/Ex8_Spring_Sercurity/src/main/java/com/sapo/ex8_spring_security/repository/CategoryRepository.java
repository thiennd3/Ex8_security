package com.sapo.ex8_spring_security.repository;

import com.sapo.ex8_spring_security.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("select p from Category p where lower(p.name) like %:name%")
    Page<Category> getByName(@Param("name") String name, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = " call delete_category_by_id(:Id) ", nativeQuery = true)
    Integer deleteCategoriesById(@Param("Id") Integer Id);

    @Query(value = "select * from  category  order by id DESC limit 1",nativeQuery = true)
    Category getTopById();


}
