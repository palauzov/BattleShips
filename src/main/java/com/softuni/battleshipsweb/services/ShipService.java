package com.softuni.battleshipsweb.services;

import com.softuni.battleshipsweb.dtos.ShipBattleDTO;
import com.softuni.battleshipsweb.dtos.ShipDTO;
import com.softuni.battleshipsweb.entities.Category;
import com.softuni.battleshipsweb.entities.Ship;
import com.softuni.battleshipsweb.entities.User;
import com.softuni.battleshipsweb.enums.CategoryName;
import com.softuni.battleshipsweb.repositories.CategoryRepository;
import com.softuni.battleshipsweb.repositories.ShipRepository;
import com.softuni.battleshipsweb.repositories.UserRepository;
import com.softuni.battleshipsweb.sessions.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipService {

    private ShipRepository shipRepository;
    private CategoryRepository categoryRepository;
    private LoggedUser loggedUser;
    private UserRepository userRepository;

    public ShipService(ShipRepository shipRepository, CategoryRepository categoryRepository, LoggedUser loggedUser, com.softuni.battleshipsweb.repositories.UserRepository userRepository) {
        this.shipRepository = shipRepository;
        this.categoryRepository = categoryRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
    }

    public boolean create(ShipDTO shipDTO) {
        Optional<Ship> ship1 = shipRepository.findByName(shipDTO.getName());

        if (ship1.isPresent()){
            return false;
        }

        CategoryName categoryName = switch (shipDTO.getCategory()){
            case 0 -> CategoryName.BATTLE;
            case 1 -> CategoryName.CARGO;
            case 2 -> CategoryName.PATROL;
            default -> CategoryName.BATTLE;
        };
        Category category = this.categoryRepository.findByType(categoryName);
        User owner = this.userRepository.searchById(loggedUser.getId());

        Ship ship = new Ship();
        ship.setName(shipDTO.getName());
        ship.setPower(shipDTO.getPower());
        ship.setHealth(shipDTO.getHealth());
        ship.setCreated(shipDTO.getCreated());
        ship.setCategory(category);
        ship.setUser(owner);

        this.shipRepository.save(ship);

        return true;
    }

    public List<ShipBattleDTO> getShipsOwnedBy(long ownerId) {
         return shipRepository.findByUserId(ownerId)
                 .stream().map(ShipBattleDTO::new).collect(Collectors.toList());
    }

    public List<ShipBattleDTO> getShipsNotOwnedBy(long ownerId) {
        return shipRepository.findByUserIdNot(ownerId)
                .stream().map(ShipBattleDTO::new).collect(Collectors.toList());
    }

    public List<ShipBattleDTO> getShipsSorted() {
        return shipRepository.findAllByOrderByNameAscHealthAscPower()
                .stream().map(ShipBattleDTO::new).collect(Collectors.toList());
    }
}
