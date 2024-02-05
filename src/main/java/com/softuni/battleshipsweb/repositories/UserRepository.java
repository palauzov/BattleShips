package com.softuni.battleshipsweb.repositories;

import com.softuni.battleshipsweb.dtos.LoginDTO;
import com.softuni.battleshipsweb.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);


    User searchById(long id);
}
