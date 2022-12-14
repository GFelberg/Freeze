package me.GFelberg.Freeze;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.GFelberg.Freeze.commands.Freeze;
import me.GFelberg.Freeze.commands.Unfreeze;
import me.GFelberg.Freeze.data.FreezeConfig;
import me.GFelberg.Freeze.events.DamageEvents;
import me.GFelberg.Freeze.events.PlayerEvents;
import me.GFelberg.Freeze.utils.FreezeUtils;

public class Main extends JavaPlugin {

	private static Main instance;

	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		loadFreezeConfig();
		loadPlayers();
		loadCommands();
		loadEvents();
		FreezeUtils.loadVariables();
		Bukkit.getConsoleSender().sendMessage("----------------------------");
		Bukkit.getConsoleSender().sendMessage("Freeze Plugin Enabled!");
		Bukkit.getConsoleSender().sendMessage("Plugin developed by GFelberg");
		Bukkit.getConsoleSender().sendMessage("----------------------------");
	}

	public static Main getInstance() {
		return instance;
	}

	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("----------------------------");
		Bukkit.getConsoleSender().sendMessage("Freeze Plugin Disabled!");
		Bukkit.getConsoleSender().sendMessage("Plugin developed by GFelberg");
		Bukkit.getConsoleSender().sendMessage("----------------------------");
	}

	public void loadFreezeConfig() {
		FreezeConfig.setupConfig();
		FreezeConfig.getConfig().options().copyDefaults(true);
		FreezeConfig.saveConfig();
	}

	public void loadCommands() {
		getCommand("freeze").setExecutor(new Freeze());
		getCommand("unfreeze").setExecutor(new Unfreeze());
	}

	public void loadEvents() {
		Bukkit.getPluginManager().registerEvents(new DamageEvents(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerEvents(), this);
	}

	public void loadPlayers() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (FreezeConfig.getConfig().contains("FrozenPlayers." + p.getUniqueId().toString())) {
				FreezeUtils.players.add(p.getUniqueId());
			}
		}
	}
}