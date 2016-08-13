package me.jack.AtherialRunes.ArmorSystem;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.meta.ItemMeta;

import me.jack.AtherialRunes.libs.ArmorEquipEvent.ArmorEquipEvent;

public class ArmorEquiptingStats implements Listener {
	
	
	
	
	
	@EventHandler
	public void onEquiptArmorArmor(ArmorEquipEvent e) {
		ItemMeta HArmor = e.getPlayer().getInventory().getHelmet().getItemMeta();
		if(HArmor.getLore().contains("ARMOR:")) {
			int Harmora = Integer.parseInt(HArmor.getLore().get(0).replaceAll("[^0-9]",""));
		}
	}

}
