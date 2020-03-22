package com.github.StevenDesroches.azaxys_commands.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.player.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogListener implements org.bukkit.event.Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("builderpro") || player.hasPermission("questeur")) {
            logToFile("[onPlayerChat] " + event.getMessage(), player.getName());
        }
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("builderpro") || player.hasPermission("questeur")) {
            logToFile("[onPlayerCommand] " + event.getMessage(), player.getName());
        }
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("builderpro") || player.hasPermission("questeur")) {
            Location locFrom = event.getFrom();
            Location locTo = event.getTo();
            logToFile("[onPlayerTeleport][from] " + locFrom.getBlockX() + " " + locFrom.getBlockY() + " "
                    + locFrom.getBlockZ() + " " + locFrom.getWorld(), player.getName());
            logToFile("[onPlayerTeleport][to] " + locTo.getBlockX() + " " + locTo.getBlockY() + " "
                    + locTo.getBlockZ() + " " + locTo.getWorld(), player.getName());
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("builderpro")) {
            logToFile("[PlayerQuitEvent][disconnect]", player.getName());
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            Bukkit.dispatchCommand(console, "gm 0 " + player.getName());
            Bukkit.dispatchCommand(console, "lp user " + player.getName() + " parent remove builderpro");
        }
        if (player.hasPermission("questeur")) {
            logToFile("[PlayerQuitEvent][disconnect]", player.getName());
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            Bukkit.dispatchCommand(console, "gm 0 " + player.getName());
            Bukkit.dispatchCommand(console, "lp user " + player.getName() + " parent remove questeur");
        }
    }

    @EventHandler
    public void onInventoryCreativeEvent(InventoryCreativeEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (player.hasPermission("builderpro") || player.hasPermission("questeur")) {
            logToFile("[InventoryCreativeEvent][Action] " + event.getAction().toString(), player.getName());
            logToFile("[InventoryCreativeEvent][Item On Cursor] " + event.getCursor().getType().toString(), player.getName());
            logToFile("[InventoryCreativeEvent][Current Item] " + event.getCurrentItem().getType().toString(), player.getName());
        }
    }

    @EventHandler
    public void onPlayerDropItemEvent(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("builderpro") || player.hasPermission("questeur")) {
            logToFile("[PlayerDropItemEvent] " + event.getItemDrop().getItemStack().getType().toString(), player.getName());
        }
    }

    public void logToFile(String message, String playerName) {
        try {
            File dataFolder = Bukkit.getServer().getPluginManager().getPlugin("azaxys_commands").getDataFolder();
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }

            File saveTo = new File(Bukkit.getServer().getPluginManager().getPlugin("azaxys_commands").getDataFolder(), playerName + "-log.txt");
            if (!saveTo.exists()) {
                saveTo.createNewFile();
            }

            FileWriter fw = new FileWriter(saveTo, true);
            PrintWriter pw = new PrintWriter(fw);
            Date dt = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = df.format(dt);
            pw.println("[" + time + "][azaxys]" + message);
            pw.flush();
            pw.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}
