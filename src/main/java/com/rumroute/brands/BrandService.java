package com.rumroute.brands;

import com.rumroute.model.brand.Brand;
import com.rumroute.model.brand.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    //Create brand
    public Brand createBrand(Brand brand){
        return brandRepository.save(brand);
    }

    //Get all brands
    public List<Brand> getAllBrands(){
        return brandRepository.findAll();
    }

    //Get a single brand
    public Optional<Brand> getBrandById(Long id){
        return brandRepository.findById(id);
    }

    //Update brand

    //Delete brand






}
