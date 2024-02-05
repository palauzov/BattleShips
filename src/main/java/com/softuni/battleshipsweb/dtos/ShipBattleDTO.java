package com.softuni.battleshipsweb.dtos;

import com.softuni.battleshipsweb.entities.Ship;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShipBattleDTO {

    @Positive
    private long id;

    @NotBlank
    @Size(min = 2, max = 10)
    private String name;

    @Positive
    private long health;

    @Positive
    private long power;

    public ShipBattleDTO(Ship ship) {
        this.id = ship.getId();
        this.name = ship.getName();
        this.health = ship.getHealth();
        this.power = ship.getPower();
    }
}
