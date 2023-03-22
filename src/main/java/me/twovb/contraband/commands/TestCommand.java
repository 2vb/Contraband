package me.twovb.contraband.commands;

import me.twovb.contraband.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Utils.removeItems((Player) sender);
        sender.sendMessage(Utils.translate("attempt remove contraband"));
//        Utils.log("meow");
        return false;
    }
}
