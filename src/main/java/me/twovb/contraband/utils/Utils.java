package me.twovb.contraband.utils;

import me.twovb.contraband.Contraband;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Utils {
    public static String translate(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static void removeItem(Player player, Material material) {
        // @TODO fix this function removing all items in inventory
        Inventory i = player.getInventory();
        for (ItemStack item : i.getContents()) {
            if (item != null) {
                player.getInventory().remove(item);
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

}
