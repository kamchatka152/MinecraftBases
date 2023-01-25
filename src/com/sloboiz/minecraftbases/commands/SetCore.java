package com.sloboiz.minecraftbases.commands;

import com.sloboiz.minecraftbases.Main;
import com.sloboiz.minecraftbases.enums.GameStates;
import com.sloboiz.minecraftbases.managers.ChatManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetCore implements CommandExecutor
{

    private Main main;

    public SetCore(Main main) { this.main = main; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!(sender instanceof Player)) { return  true; }
        Player p = (Player) sender;

        // check if game started
        if (this.main.getGameState() != GameStates.LOBBY)
        {
            p.sendMessage("You cannot place the core after the game has started");
            return true;
        }

        // validate arguments
        if (args.length >= 1 &&
            cmd.getName().equalsIgnoreCase("setCore") &&
            (args[0].equalsIgnoreCase("r") ||
             args[0].equalsIgnoreCase("b")))
        {

            // for red core
            if (args[0].equalsIgnoreCase("r"))
            {
                // sender is part of team
                if (this.main.redTeam.contains(p))
                {
                    // opposing core if far enough
                    if (this.main.blueCore == null ||
                        this.main.blueCore.distance(p.getLocation()) >= 100)
                    {
                        // destroy previous core
                        if (this.main.redCore != null)
                        {
                            Bukkit.getWorlds().get(0).getBlockAt(this.main.redCore).setType(Material.AIR);
                        }

                        this.main.redCore = p.getLocation();
                        Bukkit.getWorlds().get(0).getBlockAt(this.main.redCore).setType(Material.EMERALD_BLOCK);
                        this.main.getCustomConfig().set("GameState.Cores.Red.X", this.main.redCore.getBlockX());
                        this.main.getCustomConfig().set("GameState.Cores.Red.Y", this.main.redCore.getBlockY());
                        this.main.getCustomConfig().set("GameState.Cores.Red.Z", this.main.redCore.getBlockZ());
                        this.main.saveCustomConfig();
                        Bukkit.broadcastMessage(new ChatManager(main).prefix + "Red team's core has been place");
                    } else
                    {
                        p.sendMessage("You cannot place the core too close to the opposing team's core");
                    }
                } else
                {
                    p.sendMessage("You cannot place the core of the opposing team");
                }
            }

            if (args[0].equalsIgnoreCase("b"))
            {
                if (this.main.blueTeam.contains(p))
                {
                    if (this.main.redCore == null ||
                        this.main.redCore.distance(p.getLocation()) >= 100)
                    {
                        if (this.main.blueCore != null)
                        {
                            Bukkit.getWorlds().get(0).getBlockAt(this.main.blueCore).setType(Material.AIR);
                        }

                        this.main.blueCore = p.getLocation();
                        Bukkit.getWorlds().get(0).getBlockAt(this.main.blueCore).setType(Material.DIAMOND_BLOCK);
                        this.main.getCustomConfig().set("GameState.Cores.Blue.X", this.main.blueCore.getBlockX());
                        this.main.getCustomConfig().set("GameState.Cores.Blue.Y", this.main.blueCore.getBlockY());
                        this.main.getCustomConfig().set("GameState.Cores.Blue.Z", this.main.blueCore.getBlockZ());
                        this.main.saveCustomConfig();
                        Bukkit.broadcastMessage(new ChatManager(main).prefix + "Blue team's core has been place");
                    } else
                    {
                        p.sendMessage("You cannot place the core too close to the opposing team's core");
                    }
                } else
                {
                    p.sendMessage("You cannot place the core of the opposing team");
                }
            }
        } else
        {
            p.sendMessage("/setCore <r/b>");
        }

        return true;
    }
}
