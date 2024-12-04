package com.Stranger.Mythology.Items;

import com.Stranger.Mythology.Misc.PlayerInteract;
import com.Stranger.Mythology.Players.CooldownManager;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;

import static com.Stranger.Mythology.Items.Item.items;


public class ItemEvents implements Listener {
    @EventHandler
    public void OnPlayerInteract(PlayerInteractEvent e) {

        if (e.getItem() != null && e.getItem().getItemMeta() != null) {
            for (String key : items.keySet()) {

                //Check if item in hand is a custom item
                if (e.getItem().getItemMeta().getItemName().equals(items.get(key).name)) {
                    //System.out.println("Trigger: "+items.get(key).abilityTrigger);

                    //Check if it is right click
                    if (PlayerInteract.isRightClick(e)){
                        System.out.println("Right Clicked");
                        if (items.get(key).abilityTrigger.equals("RIGHT CLICK")){

                            UUID uuid = e.getPlayer().getUniqueId();
                            String identifier = items.get(key).identifier;

                            System.out.println("Is in Cooldown: "+CooldownManager.isInCooldown(uuid,identifier));
                            //Check if the item has a cooldown
                            if (!CooldownManager.isInCooldown(uuid,identifier)) {
                                items.get(key).activateAbility(e);
                                items.get(key).addCooldown(uuid);
                            }
                            else{
                                e.getPlayer().sendMessage(ChatColor.RED+"This item is on cooldown for "+CooldownManager.durationToString(CooldownManager.getRemainingCooldown(uuid,identifier)) + "!");
                            }
                            return;
                        }
                    }
//                    items.get(key).activateAbility(e);
                    return;
                }
            }
        }
    }
}
