package com.gdgku.study.backend;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.*;
// import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/players")
public class FootballPlayerController {

    private final List<Player> playerList = new ArrayList<>();
    private long nextId = 1L;

    public static class Player {
        private Long id;
        private String name;
        private int age;
        private String position;

        public Player() {}
        public Player(Long id, String name, int age, String position) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.position = position;
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
        public String getPosition() { return position; }
        public void setPosition(String position) {this.position = position;}
    }

    @PostMapping
    public Player createPlayer(@RequestBody Player player) {
        player.setId(nextId++);
        playerList.add(player);

        return player;
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerList;
    }
    
    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable("id") Long id) {

        for (Player player : playerList) {
            if (player.getId().equals(id)) {
                return player;
            }
        }

        return null;
    }

    @GetMapping("/search/name")
    public List<Player> getPlayersByName(@RequestParam("name") String name) {

        List<Player> result = new ArrayList<>();

        for (Player player : playerList) {
            if (player.getName().equalsIgnoreCase(name)) {
                result.add(player);
            }
        }

        return result;
    }

    @GetMapping("/search/age")
    public List<Player> getPlayersByAge(@RequestParam("age") int age) {

        List<Player> result = new ArrayList<>();

        for (Player player : playerList) {
            if (player.getAge() == age) {
                result.add(player);
            }
        }

        return result;
    }

    @GetMapping("/search/position")
    public List<Player> getPlayersByPosition(@RequestParam("position") String position) {

        List<Player> result = new ArrayList<>();

        for (Player player : playerList) {
            if (player.getPosition().equalsIgnoreCase(position)) {
                result.add(player);
            }
        }

        return result;
    }

    @PutMapping("/{id}")
    public Player updatePlayer(
            @PathVariable Long id,
            @RequestBody Player updatedPlayer) {

        for (Player player : playerList) {

            if (player.getId().equals(id)) {
                player.setName(updatedPlayer.getName());
                player.setAge(updatedPlayer.getAge());
                player.setPosition(updatedPlayer.getPosition());

                return player;
            }
        }

        return null;
    }
    
    @DeleteMapping("/{id}")
    public String deletePlayer(@PathVariable Long id) {

        boolean removed =
                playerList.removeIf(player -> player.getId().equals(id));

        if (removed) {
            return "Player deleted";
        }

        return "Player not found";
    }


}
