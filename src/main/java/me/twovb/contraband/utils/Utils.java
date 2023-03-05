package me.twovb.contraband.utils;

import me.twovb.contraband.Contraband;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Level;
import java.util.List;

public class Utils {

  public static String translate(String string) {
    return ChatColor.translateAlternateColorCodes('&', string);
  }

  // public static void removeItems(Player player, ItemStack item) {
  // Inventory inv = player.getInventory();
  // Contraband.getInstance().log("attempt remove " + item);
  // for (ItemStack is : inv.getContents()) {
  // inv.removeItem(is);
  // Contraband.getInstance().log("remove " + item);
  // }
  // }

  public static void removeItems(Player player) {
    // @TODO fix this function removing all items in inventory
    Inventory i = player.getInventory();
    for (ItemStack item : i.getContents()) {
      if (item != null) {
        if (disallowed(String.valueOf(item.getType()))) {
          log(item.getType() + " disallowed");
          i.removeItem(item);
          log("remove " + item.getType());
        }
        log(String.valueOf(item.getType()));
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
    // List<String> itemlist =
    // Contraband.getInstance().getConfig().getStringList("items");
    List<String> itemlist = Contraband.getInstance().getItems().getStringList("items");
    for (String item : itemlist) {
      if (item != string) {
        log("item " + item + ": string " + string);
        return true;
      }
    }
    return false;
  }

  public static void log(String string) {
    Contraband.getInstance().getLogger().log(Level.INFO, string);
  }

}
