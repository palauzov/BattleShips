package com.softuni.battleshipsweb.services;

import com.softuni.battleshipsweb.dtos.StartBattleDTO;
import com.softuni.battleshipsweb.entities.Ship;
import com.softuni.battleshipsweb.repositories.ShipRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BattleService {
    private ShipRepository shipRepository;

    public BattleService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    public void attack(StartBattleDTO battleData){
        Optional<Ship> attackerOpt = this.shipRepository.findById((long) battleData.getAttackerId());
        Optional<Ship> defenderOpt = this.shipRepository.findById((long) battleData.getDefenderId());

        if (attackerOpt.isEmpty() || defenderOpt.isEmpty()){
           throw new NoSuchElementException();
        }
        Ship attacker = attackerOpt.get();
        Ship defender = defenderOpt.get();

        int defenderNewHealth = (defender.getHealth() - attacker.getPower());
        if (defenderNewHealth <= 0){
            shipRepository.delete(defender);
        }else {
            defender.setHealth(defenderNewHealth);
            shipRepository.save(defender);
        }
    }

}
