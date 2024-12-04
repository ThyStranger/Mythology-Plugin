package com.Stranger.Mythology.Players;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {
    private static final Map<UUID, Map<String,Instant>> map = new HashMap<>();

    // Set cooldown
    public static void setCooldown(UUID uuid,String key, Duration duration) {
        map.put(uuid, Players.getPlayer(uuid).addCooldowns(key,Instant.now().plus(duration)));
        System.out.println("Cooldown for "+key+" set for "+duration+"!");
    }

    // Check if cooldown has expired
    public static boolean isInCooldown(UUID uuid, String key) {
        if (map.get(uuid) == null){
            return false;
        }
        Instant cooldown = map.get(uuid).get(key);
        return cooldown != null && Instant.now().isBefore(cooldown);
    }

    // Remove cooldown
    public static Instant removeCooldown(UUID uuid, String key) {
        return map.get(uuid).remove(key);
    }

    // Get remaining cooldown time
    public static Duration getRemainingCooldown(UUID uuid, String key) {
        if (map.get(uuid) == null){
            return Duration.ZERO;
        }
        Instant cooldown = map.get(uuid).get(key);
        Instant now = Instant.now();
        if (cooldown != null && now.isBefore(cooldown)) {
            return Duration.between(now, cooldown);
        } else {
            return Duration.ZERO;
        }
    }

    public static String durationToString(Duration duration){
        long s = duration.toSeconds();
        if (s<60){
            return String.valueOf(s)+"s";
        }
        else if (s<3600){
            return String.valueOf(s/60)+"min " + String.valueOf(s%60)+"s";
        }
        else{
            return String.valueOf(s/3600)+"h " + String.valueOf((s%3600)/60) + "min " + String.valueOf(s%60);
        }
    }
}

