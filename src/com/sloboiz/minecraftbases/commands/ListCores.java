package com.sloboiz.minecraftbases.commands;

import com.sloboiz.minecraftbases.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListCores implements CommandExecutor
{
    private Main main;

    public ListCores(Main main) { this.main = main; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!(sender instanceof Player)) { return  true; }

        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("listCores"))
        {
            if (this.main.redCore != null)
            {
                p.sendMessage("Red core: " +
                        this.main.redCore.getBlockX() + " " +
                        this.main.redCore.getBlockY() + " " +
                        this.main.redCore.getBlockZ());
            }

            if (this.main.redFOB != null)
            {
                p.sendMessage("Red fob: " +
                        this.main.redFOB.getBlockX() + " " +
                        this.main.redFOB.getBlockY() + " " +
                        this.main.redFOB.getBlockZ());
            }

            if (this.main.blueCore != null)
            {
                p.sendMessage("Blue core: " +
                        this.main.blueCore.getBlockX() + " " +
                        this.main.blueCore.getBlockY() + " " +
                        this.main.blueCore.getBlockZ());
            }

            if (this.main.blueFOB != null)
            {
                p.sendMessage("Blue fob: " +
                        this.main.blueFOB.getBlockX() + " " +
                        this.main.blueFOB.getBlockY() + " " +
                        this.main.blueFOB.getBlockZ());
            }
        }

        return true;
    }
}
