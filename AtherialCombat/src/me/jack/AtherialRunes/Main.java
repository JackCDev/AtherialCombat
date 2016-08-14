package me.jack.AtherialRunes;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.connorlinfoot.actionbarapi.ActionBarAPI;

import me.jack.AtherialRunes.ArmorSystem.ArmorEquipting;
import me.jack.AtherialRunes.Utils.SettingsManager;
import me.jack.AtherialRunes.libs.ArmorEquipEvent.ArmorListener;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener {

	public SettingsManager settings;

	public void onEnable() {
		settings.setup(this);
		System.out.print(ChatColor.GREEN + "Etherial Combat is working!");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this, this);
		pm.registerEvents(new ArmorEquipting(), this);
		getServer().getPluginManager().registerEvents(new ArmorListener(getConfig().getStringList("blocked")), this);

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			// Makes sure the player has a health bar.
			@Override
			public void run() {
				for (Player p : Bukkit.getServer().getOnlinePlayers()) {
					ActionHealth(p);

				}

			}

		}, 20);

	}

	public void onDisable() {
		System.out.print(ChatColor.RED + "Etherial Combat disabled.");
	}

	// Initializes Health Bar System
	public void ActionHealth(Player p) {
		int health = (int) p.getHealth();
		int maxhealth = (int) p.getMaxHealth();
		ActionBarAPI.sendActionBar(p, ChatColor.BLUE + "" + ChatColor.BOLD + "HP" + ChatColor.AQUA + health
				+ ChatColor.BOLD + "" + ChatColor.BLUE + "/" + ChatColor.AQUA + maxhealth);
	}

	private int test = 1;
}
