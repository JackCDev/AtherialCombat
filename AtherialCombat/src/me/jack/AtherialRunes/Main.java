package me.jack.AtherialRunes;

import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
	
	
	
	
	public void onEnable() {
		System.out.print(ChatColor.GREEN + "Etherial Combat is working!");
		
	
	}
	
	
	public void onDisable() {
		System.out.print(ChatColor.RED + "Etherial Combat disabled.");
	}
	
	

}
