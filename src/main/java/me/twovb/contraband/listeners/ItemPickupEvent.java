package me.twovb.contraband.listeners;

import me.twovb.contraband.Contraband;
import me.twovb.contraband.utils.CC;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class ItemPickupEvent implements Listener {
    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
    Player player = (Player) event.getEntity();
    Material item = event.getItem().getItemStack().getType();
    player.sendMessage(String.valueOf(item));
  }
}
