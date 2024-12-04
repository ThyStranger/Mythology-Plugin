package com.Stranger.Mythology.GUIs;

import com.Stranger.Mythology.GUIs.MenuGUIs.MenuGUI;
import com.Stranger.Mythology.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;


import java.util.HashMap;

public class GUIEvents implements Listener {
    //GUIOld GUI = new GUIOld();
    private Main plugin;

    public GUIEvents(Main plugin){
        this.plugin = plugin;
    }


//    MenuCommand menu = new MenuCommand();
    @EventHandler
    public void onClick(InventoryClickEvent e){
//        Player player = (Player) e.getWhoClicked();
//
//        //Clicking on 'open menu', opens menu page
//        if (e.getCurrentItem() != null && e.getCurrentItem().equals(Item.items.get("View Menu").craftItemStack())) {
//            e.setCancelled(true);
//            e.getWhoClicked().openInventory(GUI.MenuGUI(player));
//        }
//        //CLicking on menu
//        if (ChatColor.translateAlternateColorCodes('&',e.getView().getTitle()).equals(ChatColor.BOLD+""+ChatColor.BLACK+"Your Journey on This World")&&e.getCurrentItem()!=null){
//            e.setCancelled(true);
//            switch(e.getRawSlot()){
////                case 13:
////                case 22:
////                case 23:
//                case 49: break;
//                default: return;
//            }
//            player.closeInventory();
//        }
        for (HashMap<String,GUI> GUISet:GUI.GUIs.values()){
            for (GUI theGUI:GUISet.values()){
                if (theGUI.getInventory()==e.getInventory())
                    theGUI.TriggerButton(e);
            }
        }
    }


}


