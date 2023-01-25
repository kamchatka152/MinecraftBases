package com.sloboiz.minecraftbases;

import com.sloboiz.minecraftbases.commands.*;
import com.sloboiz.minecraftbases.enums.GamePhases;
import com.sloboiz.minecraftbases.enums.GameStates;
import com.sloboiz.minecraftbases.items.ItemManager;
import com.sloboiz.minecraftbases.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends JavaPlugin {

    private long BuildPhaseTime;

    public long getBuildPhaseTime() { return BuildPhaseTime; }

    public void setBuildPhaseTime(long buildPhaseTime) { BuildPhaseTime = buildPhaseTime; }

    private File customConfigFile;
    private FileConfiguration customConfig;

    public FileConfiguration getCustomConfig() { return this.customConfig; }

    public void saveCustomConfig()
    {
        try {
            this.customConfig.save(customConfigFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private GameStates gameState;
    public GameStates getGameState() { return gameState; }
    public void setGameState(GameStates gameState) { this.gameState = gameState; }

    private GamePhases gamePhase;
    public GamePhases getGamePhase() { return gamePhase; }
    public void setGamePhase(GamePhases gamePhase) { this.gamePhase = gamePhase; }

    public Location redCore = null;
    public Location blueCore = null;

    public Location redFOB = null;

    public Location blueFOB = null;

    public ArrayList<Player> redTeam = new ArrayList<>();
    public ArrayList<Player> blueTeam = new ArrayList<>();

    public ArrayList<Player> spectating = new ArrayList<>();
    public ArrayList<Player> vanished = new ArrayList<>();

    @Override
    public void onEnable() {
        //handle saved state
        createCustomConfig();

        if (this.getCustomConfig().getDouble("GameState.Cores.Red.X") != 0 ||
            this.getCustomConfig().getDouble("GameState.Cores.Red.Y") != 0 ||
            this.getCustomConfig().getDouble("GameState.Cores.Red.Z") != 0)
        {
            this.redCore = new Location(Bukkit.getWorlds().get(0),
                    this.getCustomConfig().getDouble("GameState.Cores.Red.X"),
                    this.getCustomConfig().getDouble("GameState.Cores.Red.Y"),
                    this.getCustomConfig().getDouble("GameState.Cores.Red.Z"));
            Bukkit.getWorlds().get(0).getBlockAt(this.redCore).setType(Material.EMERALD_BLOCK);
        }

        if (this.getCustomConfig().getDouble("GameState.Cores.Blue.X") != 0 ||
            this.getCustomConfig().getDouble("GameState.Cores.Blue.Y") != 0 ||
            this.getCustomConfig().getDouble("GameState.Cores.Blue.Z") != 0)
        {
            this.blueCore = new Location(Bukkit.getWorlds().get(0),
                    this.getCustomConfig().getDouble("GameState.Cores.Blue.X"),
                    this.getCustomConfig().getDouble("GameState.Cores.Blue.Y"),
                    this.getCustomConfig().getDouble("GameState.Cores.Blue.Z"));
            Bukkit.getWorlds().get(0).getBlockAt(this.blueCore).setType(Material.DIAMOND_BLOCK);
        }

        this.setGamePhase(GamePhases.valueOf(this.getCustomConfig().getString("GameState.Phase.Name")));
        this.setBuildPhaseTime(this.getCustomConfig().getLong("GameState.Phase.TimeLeft"));

        registerCommands();
        registerEvents();
        setGameState(GameStates.LOBBY);
        setGamePhase(GamePhases.UNKNOWN);
        ItemManager.init();
    }

    @Override
    public void onDisable()
    {

    }

    private void createCustomConfig()
    {
        customConfigFile = new File(getDataFolder(), "gameState.yml");
        if (!customConfigFile.exists())
        {
            customConfigFile.getParentFile().mkdirs();
            saveResource("gameState.yml", false);
        }

        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void registerCommands()
    {
        getCommand("vanish").setExecutor(new Vanish(this));
        getCommand("gameStart").setExecutor(new GameStart(this));
        getCommand("gameStop").setExecutor(new GameStop(this));
        getCommand("setTeam").setExecutor(new SetTeam(this));
        getCommand("listTeams").setExecutor(new ListTeams(this));
        getCommand("setCore").setExecutor(new SetCore(this));
        getCommand("listCores").setExecutor(new ListCores(this));
        getCommand("showGameState").setExecutor(new ShowGameState(this));
    }

    private void registerEvents()
    {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new Join(this), this);
        pm.registerEvents(new Build(this), this);
        pm.registerEvents(new Damage(this), this);
        pm.registerEvents(new Food(this), this);
        pm.registerEvents(new Respawn(this), this);
        pm.registerEvents(new Movement(this), this);
    }
}
