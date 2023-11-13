package xyz.twovb.contraband.listeners;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import xyz.twovb.contraband.Contraband;
import xyz.twovb.contraband.utils.Utils;

import java.util.List;

public class ItemPickupEvent implements Listener {

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (!(Contraband.getInstance().getConfig().getBoolean("enabled"))) return;
        LivingEntity player = event.getEntity();
        if (player.hasPermission("contraband.bypass")) return;
        Material item = event.getItem().getItemStack().getType();
        List<String> contrabandList = Contraband.getInstance().getItems().getStringList("items");
        for (String droppedItem : contrabandList) {
            if (Material.getMaterial(droppedItem) != item) continue;
            Utils.removeItems((Player) player);
            event.getItem().remove();
            player.sendMessage(Utils.translate(Contraband.getInstance().getConfig().getString("messages.detect")));
            event.setCancelled(true);
        }
    }

}
