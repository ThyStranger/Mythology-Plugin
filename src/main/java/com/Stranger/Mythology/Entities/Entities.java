package com.Stranger.Mythology.Entities;

import com.Stranger.Mythology.Entities.Mobs.Mobs;
import com.Stranger.Mythology.Main;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.HashMap;

public abstract class Entities {

    protected String name;
    protected EntityType entityType;
    protected Entity entity;
    protected String identifier;
    public static HashMap<String,HashMap<String,Entities>> entities = new HashMap<>();

    public Entities(Main plugin, String identifier){
        this.name = plugin.getEntityConfig().getString(identifier+".name");
        this.entityType = EntityType.valueOf(plugin.getEntityConfig().getString(identifier+".type"));
    }

    public void summon(Location location){
        this.entity = location.getWorld().spawnEntity(location,entityType);
    }

    public static void registerAllEntities(Main plugin){
        Mobs.registerAll(plugin);
    }
}
