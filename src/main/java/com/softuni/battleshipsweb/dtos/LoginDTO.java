package com.softuni.battleshipsweb.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    @NotBlank
    @Size(min = 3, max = 10)
    private String username;

    @NotBlank
    @Size(min = 3)
    private String password;
}
