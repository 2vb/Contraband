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

    public static void removeItem(Player player) {
        // @TODO fix this function removing all items in inventory
        Inventory i = player.getInventory();
        for (ItemStack item : i.getContents()) {
            if (item != null && disallowed(String.valueOf(item))) {
                player.getInventory().remove(item);
                player.sendMessage(translate("remove ${item}").replace("${item}", String.valueOf(item)));
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

    public static boolean disallowed(String string) {
        List<String> itemlist = Contraband.getInstance().getConfig().getStringList("items");
        for (String item : itemlist) {
            if (item != string) {
                Contraband.getInstance().log(item);
                return true;
            }
        }
        return false;
    }

}
