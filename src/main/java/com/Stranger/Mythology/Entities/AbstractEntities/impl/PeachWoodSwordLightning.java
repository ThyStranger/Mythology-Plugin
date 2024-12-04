package com.Stranger.Mythology.Entities.AbstractEntities.impl;

import com.Stranger.Mythology.Entities.AbstractEntities.AbstractEntities;
import com.Stranger.Mythology.Main;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class PeachWoodSwordLightning extends AbstractEntities {
    protected double damageScaling;
    //How much the lightning bolt damage scales with player GENERIC_ATTACK_DAMAGE
    protected double damage;
    protected LightningStrike lightningStrike;
    protected int radius;
    protected Player player;

    public PeachWoodSwordLightning(Main plugin, Player player, int radius) {
        super(plugin, "peach_wood_sword_lightning");
        this.damageScaling=plugin.getEntityConfig().getDouble("abstract_entities.peach_wood_sword_lightning.damage_scaling");
        this.damage=player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue()*damageScaling;
        this.player = player;
        this.radius = radius;
    }

    @Override
    public void summon(Location location){
        this.lightningStrike=location.getWorld().strikeLightningEffect(location);
//        this.lightningStrike.setLifeTicks((int) Math.round(this.damageTick));
//        this.lightningStrike.setCausingPlayer(player);
    }

    public void doDamage(Location location){
        for (LivingEntity entity:location.getWorld().getLivingEntities()){
            if (entity.getLocation().distance(location)<=radius && !entity.getUniqueId().equals(player.getUniqueId())){
                entity.damage(damage);
            }
        }
    }
}
