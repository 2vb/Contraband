package xyz.twovb.contraband.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xyz.twovb.contraband.Contraband;

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
            if (!disallowed(item)) continue;
            i.remove(item);
        }
    }

    public static boolean validItem(String string) {
        return Material.matchMaterial(string) != null;
    }

    public static void addOrRemoveFromList(String string) {
        List<String> items = Contraband.getInstance().getItems().getStringList("items");
        if (items.contains(string)) {
            items.remove(string);
        } else {
            items.add(string);
        }
        Contraband.getInstance().getItems().set("items", items);
        Contraband.getInstance().saveItems();
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
