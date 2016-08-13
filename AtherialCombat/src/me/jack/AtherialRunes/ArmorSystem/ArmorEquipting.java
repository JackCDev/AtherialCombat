package me.jack.AtherialRunes.ArmorSystem;

import java.util.Scanner;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.jack.AtherialRunes.libs.ArmorEquipEvent.ArmorEquipEvent;

public class ArmorEquipting implements Listener 
{
	@EventHandler
	public void Equip(ArmorEquipEvent e) 
	{

		Player p = e.getPlayer();
		
		//Checking if inventory is full
		if (p.getInventory().firstEmpty() == -1) 
		{
			e.setCancelled(true);
			p.sendMessage(ChatColor.RED + "Inventory Full!");
			return;
		}
		
		e.getType().getSlot();
		
		if (e.getNewArmorPiece() != null && e.getNewArmorPiece().hasItemMeta()) 
		{
			// ----------Setting Health----------
			String lorehealth = e.getNewArmorPiece().getItemMeta().getLore().get(2);
			Scanner in2 = new Scanner(lorehealth).useDelimiter("[^0-9]+");
			int h = in2.nextInt();
			double maxhealth = p.getMaxHealth();
			p.setMaxHealth(maxhealth + h);
			
			
			p.sendMessage("");
			p.sendMessage(ChatColor.DARK_PURPLE + "    ■You equipped: "
					+ e.getNewArmorPiece().getItemMeta().getDisplayName());
			p.sendMessage(ChatColor.DARK_PURPLE + "    ■" + ChatColor.WHITE + "MaxHealth is now: "
					+ ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + p.getMaxHealth() + "Health");
			p.sendMessage("");
		}

		if (e.getOldArmorPiece() != null && e.getOldArmorPiece().hasItemMeta()) {
			// ----------Setting Health----------
			String lorehealth = e.getOldArmorPiece().getItemMeta().getLore().get(2);
			Scanner in2 = new Scanner(lorehealth).useDelimiter("[^0-9]+");
			int h = in2.nextInt();
			double maxhealth = p.getMaxHealth();
			p.setMaxHealth(maxhealth - h);


			p.sendMessage("");
			p.sendMessage(ChatColor.DARK_PURPLE + "    ■You unequipped: "
					+ e.getOldArmorPiece().getItemMeta().getDisplayName());
			p.sendMessage(ChatColor.DARK_PURPLE + "    ■" + ChatColor.WHITE + "MaxHealth is now: "
					+ ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + p.getMaxHealth() + "Health");
			p.sendMessage("");
		}
	}

}
