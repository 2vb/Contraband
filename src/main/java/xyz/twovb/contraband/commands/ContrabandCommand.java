package xyz.twovb.contraband.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import xyz.twovb.contraband.Contraband;
import xyz.twovb.contraband.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContrabandCommand implements CommandExecutor, TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> subcommands = new ArrayList<>();
        if (args.length == 1) {
            subcommands.add("add");
            subcommands.add("remove");
            subcommands.add("toggle");
        }
        return subcommands;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("contraband.manage")) {
            sender.sendMessage(Utils.translate(Contraband.getInstance().getConfig().getString("messages.commands.no-permissions")));
        }
        if (args.length == 0) {
            sender.sendMessage(Utils.translate(Contraband.getInstance().getConfig().getString("messages.commands.usage")));
        } else {
            switch (args[0]) {
                case "add":
                    if (args.length == 1)
                    sender.sendMessage(Utils.translate(Contraband.getInstance().getConfig().getString("messages.invalid")));
                if (Utils.validItem(args[1])) {
                    sender.sendMessage(Utils.translate(Contraband.getInstance().getConfig().getString("messages.commands.add").replace("%item%", args[1].toUpperCase())));
                    Utils.addOrRemoveFromList(args[1].toUpperCase());
                } else {
                    sender.sendMessage(Utils.translate(Contraband.getInstance().getConfig().getString("messages.invalid")));
                }
                break;
                case "remove":
                    if (args.length == 1)
                        sender.sendMessage(Utils.translate(Contraband.getInstance().getConfig().getString("messages.invalid")));
                    if (Utils.validItem(args[1])) {
                        sender.sendMessage(Utils.translate(Contraband.getInstance().getConfig().getString("messages.commands.remove").replace("%item%", args[1].toUpperCase())));
                        Utils.addOrRemoveFromList(args[1].toUpperCase());
                    } else {
                        sender.sendMessage(Utils.translate(Contraband.getInstance().getConfig().getString("messages.invalid")));
                    }
                    break;
                case "toggle":
                    boolean enabled = Contraband.getInstance().getConfig().getBoolean("enabled");
                    Contraband.getInstance().getConfig().set("enabled", !enabled);
                    Contraband.getInstance().saveConfig();
                    Contraband.getInstance().reloadConfig();
                    sender.sendMessage(Utils.translate((enabled ? Contraband.getInstance().getConfig().getString("messages.commands.toggle.disable") : Contraband.getInstance().getConfig().getString("messages.commands.toggle.enable"))));
                    break;
                default:
                    sender.sendMessage(Utils.translate(Contraband.getInstance().getConfig().getString("messages.commands.usage")));
                    break;
            }
        }
        return false;
    }
}
