package com.softuni.battleshipsweb.controllers;

import com.softuni.battleshipsweb.dtos.ShipBattleDTO;
import com.softuni.battleshipsweb.dtos.StartBattleDTO;
import com.softuni.battleshipsweb.services.AuthService;
import com.softuni.battleshipsweb.services.ShipService;
import com.softuni.battleshipsweb.sessions.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    private final ShipService shipService;
    private final LoggedUser loggedUser;

    private AuthService authService;

    @ModelAttribute("startBattleDTO")
    public StartBattleDTO initStartBattleDTO(){
        return new StartBattleDTO();
    }

    @Autowired
    public HomeController(ShipService shipService, LoggedUser loggedUser, AuthService authService) {
        this.shipService = shipService;
        this.loggedUser = loggedUser;
        this.authService = authService;
    }

    @GetMapping("/home")
    public String loggedInIndex(Model model){
        if (!authService.isLoggedIn()){
            return "redirect:/";
        }

        long loggedUserId = this.loggedUser.getId();

        List<ShipBattleDTO> ownedShips = this.shipService.getShipsOwnedBy(loggedUserId);
        List<ShipBattleDTO> enemyShips = this.shipService.getShipsNotOwnedBy(loggedUserId);
        List<ShipBattleDTO> sortedShips = this.shipService.getShipsSorted();

        model.addAttribute("ownedShips", ownedShips);
        model.addAttribute("enemyShips", enemyShips);
        model.addAttribute("sortedShips", sortedShips);

        return "home";
    }

    @GetMapping("/")
    public String loggedOutIndex(){
        if (authService.isLoggedIn()){
            return "redirect:/home";
        }
        return "index";
    }


}
