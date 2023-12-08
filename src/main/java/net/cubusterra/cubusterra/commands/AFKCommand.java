package net.cubusterra.cubusterra.commands;

import net.cubusterra.cubusterra.CubusTerra;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class AFKCommand implements CommandExecutor, Listener {

    public List<String> afk = new ArrayList<String>();
    public List<Integer> afkDamage = new ArrayList<Integer>();
    public final CubusTerra plugin;
    public AFKCommand(CubusTerra plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(afk.contains(commandSender.getName()))
        {
            commandSender.sendMessage(ChatColor.WHITE + "Du bist nun nicht mehr AFK!");
            afkDamage.remove(afk.indexOf(commandSender.getName()));
            afk.remove(commandSender.getName());
        }
        else
        {
            if(strings.length > 0)
            {
                int health;
                try
                {
                    health = Integer.parseInt(strings[0]);
                } catch (NumberFormatException e)
                {
                    commandSender.sendMessage(ChatColor.WHITE + "Bitte gib eine gültige Halbherzenanzahl als Threshold an!");
                    return false;
                }
                if(health < 20)
                {
                    commandSender.sendMessage("Du bist nun AFK und wirst gekickt, wenn du unter oder gleich " + ChatColor.AQUA + health + ChatColor.WHITE + " Halbherzen hast!");
                    afk.add(commandSender.getName());
                    afkDamage.add(health);
                }
            }
            else
            {
                commandSender.sendMessage("Du bist nun AFK");
                afk.add(commandSender.getName());
                afkDamage.add(20);
            }
        }
        return true;
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event)
    {
        if(event.getEntity() instanceof Player)
        {
            Player player = ((Player) event.getEntity()).getPlayer();
            if(afk.contains(player.getName()) && (player.getHealth() - event.getDamage()) <= afkDamage.get(afk.indexOf(player.getName()))) {
                BukkitTask task = new PlayerKickAfterDamageTask(player).runTaskLater(plugin, 5);

            }
        }
    }

    public class PlayerKickAfterDamageTask extends BukkitRunnable {
        Player player;
        public PlayerKickAfterDamageTask(Player player)
        {
            this.player = player;
        }

        public void run()
        {
            player.kickPlayer(ChatColor.RED + "Du warst AFK und hast Schaden genommen!");
        }
    }
}


