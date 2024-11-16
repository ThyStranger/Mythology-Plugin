package com.Stranger.Mythology.Trashcan;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class ItemSuperClass {

    //Any change in the name of an item with ability MUST be updated in Events!

    //Fractured Holy Sword
    public ItemStack fractured_holy_sword(){
        ItemStack fractured_holy_sword = new ItemStack(Material.GOLDEN_SWORD,1);
        ItemMeta meta = getItemMeta(fractured_holy_sword,"Fractured Holy Sword","Pray","Pray to the Lord to receive blessings. What can go wrong?",ChatColor.YELLOW + "The powerful Holy Sword used by the" + ChatColor.ITALIC + " " + ChatColor.BOLD + "Angels" + ChatColor.RESET + " " + ChatColor.YELLOW + "in the ancient war.\n"+ChatColor.YELLOW+"Although fractured, it still seems to be able to communicate with another dimension?");
        meta.addEnchant(Enchantment.SMITE,10,true);
        meta.addEnchant(Enchantment.SWEEPING_EDGE,10,true);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,new AttributeModifier(new NamespacedKey("fractured_holy_sword","damage"),15, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        fractured_holy_sword.setItemMeta(meta);
        return fractured_holy_sword;
    }
    public void fractured_holy_sword_ABILITY(Player player){
        if (Math.random()>0.3) {
            double heal_amt=10*Math.random();
            if (heal_amt>player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()-player.getHealth()){
                heal_amt=player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()-player.getHealth();
            }
            player.sendMessage(ChatColor.YELLOW+"He heard you");
            player.playEffect(EntityEffect.LOVE_HEARTS);
            player.playSound(player, Sound.ENTITY_ZOMBIE_VILLAGER_CURE,100,300);
            player.setHealth(player.getHealth() + heal_amt);
        }
        else{
            double dmg_amt=Math.random()*15;
            if (player.getHealth()<dmg_amt){
                dmg_amt=player.getHealth();
            }
            player.sendMessage(ChatColor.RED+"Someone heard you, but it's not him..."+ChatColor.BOLD+" "+ChatColor.ITALIC+"SATAN"+ChatColor.RESET+" "+ChatColor.RED+"glanced at you!");
            player.playHurtAnimation(180);
            player.playSound(player,Sound.ENTITY_PLAYER_HURT,100,300);
            player.setHealth(player.getHealth()-dmg_amt);
        }
    }

    //锦襕袈裟（仿）
    public ItemStack jin_lan_jia_sha_FangZao(){
        ItemStack jin_lan_jia_sha_fangzao = new ItemStack(Material.ELYTRA);
        ItemMeta meta = getItemMeta(jin_lan_jia_sha_fangzao,"锦襕袈裟（仿）","功德加持","水火不侵，不坠地狱\n"+ChatColor.WHITE+"The wearer shall be immune to fire and drowning, and shall never descend to hell",ChatColor.GOLD+"黑熊精成收山大神后对功德佛的锦襕袈裟念念不忘，便仿造了一个，不知为何却流落此界\n\""+"这袈裟，龙披一缕，免大鹏吞噬之灾；鹤挂一丝，得超凡入圣之妙。但坐处, 有万神朝礼；凡举动，有七佛随身。\"\n——南海观世音菩萨");
        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH,new AttributeModifier(new NamespacedKey("jin_lan_jiasha_fangzao","health"),10, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlotGroup.CHEST));
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR,new AttributeModifier(new NamespacedKey("jin_lan_jiasha_fangzao","armor"),10, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlotGroup.CHEST));
        jin_lan_jia_sha_fangzao.setItemMeta(meta);
        return jin_lan_jia_sha_fangzao;
    }
    public void jin_lan_jia_sha_FangZao_ABILITIES(Player player,boolean wear){
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

    public static ItemMeta getItemMeta(ItemStack item, String name,String ability_name, String ability_description,String lore) {
        ItemMeta meta = item.getItemMeta();
        meta.setItemName(name);
        meta.setUnbreakable(true);
        if (ability_description!=null&&ability_name!=null) {
            String ability = ChatColor.YELLOW +""+ChatColor.BOLD+ "ITEM ABILITY: " + ability_name + ChatColor.RESET + "\n"+ChatColor.WHITE+ability_description;
            lore=ability +"\n\n"+lore;
        }
        if (lore!=null) meta.setLore(List.of(lore.split("\n")));
//        System.out.println(Arrays.toString(lore.split("\n")));
        return meta;
    }


}

