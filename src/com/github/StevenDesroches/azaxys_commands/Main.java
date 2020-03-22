package com.github.StevenDesroches.azaxys_commands;

import com.github.StevenDesroches.azaxys_commands.commands.*;
import com.github.StevenDesroches.azaxys_commands.listeners.LogListener;
import com.github.StevenDesroches.azaxys_commands.objects.SponsorGui;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class Main extends JavaPlugin {

    private static Main instance;
    public List<UUID> playerWhoVoted;

    @Override
    public void onEnable() {
        instance = this;
        playerWhoVoted = new ArrayList<>();

        this.getCommand("passeport").setExecutor(new PassePortCommand());
        this.getCommand("reroll").setExecutor(new RerollCommand());
        this.getCommand("informations").setExecutor(new InformationsCommand());
        this.getCommand("vote").setExecutor(new VoteCommand(instance));
        this.getCommand("builderpro").setExecutor(new BuilderProCommand());
        this.getCommand("questeur").setExecutor(new QuesteurCommand());
        this.getCommand("jojo").setExecutor(new JojoCommand());

        SponsorGui sponsorGui = new SponsorGui();
        Bukkit.getPluginManager().registerEvents(sponsorGui, this);

        this.getCommand("sponsorazaxys").setExecutor(new SponsorCommand(sponsorGui));
        this.getServer().getPluginManager().registerEvents(new LogListener(), this);

    }

    @Override
    public void onDisable() {

    }


}