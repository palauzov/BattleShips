package com.softuni.battleshipsweb.sessions;

import com.softuni.battleshipsweb.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Getter
@Setter
@Component
@SessionScope
public class LoggedUser {

    public void logout(){
        this.id = 0;
        this.fullName = null;
    }
    public void login(User user){
        this.id = user.getId();
        this.fullName = user.getFullName();
    }

    private long id;

    private String fullName;
}
