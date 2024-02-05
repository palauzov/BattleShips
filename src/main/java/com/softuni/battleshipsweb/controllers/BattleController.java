package com.softuni.battleshipsweb.controllers;

import com.softuni.battleshipsweb.dtos.StartBattleDTO;
import com.softuni.battleshipsweb.entities.Ship;
import com.softuni.battleshipsweb.services.BattleService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class BattleController {

    private final BattleService battleService;

    public BattleController(BattleService battleService) {
        this.battleService = battleService;
    }


    @PostMapping("/battle")
    public String battle(@Valid StartBattleDTO startBattleDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("startBattleDTO", startBattleDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.startBattleDTO");
            return "redirect:/home";

        }

        this.battleService.attack(startBattleDTO);


        return "redirect:/home";
    }
}
