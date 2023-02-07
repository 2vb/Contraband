package me.twovb.contraband.listeners;

import me.twovb.contraband.Contraband;
import me.twovb.contraband.utils.CC;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class ItemPickupEvent implements Listener {
  @EventHandler
  public void onItemPickup(EntityPickupItemEvent event) {
    Player player = (Player) event.getEntity();
    Material item = event.getItem().getItemStack().getType();
    // player.sendMessage(String.valueOf(item));
    for (String droppedItem : Contraband.getInstance().getConfig().getStringList("items")) {
      if (Material.getMaterial(droppedItem) == item) {
        player.sendMessage(CC.translate(Contraband.getInstance().getConfig().getString("messages.pickup")));
      }
    }
  }
}
