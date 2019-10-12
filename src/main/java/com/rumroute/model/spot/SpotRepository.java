package com.rumroute.model.spot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SpotRepository extends JpaRepository<Spot, Long> {

    @Query(value = "select * from Spot order by RAND() limit 1", nativeQuery = true)
    Spot getRandomSpot();
}
