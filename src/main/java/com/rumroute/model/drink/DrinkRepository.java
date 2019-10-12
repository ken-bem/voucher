package com.rumroute.model.drink;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DrinkRepository extends JpaRepository<Drink, Long> {


    @Query(value = "select * from rumroute.drink order by RAND() limit 1",
            nativeQuery = true)
    Drink getRandomDrink();


}
