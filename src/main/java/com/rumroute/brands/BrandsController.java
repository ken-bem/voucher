package com.rumroute.brands;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class BrandsController {

    private final BrandService service;

    @RequestMapping("/info")
    public String brands(Model model){
        return "site/info";
    }



}
