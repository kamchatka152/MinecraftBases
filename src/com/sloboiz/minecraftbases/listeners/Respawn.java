package com.sloboiz.minecraftbases.listeners;

import com.sloboiz.minecraftbases.Main;
import com.sloboiz.minecraftbases.enums.GameStates;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Respawn implements Listener
{
    private Main main;

    public Respawn(Main main) { this.main = main; }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event)
    {
        // teleport players back to their core
        if (main.getGameState() == GameStates.INGAME)
        {
            Player p = event.getPlayer();

            if (this.main.redTeam.contains(p))
            {
                if (this.main.redFOB == null)
                {
                    p.sendMessage("core");
                    event.setRespawnLocation(this.main.redCore);
                }
                else
                {
                    p.sendMessage("fob");
                    event.setRespawnLocation(this.main.redFOB);
                }
            }

            if (this.main.blueTeam.contains(p))
            {
                if (this.main.blueFOB == null)
                {
                    p.sendMessage("core");
                    event.setRespawnLocation(this.main.blueCore);
                }
                else
                {
                    p.sendMessage("fob");
                    event.setRespawnLocation(this.main.blueFOB);
                }
            }
        }
    }
}
