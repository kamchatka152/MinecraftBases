package com.sloboiz.minecraftbases.listeners;

import com.sloboiz.minecraftbases.Main;
import com.sloboiz.minecraftbases.enums.GameStates;
import com.sloboiz.minecraftbases.items.ItemManager;
import com.sloboiz.minecraftbases.managers.ChatManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Build implements Listener
{

    private Main main;
    public Build(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event)
    {
        Player p = event.getPlayer();
        if (main.getGameState() == GameStates.LOBBY || main.getGameState() == GameStates.PREGAME)
        {
            event.setCancelled(true);
            p.sendMessage(new ChatManager(main).prefix + "You cannot build before the game!");
        }

        if (main.getGameState() == GameStates.INGAME)
        {
            Block b = event.getBlock();

            if (this.main.redFOB != null &&
                    b.getLocation().getBlockX() == this.main.redFOB.getBlockX() &&
                    b.getLocation().getBlockY() == this.main.redFOB.getBlockY() &&
                    b.getLocation().getBlockZ() == this.main.redFOB.getBlockZ())
            {
                event.setCancelled(true);
                b.setType(Material.AIR);
                event.getBlock().getWorld().dropItemNaturally(this.main.redFOB, ItemManager.forwardOperatingBase);

                this.main.redFOB = null;
                p.sendMessage("red " + this.main.redFOB.toString());
            }

            if (this.main.blueFOB != null &&
                    b.getLocation().getBlockX() == this.main.blueFOB.getBlockX() &&
                    b.getLocation().getBlockY() == this.main.blueFOB.getBlockY() &&
                    b.getLocation().getBlockZ() == this.main.blueFOB.getBlockZ())
            {
                event.setCancelled(true);
                b.setType(Material.AIR);
                event.getBlock().getWorld().dropItemNaturally(this.main.blueFOB, ItemManager.forwardOperatingBase);

                this.main.blueFOB = null;
                p.sendMessage("blue " + this.main.blueFOB.toString());
            }

            if (b.getType() == Material.IRON_ORE)
            {
                ItemStack drop = new ItemStack(Material.RAW_IRON, 2);
                event.getBlock().getWorld().dropItemNaturally(b.getLocation(), drop);
            }

            if (b.getType() == Material.DIAMOND_ORE)
            {
                ItemStack drop = new ItemStack(Material.DIAMOND, 2);
                event.getBlock().getWorld().dropItemNaturally(b.getLocation(), drop);
            }

            if (p.getInventory().getItemInMainHand().getType() != Material.AIR)
            {
                if (b.getLocation().distance(main.redCore) <= 30 &&
                        main.blueTeam.contains(p))
                {
                    event.setCancelled(true);
                    p.sendMessage(new ChatManager(main).prefix + "You cannot destroy in the opposing protection zone!");
                }

                if (b.getLocation().distance(main.blueCore) <= 30 &&
                        main.redTeam.contains(p))
                {
                    event.setCancelled(true);
                    p.sendMessage(new ChatManager(main).prefix + "You cannot destroy in the opposing protection zone!");
                }
            }
            else
            {
                if (b.getLocation().getBlockX() == this.main.redCore.getBlockX() &&
                        b.getLocation().getBlockY() == this.main.redCore.getBlockY() &&
                        b.getLocation().getBlockZ() == this.main.redCore.getBlockZ())
                {
                    Bukkit.broadcastMessage(new ChatManager(main).prefix + "Red core has been destroyed!");
                    Bukkit.broadcastMessage(new ChatManager(main).prefix + "Blue team wins!");
                    this.main.setGameState(GameStates.LOBBY);
                }

                if (b.getLocation().getBlockX() == this.main.blueCore.getBlockX() &&
                        b.getLocation().getBlockY() == this.main.blueCore.getBlockY() &&
                        b.getLocation().getBlockZ() == this.main.blueCore.getBlockZ())
                {
                    Bukkit.broadcastMessage(new ChatManager(main).prefix + "Blue core has been destroyed!");
                    Bukkit.broadcastMessage(new ChatManager(main).prefix + "Red team wins!");
                    this.main.setGameState(GameStates.LOBBY);
                }
            }
        }
    }

    @EventHandler
    public void onBlockExplosion(BlockExplodeEvent event)
    {
        List<Block> blocks = event.blockList();

        for (Block b: blocks)
        {
            if (b.getLocation().getBlockX() == this.main.redCore.getBlockX() &&
                    b.getLocation().getBlockY() == this.main.redCore.getBlockY() &&
                    b.getLocation().getBlockZ() == this.main.redCore.getBlockZ())
            {
                Bukkit.broadcastMessage(new ChatManager(main).prefix + "Red core has been destroyed!");
                Bukkit.broadcastMessage(new ChatManager(main).prefix + "Blue team wins!");
                this.main.setGameState(GameStates.LOBBY);
            }

            if (b.getLocation().getBlockX() == this.main.blueCore.getBlockX() &&
                    b.getLocation().getBlockY() == this.main.blueCore.getBlockY() &&
                    b.getLocation().getBlockZ() == this.main.blueCore.getBlockZ())
            {
                Bukkit.broadcastMessage(new ChatManager(main).prefix + "Blue core has been destroyed!");
                Bukkit.broadcastMessage(new ChatManager(main).prefix + "Red team wins!");
                this.main.setGameState(GameStates.LOBBY);
            }

            if (b.getLocation().getBlockX() == this.main.redFOB.getBlockX() &&
                    b.getLocation().getBlockY() == this.main.redFOB.getBlockY() &&
                    b.getLocation().getBlockZ() == this.main.redFOB.getBlockZ())
            {
                event.setCancelled(true);
                b.setType(Material.AIR);
                event.getBlock().getWorld().dropItemNaturally(this.main.redFOB, ItemManager.forwardOperatingBase);

                this.main.redFOB = null;
            }

            if (b.getLocation().getBlockX() == this.main.blueFOB.getBlockX() &&
                    b.getLocation().getBlockY() == this.main.blueFOB.getBlockY() &&
                    b.getLocation().getBlockZ() == this.main.blueFOB.getBlockZ())
            {
                event.setCancelled(true);
                b.setType(Material.AIR);
                event.getBlock().getWorld().dropItemNaturally(this.main.blueFOB, ItemManager.forwardOperatingBase);

                this.main.blueFOB = null;
            }
        }
    }

    @EventHandler
    public void onEntityExplosion(EntityExplodeEvent event)
    {
        List<Block> blocks = event.blockList();

        for (Block b: blocks)
        {
            if (b.getLocation().getBlockX() == this.main.redCore.getBlockX() &&
                    b.getLocation().getBlockY() == this.main.redCore.getBlockY() &&
                    b.getLocation().getBlockZ() == this.main.redCore.getBlockZ())
            {
                Bukkit.broadcastMessage(new ChatManager(main).prefix + "Red core has been destroyed!");
                Bukkit.broadcastMessage(new ChatManager(main).prefix + "Blue team wins!");
                this.main.setGameState(GameStates.LOBBY);
            }

            if (b.getLocation().getBlockX() == this.main.blueCore.getBlockX() &&
                    b.getLocation().getBlockY() == this.main.blueCore.getBlockY() &&
                    b.getLocation().getBlockZ() == this.main.blueCore.getBlockZ())
            {
                Bukkit.broadcastMessage(new ChatManager(main).prefix + "Blue core has been destroyed!");
                Bukkit.broadcastMessage(new ChatManager(main).prefix + "Red team wins!");
                this.main.setGameState(GameStates.LOBBY);
            }

            if (b.getLocation().getBlockX() == this.main.redFOB.getBlockX() &&
                    b.getLocation().getBlockY() == this.main.redFOB.getBlockY() &&
                    b.getLocation().getBlockZ() == this.main.redFOB.getBlockZ())
            {
                event.setCancelled(true);
                b.setType(Material.AIR);
                b.getWorld().dropItemNaturally(this.main.redFOB, ItemManager.forwardOperatingBase);

                this.main.redFOB = null;
            }

            if (b.getLocation().getBlockX() == this.main.blueFOB.getBlockX() &&
                    b.getLocation().getBlockY() == this.main.blueFOB.getBlockY() &&
                    b.getLocation().getBlockZ() == this.main.blueFOB.getBlockZ())
            {
                event.setCancelled(true);
                b.setType(Material.AIR);
                b.getWorld().dropItemNaturally(this.main.blueFOB, ItemManager.forwardOperatingBase);

                this.main.blueFOB = null;
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event)
    {
        Player p = event.getPlayer();
        if (main.getGameState() == GameStates.LOBBY || main.getGameState() == GameStates.PREGAME)
        {
            event.setCancelled(true);
            p.sendMessage(new ChatManager(main).prefix + "You cannot build before the game!");
        }

        if (main.getGameState() == GameStates.INGAME)
        {
            Block b = event.getBlockPlaced();

            if (b.getType() != Material.TNT &&
                b.getType() != Material.LAVA_BUCKET &&
                b.getType() != Material.FIRE &&
                !event.getItemInHand().getItemMeta().equals(ItemManager.assaultLadder.getItemMeta()))
            {
                if (b.getLocation().distance(main.redCore) <= 30 &&
                        main.blueTeam.contains(p))
                {
                    event.setCancelled(true);
                    p.sendMessage(new ChatManager(main).prefix + "You cannot place this block in the opposing protection zone!");
                    return;
                }

                if (b.getLocation().distance(main.blueCore) <= 30 &&
                        main.redTeam.contains(p))
                {
                    event.setCancelled(true);
                    p.sendMessage(new ChatManager(main).prefix + "You cannot place this block in the opposing protection zone!");
                    return;
                }

                if (event.getItemInHand().getItemMeta().equals(ItemManager.forwardOperatingBase.getItemMeta()))
                {
                    if (this.main.redTeam.contains(p))
                    {
                        b.setType(Material.RED_BANNER);
                        this.main.redFOB = b.getLocation();
                        p.sendMessage(this.main.redFOB.getBlockX() + " " +
                                this.main.redFOB.getBlockY()+ " " +
                                this.main.redFOB.getBlockZ());
                    }

                    if (this.main.blueTeam.contains(p))
                    {
                        b.setType(Material.BLUE_BANNER);
                        this.main.blueFOB = b.getLocation();
                        p.sendMessage(this.main.blueFOB.getBlockX() + " " +
                                this.main.blueFOB.getBlockY()+ " " +
                                this.main.blueFOB.getBlockZ());
                    }
                }
            }
        }
    }
}
