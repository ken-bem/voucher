package com.rumroute.category;

import com.rumroute.model.category.Category;
import com.rumroute.model.category.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category getRandomCategory(){
        return categoryRepository.getRandomCategory();
    }




}
