package com.example.backend.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    // Find should be used when you actually have to find something, and expect that the searched object might not exist.
    // Get should be used when you know where the object is (you don't have to perform a long action to find it), and it
    // implies that the object exists
    Player findPlayerById(Long id);
}
