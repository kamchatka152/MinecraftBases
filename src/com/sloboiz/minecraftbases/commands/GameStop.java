package com.sloboiz.minecraftbases.commands;

import com.sloboiz.minecraftbases.Main;
import com.sloboiz.minecraftbases.enums.GameStates;
import com.sloboiz.minecraftbases.managers.ChatManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameStop implements CommandExecutor {
    private Main main;

    public GameStop(Main main) { this.main = main; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!(sender instanceof Player)) { return  true; }

        Player p = (Player) sender;

        if (!p.hasPermission("game.gameStop"))
        {
            p.sendMessage(new ChatManager(main).permission);
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("gameStop"))
        {
            this.main.getCustomConfig().set("GameState.Phase.TimeLeft", this.main.getBuildPhaseTime());
            this.main.saveCustomConfig();
            Bukkit.broadcastMessage(new ChatManager(main).prefix + "Game stopped.");
            this.main.setGameState(GameStates.LOBBY);
        }

        return true;
    }
}
