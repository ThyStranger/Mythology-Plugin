package com.Stranger.Mythology.Commands;

import com.Stranger.Mythology.Items.Item;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static com.Stranger.Mythology.Items.Item.items;


public class PrayCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender Sender, Command command, String s, String[] strings) {
        if (Sender instanceof Player player){
            for (Item item:items.values()){
                player.getInventory().addItem(item.itemStack);
            }
//            System.out.println(item);
//            System.out.println(item.getAmount());
//            player.getInventory().addItem(item);
//            player.getInventory().addItem(itemSuperClassWest.jin_lan_jia_sha_FangZao());
            //System.out.println(player.getInventory().getItemInMainHand());
        }
        return false;
    }
}
