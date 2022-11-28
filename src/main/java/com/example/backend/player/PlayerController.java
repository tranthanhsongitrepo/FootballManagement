package com.example.backend.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/players")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/create")
    public void createPlayer(@RequestBody Player player) {
        this.playerService.createPlayer(player);
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable(name = "id") Long id) {
        return this.playerService.getPlayerById(id);
    }

    @GetMapping("")
    public List<Player> getPlayerByName(Player player) {
        return this.playerService.findPlayers(player);
    }
    @GetMapping("/all")
    public List<Player> getPlayers() {
        return this.playerService.findPlayers();
    }

    @PutMapping("/update")
    public Player updateGoal(@RequestBody Player goal) {
        return this.playerService.updatePlayer(goal);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePlayerById(@PathVariable(name = "id") Long id) {
        this.playerService.deletePlayerById(id);
    }
}
