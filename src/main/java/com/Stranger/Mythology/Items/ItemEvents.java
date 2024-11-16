package com.Stranger.Mythology.Items;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

import static com.Stranger.Mythology.Items.Item.items;


public class ItemEvents implements Listener {
    @EventHandler
    public void OnPlayerInteract(PlayerInteractEvent e) {
//       System.out.println(e.getAction());
//       System.out.println(e.getItem());
//       System.out.println("hi\nhehe");
//       System.out.println(e.getItem().getItemMeta().getItemName());
//       System.out.println("border");
//       System.out.println(Items.fractured_holy_sword());

        //System.out.println(item);
//       if (item.getItemMeta().getItemName().equals(Items.fractured_holy_sword().getItemMeta().getItemName())) {
//           if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
////               System.out.println("We're here...");
////               e.setCancelled(true);
//               Items.fractured_holy_sword_ability(e.getPlayer());
//           }
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR)||e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (e.getItem() != null && e.getItem().hasItemMeta()) {
                for (String key : items.keySet()) {
                    if (e.getItem().getItemMeta().getItemName().equals(key)) {
                        items.get(key).activateAbility(e.getPlayer());
                        return;
                    }
                }
            }
        }
    }
}
