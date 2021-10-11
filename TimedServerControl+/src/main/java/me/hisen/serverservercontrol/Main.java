package me.hisen.serverservercontrol;

import me.hisen.serverservercontrol.commands.timedreload;
import me.hisen.serverservercontrol.commands.timedrestart;
import me.hisen.serverservercontrol.commands.timedstop;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("tstop").setExecutor(new timedstop(this));
        getCommand("trestart").setExecutor(new timedrestart(this));
        getCommand("treload").setExecutor(new timedreload(this));

    }
}
