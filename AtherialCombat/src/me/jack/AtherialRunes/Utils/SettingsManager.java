package me.jack.AtherialRunes.Utils;

import java.io.File;
import java.io.IOException;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class SettingsManager {
   
    public SettingsManager() { }
   
    static SettingsManager instance = new SettingsManager();
   
    public static SettingsManager getInstance() {
        return instance;
    }
   
    Plugin p;
   
    FileConfiguration config;
    File cfile;
   
    public void setup(Plugin p) {
        cfile = new File(p.getDataFolder(), "config.yml");
        config = p.getConfig();

        config.options().copyDefaults(true);
        saveConfig();
       
        if(!p.getDataFolder().exists()) {
            p.getDataFolder().mkdir();
        }
       
   
    }
    public FileConfiguration getConfig() {
        return config;
    }
    public void saveConfig() {
        try {
            config.save(cfile);
        }
        catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save config.yml!");
        }
    }
   
    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(cfile);
    }
    public PluginDescriptionFile getDesc() {
        return p.getDescription();
    }
}
 