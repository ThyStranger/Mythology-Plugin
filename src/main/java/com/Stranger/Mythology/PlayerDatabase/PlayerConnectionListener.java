package com.Stranger.Mythology.PlayerDatabase;

import com.Stranger.Mythology.GUIs.MenuGUIs.MenuGUI;
import com.Stranger.Mythology.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;


public class PlayerConnectionListener implements Listener {
    private Main main;
    public PlayerConnectionListener(Main main){
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

        //Register MenuGUIs
        MenuGUI.registerMenuGUIs(this.main,e.getPlayer());
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
