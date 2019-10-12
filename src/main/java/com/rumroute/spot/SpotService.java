package com.rumroute.spot;

import com.rumroute.model.spot.Spot;
import com.rumroute.model.spot.SpotRepository;
import com.rumroute.spot.dto.SpotDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SpotService {

    private final Logger logger = LoggerFactory.getLogger(SpotService.class);

    private final SpotRepository spotRepository;

    /**
     * Get all the spots
     * @return List of spots.
     */
    public List<Spot> getAllSpots(){
        return spotRepository.findAll();
    }

    public Optional<Spot> findSpotById(Long id){
        return spotRepository.findById(id);
    }

    public Spot createSpot(SpotDto spotDto){

        Spot spot = new Spot();

        //Todo: pass data from the spotDto to the spot entity.

        spotRepository.save(spot);
        logger.debug("Created a new Spot: {}", spot);
        return spot;
    }

    //Todo: change it so that it accepts an user dto.
    public Optional<SpotDto> updateSpot(Spot spot){
        return Optional
                .of(spotRepository.findById(spot.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(old-> {
                    logger.debug("Updating spot {}", old);
                    return spot;
                })
                .map(SpotDto::new);
    }

    public void deleteSpot(Long id){
        spotRepository.findById(id)
                .ifPresent( spot -> {
                    spotRepository.delete(spot);
                    logger.debug("Deleting spot {}", spot);
                });
    }

    public Spot getRandomSpot(){
       return spotRepository.getRandomSpot();
    }



}
