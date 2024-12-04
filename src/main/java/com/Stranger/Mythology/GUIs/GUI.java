package com.Stranger.Mythology.GUIs;

import com.Stranger.Mythology.GUIs.MenuGUIs.MenuGUI;
import com.Stranger.Mythology.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.*;

public abstract class GUI {
    public static HashMap<String, HashMap<String, GUI>> GUIs=new HashMap<>();

    protected Main plugin;
    protected static final ItemStack PlaceHolderGlass = createItemStack("",Material.GRAY_STAINED_GLASS_PANE);
    protected static final ItemStack CloseButton = createItemStack("Close",Material.BARRIER);
    protected Inventory inventory;
    protected HashMap<Integer,ItemStack> GUISetup=new HashMap<>();
    protected final int NumberOfSlots;
    protected final String Title;

    public GUI(String identifier, Main plugin){
        YamlConfiguration config = plugin.getGUIConfig();
        String GUIPath = "gui_types."+identifier;

        Set<String> ItemKey = config.getConfigurationSection(GUIPath).getKeys(false);
        this.NumberOfSlots = plugin.getGUIConfig().getInt(GUIPath+".number_of_slots");
        this.Title = plugin.getGUIConfig().getString(GUIPath+".title");
        this.plugin = plugin;
        for (String ItemPath:ItemKey){
            System.out.println("GUI Item Path: "+ItemPath);
            if (Objects.equals(ItemPath, "number_of_slots")||Objects.equals(ItemPath,"title")){
                continue;
            }
            registerGUIItem(GUIPath+"."+ItemPath);
        }
        createGUIInventory(this.NumberOfSlots,this.Title);
    }

    public void registerGUIItem(String ItemPath){
        Material material = Material.BARRIER;
        String display_name = this.plugin.getGUIConfig().getString(ItemPath+".display_name","Item Not Found");
        System.out.println(display_name+" button registered in GUI");

        List<String> description = this.plugin.getGUIConfig().getStringList(ItemPath+".description");

        if (this.plugin.getGUIConfig().getString(ItemPath+".material")!=null){
            material = Material.getMaterial(this.plugin.getGUIConfig().getString(ItemPath+".material"));
        }

        int number = this.plugin.getGUIConfig().getInt(ItemPath+".number",1);
        int SlotNumber = this.plugin.getGUIConfig().getInt(ItemPath+".slot_number");
        this.GUISetup.put(SlotNumber,createItemStack(display_name,description,material,number));

        System.out.println("GUI Item material: "+material.toString());
    }

    public void createGUIInventory (int numberOfSlots, String title){
        this.inventory= Bukkit.createInventory(null,numberOfSlots,title);
        for (int i=0;i<numberOfSlots;i++){
            inventory.setItem(i, GUISetup.getOrDefault(i, PlaceHolderGlass));
        }
    }

    public static void registerAllGUIs(){
        GUI.GUIs.put("menu", MenuGUI.MenuGUIs);
    }

    public static ItemStack createItemStack(@NotNull String display_name, @Nullable List<String> description, Material material){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (!display_name.contains("&")){
            display_name = "&f"+display_name;
        }
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',display_name));

        if (description!=null){
            for (String text:description) {
                if (text.contains("&")) {
                    text="&f"+text;
                }
            }
            description.replaceAll(text->ChatColor.translateAlternateColorCodes('&',text));
            meta.setLore(description);
        }
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack createItemStack(@NotNull String display_name, Material material){
        if (!display_name.contains("&")){
            display_name = "&f"+display_name;
        }
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',display_name));
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack createItemStack(@NotNull String display_name, Material material, int number){
        if (!display_name.contains("&")){
            display_name = "&f"+display_name;
        }
        ItemStack item = new ItemStack(material,number);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',display_name));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createItemStack(@NotNull String display_name, @Nullable List<String> description, Material material, int number){
        ItemStack item;
        item = new ItemStack(material, number);


        ItemMeta meta = item.getItemMeta();
        if (!display_name.contains("&")){
            display_name = "&f"+display_name;
        }
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',display_name));

        if (description!=null){
            for (String text:description) {
                if (text.contains("&")) {
                    text="&f"+text;
                }
            }
            description.replaceAll(text->ChatColor.translateAlternateColorCodes('&',text));
            meta.setLore(description);
        }
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack createPlayerHead(@NotNull String display_name, List<String> description, Player player){
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwningPlayer(player);
        if (!display_name.contains("&")){
            display_name = "&f"+display_name;
        }
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',display_name));
        if (description!=null){
            for (String text:description) {
                if (text.contains("&")) {
                    text="&f"+text;
                }
            }
            description.replaceAll(text->ChatColor.translateAlternateColorCodes('&',text));
            meta.setLore(description);
        }
        item.setItemMeta(meta);
        return item;
    }

    public void TriggerButton(InventoryClickEvent e){}

    public Inventory getInventory() {
        return inventory;
    }
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getNumberOfSlots() {
        return NumberOfSlots;
    }
}
