package com.sloboiz.minecraftbases.listeners;

import com.sloboiz.minecraftbases.Main;
import com.sloboiz.minecraftbases.enums.GameStates;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class Food implements Listener {

    private Main main;
    public Food(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (main.getGameState() == GameStates.LOBBY || main.getGameState() == GameStates.PREGAME) {
            event.setCancelled(true);
        }
    }
}
