package me.twovb.contraband.listeners;

import me.twovb.contraband.Contraband;
import me.twovb.contraband.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class ItemPickupEvent implements Listener {
  @EventHandler
  public void onItemPickup(EntityPickupItemEvent event) {
    Player player = (Player) event.getEntity();
    Material itemType = event.getItem().getItemStack().getType();
    for (String droppedItem : Contraband.getInstance().getConfig().getStringList("items")) {
      if (Material.getMaterial(droppedItem) == itemType) {
        player.sendMessage(
            Utils.translate(Contraband.getInstance().getConfig().getString("messages.pickup")));
      }
    }
    Utils.removeItem(player, itemType);
  }
}
