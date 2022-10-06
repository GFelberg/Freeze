package me.GFelberg.Freeze.utils;

import me.GFelberg.Freeze.Main;

public class HelpPageUtils {

	public String getHelp_page() {
		return Main.getInstance().getConfig().getString("Help.Page").replace("&", "§");
	}

	public String getHelp_freezePlayer() {
		return Main.getInstance().getConfig().getString("Help.FreezePlayer").replace("&", "§");
	}
	
	public String getHelp_unfreezePlayer() {
		return Main.getInstance().getConfig().getString("Help.UnfreezePlayer").replace("&", "§");
	}

	public String getHelp_reload() {
		return Main.getInstance().getConfig().getString("Help.Reload").replace("&", "§");
	}
}