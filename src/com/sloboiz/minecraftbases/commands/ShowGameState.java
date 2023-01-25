package com.sloboiz.minecraftbases.commands;

import com.sloboiz.minecraftbases.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShowGameState implements CommandExecutor
{
    private Main main;

    public ShowGameState(Main main) { this.main = main; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!(sender instanceof Player)) { return  true; }

        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("showGameState"))
        {
            p.sendMessage("Game state: " + this.main.getGameState());
            p.sendMessage("Game phase: " + this.main.getGamePhase());
        }

        return true;
    }
}