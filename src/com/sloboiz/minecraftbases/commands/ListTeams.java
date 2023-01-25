package com.sloboiz.minecraftbases.commands;

import com.sloboiz.minecraftbases.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListTeams implements CommandExecutor
{

    private Main main;

    public ListTeams(Main main) { this.main = main; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!(sender instanceof Player)) { return  true; }

        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("listTeams"))
        {
            p.sendMessage("Red team:");
            for (Player redMember : this.main.redTeam)
            {
                p.sendMessage(redMember.getName());
            }

            p.sendMessage("Blue team:");
            for (Player redMember : this.main.blueTeam)
            {
                p.sendMessage(redMember.getName());
            }

            p.sendMessage("Unassigned players:");
            for (Player unassignedPlayer : Bukkit.getOnlinePlayers())
            {
                if (!this.main.redTeam.contains(unassignedPlayer) &&
                        !this.main.blueTeam.contains(unassignedPlayer))
                {
                    p.sendMessage(unassignedPlayer.getName());
                }
            }
        }

        return true;
    }
}
