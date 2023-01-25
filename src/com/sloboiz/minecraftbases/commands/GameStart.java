package com.sloboiz.minecraftbases.commands;

import com.sloboiz.minecraftbases.Main;
import com.sloboiz.minecraftbases.countdowns.PreGameTimer;
import com.sloboiz.minecraftbases.managers.ChatManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameStart implements CommandExecutor
{

    private Main main;
    public GameStart(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        Player p = (Player) sender;

        if (!p.hasPermission("game.gameStart"))
        {
            p.sendMessage(new ChatManager(main).permission);
            return true;
        }
        else if (cmd.getName().equalsIgnoreCase("gameStart"))
        {
            if (this.main.redTeam.size() + this.main.blueTeam.size() < Bukkit.getOnlinePlayers().size())
            {
                p.sendMessage(new ChatManager(main).prefix + "Not all players have joined a team.");
                return true;
            }

            if (this.main.redCore == null)
            {
                p.sendMessage(new ChatManager(main).prefix + "Red core not set");
                return true;
            }

            if (this.main.blueCore == null)
            {
                p.sendMessage(new ChatManager(main).prefix + "Blue core not set");
                return true;
            }

            new PreGameTimer(main).startCountdown();
            p.sendMessage(new ChatManager(main).prefix + "You have started the game.");
            return true;
        }
        return true;
    }
}
