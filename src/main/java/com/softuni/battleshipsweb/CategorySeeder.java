package com.softuni.battleshipsweb;

import com.softuni.battleshipsweb.entities.Category;
import com.softuni.battleshipsweb.enums.CategoryName;
import com.softuni.battleshipsweb.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategorySeeder implements CommandLineRunner {
    private CategoryRepository categoryRepository;
    @Autowired
    public CategorySeeder(CategoryRepository categoryRepository){
          this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(categoryRepository.count() == 0){
           List<Category> categories = Arrays.stream(CategoryName.values())
                   .map(Category::new)
                   .collect(Collectors.toList());
            this.categoryRepository.saveAll(categories);
            System.out.println("seeded");
        }
    }
}
