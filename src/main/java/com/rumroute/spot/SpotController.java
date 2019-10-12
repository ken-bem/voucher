package com.rumroute.spot;

import com.rumroute.model.spot.Spot;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class SpotController {

    private SpotService spotService;

    @DeleteMapping(value = "/api/spot/{id}")
    public ResponseEntity<?> deleteSpot(@PathVariable("id") Long id){
        return ResponseEntity.noContent().build();
    }

    //Add a Spot
    @PostMapping(value = "/api/spots")
    public ResponseEntity<?> addSpot(@RequestBody Spot spot) {
        return null;
    }

    /**
     * Spots
     **/

    @RequestMapping("/spots")
    public String spots(Model model) {
        return "spot/index";
    }



    @RequestMapping("/spots/{route}")
    public String specificRoute(Model model, @PathVariable String route) {
        return "spot/index";
    }


    @RequestMapping(value = "/spot/{spotcode}", method = RequestMethod.GET)
    public String spotdetails(Model model, @PathVariable int spotcode) {

        return "spot/detail";

    }

    /**
     * END Spots
     **/

    @RequestMapping("/routes")
    public String routes(Model model) {

        return "drink/routes";
    }

    @GetMapping("/routes/{route}")
    public String specificRouteDrink(Model model, @PathVariable String route) {
        return "drink/routes";
    }

}
