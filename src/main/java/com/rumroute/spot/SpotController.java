package com.rumroute.spot;

import com.rumroute.model.spot.Spot;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class SpotController {

    private SpotService spotService;

    @GetMapping("/api/spots")
    public ResponseEntity getAllSpots(){
        return ResponseEntity
                .ok(spotService.getAllSpots());
    }

    @GetMapping("/api/spot/{id}")
    public ResponseEntity getSpotById(Long id){
        return null;
    }


    @PostMapping("/api/spots")
    public ResponseEntity createSpot(@RequestBody Spot spot){
        return null;
    }







}
