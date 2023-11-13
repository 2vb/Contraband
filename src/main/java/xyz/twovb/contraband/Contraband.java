package xyz.twovb.contraband;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.twovb.contraband.commands.ContrabandCommand;
import xyz.twovb.contraband.listeners.ItemPickupEvent;
import xyz.twovb.contraband.utils.Utils;

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
        loadItems();
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            if (Bukkit.getOnlinePlayers().isEmpty()) return;
            if (!(Contraband.getInstance().getConfig().getBoolean("enabled"))) return;
            for (Player player : Bukkit.getOnlinePlayers()) {
                Inventory i = player.getInventory();
                for (ItemStack item : i.getContents()) {
                    if (item == null) continue;
                    if (!Utils.disallowed(item)) continue;
                    Utils.removeItems(player);
                    player.sendMessage(Utils.translate(getConfig().getString("messages.detect")));
                    return;
                }
            }
        }, 0, getConfig().getInt("minutes-between-checks") * 1200L);
    }

    public FileConfiguration getItems() {
        return this.itemsFileConfig;
    }

    public void saveItems() {
        try {
            getItems().save(this.itemsFile);
        } catch (IOException var2) {
            this.getLogger().log(Level.SEVERE, "Could not save config to " + this.itemsFile, var2);
        }
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ItemPickupEvent(), this);
//        pm.registerEvents(new InventoryListener(), this);
    }

    private void registerCommands() {
        getCommand("contraband").setExecutor(new ContrabandCommand());
        getCommand("contraband").setTabCompleter(new ContrabandCommand());
//        getCommand("test").setExecutor(new TestCommand());
    }

    private void loadItems() {
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

}
