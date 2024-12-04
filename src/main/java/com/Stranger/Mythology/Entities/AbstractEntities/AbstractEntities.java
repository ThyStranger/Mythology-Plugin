package com.Stranger.Mythology.Entities.AbstractEntities;

import com.Stranger.Mythology.Entities.Entities;
import com.Stranger.Mythology.Main;

public abstract class AbstractEntities extends Entities {

    public AbstractEntities(Main plugin, String identifier) {
        super(plugin, "abstract_entities."+identifier);
    }
}
