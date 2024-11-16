package com.Stranger.Mythology.PlayerDatabase;

import com.Stranger.Mythology.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CustomPlayer {
    private UUID uuid;
    private String path;
    private int progress;
    private int spirit;
    private Main main = new Main();

    public CustomPlayer(Main main,UUID uuid) throws SQLException{
        this.main = main;
        this.uuid = uuid;

        PreparedStatement getInfo = main.getDatabase().getConnection().prepareStatement("SELECT PATH, PROGRESS, SPIRIT FROM player WHERE UUID = ?;");
        getInfo.setString(1, uuid.toString());
        ResultSet rs = getInfo.executeQuery();
        if (rs.next()) {
            path = rs.getString("PATH");
            progress = rs.getInt("PROGRESS");
            spirit = rs.getInt("SPIRIT");
        }
        else {
            path = "";
            progress = 0;
            spirit = 0;
            PreparedStatement setInfo = main.getDatabase().getConnection().prepareStatement("INSERT INTO player (ID, UUID, PATH, PROGRESS, SPIRIT) VALUES (" +
                    "default," +
                    "'" + uuid + "'," +
                    "'" + path + "'," +
                    progress + "," +
                    spirit + ");");
            setInfo.executeUpdate();
        }


    }

    public void updatePlayerInfo() throws SQLException {
        PreparedStatement updateInfo = main.getDatabase().getConnection().prepareStatement("UPDATE player SET " +
                "PATH = '"+path+"',"+
                "PROGRESS = "+progress+"," +
                "SPIRIT = "+spirit+
                " WHERE UUID = '"+uuid.toString()+"';");
        updateInfo.executeUpdate();
    }

//    public void updatePlayerInfo(CustomPlayer playerData) throws SQLException {
//        PreparedStatement updateInfo = main.getDatabase().getConnection().prepareStatement("UPDATE player SET " +
//                "PATH = '" + playerData.getPath() + "'," +
//                "PROGRESS = " + playerData.getProgress() + "," +
//                "SPIRIT = " + playerData.getSpirit() +
//                " WHERE UUID = '" + playerData.getUuid().toString() + "';");
//        updateInfo.executeUpdate();
//    }

    public void setPath(String path){
        this.path=path;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public void setSpirit(int spirit) {
        this.spirit = spirit;
    }

    public int getProgress() {
        return progress;
    }

    public String getPath() {
        return path;
    }

    public int getSpirit() {
        return spirit;
    }

    public UUID getUuid() {
        return uuid;
    }
}
