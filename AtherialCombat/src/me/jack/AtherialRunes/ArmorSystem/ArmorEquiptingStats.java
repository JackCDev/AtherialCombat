package me.jack.AtherialRunes.ArmorSystem;

import java.awt.List;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.jack.AtherialRunes.libs.ArmorEquipEvent.ArmorEquipEvent;
import me.jack.AtherialRunes.libs.ArmorEquipEvent.ArmorType;

public class ArmorEquiptingStats implements Listener {
	
	public HashMap<UUID, Integer> armor = new HashMap<UUID, Integer>();
	
	
	
	@SuppressWarnings({ "unused", "resource" })
	@EventHandler
	
	public void onEquiptArmorArmor(ArmorEquipEvent e) {
		UUID pu = e.getPlayer().getUniqueId();
		if (e.getNewArmorPiece() != null && e.getNewArmorPiece().hasItemMeta()) {
		ItemStack helmet = e.getNewArmorPiece();
        ItemMeta helmetMeta = helmet.getItemMeta();
        java.util.List<String> bootsLore = helmetMeta.getLore();

        int loreSize = helmetMeta.getLore().size();
        int place = Integer.parseInt(bootsLore.get(1));
       
        String ArmorLore = helmetMeta.getLore().get(place);
        Scanner in = new Scanner(ArmorLore).useDelimiter("[^0-9]+");
        int Armor = in.nextInt();
        int CurrentArm = armor.get(pu);
        armor.put(pu, CurrentArm + Armor);
		}
		if (e.getOldArmorPiece() != null && e.getOldArmorPiece().hasItemMeta()) {
			ItemStack helmet = e.getOldArmorPiece();
	        ItemMeta helmetMeta = helmet.getItemMeta();
	        java.util.List<String> bootsLore = helmetMeta.getLore();

	        int loreSize = helmetMeta.getLore().size();
	        int place = Integer.parseInt(bootsLore.get(1));
	       
	        String ArmorLore = helmetMeta.getLore().get(place);
	        Scanner in = new Scanner(ArmorLore).useDelimiter("[^0-9]+");
	        int Armor = in.nextInt();
	        int CurrentArm = armor.get(pu);
	        armor.put(pu, CurrentArm - Armor);
	        
			
		}
	}
	
        
	}


