package com.Stranger.Mythology.Items.impl;

import com.Stranger.Mythology.Items.Item;
import com.Stranger.Mythology.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;

public class Peach_Wood_Sword extends Item {
    public Peach_Wood_Sword(Main plugin) {
        super(plugin, "peach_wood_sword");
        this.setItemStack();
    }
    public void setItemStack(){
        ItemMeta meta = super.itemStack.getItemMeta();
        meta.addEnchant(Enchantment.SMITE,5,true);
        meta.addEnchant(Enchantment.BANE_OF_ARTHROPODS,5,true);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,new AttributeModifier(new NamespacedKey("peach_wood_sword","damage"),20, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        super.itemStack.setItemMeta(meta);
    }
    @Override
    public void activateAbility(Player player){
        if (Math.random()<0.1){
            player.getLocation();
        }
    }
}
