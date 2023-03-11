package me.twovb.contraband.utils;

import me.twovb.contraband.Contraband;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.logging.Level;

public class Utils {

    public static String translate(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static void removeItems(Player player) {
        Inventory i = player.getInventory();
        for (ItemStack item : i.getContents()) {
            if (item == null) return;
            if (disallowed(item)) {
                i.remove(item);
            }
        }
    }

    public static boolean validItem(String string) {
        return Material.matchMaterial(string) != null;
    }

    public static void addToList(String string) {
        List<String> items = Contraband.getInstance().getConfig().getStringList("items");
        items.add(string);
        Contraband.getInstance().getConfig().set("items", items);
        Contraband.getInstance().saveConfig();
        Contraband.getInstance().reloadConfig();
    }

    public static void removeFromList(String string) {
        List<String> items = Contraband.getInstance().getConfig().getStringList("items");
        items.remove(string);
        Contraband.getInstance().getConfig().set("items", items);
        Contraband.getInstance().saveConfig();
        Contraband.getInstance().reloadConfig();
    }

    public static boolean disallowed(ItemStack is) {
        List<String> itemlist = Contraband.getInstance().getItems().getStringList("items");
        for (String item : itemlist) {
            if (item != is.getType().toString()) {
                if (Material.getMaterial(item) != is.getType()) continue;
                return true;
            }
        }
        return false;
    }

    public static void log(String string) {
        Contraband.getInstance().getLogger().log(Level.INFO, string);
    }

}
