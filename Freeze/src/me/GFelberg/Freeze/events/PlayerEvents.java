package me.GFelberg.Freeze.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockMultiPlaceEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.GFelberg.Freeze.Main;
import me.GFelberg.Freeze.data.FreezeConfig;
import me.GFelberg.Freeze.utils.FreezeUtils;

public class PlayerEvents implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {

		Player p = event.getPlayer();
		FileConfiguration custom = FreezeConfig.getConfig();
		FileConfiguration config = Main.getInstance().getConfig();

		if (!(custom.contains("FrozenPlayers." + p.getUniqueId().toString()))) {
			return;
		} else {
			if (config.getBoolean("NotifyOperators")) {
				for (Player operator : Bukkit.getServer().getOnlinePlayers()) {
					if (operator.isOp() || operator.hasPermission("freeze.notify")) {
						String msg = ChatColor.WHITE + "----------------------------";
						String msg2 = ChatColor.RED + "A frozen player is online:";
						String msg3 = ChatColor.RED + "UUID: " + ChatColor.YELLOW + p.getUniqueId();
						String msg4 = ChatColor.RED + "Name: " + ChatColor.YELLOW + p.getName();
						String msg5 = ChatColor.WHITE + "----------------------------";
						operator.sendMessage(msg + "\n" + msg2 + "\n" + msg3 + "\n" + msg4 + "\n" + msg5);
					}
				}
			}
			FreezeUtils.players.add(p.getUniqueId());
		}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {

		Player p = event.getPlayer();
		FileConfiguration config = Main.getInstance().getConfig();

		if (!(FreezeUtils.players.contains(p.getUniqueId()))) {
			return;
		} else {
			if (config.getBoolean("NotifyOperators")) {
				for (Player operator : Bukkit.getServer().getOnlinePlayers()) {
					if (operator.isOp() || operator.hasPermission("freeze.notify")) {
						String msg = ChatColor.WHITE + "----------------------------";
						String msg2 = ChatColor.RED + "A frozen player is now offline:";
						String msg3 = ChatColor.RED + "UUID: " + ChatColor.YELLOW + p.getUniqueId();
						String msg4 = ChatColor.RED + "Name: " + ChatColor.YELLOW + p.getName();
						String msg5 = ChatColor.WHITE + "----------------------------";
						operator.sendMessage(msg + "\n" + msg2 + "\n" + msg3 + "\n" + msg4 + "\n" + msg5);
					}
				}
			}
			FreezeUtils.players.remove(p.getUniqueId());
		}
	}

	@EventHandler
	public void onMove(PlayerMoveEvent event) {

		Player p = event.getPlayer();

		if (!(FreezeUtils.players.contains(p.getUniqueId()))) {
			return;
		} else {
			event.setCancelled(true);
			p.sendMessage(ChatColor.RED + "You are frozen!");
		}
	}

	@EventHandler
	public void onDropItem(PlayerDropItemEvent event) {

		Player p = event.getPlayer();
		FileConfiguration config = Main.getInstance().getConfig();

		if (!(FreezeUtils.players.contains(p.getUniqueId()))) {
			return;
		}

		if (FreezeUtils.players.contains(p.getUniqueId())) {
			if (config.getBoolean("Block.DropItem")) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPickupItem(EntityPickupItemEvent event) {

		if (!(event.getEntity() instanceof Player)) {
			return;
		}

		Player p = (Player) event.getEntity();
		FileConfiguration config = Main.getInstance().getConfig();

		if (!(FreezeUtils.players.contains(p.getUniqueId()))) {
			return;
		}

		if (FreezeUtils.players.contains(p.getUniqueId())) {
			if (config.getBoolean("Block.PickupItem")) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onBreak(BlockBreakEvent event) {

		Player p = event.getPlayer();
		FileConfiguration config = Main.getInstance().getConfig();

		if (!(FreezeUtils.players.contains(p.getUniqueId()))) {
			return;
		}

		if (FreezeUtils.players.contains(p.getUniqueId())) {
			if (config.getBoolean("Block.BreakBlocks")) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {

		Player p = event.getPlayer();
		FileConfiguration config = Main.getInstance().getConfig();

		if (!(FreezeUtils.players.contains(p.getUniqueId()))) {
			return;
		}

		if (FreezeUtils.players.contains(p.getUniqueId())) {
			if (config.getBoolean("Block.PlaceBlocks")) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onEntityPlace(EntityPlaceEvent event) {

		Player p = event.getPlayer();
		FileConfiguration config = Main.getInstance().getConfig();

		if (!(FreezeUtils.players.contains(p.getUniqueId()))) {
			return;
		}

		if (FreezeUtils.players.contains(p.getUniqueId())) {
			if (config.getBoolean("Block.EntityPlaceBlocks")) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onBlockMultiPlaceEntity(BlockMultiPlaceEvent event) {

		Player p = event.getPlayer();
		FileConfiguration config = Main.getInstance().getConfig();

		if (!(FreezeUtils.players.contains(p.getUniqueId()))) {
			return;
		}

		if (FreezeUtils.players.contains(p.getUniqueId())) {
			if (config.getBoolean("Block.MultiPlaceBlocks")) {
				event.setCancelled(true);
			}
		}
	}
}
