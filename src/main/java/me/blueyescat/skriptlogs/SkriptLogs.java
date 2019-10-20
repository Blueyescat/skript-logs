package me.blueyescat.skriptlogs;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;

import me.blueyescat.skriptlogs.util.LogAppender;
import me.blueyescat.skriptlogs.util.Metrics;

public class SkriptLogs extends JavaPlugin {

	private static SkriptLogs instance;
	private static SkriptAddon addonInstance;

	public SkriptLogs() {
		if (instance == null) {
			instance = this;
		} else {
			throw new IllegalStateException();
		}
	}

	@Override
	public void onEnable() {
		if (!Skript.isAcceptRegistrations()) {
			getServer().getPluginManager().disablePlugin(this);
			getLogger().severe("skript-logs can't be loaded when the server is already loaded! Plugin is disabled.");
			return;
		}

		try {
			SkriptAddon addonInstance = Skript.registerAddon(this).setLanguageFileDirectory("lang");
			addonInstance.loadClasses("me.blueyescat.skriptlogs", "skript");
		} catch (IOException e) {
			e.printStackTrace();
		}

		new LogAppender().start();

		Metrics metrics = new Metrics(getInstance());
		metrics.addCustomChart(new Metrics.SimplePie("skript_version", () ->
				Skript.getInstance().getDescription().getVersion()));
		getLogger().info("Started metrics!");
		getLogger().info("Finished loading!");
	}

	public static SkriptLogs getInstance() {
		if (instance == null) {
			throw new IllegalStateException();
		}
		return instance;
	}

	public static SkriptAddon getAddonInstance() {
		if (addonInstance == null) {
			addonInstance = Skript.registerAddon(getInstance());
		}
		return addonInstance;
	}

}
