package com.Stranger.Mythology.GUIs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GUIs {
    private static ItemMeta getGUIMeta(ItemMeta meta, String display_name, String lore) {
        if (lore != null) {
            meta.setLore(List.of(lore.split("\n")));
        }
        if (display_name != null) {
            if (display_name.contains("&") && display_name.equals(ChatColor.translateAlternateColorCodes('&', display_name))) {
                meta.setDisplayName(display_name);
            }
            else{
                meta.setDisplayName(ChatColor.WHITE+display_name);
            }
        }
        return meta;
    }
    public Inventory MenuGUI(Player player){
        Inventory menu = Bukkit.createInventory(player, 54, ChatColor.BOLD + "" + ChatColor.BLACK + "Your Journey on This World");

        //profile
        ItemStack profile = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta profile_meta = (SkullMeta) profile.getItemMeta();
        profile_meta.setOwningPlayer(player);
        profile_meta.setDisplayName(ChatColor.WHITE + "Your Profile");
        profile_meta.setLore(Arrays.asList(
                ChatColor.DARK_RED + "Health" + ChatColor.RESET + ": " + ChatColor.WHITE + String.valueOf(Math.round(5 * player.getHealth())),
                ChatColor.GREEN + "Defence" + ChatColor.RESET + ": " + ChatColor.WHITE + String.valueOf(10 * player.getAttribute(Attribute.GENERIC_ARMOR).getValue()),
                ChatColor.RED + "Attack Strength" + ChatColor.RESET + ": " + ChatColor.WHITE + String.valueOf(10 * player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue())
        ));
        profile.setItemMeta(profile_meta);
        //can put playtime as lore
        menu.setItem(13, profile);

        //pathway
        ItemStack pathway = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta pathway_meta = pathway.getItemMeta();
        pathway_meta.setDisplayName(ChatColor.WHITE + "Path Ascension");
        pathway_meta.setLore(Arrays.asList(ChatColor.AQUA + "Current Path: "));
        pathway.setItemMeta(pathway_meta);
        menu.setItem(22, pathway);

        //storage
        ItemStack storage = new ItemStack(Material.CHEST);
        ItemMeta storage_meta = storage.getItemMeta();
        storage_meta.setDisplayName(ChatColor.WHITE + "Storage");
        storage_meta.setLore(Collections.singletonList("The unique portable space given by " + ChatColor.ITALIC + "him."));
        storage.setItemMeta(storage_meta);
        menu.setItem(23, storage);

        //close
        ItemStack close = new ItemStack(Material.BARRIER);
        close.setItemMeta(getGUIMeta(close.getItemMeta(), "Close", null));
        menu.setItem(49, close);

        //Space filler
        ItemStack blank = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        blank.setItemMeta(getGUIMeta(blank.getItemMeta(), " ", null));
        for (int i = 0; i < 53; i++) {
            if (menu.getItem(i) == null || menu.getItem(i).getType().equals(Material.AIR)) {
                menu.setItem(i, blank);
            }
        }
        return menu;
    }

}
