package com.sloboiz.minecraftbases.commands;

import com.sloboiz.minecraftbases.Main;
import com.sloboiz.minecraftbases.enums.GameStates;
import com.sloboiz.minecraftbases.managers.ChatManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetTeam implements CommandExecutor {

    private Main main;

    public SetTeam(Main main) { this.main = main; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) { return  true; }
        Player p = (Player) sender;

        if (this.main.getGameState() != GameStates.LOBBY) {
            p.sendMessage("You cannot change teams after the game has started");
            return true;
        }

        if (args.length >= 1 &&
            cmd.getName().equalsIgnoreCase("setTeam") &&
            (args[0].equalsIgnoreCase("r") ||
            args[0].equalsIgnoreCase("b"))) {
                if (args[0].equalsIgnoreCase("r")) {
                    this.removePlayerFromTeams(p);
                    this.main.redTeam.add(p);

                    //confirm to all players
                    Bukkit.broadcastMessage(new ChatManager(main).prefix + p.getName() + " has joined the red team");
                }

                if (args[0].equalsIgnoreCase("b")) {
                    this.removePlayerFromTeams(p);
                    this.main.blueTeam.add(p);

                    // confirm to all players
                    Bukkit.broadcastMessage(new ChatManager(main).prefix + p.getName() + " has joined the blue team");
                }
            } else {
                p.sendMessage("/setTeam <r/b>");
            }

        return true;
    }

    private void removePlayerFromTeams(Player player) {
        this.main.redTeam.remove(player);
        this.main.blueTeam.remove(player);
    }
}
