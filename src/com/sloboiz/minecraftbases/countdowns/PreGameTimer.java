package com.sloboiz.minecraftbases.countdowns;

import com.sloboiz.minecraftbases.Main;
import com.sloboiz.minecraftbases.enums.GamePhases;
import com.sloboiz.minecraftbases.enums.GameStates;
import com.sloboiz.minecraftbases.managers.ChatManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PreGameTimer
{

    private Main main;
    public PreGameTimer(Main main) {
        this.main = main;
    }

    public void startCountdown()
    {
        new BukkitRunnable()
        {

            int number = 30;

            @Override
            public void run()
            {
                if (number > 0)
                {
                    if (number == 30)
                    {
                        main.setGameState(GameStates.PREGAME);
                        Bukkit.broadcastMessage(new ChatManager(main).prefix + "Game starting in 30 seconds.");
                    }
                    if (number == 20)
                    {
                        Bukkit.broadcastMessage(new ChatManager(main).prefix + "Game starting in 20 seconds.");
                    }
                    if (number == 10)
                    {
                        Bukkit.broadcastMessage(new ChatManager(main).prefix + "Game starting in 10 seconds.");
                    }
                    if (number == 5)
                    {
                        Bukkit.broadcastMessage(new ChatManager(main).prefix + "Game starting in 5 seconds.");
                    }
                    if (number == 4)
                    {
                        Bukkit.broadcastMessage(new ChatManager(main).prefix + "Game starting in 4 seconds.");
                    }
                    if (number == 3)
                    {
                        Bukkit.broadcastMessage(new ChatManager(main).prefix + "Game starting in 3 seconds.");
                    }
                    if (number == 2)
                    {
                        Bukkit.broadcastMessage(new ChatManager(main).prefix + "Game starting in 2 seconds.");
                    }
                    if (number == 1)
                    {
                        Bukkit.broadcastMessage(new ChatManager(main).prefix + "Game starting in 1 second.");

                        main.setGameState(GameStates.INGAME);
                        main.setGamePhase(GamePhases.PEACE);

                        for (Player p : main.redTeam)
                        {
                            p.teleport(main.redCore);
                        }

                        for (Player p : main.blueTeam)
                        {
                            p.teleport(main.blueCore);
                        }

                        new PeacePhaseTimer(main).startCountdown();
                        // save cores for multiple session games
                    }
                    number--;
                } else
                {
                    Bukkit.broadcastMessage(new ChatManager(main).prefix + "The game has now started!");
                    cancel();
                }
            }
        }.runTaskTimer(main,20L, 20L);
    }
}
