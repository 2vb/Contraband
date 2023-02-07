package me.twovb.contraband.commands;

import me.twovb.contraband.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TestCommand implements CommandExecutor, TabCompleter {

  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String alias,
      String[] args) {
    List<String> list = new ArrayList<>();
    if (args.length == 1) {
      list.add("1");
      list.add("2");
    }
    return list;
  }

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
      @NotNull String label, @NotNull String[] args) {
    Player player = (Player) sender;
    if (args.length == 0) {
      player.sendMessage(Utils.translate("yo"));
    } else {
      switch (args[0]) {
        case "1":
          player.sendMessage(Utils.translate("1"));
          break;
        case "2":
          player.sendMessage(Utils.translate("2"));
          break;
        default:
          player.sendMessage(Utils.translate("yo"));
          break;
      }
    }
    return false;
  }
}
