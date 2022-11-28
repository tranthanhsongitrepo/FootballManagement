package com.example.backend.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void createPlayer(Player player) {
        this.playerRepository.save(player);
    }

    public Player getPlayerById(Long id) {
        return this.playerRepository.findPlayerById(id);
    }

    public List<Player> findPlayers() {
        return this.playerRepository.findAll();
    }

    public List<Player> findPlayers(Player player) {
        if (Objects.equals(player.getName(), "")) {
            return List.of();
        }

        ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAny().withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        return this.playerRepository.findAll(Example.of(player, caseInsensitiveExampleMatcher));
    }
    public void deletePlayerById(Long id) {
        this.playerRepository.deleteById(id);
    }

    public Player updatePlayer(Player player) {
        return this.playerRepository.save(player);
    }

}
