package com.softuni.battleshipsweb.repositories;

import com.softuni.battleshipsweb.entities.Category;
import com.softuni.battleshipsweb.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByType(CategoryName categoryName);
}
