package me.twovb.contraband.listeners;

import me.twovb.contraband.Contraband;
import me.twovb.contraband.utils.Utils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryMove(InventoryMoveItemEvent event) {
        ItemStack item = event.getItem();
        List<String> contrabandList = Contraband.getInstance().getItems().getStringList("items");
        for (String droppedItem : contrabandList) {
            if (Material.getMaterial(droppedItem) != item.getType()) continue;
            Utils.log("attempt move " + item);
        }
    }

}
