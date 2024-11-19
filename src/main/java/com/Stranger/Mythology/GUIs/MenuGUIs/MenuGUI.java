package com.Stranger.Mythology.GUIs.MenuGUIs;

import com.Stranger.Mythology.GUIs.GUI;
import com.Stranger.Mythology.GUIs.MenuGUIs.impl.MenuPage;
import com.Stranger.Mythology.Main;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuGUI extends GUI {
    public static HashMap<String, GUI> MenuGUIs = new HashMap<>();

    public MenuGUI(String identifier, Main plugin) {
        super("menu."+identifier,plugin);
    }

    public static void registerMenuGUIs(Main plugin, Player player){
        MenuGUIs.put(player.getName()+"_menu_page",new MenuPage(plugin,player));
        GUI.GUIs.replace("menu",MenuGUIs);
        System.out.println("MenuGUIRegistered");
    }
}
