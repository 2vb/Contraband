package me.twovb.contraband;

import lombok.Getter;
//import me.twovb.contraband.commands.TestCommand;
import me.twovb.contraband.listeners.ItemPickupEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Contraband extends JavaPlugin {

    @Getter
    private static Contraband instance;

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
     //   registerCommands();
        registerEvents();
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ItemPickupEvent(), this);
    }

//    private void registerCommands() {
  //      getCommand("test").setExecutor(new TestCommand());
    //    getCommand("test").setTabCompleter(new TestCommand());
    //}

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
