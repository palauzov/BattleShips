package com.softuni.battleshipsweb.entities;

import com.softuni.battleshipsweb.enums.CategoryName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(unique = true, nullable = false)
    private CategoryName type;

    @Column(columnDefinition = "text")
    private String description;

    public Category() {}
    public Category(CategoryName type) {
        this.type = type;
    }



}
