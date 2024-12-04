package com.Stranger.Mythology.GUIs.MenuGUIs.impl;

import com.Stranger.Mythology.GUIs.GUI;
import com.Stranger.Mythology.GUIs.MenuGUIs.MenuGUI;
import com.Stranger.Mythology.Main;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Arrays;
import java.util.List;

public class MenuPage extends MenuGUI {
    public MenuPage(Main plugin, Player player) {
        super("menu_page",plugin);
        super.inventory.setItem(13,createPlayerHead(player.getDisplayName(), Arrays.asList(
                "&4Health&r: &f" + String.valueOf(Math.round(5 * player.getHealth())),
                "&aDefence&r: &f" + String.valueOf(10 * player.getAttribute(Attribute.GENERIC_ARMOR).getValue()),
                "&cAttack Strength&r: &f" + String.valueOf(10 * player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue())
        ),player));

        //super.GUISetup.put(22,createItemStack("Path Ascension",Arrays.asList("&bCurrent Path: ","","&o\"朝闻道，朝闻道，夕死可矣\""), Material.ENCHANTED_BOOK));
        //super.GUISetup.put(23,createItemStack("Storage", List.of("A unique portable space gifted by the world"),Material.CHEST));

        //super.createGUIInventory(54,"Your Journey In This World");
        //MenuGUI.MenuGUIs.put(player.getName()+"_MenuPage",this);
    }

    @Override
    public void TriggerButton(InventoryClickEvent e){
        e.setCancelled(true);
        switch(e.getRawSlot()){
//                case 13:
//                case 22:
//                case 23:
            case 49: break;
            default: return;
        }
        e.getWhoClicked().closeInventory();
    }
}
