package com.sloboiz.minecraftbases.listeners;

import com.sloboiz.minecraftbases.Main;
import com.sloboiz.minecraftbases.enums.GameStates;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class Damage implements Listener {

    private Main main;
    public Damage(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity().getType() == EntityType.PLAYER) {
            if (main.getGameState() == GameStates.LOBBY || main.getGameState() == GameStates.PREGAME) {
                event.setCancelled(true);
            }
        }
    }
}
