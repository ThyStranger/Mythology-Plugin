package com.Stranger.Mythology.PlayerDatabase;

import com.Stranger.Mythology.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;


public class ConnectionListener implements Listener {
    private Main main;
    public ConnectionListener(Main main){
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        try {
            CustomPlayer playerData = new CustomPlayer(main,player.getUniqueId());
            main.getPlayerManager().addCustomPlayer(player.getUniqueId(),playerData);
        } catch (SQLException ex) {
            player.kickPlayer("Your data cannot be loaded!");
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        try {
            main.getPlayerManager().getCustomPlayer(e.getPlayer().getUniqueId()).updatePlayerInfo();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        main.getPlayerManager().removeCustomPlayer(e.getPlayer().getUniqueId());
    }
}
