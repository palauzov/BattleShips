package com.softuni.battleshipsweb.services;

import com.softuni.battleshipsweb.dtos.LoginDTO;
import com.softuni.battleshipsweb.dtos.UserRegistrationDTO;
import com.softuni.battleshipsweb.entities.User;
import com.softuni.battleshipsweb.repositories.UserRepository;
import com.softuni.battleshipsweb.sessions.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private UserRepository userRepository;
    private LoggedUser loggedUser;


    public AuthService(UserRepository userRepository, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    public boolean registration(UserRegistrationDTO userRegistrationDTO){
        if(!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())){
            return false;
        }
        Optional<User> byEmail = userRepository.findByEmail(userRegistrationDTO.getEmail());
        if(byEmail.isPresent()){
            return false;
        }
        Optional<User> byUsername = userRepository.findByUsername(userRegistrationDTO.getUsername());
        if (byUsername.isPresent()){
            return false;
        }

        User user = new User();
        user.setUsername(userRegistrationDTO.getUsername());
        user.setFullName(userRegistrationDTO.getFullName());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setPassword(userRegistrationDTO.getPassword());

        userRepository.save(user);

        return true;

    }

    public boolean login(LoginDTO loginDTO){
          Optional<User> user = userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
          if (user.isEmpty()){
              return false;
          }

          this.loggedUser.login(user.get());
          return true;
    }



    public boolean isLoggedIn(){
        return this.loggedUser.getId() > 0;
    }

    public void logout(){
        this.loggedUser.logout();
    }

    public long getLoggedUserId() {
        return this.loggedUser.getId();
    }
}
