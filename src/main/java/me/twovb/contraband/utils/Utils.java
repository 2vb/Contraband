package me.twovb.contraband.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Utils {
  public static String translate(String string) {
    return ChatColor.translateAlternateColorCodes('&', string);
  }

  public static void removeItem(Player player, Material material) {
    Inventory i = player.getInventory();
    for (ItemStack item : i.getContents()) {
      if (item != null) {
        player.getInventory().remove(item);
      }
    }
  }


}
