package com.rumroute.model.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {


   @Query(value = "select * from rumroute.Category order by RAND() limit 1",
           nativeQuery = true)
    Category getRandomCategory();
}