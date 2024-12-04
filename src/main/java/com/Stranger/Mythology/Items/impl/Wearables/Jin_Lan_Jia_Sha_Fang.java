package com.Stranger.Mythology.Items.impl.Wearables;

import com.Stranger.Mythology.Items.Item;
import com.Stranger.Mythology.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Jin_Lan_Jia_Sha_Fang extends Item {

    public Jin_Lan_Jia_Sha_Fang(Main plugin) {
        super(plugin, "jin_lan_jia_sha_fang");
        this.setItemStack();
    }

    public void setItemStack() {
        super.craftItemStack();
        ItemMeta meta = itemStack.getItemMeta();
        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH,new AttributeModifier(new NamespacedKey("jin_lan_jiasha_fangzao","health"),10, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR,new AttributeModifier(new NamespacedKey("jin_lan_jiasha_fangzao","armor"),10, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlotGroup.CHEST));
        itemStack.setItemMeta(meta);
        super.changeItemStack(itemStack);
    }

    @Override
    public void activateAbility(Player player){
        boolean wear = true;
         if (wear) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, PotionEffect.INFINITE_DURATION, 1));
        }
        else{
            player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
            player.removePotionEffect(PotionEffectType.WATER_BREATHING);
        }
    }
}
