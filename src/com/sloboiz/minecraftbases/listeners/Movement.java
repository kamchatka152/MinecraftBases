package com.sloboiz.minecraftbases.listeners;

import com.sloboiz.minecraftbases.Main;
import com.sloboiz.minecraftbases.enums.GamePhases;
import com.sloboiz.minecraftbases.enums.GameStates;
import com.sloboiz.minecraftbases.managers.ChatManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Movement implements Listener
{
    private Main main;

    public Movement(Main main) { this.main = main; }

    @EventHandler
    public void onMove(PlayerMoveEvent event)
    {
        if (this.main.getGamePhase() == GamePhases.PEACE)
        {
            Player p = event.getPlayer();
            Location l = event.getTo();
            if (l.distance(main.redCore) <= 50 &&
                    main.blueTeam.contains(p)) {
                event.setCancelled(true);
                p.sendMessage(new ChatManager(main).prefix + "You cannot move closer to the enemy core during peace!");
            }

            if (l.distance(main.blueCore) <= 50 &&
                    main.redTeam.contains(p)) {
                event.setCancelled(true);
                p.sendMessage(new ChatManager(main).prefix + "You cannot move closer to the enemy core during peace!");
            }
        }
    }
}
