package me.twovb.contraband.commands;

import me.twovb.contraband.Contraband;
import me.twovb.contraband.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ContrabandCommand implements CommandExecutor, TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 1) {
            list.add("add");
            list.add("remove");
            list.add("list");
        }
        return list;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
//    if (!(sender instanceof Player)) {
//      sender.sendMessage(Utils.translate("&cThis command can only be run in-game."));
//    }
        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage(Utils.translate(Contraband.getInstance().getConfig().getString("messages.commands.usage")));
        } else {
            switch (args[0]) {
                case "add":
                    if (args.length == 1)
                        player.sendMessage(Utils.translate(Contraband.getInstance().getConfig().getString("messages.invalid")));

                    if (Utils.validItem(args[1])) {
                        player.sendMessage(Utils.translate(Contraband.getInstance().getConfig().getString("messages.commands.add.success").replace("%item%", args[1])));
                        Utils.addToList(args[1]);
                    } else {
                        player.sendMessage(Utils.translate(Contraband.getInstance().getConfig().getString("messages.invalid")));
                    }
                    break;
                case "remove":
                    if (args.length == 1)
                        player.sendMessage(Utils.translate(Contraband.getInstance().getConfig().getString("messages.invalid")));

                    if (Utils.validItem(args[1])) {
                        player.sendMessage(Utils.translate(Contraband.getInstance().getConfig().getString("messages.commands.remove.success").replace("%item%", args[1])));
                        Utils.removeFromList(args[1]);
                    } else {
                        player.sendMessage(Utils.translate(Contraband.getInstance().getConfig().getString("messages.invalid")));
                    }
                    break;
                case "list":
                    player.sendMessage(Utils.translate(Contraband.getInstance().getConfig().getString("messages.commands.remove")));
                    break;
                default:
                    player.sendMessage(Utils.translate(Contraband.getInstance().getConfig().getString("messages.commands.usage")));
                    break;
            }
        }
        return false;
    }
}