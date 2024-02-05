package com.softuni.battleshipsweb.dtos;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StartBattleDTO {
    @Positive
    private int attackerId;

    @Positive
    private int defenderId;
}
