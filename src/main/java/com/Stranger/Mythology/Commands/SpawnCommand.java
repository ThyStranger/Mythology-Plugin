package com.Stranger.Mythology.Commands;

import com.Stranger.Mythology.Entities.Entities;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            if (strings==null||strings.length==0){
                player.sendMessage(ChatColor.RED+"Specify the entity to be summoned!");
            }
            else if (strings.length == 1) {
                for (HashMap<String, Entities> entityType : Entities.entities.values()) {
                    if (entityType.containsKey(strings[0])) {
                        entityType.get(strings[0]).summon(player.getEyeLocation());
                        break;
                    }
                    else{
                        player.sendMessage(ChatColor.RED+"Entity does not exist");
                    }
                }
            }
        }
        return false;
    }
}
