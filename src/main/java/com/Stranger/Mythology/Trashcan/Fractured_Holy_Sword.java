//package com.Stranger.Mythology.Items;
//
//import org.bukkit.*;
//import org.bukkit.attribute.Attribute;
//import org.bukkit.attribute.AttributeModifier;
//import org.bukkit.enchantments.Enchantment;
//import org.bukkit.entity.Player;
//import org.bukkit.inventory.EquipmentSlotGroup;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.meta.ItemMeta;
//
////import static com.Stranger.Mythology.Trashcan.ItemSuperClass.getItemMeta;
//
//public class Fractured_Holy_Sword {
//    public ItemStack fractured_holy_sword(){
//        ItemStack fractured_holy_sword = new ItemStack(Material.GOLDEN_SWORD,1);
//        ItemMeta meta = super.getItemMeta(fractured_holy_sword,"Fractured Holy Sword","Pray","Pray to the Lord to receive blessings. What can go wrong?", ChatColor.YELLOW + "The powerful Holy Sword used by the" + ChatColor.ITALIC + " " + ChatColor.BOLD + "Angels" + ChatColor.RESET + " " + ChatColor.YELLOW + "in the ancient war.\n"+ChatColor.YELLOW+"Although fractured, it still seems to be able to communicate with another dimension?");
//        meta.addEnchant(Enchantment.SMITE,10,true);
//        meta.addEnchant(Enchantment.SWEEPING_EDGE,10,true);
//        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,new AttributeModifier(new NamespacedKey("fractured_holy_sword","damage"),15, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
//        fractured_holy_sword.setItemMeta(meta);
//        return fractured_holy_sword;
//    }
//    public void fractured_holy_sword_ABILITY(Player player){
//        if (Math.random()>0.3) {
//            double heal_amt=10*Math.random();
//            if (heal_amt>player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()-player.getHealth()){
//                heal_amt=player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()-player.getHealth();
//            }
//            player.sendMessage(ChatColor.YELLOW+"He heard you");
//            player.playEffect(EntityEffect.LOVE_HEARTS);
//            player.playSound(player, Sound.ENTITY_ZOMBIE_VILLAGER_CURE,100,300);
//            player.setHealth(player.getHealth() + heal_amt);
//        }
//        else{
//            double dmg_amt=Math.random()*15;
//            if (player.getHealth()<dmg_amt){
//                dmg_amt=player.getHealth();
//            }
//            player.sendMessage(ChatColor.RED+"Someone heard you, but it's not him..."+ChatColor.BOLD+" "+ChatColor.ITALIC+"SATAN"+ChatColor.RESET+" "+ChatColor.RED+"glanced at you!");
//            player.playHurtAnimation(180);
//            player.playSound(player,Sound.ENTITY_PLAYER_HURT,100,300);
//            player.setHealth(player.getHealth()-dmg_amt);
//        }
//    }
//
//}
