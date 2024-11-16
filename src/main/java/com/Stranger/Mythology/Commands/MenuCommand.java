package com.Stranger.Mythology.Commands;

import com.Stranger.Mythology.GUIs.GUIs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MenuCommand implements CommandExecutor {
//    private static obtainItemMeta getGUIMeta(obtainItemMeta meta, String display_name, String lore){
//        if (lore!=null) { meta.setLore(List.of(lore.split("\n"))); }
//        if (display_name!=null&&display_name.contains("&")) {
//            String translated = ChatColor.translateAlternateColorCodes('&', display_name);
//            if (display_name.equals(translated)) {
//                meta.setDisplayName(display_name);
//                return meta;
//            }
//        }
//        else { meta.setDisplayName(ChatColor.WHITE+display_name); }
//        return meta;
//    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player player){
//            Inventory menu = Bukkit.createInventory(player,54, ChatColor.BOLD+""+ChatColor.BLACK+"Your Journey on This World");
            Inventory menu = new GUIs().MenuGUI(player);
//            //profile
//            ItemStack profile = new ItemStack(Material.PLAYER_HEAD);
//            SkullMeta profile_meta = (SkullMeta) profile.getItemMeta();
//            profile_meta.setOwningPlayer(player);
//            profile_meta.setDisplayName(ChatColor.WHITE+"Your Profile");
//            profile_meta.setLore(Arrays.asList(
//                    ChatColor.DARK_RED+"Health"+ChatColor.RESET+": "+ChatColor.WHITE+String.valueOf(Math.round(5*player.getHealth())),
//                    ChatColor.GREEN+"Defence"+ChatColor.RESET+": "+ChatColor.WHITE+String.valueOf(10*player.getAttribute(Attribute.GENERIC_ARMOR).getValue()),
//                    ChatColor.RED+"Attack Strength"+ChatColor.RESET+": "+ChatColor.WHITE+String.valueOf(10*player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue())
//                    ));
//            profile.setItemMeta(profile_meta);
//            //can put playtime as lore
//            menu.setItem(13,profile);
//
//            ItemStack pathway = new ItemStack(Material.ENCHANTED_BOOK);
//            obtainItemMeta pathway_meta = pathway.getItemMeta();
//            pathway_meta.setDisplayName(ChatColor.WHITE+"Path Ascension");
//            pathway_meta.setLore(Arrays.asList(ChatColor.AQUA+"Current Path: "));
//            pathway.setItemMeta(pathway_meta);
//            menu.setItem(22,pathway);
//
//            ItemStack storage = new ItemStack(Material.CHEST);
//            obtainItemMeta storage_meta = storage.getItemMeta();
//            storage_meta.setDisplayName(ChatColor.WHITE+"Storage");
//            storage_meta.setLore(Collections.singletonList("The unique portable space given by " + ChatColor.ITALIC + "him."));
//            storage.setItemMeta(storage_meta);
//            menu.setItem(23,storage);
//
//            ItemStack close = new ItemStack(Material.BARRIER);
//            close.setItemMeta(getGUIMeta(close.getItemMeta(),"Close",null));
//            menu.setItem(49,close);
//
//            ItemStack blank = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
//            blank.setItemMeta(getGUIMeta(blank.getItemMeta(), " ",null));
//            for (int i=0;i<53;i++){
//                if (menu.getItem(i)==null||menu.getItem(i).getType().equals(Material.AIR)) {menu.setItem(i,blank);}
//            }

            player.openInventory(menu);
        }
        return false;
    }
}
