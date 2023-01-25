package com.sloboiz.minecraftbases.managers;

import com.sloboiz.minecraftbases.Main;
import com.sloboiz.minecraftbases.commands.Vanish;
import com.sloboiz.minecraftbases.enums.GameStates;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class PlayerManager {

    private Main main;
    public PlayerManager(Main main) {
        this.main = main;
    }

    public void handle(Player player) {
        if (main.getGameState() == GameStates.LOBBY) {
            main.spectating.remove(player);
            player.setExp(0);
            player.setTotalExperience(0);
            player.setHealth(20);
            player.setFoodLevel(20);
            player.setGameMode(GameMode.SURVIVAL);
            player.setAllowFlight(false);
            player.sendMessage(new ChatManager(main).prefix + "Welcome to Base wars!");
            Bukkit.broadcastMessage(new ChatManager(main).prefix + player.getDisplayName() + " has joined the Base wars.");
        } else if (main.getGameState() == GameStates.INGAME || main.getGameState() == GameStates.ENDGAME || main.getGameState() == GameStates.PREGAME) {
            main.spectating.remove(player);
            main.spectating.add(player);
            new Vanish(main).toggleVanish(player);
            Bukkit.broadcastMessage(new ChatManager(main).prefix + player.getDisplayName() + " has joined as a spectator.");
        }
    }
}
