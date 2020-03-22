package com.github.StevenDesroches.azaxys_commands.runnables;

import com.github.StevenDesroches.azaxys_commands.Main;
import com.github.StevenDesroches.azaxys_commands.utilities.JsonReader;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.net.Inet4Address;

public class VoteApiPlayerRunnable extends BukkitRunnable {

    private Player player;
    private Main instance;

    public VoteApiPlayerRunnable(Player player, Main instance) {
        this.player = player;
        this.instance = instance;
    }

    @Override
    public void run() {
        Inet4Address currentIp = (Inet4Address) player.getAddress().getAddress();
        try {
            if (JsonReader.checkifThisIpVoted(currentIp.toString())) {
                player.sendMessage("Merci pour ton vote !");
                System.out.println("TEST");
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(console, "loot give " + player.getName() + " voteLoot");
            } else {
                instance.playerWhoVoted.remove(player.getUniqueId());
            }
        } catch (IOException e) {
            e.printStackTrace();
            instance.playerWhoVoted.remove(player.getUniqueId());

        }
    }
}
