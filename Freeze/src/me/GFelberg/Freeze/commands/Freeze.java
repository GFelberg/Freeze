package me.GFelberg.Freeze.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.GFelberg.Freeze.utils.FreezeUtils;

public class Freeze implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (command.getName().equalsIgnoreCase("freeze")) {

			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "This command can be only made by players!");
				return true;
			}

			if (!(sender.hasPermission("freeze.admin"))) {
				sender.sendMessage(ChatColor.RED + "You dont have permission to perform this command!");
				return true;
			}

			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED + "Usage: /freeze help | /freeze <player>");
				return true;
			}

			if (args.length == 1) {
				Player p = (Player) sender;
				FreezeUtils utils = new FreezeUtils();

				if (args[0].equalsIgnoreCase("reload")) {
					utils.reloadConfig(p);
					return true;
				} else if (args[0].equalsIgnoreCase("help")) {
					utils.helpPage(p);
					return true;
				}
				Player selected = Bukkit.getServer().getPlayer(args[0]);
				utils.freezePlayer(p, selected);
				return true;
			}
		}
		return true;
	}
}