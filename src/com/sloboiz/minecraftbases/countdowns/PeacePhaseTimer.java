package com.sloboiz.minecraftbases.countdowns;

import com.sloboiz.minecraftbases.Main;
import com.sloboiz.minecraftbases.enums.GamePhases;
import com.sloboiz.minecraftbases.enums.GameStates;
import com.sloboiz.minecraftbases.listeners.Movement;
import com.sloboiz.minecraftbases.managers.ChatManager;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PeacePhaseTimer
{
    private Main main;
    public PeacePhaseTimer(Main main) {
        this.main = main;
    }

    public void startCountdown()
    {
        new BukkitRunnable()
        {
            long seconds = main.getBuildPhaseTime();

            @Override
            public void run()
            {
                if (seconds % 60 == 0)
                {
                    Bukkit.broadcastMessage("60 seconds have passed.");
                    main.getCustomConfig().set("GameState.Phase.TimeLeft", seconds);
                }

                if (seconds > 0)
                {
                    if (seconds == 180)
                    {
                        Bukkit.broadcastMessage(new ChatManager(main).prefix + "War starting in 3 minutes");
                    }

                    if (seconds == 120)
                    {
                        Bukkit.broadcastMessage(new ChatManager(main).prefix + "War starting in 2 minute");
                    }

                    if (seconds == 60)
                    {
                        Bukkit.broadcastMessage(new ChatManager(main).prefix + "War starting in 1 minute");
                    }

                    if (seconds == 30)
                    {
                        Bukkit.broadcastMessage(new ChatManager(main).prefix + "War starting in 30 seconds.");
                    }
                    if (seconds == 20)
                    {
                        Bukkit.broadcastMessage(new ChatManager(main).prefix + "War starting in 20 seconds.");
                    }
                    if (seconds == 10)
                    {
                        Bukkit.broadcastMessage(new ChatManager(main).prefix + "War starting in 10 seconds.");
                    }
                    if (seconds == 5)
                    {
                        Bukkit.broadcastMessage(new ChatManager(main).prefix + "War starting in 5 seconds.");
                    }
                    if (seconds == 4)
                    {
                        Bukkit.broadcastMessage(new ChatManager(main).prefix + "War starting in 4 seconds.");
                    }
                    if (seconds == 3)
                    {
                        Bukkit.broadcastMessage(new ChatManager(main).prefix + "War starting in 3 seconds.");
                    }
                    if (seconds == 2)
                    {
                        Bukkit.broadcastMessage(new ChatManager(main).prefix + "War starting in 2 seconds.");
                    }
                    if (seconds == 1)
                    {
                        Bukkit.broadcastMessage(new ChatManager(main).prefix + "War starting in 1 second.");
                    }

                    seconds--;
                }
                else
                {
                    main.setGamePhase(GamePhases.WAR);
                    PlayerMoveEvent.getHandlerList().unregister(main);
                    main.getCustomConfig().set("GameState.Phase.TimeLeft", 0);
                    main.getCustomConfig().set("GameState.Phase.Name", GamePhases.WAR.toString());
                    main.saveCustomConfig();
                    Bukkit.broadcastMessage(new ChatManager(main).prefix + "War phase has started.");
                    cancel();
                }
            }
        }.runTaskTimer(main,20L, 20L);
    }
}
