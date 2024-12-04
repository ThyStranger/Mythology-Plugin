package com.Stranger.Mythology.Misc;

import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract {
    public static boolean isRightClick(PlayerInteractEvent e){
        return e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK);
    }
}
