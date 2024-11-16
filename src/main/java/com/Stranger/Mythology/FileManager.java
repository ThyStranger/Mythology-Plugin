package com.Stranger.Mythology;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class FileManager {
    public static YamlConfiguration initiateFile(File PluginFolder, String name) throws IOException {
        File file = new File(PluginFolder,name);
        if (!file.exists()){
            file.createNewFile();
        }
        return YamlConfiguration.loadConfiguration(file);
    }
}
