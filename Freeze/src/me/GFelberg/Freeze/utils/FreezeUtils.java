package me.GFelberg.Freeze.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.GFelberg.Freeze.Main;

public class FreezeUtils {

	public static List<UUID> players = new ArrayList<UUID>();
	public static String prefix, freezeoff, freezeon, freezeoffadmin, freezeonadmin, playernotfound;
	public static String alreadyfrozen, alreadyunfrozen;

	public static void loadVariables() {
		prefix = Main.getInstance().getConfig().getString("Freeze.Prefix").replace("&", "§");
		freezeon = Main.getInstance().getConfig().getString("Freeze.Enabled").replace("&", "§");
		freezeoff = Main.getInstance().getConfig().getString("Freeze.Disabled").replace("&", "§");
		freezeonadmin = Main.getInstance().getConfig().getString("Freeze.AdminOn").replace("&", "§");
		freezeoffadmin = Main.getInstance().getConfig().getString("Freeze.AdminOff").replace("&", "§");
		alreadyfrozen = Main.getInstance().getConfig().getString("Freeze.AlreadyFrozen").replace("&", "§");
		alreadyunfrozen = Main.getInstance().getConfig().getString("Freeze.AlreadyUnfrozen").replace("&", "§");
		playernotfound = Main.getInstance().getConfig().getString("Freeze.PlayerNotFound").replace("&", "§");
	}

	public void reloadConfig(Player p) {

		if (!(p.hasPermission("freeze.reload"))) {
			p.sendMessage(ChatColor.RED + "You dont have permission to perform this command!");
		} else {
			Main.getInstance().reloadConfig();
			loadVariables();
			p.sendMessage(prefix + " " + ChatColor.GREEN + "Plugin reloaded successfully!");
			Bukkit.getConsoleSender().sendMessage("========================================");
			Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Freeze Plugin reloaded");
			Bukkit.getConsoleSender().sendMessage("========================================");
		}
	}

	public void helpPage(Player p) {
		HelpPageUtils helpUtils = new HelpPageUtils();

		if (p.hasPermission("freeze.admin")) {
			p.sendMessage(ChatColor.WHITE + "-----------------------------------------");
			p.sendMessage(ChatColor.AQUA + "Freeze - Help Commands");
			p.sendMessage(ChatColor.YELLOW + "/freeze help: " + helpUtils.getHelp_page());
			p.sendMessage(ChatColor.YELLOW + "/freeze <player>: " + helpUtils.getHelp_freezePlayer());
			p.sendMessage(ChatColor.YELLOW + "/unfreeze <player> : " + helpUtils.getHelp_unfreezePlayer());
			p.sendMessage(ChatColor.YELLOW + "/freeze reload : " + helpUtils.getHelp_reload());
			p.sendMessage(ChatColor.WHITE + "-----------------------------------------");
		}
	}

	public void freezePlayer(Player p, Player selected) {

		if (selected == null) {
			p.sendMessage(playernotfound);
			return;
		}

		FileConfiguration config = Main.data.playerscfg;

		if (!(players.contains(selected.getUniqueId()))) {
			config.set("FrozenPlayers." + "UUID." + selected.getUniqueId().toString(), selected.getName());
			Main.data.savePlayers();
			Main.data.reloadPlayers();
			players.add(selected.getUniqueId());
			selected.sendMessage(freezeon);
			p.sendMessage(freezeonadmin);
		} else {
			p.sendMessage(alreadyfrozen);
		}
	}

	public void unfreezePlayer(Player p, Player selected) {

		if (selected == null) {
			p.sendMessage(playernotfound);
			return;
		}

		FileConfiguration config = Main.data.playerscfg;

		if (players.contains(selected.getUniqueId())) {
			config.set("FrozenPlayers." + "UUID." + selected.getUniqueId().toString(), null);
			Main.data.savePlayers();
			Main.data.reloadPlayers();
			players.remove(selected.getUniqueId());
			selected.sendMessage(freezeoff);
			p.sendMessage(freezeoffadmin);
		} else {
			p.sendMessage(alreadyunfrozen);
		}
	}
}