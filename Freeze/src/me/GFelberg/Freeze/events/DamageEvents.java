package me.GFelberg.Freeze.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import me.GFelberg.Freeze.Main;
import me.GFelberg.Freeze.utils.FreezeUtils;

public class DamageEvents implements Listener {

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {

		if (!(event.getEntity() instanceof Player)) {
			return;
		}

		if (!(event.getDamager() instanceof Player)) {
			return;
		}

		Player p = (Player) event.getEntity();
		Player damager = (Player) event.getDamager();
		FileConfiguration config = Main.getInstance().getConfig();

		if (!(FreezeUtils.players.contains(p.getUniqueId())) && FreezeUtils.players.contains(damager.getUniqueId())) {
			if (config.getBoolean("Block.PlayerDamage")) {
				event.setCancelled(true);
			}
		}

		if (FreezeUtils.players.contains(p.getUniqueId())) {
			if (config.getBoolean("Block.PlayerDamage")) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onHit(ProjectileHitEvent event) {

		if (!(event.getEntity() instanceof Player)) {
			return;
		}

		if (!(event.getHitEntity() instanceof Player) || event.getHitEntity() == null) {
			return;
		}

		Player damager = (Player) event.getEntity();
		Player p = (Player) event.getHitEntity();
		FileConfiguration config = Main.getInstance().getConfig();

		if (!(FreezeUtils.players.contains(p.getUniqueId())) && FreezeUtils.players.contains(damager.getUniqueId())) {
			if (config.getBoolean("Block.PlayerDamage")) {
				event.setCancelled(true);
			}
		}

		if (FreezeUtils.players.contains(p.getUniqueId())) {
			if (config.getBoolean("Block.PlayerDamage")) {
				event.setCancelled(true);
			}
		}
	}
}