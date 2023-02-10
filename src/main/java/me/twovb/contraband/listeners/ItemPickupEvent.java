package me.twovb.contraband.listeners;

import me.twovb.contraband.Contraband;
import me.twovb.contraband.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import java.util.List;
import java.util.logging.Level;

public class ItemPickupEvent implements Listener {

//    @EventHandler
//    public void onItemPickup(PlayerPickupItemEvent event) {
//        Player player = event.getPlayer();
//        Material itemType = event.getItem().getItemStack().getType();
//        for (String droppedItem : Contraband.getInstance().getConfig().getStringList("items")) {
//            if (Material.getMaterial(droppedItem) == itemType) return;
//            Utils.removeItem(player, itemType);
//            event.getItem().remove();
//            player.sendMessage(Utils.translate(Contraband.getInstance().getConfig().getString("messages.pickup")));
//            Contraband.getInstance().getLogger().log(Level.INFO, "removed from player");
//            Contraband.getInstance().getLogger().log(Level.INFO, String.valueOf(Contraband.getInstance().getConfig().getStringList("items")));
//            event.setCancelled(true);
//        }
//    }

    //TODO Amount of messages sent depends on where in the array the item is ([BEDROCK, BARRIER, GRASS_BLOCK, STONE, COBBLESTONE]) stone will display 5 times, first value is ignored.

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        LivingEntity player = event.getEntity();
        Material item = event.getItem().getItemStack().getType();
        List<String> contrabandList = Contraband.getInstance().getConfig().getStringList("items");
        for (String droppedItem : contrabandList) {
            if (Material.getMaterial(droppedItem) == item) return;
            Utils.removeItem((Player) player, Material.valueOf(droppedItem));
            event.getItem().remove();
            player.sendMessage(Utils.translate(Contraband.getInstance().getConfig().getString("messages.pickup")));
            Contraband.getInstance().getLogger().log(Level.INFO, "removed from player");
            Contraband.getInstance().getLogger().log(Level.INFO, String.valueOf(Contraband.getInstance().getConfig().getStringList("items")));
            event.setCancelled(true);
        }
    }

}
