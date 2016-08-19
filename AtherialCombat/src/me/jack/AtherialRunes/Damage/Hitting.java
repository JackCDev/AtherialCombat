package me.jack.AtherialRunes.Damage;

import java.awt.List;
import java.util.HashMap;
import java.util.Scanner;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.jack.AtherialRunes.Utils.SettingsManager;

public class Hitting implements Listener {
	
	
	public HashMap<UUID, Integer> damage = new HashMap<UUID, Integer>();
	
	
	public SettingsManager settings;
	
	
	
	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {
		
		if(e.getDamager() instanceof Player && (e.getDamager() != null)) {
			Player p = (Player) e.getDamager();
			UUID pu = p.getUniqueId();
			ItemStack weapon = p.getInventory().getItemInMainHand();
	        ItemMeta weaponMeta = weapon.getItemMeta();
	        java.util.List<String> weaponLore = weaponMeta.getLore();
	        int place = Integer.parseInt(weaponLore.get(1));
	       
	        String wepLore = weaponMeta.getLore().get(place);
	        Scanner in = new Scanner(wepLore).useDelimiter("[^0-9]+");
	        int Damage = in.nextInt();
	        damage.put(pu, Damage);
	        e.setCancelled(true);
		}
	}
	@EventHandler
	public void onPlayerHit(EntityDamageByEntityEvent e) {
		String player = e.getDamager().getName();
		if(e.getDamager() instanceof Player) {
			if(e.getEntity() instanceof Player) {
				if(settings.getConfig().get("Players." + player + ".TogglePVP") == "true") {
					e.setDamage(damage.get(e.getDamager().getUniqueId()));
					damage.remove(e.getDamager().getUniqueId());
				}else{
					e.setCancelled(true);
					e.getDamager().sendMessage(ChatColor.YELLOW + "You have PvP toggled off so you did not do any damage to " + player + "!");
					damage.remove(e.getDamager().getUniqueId());
				}
			}
		}
		if(e.getEntity() instanceof LivingEntity) {
			if(!(e.getEntity() instanceof Player)) {
				if(e.getDamager() instanceof Player) {
					e.setDamage(damage.get(e.getDamager().getUniqueId()));
					damage.remove(e.getDamager().getUniqueId());
				}
			}
			
	        
			
		}
	}
	

}
