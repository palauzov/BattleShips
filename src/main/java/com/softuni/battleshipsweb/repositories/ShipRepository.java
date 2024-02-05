package com.softuni.battleshipsweb.repositories;

import com.softuni.battleshipsweb.entities.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
    Optional<Ship> findByName(String name);

    List<Ship> findByUserId(long ownerId);

    List<Ship> findByUserIdNot(long ownerId);

    List<Ship> findAllByOrderByNameAscHealthAscPower();
}
