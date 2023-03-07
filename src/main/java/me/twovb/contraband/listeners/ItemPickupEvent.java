package me.twovb.contraband.listeners;

import me.twovb.contraband.Contraband;
import me.twovb.contraband.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

import java.util.List;

public class ItemPickupEvent implements Listener {

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (!(Contraband.getInstance().getConfig().getBoolean("enabled"))) return;
        LivingEntity player = event.getEntity();
        Material item = event.getItem().getItemStack().getType();
        List<String> contrabandList = Contraband.getInstance().getItems().getStringList("items");
        for (String droppedItem : contrabandList) {
            if (Material.getMaterial(droppedItem) != item) continue;
            Utils.removeItems((Player) player);
//            Utils.log(item + " event class smilew");
            event.getItem().remove();
            player.sendMessage(Utils.translate(Contraband.getInstance().getConfig().getString("messages.pickup")));
            event.setCancelled(true);
        }
    }

}
