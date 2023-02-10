package me.twovb.contraband;

import lombok.Getter;
import me.twovb.contraband.commands.ContrabandCommand;
import me.twovb.contraband.listeners.ItemPickupEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class Contraband extends JavaPlugin {

    @Getter
    private static Contraband instance;

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        registerCommands();
        registerEvents();
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ItemPickupEvent(), this);
    }

    private void registerCommands() {
        getCommand("contraband").setExecutor(new ContrabandCommand());
        getCommand("contraband").setTabCompleter(new ContrabandCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void log(String string) {
       getLogger().log(Level.INFO, string);
    }

}
