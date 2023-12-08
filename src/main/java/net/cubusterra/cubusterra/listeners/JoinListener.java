package net.cubusterra.cubusterra.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void OnJoin(PlayerJoinEvent event)
    {
        event.setJoinMessage(ChatColor.YELLOW + "Der Spieler " + ChatColor.AQUA + event.getPlayer().getDisplayName() + ChatColor.YELLOW + " betritt den Server!");
    }

    @EventHandler
    public void OnLeave(PlayerQuitEvent event)
    {
        event.setQuitMessage(ChatColor.YELLOW + "Der Spieler " + ChatColor.AQUA + event.getPlayer().getDisplayName() + ChatColor.YELLOW + " hat den Server verlassen!");
    }
}
