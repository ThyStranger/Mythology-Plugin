package com.Stranger.Mythology.Entities.Mobs;

import com.Stranger.Mythology.Entities.Entities;
import com.Stranger.Mythology.Entities.Mobs.impl.Imp;
import com.Stranger.Mythology.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Mob;
import org.bukkit.inventory.EquipmentSlotGroup;

import java.util.HashMap;

public abstract class Mobs extends Entities{
    protected int health;
    protected int defence;
    protected int damage;

    public static HashMap<String,Entities> mobs = new HashMap<>();

    public Mobs(Main plugin, String identifier) {
        super(plugin,"mobs."+identifier);
        String entitypath = "mobs."+identifier;
        super.identifier=identifier;

        this.health = plugin.getEntityConfig().getInt(entitypath+".health");
        this.defence = plugin.getEntityConfig().getInt(entitypath+".defence");
        this.damage = plugin.getEntityConfig().getInt(entitypath+".damage");
    }

    public static void registerAll(Main plugin){
        mobs.put("imp",new Imp(plugin));
        for (String key:mobs.keySet()){
            System.out.println("Registered Mobs: "+key);
        }
        Entities.entities.put("Mob",mobs);
    }

    public Mob setAttributeModifier(Attribute attribute, Mob mob, double number){
        System.out.println("Previous attribute value: "+mob.getAttribute(attribute).getValue());
        mob.getAttribute(attribute).addModifier(new AttributeModifier(new NamespacedKey(super.identifier,attribute.toString().toLowerCase()),number-mob.getAttribute(attribute).getValue(), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        System.out.println("Attribute Added: "+attribute.toString()+" for "+super.identifier);
        System.out.println("Current attribute value: "+mob.getAttribute(attribute).getValue());

        if (attribute==Attribute.GENERIC_MAX_HEALTH){
            mob.setHealth(mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        }

        return mob;
    }

    public void onDeath() {}

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
