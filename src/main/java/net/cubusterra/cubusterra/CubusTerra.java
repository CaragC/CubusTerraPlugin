package net.cubusterra.cubusterra;

import net.cubusterra.cubusterra.commands.AFKCommand;
import net.cubusterra.cubusterra.listeners.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public final class CubusTerra extends JavaPlugin {

    @Override
    public void onEnable() {
        AFKCommand afkCommand = new AFKCommand(this );
        ListenerRegistration(new Listener[]{afkCommand, new JoinListener()});
        CommandRegistration(new CommandExecutor[]{afkCommand});
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void ListenerRegistration(Listener[] listeners) {
        PluginManager pluginManager = Bukkit.getPluginManager();
        for (Listener listener : listeners) {
            pluginManager.registerEvents(listener, this);
        }
    }

    private void CommandRegistration(CommandExecutor[] commandExecutors) {
        getCommand("afk").setExecutor(commandExecutors[0]);
    }
}
