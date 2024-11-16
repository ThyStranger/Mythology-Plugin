package com.Stranger.Mythology;

import com.Stranger.Mythology.GUIs.GUIs;
import com.Stranger.Mythology.Trashcan.ItemSuperClass;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InGameEvents implements Listener {
    GUIs GUI = new GUIs();
    ItemSuperClass itemSuperClass = new ItemSuperClass();

//    @EventHandler
//    public void OnPlayerJoin(PlayerJoinEvent e){
////        e.getPlayer().updateInventory();
//    }


//    MenuCommand menu = new MenuCommand();
    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();

        //Clicking on 'open menu', opens menu page
        if (e.getCurrentItem()!=null&&e.getCurrentItem().equals(itemSuperClass.viewmenu())){
            e.setCancelled(true);
            e.getWhoClicked().openInventory(GUI.MenuGUI(player));
        }

        //CLicking on menu
        if (ChatColor.translateAlternateColorCodes('&',e.getView().getTitle()).equals(ChatColor.BOLD+""+ChatColor.BLACK+"Your Journey on This World")&&e.getCurrentItem()!=null){
            e.setCancelled(true);
            switch(e.getRawSlot()){
//                case 13:
//                case 22:
//                case 23:
                case 49: break;
                default: return;
            }
            player.closeInventory();
        }


    }
}


