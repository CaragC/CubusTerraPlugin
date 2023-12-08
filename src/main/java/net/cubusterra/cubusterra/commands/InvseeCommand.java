package net.cubusterra.cubusterra.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvseeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender.isOp())
        {
            Player player = (Player) commandSender;
            player.openInventory(Bukkit.getPlayer(strings[0]).getInventory());
        }
        return true;
    }
}
