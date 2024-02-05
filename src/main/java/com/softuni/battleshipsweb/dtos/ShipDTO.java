package com.softuni.battleshipsweb.dtos;

import com.softuni.battleshipsweb.entities.Ship;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class ShipDTO {


    @NotBlank
    @Size(min = 2, max = 10)
    private String name;


    @Positive
    private int health;


    @Positive
    private int power;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private LocalDate created;


    @Min(0)
    private int category;
}
