package com.softuni.battleshipsweb.controllers;

import com.softuni.battleshipsweb.dtos.ShipDTO;
import com.softuni.battleshipsweb.services.ShipService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ShipController {
    private ShipService shipService;

    public ShipController(ShipService shipService) {

        this.shipService = shipService;
    }


    @ModelAttribute("shipDTO")
    public ShipDTO initShipDTO(){
        return new ShipDTO();
    }


    @GetMapping("/ships")
    public String ships(){
        return "ship-add";
    }

    @PostMapping("/ships")
    public String ships(@Valid  ShipDTO shipDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors() || !this.shipService.create(shipDTO)){
            redirectAttributes.addFlashAttribute("shipDTO", shipDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.shipDTO");
            return "redirect:/ships";

        }
        return "redirect:/home";

    }
}
