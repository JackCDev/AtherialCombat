package me.jack.AtherialRunes;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.connorlinfoot.actionbarapi.ActionBarAPI;

import me.jack.AtherialRunes.ArmorSystem.ArmorEquipting;
import me.jack.AtherialRunes.Utils.SettingsManager;
import me.jack.AtherialRunes.libs.ArmorEquipEvent.ArmorListener;
import me.jack.AtherialRunes.toggles.ToggleMain;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener {

	public SettingsManager settings;
	Logger log = Logger.getLogger("Minecraft");
	public void onEnable() 
	{
		settings.setup(this);
		System.out.print(ChatColor.GREEN + "Etherial Combat is working!");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this, this);
		pm.registerEvents(new ArmorEquipting(), this);
		getCommand("toggle").setExecutor(new ToggleMain(this));
		getServer().getPluginManager().registerEvents(new ArmorListener(getConfig().getStringList("blocked")), this);

		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			// Makes sure the player has a health bar.
			@Override
			public void run() {
				for (Player p : Bukkit.getServer().getOnlinePlayers()) {
					ActionHealth(p);

				}

			}

		}, 20, 20);

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
	
	// Sets player HealthScale to 40 hearts! And gives starters gear when
	// joining for first time.
	@EventHandler
	public void PlayerJoin(PlayerLoginEvent event) {
		

		Player p = event.getPlayer();

		// removes 1.9 combat
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			public void run() {
				p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(16);
			}
		}, 20);

		// Sets healthscale to 40
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			public void run() {
				if (!(event.getPlayer().hasPlayedBefore())) {
					event.getPlayer().setMaxHealth(100);
					event.getPlayer().setHealthScale(40);
					event.getPlayer().setHealth(event.getPlayer().getMaxHealth());
					event.getPlayer().sendMessage(org.bukkit.ChatColor.GREEN + "Welcome to the server!");
					Location spawn = new Location(event.getPlayer().getWorld(), -538, 25, -504);
					event.getPlayer().teleport(spawn);

					ItemStack sword1 = new ItemStack(Material.WOOD_SWORD);
					ItemMeta swordMeta1 = sword1.getItemMeta();
					List<String> swordLore1 = new ArrayList<String>();
					swordMeta1.setDisplayName(ChatColor.GREEN + ChatColor.BOLD.toString() + ChatColor.ITALIC.toString()
							+ "Training Sword");
					swordLore1.add(ChatColor.GREEN + "UNCOMMON item T1");
					swordLore1
							.add(ChatColor.RED + "   ☼DAMAGE: " + ChatColor.WHITE + ChatColor.BOLD.toString() + "+16");
					swordLore1.add(ChatColor.RED + "____________________");
					swordLore1.add(ChatColor.RED + "   ☼DURABILITY " + ChatColor.WHITE + ChatColor.BOLD.toString()
							+ "[ 100 / 100 ]");
					swordMeta1.setLore(swordLore1);
					sword1.setItemMeta(swordMeta1);

					ItemStack thelm = new ItemStack(Material.LEATHER_HELMET);
					ItemMeta thelmmeta = thelm.getItemMeta();
					List<String> thelmlore = new ArrayList<String>();
					thelmmeta.setDisplayName(ChatColor.GREEN + ChatColor.BOLD.toString() + ChatColor.ITALIC.toString()
							+ "Training Helm");
					thelmlore.add(ChatColor.GREEN + "Common item T1");
					thelmlore.add(ChatColor.RED + "   ☼Armor: " + ChatColor.WHITE + ChatColor.BOLD.toString() + "+2");
					thelmlore.add(ChatColor.RED + "   ☼HEALTH: " + ChatColor.WHITE + ChatColor.BOLD.toString() + "+10");
					swordLore1.add(ChatColor.RED + "____________________");
					swordLore1.add(ChatColor.RED + "   ☼DURABILITY " + ChatColor.WHITE + ChatColor.BOLD.toString()
							+ "[ 100 / 100 ]");
					thelmmeta.setLore(thelmlore);
					thelm.setItemMeta(thelmmeta);

					ItemStack tboots = new ItemStack(Material.LEATHER_BOOTS);
					ItemMeta tbootsmmeta = tboots.getItemMeta();
					List<String> tbootslore = new ArrayList<String>();
					tbootsmmeta.setDisplayName(ChatColor.GREEN + ChatColor.BOLD.toString() + ChatColor.ITALIC.toString()
							+ "Training Boots");
					tbootslore.add(ChatColor.GREEN + "Common item T1");
					tbootslore.add(ChatColor.RED + "   ☼Armor: " + ChatColor.WHITE + ChatColor.BOLD.toString() + "+2");
					tbootslore
							.add(ChatColor.RED + "   ☼HEALTH: " + ChatColor.WHITE + ChatColor.BOLD.toString() + "+10");
					tbootslore.add(ChatColor.RED + "____________________");
					tbootslore.add(ChatColor.RED + "   ☼DURABILITY " + ChatColor.WHITE + ChatColor.BOLD.toString()
							+ "[ 100 / 100 ]");
					tbootsmmeta.setLore(tbootslore);
					tboots.setItemMeta(tbootsmmeta);

					ItemStack tchest = new ItemStack(Material.LEATHER_CHESTPLATE);
					ItemMeta tchestmmeta1 = tchest.getItemMeta();
					List<String> tchestlore1 = new ArrayList<String>();
					tchestmmeta1.setDisplayName(ChatColor.GREEN + ChatColor.BOLD.toString()
							+ ChatColor.ITALIC.toString() + "Training Chest");
					tchestlore1.add(ChatColor.GREEN + "Common item T1");
					tchestlore1.add(ChatColor.RED + "   ☼Armor: " + ChatColor.WHITE + ChatColor.BOLD.toString() + "+3");
					tchestlore1
							.add(ChatColor.RED + "   ☼HEALTH: " + ChatColor.WHITE + ChatColor.BOLD.toString() + "+15");
					tchestlore1.add(ChatColor.RED + "____________________");
					tchestlore1.add(ChatColor.RED + "   ☼DURABILITY " + ChatColor.WHITE + ChatColor.BOLD.toString()
							+ "[ 100 / 100 ]");
					tchestmmeta1.setLore(tchestlore1);
					tchest.setItemMeta(tchestmmeta1);

					ItemStack tlegs = new ItemStack(Material.LEATHER_LEGGINGS);
					ItemMeta tlegsmeta = tlegs.getItemMeta();
					List<String> tlegslore1 = new ArrayList<String>();
					tlegsmeta.setDisplayName(ChatColor.GREEN + ChatColor.BOLD.toString() + ChatColor.ITALIC.toString()
							+ "Training Legs");
					tlegslore1.add(ChatColor.GREEN + "Common item T1");
					tlegslore1.add(ChatColor.RED + "   ☼Armor: " + ChatColor.WHITE + ChatColor.BOLD.toString() + "+3");
					tlegslore1
							.add(ChatColor.RED + "   ☼HEALTH: " + ChatColor.WHITE + ChatColor.BOLD.toString() + "+13");
					tlegslore1.add(ChatColor.RED + "____________________");
					tlegslore1.add(ChatColor.RED + "   ☼DURABILITY " + ChatColor.WHITE + ChatColor.BOLD.toString()
							+ "[ 100 / 100 ]");
					tlegsmeta.setLore(tlegslore1);
					tlegs.setItemMeta(tlegsmeta);

					ItemStack tapple = new ItemStack(Material.APPLE, 30);
					event.getPlayer().getInventory().addItem(sword1, thelm, tlegs, tchest, tboots, tapple);

				} else event.getPlayer().setHealthScale(40);

			}
		}, 20);
	}

}
