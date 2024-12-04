package com.Stranger.Mythology.Items.impl.Weapons;

import com.Stranger.Mythology.Entities.AbstractEntities.impl.PeachWoodSwordLightning;
import com.Stranger.Mythology.Items.Item;
import com.Stranger.Mythology.Main;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashSet;

public class Peach_Wood_Sword extends Item {

    protected int radius;

    public Peach_Wood_Sword(Main plugin) {
        super(plugin, "peach_wood_sword");
        this.radius=plugin.getItemConfig().getInt("items.peach_wood_sword.radius");
        super.abilityDescription.replaceAll(text->text.replaceAll("%radius%",String.valueOf(radius)));
        this.setItemStack();
    }

    public void setItemStack(){
        super.craftItemStack();
        ItemMeta meta = itemStack.getItemMeta();
        meta.addEnchant(Enchantment.SMITE,5,true);
        meta.addEnchant(Enchantment.BANE_OF_ARTHROPODS,5,true);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,new AttributeModifier(new NamespacedKey("peach_wood_sword","damage"),20, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        itemStack.setItemMeta(meta);
//        super.changeItemStack(itemStack);
    }
    @Override
    public void activateAbility(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        PeachWoodSwordLightning lightning = new PeachWoodSwordLightning(this.plugin, player, this.radius);

        double x_coord = player.getLocation().getBlockX();
        double y_coord = player.getLocation().getBlockY();
        double z_coord = player.getLocation().getBlockZ();

        HashSet<double[]> coords = new HashSet<>();
        for (int r = 0; r < this.radius; r++) {
            for (double angle = 0; angle < 2 * Math.PI; angle += Math.acos(1 - 1.1 / (2 * radius * radius))) {
                coords.add(new double[]{x_coord + r * Math.sin(angle), z_coord + r * Math.cos(angle), y_coord});
            }
        }
        lightning.doDamage(player.getLocation());
        for (double[] singleCoord : coords) {
            lightning.summon(new Location(player.getWorld(), singleCoord[0], y_coord, singleCoord[1]));
        }

    }

}
