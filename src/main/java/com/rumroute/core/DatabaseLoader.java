package com.rumroute.core;

import com.github.javafaker.Faker;
import com.rumroute.category.CategoryService;
import com.rumroute.model.brand.Brand;
import com.rumroute.model.category.Category;
import com.rumroute.model.category.CategoryRepository;
import com.rumroute.model.drink.Drink;
import com.rumroute.model.spot.Spot;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
@AllArgsConstructor
public class DatabaseLoader implements ApplicationRunner{

    private final CategoryRepository categoryRepository;

    private final CategoryService categoryService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.loadCategories();
    }

    public void loadCategories(){

        List<Category> categories = Stream
                .generate(categorySupplier())
                .limit(10)
                .collect(Collectors.toList());

        categoryRepository.saveAll(categories);
    }

    private Supplier<Category> categorySupplier(){
        return () ->{
            Category category = new Category();
            category.setName(faker().beer().style());
            category.setCode(category.getName());
            category.setDescription(faker().shakespeare().asYouLikeItQuote());
            return category;
        };
    }

    //Todo: Spot Loader
    public void loadSpots(){
        List<Spot> spots = Stream
                .generate(spotSupplier())
                .limit(10)
                .collect(Collectors.toList());
    }

    private Supplier<Spot> spotSupplier(){
        return Spot::new;
    }

    //Todo Drink Loader

    public void loadDrink(){

        List<Drink> drinks = Stream
                .generate(drinkSupplier())
                .limit(10)
                .collect(Collectors.toList());

    }

    private Supplier<Drink> drinkSupplier(){
        return Drink::new;
    }

    //Todo Brand Loader
    public void loadBrand(){
        List<Brand> brands = Stream
                .generate(brandSupplier())
                .limit(10)
                .collect(Collectors.toList());
    }

    private Supplier<Brand> brandSupplier(){

        return ()->{
            return new Brand();
        };
    }


    private Faker faker(){
        return Faker.instance();
    }

}
