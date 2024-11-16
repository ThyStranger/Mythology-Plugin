package com.Stranger.Mythology.GUIs;

import com.Stranger.Mythology.Items.Item;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUIEvents implements Listener {
    com.Stranger.Mythology.GUIs.GUI GUI = new GUI();

//    @EventHandler
//    public void OnPlayerJoin(PlayerJoinEvent e){
////        e.getPlayer().updateInventory();
//    }


//    MenuCommand menu = new MenuCommand();
    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();

        //Clicking on 'open menu', opens menu page
        if (e.getCurrentItem() != null && e.getCurrentItem().equals(Item.items.get("View Menu").itemStack)) {
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


