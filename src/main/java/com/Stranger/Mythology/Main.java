package com.Stranger.Mythology;

import com.Stranger.Mythology.Commands.MenuCommand;
import com.Stranger.Mythology.Commands.PrayCommand;
import com.Stranger.Mythology.Commands.SpawnCommand;
import com.Stranger.Mythology.Entities.Entities;
import com.Stranger.Mythology.Entities.EntityEvents;
import com.Stranger.Mythology.GUIs.GUI;
import com.Stranger.Mythology.GUIs.GUIEvents;
import com.Stranger.Mythology.Items.Item;
import com.Stranger.Mythology.Items.ItemEvents;
import com.Stranger.Mythology.Players.PlayerConnectionListener;
import com.Stranger.Mythology.Players.Database.Database;



import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.SQLException;

import static com.Stranger.Mythology.Items.Item.items;

public final class Main extends JavaPlugin implements Listener {
    private Database database;
    //private PlayerManagerOld playerManager = new PlayerManagerOld();
    private YamlConfiguration ItemConfig;
    private YamlConfiguration GUIConfig;
    private YamlConfiguration EntityConfig;

    public Database getDatabase() {
        return database;
    }
//    public PlayerManagerOld getPlayerManager(){
//        return playerManager;
//    }
    public YamlConfiguration getItemConfig() { return this.ItemConfig; }
    public YamlConfiguration getGUIConfig() {
        return GUIConfig;
    }
    public YamlConfiguration getEntityConfig() {
        return EntityConfig;
    }

    @Override
    public void onEnable() {
        //Plugin startup logic
        //Initiate YML files
        //System.out.println(getDataFolder());

        //System.out.println("File Path: "+getDataFolder().getPath());

        //Initiate file, but how to read from the resource folder?

        File ItemConfigFile = new File(getDataFolder(), "ItemConfig.yml");
        System.out.println("ItemConfigFile exist: "+ItemConfigFile.exists());
        this.ItemConfig = YamlConfiguration.loadConfiguration(ItemConfigFile);

        File GUIConfigFile = new File(getDataFolder(),"GUIConfig.yml");
        System.out.println("GUIConfigFile exist: "+GUIConfigFile.exists());
        this.GUIConfig = YamlConfiguration.loadConfiguration(GUIConfigFile);

        File EntityConfigFile = new File(getDataFolder(),"EntityConfig.yml");
        System.out.println("EntityConfig.yml exist: "+EntityConfigFile);
        this.EntityConfig = YamlConfiguration.loadConfiguration(EntityConfigFile);

        //Initiate Database
        database = new Database();
        try {
            database.connect();
//            PreparedStatement ps = database.getConnection().prepareStatement("INSERT INTO player (ID,UUID,PATH,PROGRESS,SPIRIT) VALUES (?,?,?,?,?);");
//            ps.setInt(1, 0);
//            ps.setString(2, "");
//            ps.setString(3, "");
//            ps.setInt(4, 0);
//            ps.setInt(5, 0);
//            ps.executeUpdate();
//
//
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Database Connected: "+database.isConnected());

        GUI.registerAllGUIs();

        Bukkit.getPluginManager().registerEvents(new PlayerConnectionListener(this), this);
        Bukkit.getPluginManager().registerEvents(new GUIEvents(this), this);
        Bukkit.getPluginManager().registerEvents(new ItemEvents(), this);
        Bukkit.getPluginManager().registerEvents(new EntityEvents(),this);

        getCommand("pray").setExecutor(new PrayCommand());
        getCommand("menu").setExecutor(new MenuCommand(this));
        getCommand("summon").setExecutor(new SpawnCommand());

        Item.AddToListAll(this);
//        System.out.println("Printing Registered Items");
//        for (String key:items.keySet()){
//            System.out.println("Item Registered: "+key);
//        }

        Entities.registerAllEntities(this);

        System.out.println("All items registered:"+!items.isEmpty());
        System.out.println("Mythology is Enabled");
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        database.disconnect();
        System.out.println("Mythology is Disabled");
    }


}
