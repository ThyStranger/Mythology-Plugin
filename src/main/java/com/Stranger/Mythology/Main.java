package com.Stranger.Mythology;

import com.Stranger.Mythology.Commands.MenuCommand;
import com.Stranger.Mythology.Commands.PrayCommand;
import com.Stranger.Mythology.Items.Item;
import com.Stranger.Mythology.Items.ItemEvents;
import com.Stranger.Mythology.PlayerDatabase.ConnectionListener;
import com.Stranger.Mythology.PlayerDatabase.Database;
import com.Stranger.Mythology.PlayerDatabase.PlayerManager;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.SQLException;

import static com.Stranger.Mythology.Items.Item.items;

public final class Main extends JavaPlugin implements Listener {
    private Database database;
    private PlayerManager playerManager = new PlayerManager();
    private YamlConfiguration config;

    public Database getDatabase() {
        return database;
    }
    public PlayerManager getPlayerManager(){
        return playerManager;
    }
    public YamlConfiguration getConfigFile() { return this.config; }

    @Override
    public void onEnable() {
        System.out.println("Colour"+ChatColor.RED.getChar());
        System.out.println("Colour"+ChatColor.DARK_BLUE.getChar());
        //Plugin startup logic
        //Initiate YML files
        //System.out.println(getDataFolder());

        //System.out.println("File Path: "+getDataFolder().getPath());

        //Initiate file, but how to read from the resource folder?

        File configFile = new File(getDataFolder(), "config.yml");
        System.out.println("File exist: "+configFile.exists());

        this.config = YamlConfiguration.loadConfiguration(configFile);

//        File itemFormatFile = new File(getDataFolder(), "config.yml");
//        try {
//            config = FileManager.initiateFile(getDataFolder(), "config.yml");
//        } catch (IOException e) {
//            System.out.println("config.yml cannot be loaded :(");
//            e.printStackTrace();
//        }
//        this.config = YamlConfiguration.loadConfiguration(itemFormatFile);
//        //modifyItemFormatFile.set("",1);
//
//        try {
//            this.config.save(itemFormatFile);
//        } catch (IOException e) {
//            System.out.println("configed_config.yml cannot be saved :(");
//        }

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
        Bukkit.getPluginManager().registerEvents(new ConnectionListener(this), this);
        Bukkit.getPluginManager().registerEvents(new InGameEvents(), this);
        Bukkit.getPluginManager().registerEvents(new ItemEvents(), this);
        getCommand("pray").setExecutor(new PrayCommand());
        getCommand("menu").setExecutor(new MenuCommand());

        Item.AddToListAll(this);
//        System.out.println("Printing Registered Items");
//        for (String key:items.keySet()){
//            System.out.println("Item Registered: "+key);
//        }
        System.out.println("All items registered:"+!items.isEmpty());


        //Entities
//        ArmorStand Angel=(ArmorStand) Bukkit.getWorld("world").spawnEntity(new Location(Bukkit.getWorld("world"),0,75,70), EntityType.ARMOR_STAND);
//
//        ItemStack fractured_holy_sword = new ItemStack(Material.GOLDEN_SWORD,1);
//        obtainItemMeta meta = fractured_holy_sword.getItemMeta();
//
//        meta.setItemName("Holy Sword");
//        meta.setUnbreakable(true);
//        meta.setLore(Collections.singletonList("The Holy Sword used by Angels in the ancient war. Seems to process the ability to communicate with another dimension?"));
//        meta.addEnchant(Enchantment.SHARPNESS,5,true);
//        meta.addEnchant(Enchantment.SWEEPING_EDGE,5,true);
//        fractured_holy_sword.setItemMeta(meta);
        System.out.println("Mythology is Enabled");
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        database.disconnect();
    }


}
