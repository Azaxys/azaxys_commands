package com.github.StevenDesroches.azaxys_commands.commands;

import com.github.StevenDesroches.azaxys_commands.objects.SponsorGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SponsorCommand implements CommandExecutor {

    private final SponsorGui sponsorGui;

    public SponsorCommand(SponsorGui sponsorGui){
        this.sponsorGui = sponsorGui;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player && commandSender.hasPermission("azaxys.commands.sponsor")){
            sponsorGui.openInventory((Player) commandSender);
            return true;
        } else {
            return false;
        }
    }

}
