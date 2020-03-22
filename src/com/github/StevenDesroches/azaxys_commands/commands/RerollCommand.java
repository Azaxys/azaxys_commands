package com.github.StevenDesroches.azaxys_commands.commands;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.api.SkillAPI;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.UUID;

public class RerollCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        boolean commandState = false;

        if (commandSender.hasPermission("azaxys.commands.passeport")) {
            if (args.length != 0) {
                String playerName = args[0];
                Player player = Bukkit.getPlayer(playerName);///////////////
                if(player != null){
                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.HOUR, 110);
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    Bukkit.dispatchCommand(console, "warp classe " + playerName);
                    Bukkit.dispatchCommand(console, "whitelist add " + playerName);
                    Bukkit.dispatchCommand(console, "lp user " + playerName + " parent set base");
                    Bukkit.dispatchCommand(console, "lp user " + playerName + " permission settemp free_spells true " + (cal.getTime().getTime() / 1000));


                    SkillAPI.getSkills();
                    for (String str : SkillAPI.getSkills() ){
                        int level = ExperienceAPI.getLevel(player, str);
                        level *= 0.7;
                        ExperienceAPI.setLevel(player, str, level);
                    }


                    Bukkit.getServer().broadcastMessage(args[0] + " reroll");

                } else {
                    commandSender.sendMessage("Le joueur est hors ligne");
                    commandState = true;
                }


            } else {
                commandSender.sendMessage("Vous devez rajouter un nom de joueur");
                commandState = true;
            }
        } else {
            commandSender.sendMessage("Vous n'avez pas la permission d'utiliser cette commande");
            commandState = true;
        }

        return commandState;
    }
}