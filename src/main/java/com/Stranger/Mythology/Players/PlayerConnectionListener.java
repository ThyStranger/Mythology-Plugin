package com.Stranger.Mythology.Players;

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
        Players.addPlayer(player.getUniqueId(),new Players(main,player.getUniqueId()));
//            PlayerManager playerData = new PlayerManager(main,player.getUniqueId());
//            main.getPlayerManager().addPlayer(player.getUniqueId(),playerData);

        //Register MenuGUIs
        MenuGUI.registerMenuGUIs(this.main,e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        try {
            Players.getPlayer(e.getPlayer().getUniqueId()).updatePlayerInfo();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        Players.removePlayer(e.getPlayer().getUniqueId());
    }
}
