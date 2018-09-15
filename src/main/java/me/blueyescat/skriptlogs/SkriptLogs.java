package me.blueyescat.skriptlogs;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;

import me.blueyescat.skriptlogs.util.LogAppender;
import me.blueyescat.skriptlogs.util.Metrics;

import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class SkriptLogs extends JavaPlugin {

    private static SkriptLogs instance;
    private static SkriptAddon addonInstance;
	private static AbstractAppender logAppender;

    public SkriptLogs() {
        if (instance == null) {
            instance = this;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void onEnable() {
        if (getServer().getPluginManager().getPlugin("Skript") != null) {
            // Addon
            try {
                SkriptAddon addonInstance = Skript.registerAddon(this).setLanguageFileDirectory("lang");;
                addonInstance.loadClasses("me.blueyescat.skriptlogs", "skript");
            } catch (IOException e) {
                e.printStackTrace();
            }

			new LogAppender().start();

            // Metrics
            getLogger().info("Starting metrics...");
            Metrics metrics = new Metrics(getInstance());
            metrics.addCustomChart(new Metrics.SimplePie("skript_version", () ->
                    getServer().getPluginManager().getPlugin("Skript").getDescription().getVersion()));
            getLogger().info("And done!");
        } else {
            getServer().getPluginManager().disablePlugin(this);
            getLogger().severe("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            getLogger().severe("Skript not found, plugin is disabled.");
            getLogger().severe("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        }
        getLogger().info("Finished loading!");
    }

    public static SkriptAddon getAddonInstance() {
        if (addonInstance == null) {
            addonInstance = Skript.registerAddon(getInstance());
        }
        return addonInstance;
    }

    public static SkriptLogs getInstance() {
        if (instance == null) {
            throw new IllegalStateException();
        }
        return instance;
    }

}
