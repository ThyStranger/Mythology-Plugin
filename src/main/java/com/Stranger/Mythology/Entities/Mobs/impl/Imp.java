package com.Stranger.Mythology.Entities.Mobs.impl;

import com.Stranger.Mythology.Entities.Mobs.Mobs;
import com.Stranger.Mythology.Main;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.EntityDeathEvent;

public class Imp extends Mobs {
    protected Zombie entity;
    private double reinforcementChance;
    public Imp(Main plugin){
        super(plugin,"imp");
        this.reinforcementChance = plugin.getEntityConfig().getInt("mobs.imp.reinforcement_chance");
    }

    public void onDeath(EntityDeathEvent e){
        e.getEntity().getLocation().getWorld().createExplosion(e.getEntity().getLocation(),5);
    }

    @Override
    public void summon(Location location){
        this.entity = (Zombie) location.getWorld().spawnEntity(location,this.entityType);
        this.entity = (Zombie) setAttributeModifier(Attribute.GENERIC_MAX_HEALTH,this.entity,this.health);
        this.entity = (Zombie) setAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,this.entity,this.damage);
        this.entity = (Zombie) setAttributeModifier(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS,this.entity,this.reinforcementChance);
        //this.entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).addModifier(new AttributeModifier(new NamespacedKey("imp","health"), this.health,AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        //this.entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).addModifier(new AttributeModifier(new NamespacedKey("imp","damage"), this.damage,AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        //this.entity.getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS).addModifier(new AttributeModifier(new NamespacedKey("imp","reinforcement_chance"),this.reinforcementChance, AttributeModifier.Operation.ADD_NUMBER,EquipmentSlotGroup.ANY));
    }
}
