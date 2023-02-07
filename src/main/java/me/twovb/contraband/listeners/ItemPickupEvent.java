package me.twovb.contraband.listeners;

import me.twovb.contraband.Contraband;
import me.twovb.contraband.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.entity.Player;
import org.bukkit.Material;

public class ItemPickupEvent implements Listener {
  @EventHandler
  public void onItemPickup(PlayerPickupItemEvent event) {
    Player player = event.getPlayer();
    Material itemType = event.getItem().getItemStack().getType();
    for (String droppedItem : Contraband.getInstance().getConfig().getStringList("items")) {
      if (Material.getMaterial(droppedItem) == itemType) {
        player.sendMessage(
            Utils.translate(Contraband.getInstance().getConfig().getString("messages.pickup")));
      }
    }
    Utils.removeItem(player, itemType);
    event.setCancelled(true);
    event.getItem().remove();
  }
}
