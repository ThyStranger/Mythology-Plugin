package com.Stranger.Mythology.Trashcan;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static com.Stranger.Mythology.Trashcan.ItemSuperClass.getItemMeta;

public class Jin_Lan_Jia_Sha_Fang {
    public String name = "锦襕袈裟（仿）";
    public String ability_name = "功德加持";
    //public String
    public ItemStack jin_lan_jia_sha_FangZao(){
        ItemStack jin_lan_jia_sha_fangzao = new ItemStack(Material.ELYTRA);
        ItemMeta meta = getItemMeta(jin_lan_jia_sha_fangzao,"锦襕袈裟（仿）","功德加持","水火不侵，不坠地狱\n"+ ChatColor.WHITE+"The wearer shall be immune to fire and drowning, and shall never descend to hell",ChatColor.GOLD+"黑熊精成收山大神后对功德佛的锦襕袈裟念念不忘，便仿造了一个，不知为何却流落此界\n\""+"这袈裟，龙披一缕，免大鹏吞噬之灾；鹤挂一丝，得超凡入圣之妙。但坐处, 有万神朝礼；凡举动，有七佛随身。\"\n——南海观世音菩萨");
        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH,new AttributeModifier(new NamespacedKey("jin_lan_jiasha_fangzao","health"),10, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR,new AttributeModifier(new NamespacedKey("jin_lan_jiasha_fangzao","armor"),10, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlotGroup.CHEST));
        jin_lan_jia_sha_fangzao.setItemMeta(meta);
        return jin_lan_jia_sha_fangzao;
    }
    public void jin_lan_jia_sha_FangZao_ABILITIES(Player player, boolean wear){
        //when wear==true, it is taken that the player wears the armor, when wear==false, it is taken that the player takes off the armor
        if (wear) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, PotionEffect.INFINITE_DURATION, 1));
        }
        else{
            player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
            player.removePotionEffect(PotionEffectType.WATER_BREATHING);
        }
    }

    public ItemStack viewmenu(){
        ItemStack viewmenu = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = viewmenu.getItemMeta();
        meta.setDisplayName("View Menu");
        viewmenu.setItemMeta(meta);
        return viewmenu;
    }

}
