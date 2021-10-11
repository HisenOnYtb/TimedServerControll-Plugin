package me.hisen.serverservercontrol.commands;

import me.hisen.serverservercontrol.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class timedstop implements CommandExecutor {

    Main plugin;
    public timedstop(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            if(args.length == 0){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix") + "&7You have to specify a time"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',  plugin.getConfig().getString("prefix") + "&7Usage: &6/tstop <time>"));
            }

            if(args.length == 1){
                for(Player allplayers : Bukkit.getServer().getOnlinePlayers()){
                    Integer cislo = Integer.parseInt(args[0]);
                    allplayers.sendTitle(ChatColor.translateAlternateColorCodes('&', "&c&lSTOP"), ChatColor.translateAlternateColorCodes('&', "&7The server will be stopped in " + args[0] + " seconds"), 20, 80, 20);
                    allplayers.playSound(allplayers.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 50, 3);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        @Override
                        public void run() {
                            allplayers.kickPlayer(ChatColor.translateAlternateColorCodes('&', "&6&l&nAstreans\n\n&7       The server is stopping\n&7Please reconnect in few minutes"));
                        }
                    }, cislo * 20); //20 Tick (1 Second) delay before run() is called
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        @Override
                        public void run() {
                            Bukkit.getServer().shutdown();
                        }
                    }, cislo * 20 - 20) ; //20 Tick (1 Second) delay before run() is called
                }
            }

        }

        return true;
    }
}
