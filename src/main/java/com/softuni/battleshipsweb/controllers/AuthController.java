package com.softuni.battleshipsweb.controllers;

import com.softuni.battleshipsweb.dtos.LoginDTO;
import com.softuni.battleshipsweb.dtos.UserRegistrationDTO;
import com.softuni.battleshipsweb.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ModelAttribute("userRegistrationDTO")
    public UserRegistrationDTO initUserRegistrationDTO(){
        return new UserRegistrationDTO();
    }
    @ModelAttribute("loginDTO")
    public LoginDTO inintLoginDTO(){
        return new LoginDTO();
    }


    @GetMapping("/register")
    public String register(){
        return "register";
    }


    @PostMapping("/register")
    public String register(@Valid UserRegistrationDTO userRegistrationDTO, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){

           if(bindingResult.hasErrors() || !this.authService.registration(userRegistrationDTO)){
               redirectAttributes.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);
               redirectAttributes.addFlashAttribute(
                       "org.springframework.validation.BindingResult.userRegistrationDTO");

               return "redirect:/register";
           }

           return "redirect:/login";
    }



    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginDTO loginDTO, BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.loginDTO");
            return "redirect:/login";

        }
        if (!authService.login(loginDTO)){
            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute("badCredentials", true);
            return "redirect:/login";
        }
        return "redirect:/home";

    }
    @GetMapping("/logout")
    public String logout() {
        this.authService.logout();

        return "redirect:/";
    }


}
