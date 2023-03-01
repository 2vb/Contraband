package me.twovb.contraband;

import lombok.Getter;
import me.twovb.contraband.commands.ContrabandCommand;
import me.twovb.contraband.listeners.ItemPickupEvent;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public final class Contraband extends JavaPlugin {

  @Getter
  private static Contraband instance;

  private File itemsFile;
  private FileConfiguration itemsFileConfig;

  @Override
  public void onEnable() {
    instance = this;
    this.saveDefaultConfig();
    registerCommands();
    registerEvents();
    createItemsFile();
  }

  public FileConfiguration getItems() {
    return this.itemsFileConfig;
  }

  private void registerEvents() {
    PluginManager pm = getServer().getPluginManager();
    pm.registerEvents(new ItemPickupEvent(), this);
  }

  private void registerCommands() {
    getCommand("contraband").setExecutor(new ContrabandCommand());
    getCommand("contraband").setTabCompleter(new ContrabandCommand());
  }

  private void createItemsFile() {
    itemsFile = new File(getDataFolder(), "items.yml");
    if (!itemsFile.exists()) {
      itemsFile.getParentFile().mkdirs();
      saveResource("items.yml", false);
    }
    try {
      itemsFileConfig = new YamlConfiguration();
      itemsFileConfig.load(itemsFile);
    } catch (IOException | InvalidConfigurationException e) {
      e.printStackTrace();

    }
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }

  public void log(String string) {
    getLogger().log(Level.INFO, string);
  }

}
