package me.jack.AtherialRunes.toggles;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;
import org.bukkit.plugin.Plugin;

import me.jack.AtherialRunes.Main;
import me.jack.AtherialRunes.Utils.SettingsManager;

public class ToggleMain implements Listener, CommandExecutor{
	
	public Main m;
	public SettingsManager settings;
	private ItemStack togglepvp;
	Inventory inv = Bukkit.getServer().createInventory(null, 18, "Toggle Menu");
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("toggle")) {
			p.openInventory(inv);
			
		}
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
	
	
	public void Toggle(Player pl, Plugin p) {
		Inventory inv = Bukkit.getServer().createInventory(null, 18, ChatColor.DARK_GRAY + "Toggle Menu");
		String player = pl.getName();
			if(settings.getConfig().get("Players." + player + ".TogglePVP", "true") != null) {
				togglepvp = createItem(Material.INK_SACK, DyeColor.GREEN, ChatColor.GREEN, "Toggle PvP", ChatColor.GREEN, "Anti-PVP is currently Toggled [ON]");
				
				inv.addItem(togglepvp);
			}
			if(settings.getConfig().get("Players." + player + ".TogglePVP", "false") != null) {
				togglepvp = createItem(Material.INK_SACK, DyeColor.RED, ChatColor.GREEN, "Toggle PvP", ChatColor.GREEN, "Anti-PVP is currently Toggled [OFF]");
				
				inv.addItem(togglepvp);
		}
	}
	@EventHandler
	public void ToggleInteract(InventoryClickEvent e) {
		if(e.getInventory().getName().equals(inv.getName()));
		if(e.getCurrentItem().getItemMeta().getDisplayName().equals("Toggle PvP")) {
		if(e.getCurrentItem().getItemMeta().getLore().equals("Anti-PVP is currently Toggled [OFF]")) {
			
		}
	}
	@SuppressWarnings("unused")
	private ItemStack createItem(Material mat, DyeColor color, ChatColor cl, String ToggleName, ChatColor cll, String lore) {
		if(mat == Material.INK_SACK) { 
			ItemStack item = new ItemStack(mat, 1 );
			Dye dye = new Dye();
			dye.setColor( color );
			item.setData( dye );
			ItemMeta meta1 = item.getItemMeta();
	        meta1.setDisplayName(cl + ToggleName);
	        meta1.setLore(Arrays.asList(cll + lore));
			return item;
		}
		return null;
	
		
	}
	@EventHandler
	public void onFirstLogin(PlayerJoinEvent e) {
		String player = e.getPlayer().getName();
		if(!e.getPlayer().hasPlayedBefore()) {
			settings.getConfig().set("Players." + player + ".TogglePVP", "true");
			
		}
        
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
