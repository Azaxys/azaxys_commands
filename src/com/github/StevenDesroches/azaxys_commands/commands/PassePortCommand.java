package com.github.StevenDesroches.azaxys_commands.commands;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class PassePortCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        boolean commandState = false;

        if (commandSender.hasPermission("azaxys.commands.passeport")) {
            if (args.length != 0) {
                String uuid = getUuid(args[0]);
                if (!uuid.equalsIgnoreCase("error")) {
                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.MONTH, 1);
                    //Player p = Bukkit.getPlayer(UUID.fromString(uuid));
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    Bukkit.dispatchCommand(console, "warp classe " + args[0]);
                    Bukkit.dispatchCommand(console, "whitelist add " + args[0]);
                    Bukkit.dispatchCommand(console, "lp user " + args[0] + " parent set base");
                    Bukkit.dispatchCommand(console, "lp user " + args[0] + " permission settemp free_spells true " + (cal.getTime().getTime() / 1000));
                    Bukkit.dispatchCommand(console, "skillreset " + args[0] + " acrobatics");
                    Bukkit.dispatchCommand(console, "skillreset " + args[0] + " axes");
                    Bukkit.dispatchCommand(console, "skillreset " + args[0] + " fishing");
                    Bukkit.dispatchCommand(console, "skillreset " + args[0] + " mining");
                    Bukkit.dispatchCommand(console, "skillreset " + args[0] + " repair");
                    Bukkit.dispatchCommand(console, "skillreset " + args[0] + " swords");
                    Bukkit.dispatchCommand(console, "skillreset " + args[0] + " unarmed");
                    Bukkit.dispatchCommand(console, "skillreset " + args[0] + " archery");
                    Bukkit.dispatchCommand(console, "skillreset " + args[0] + " excavation");
                    Bukkit.dispatchCommand(console, "skillreset " + args[0] + " herbalism");
                    Bukkit.dispatchCommand(console, "skillreset " + args[0] + " enchanting");
                    Bukkit.dispatchCommand(console, "skillreset " + args[0] + " taming");
                    Bukkit.dispatchCommand(console, "skillreset " + args[0] + " woodcutting");
                    Bukkit.getServer().broadcastMessage(args[0] + " possède maintenant son passeport !!!");

                    if(commandSender instanceof ConsoleCommandSender){
                        Bukkit.dispatchCommand(console, "mmoedit " + args[0] + " swords 500");
                        Bukkit.dispatchCommand(console, "mmoedit " + args[0] + " unarmed 500");
                        Bukkit.dispatchCommand(console, "mmoedit " + args[0] + " archery 500");
                        Bukkit.dispatchCommand(console, "mmoedit " + args[0] + " axes 500");
                    }

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

    public String getUuid(String name) {
        String url = "https://api.mojang.com/users/profiles/minecraft/" + name;
        try {
            @SuppressWarnings("deprecation")
            String UUIDJson = IOUtils.toString(new URL(url));
            if (UUIDJson.isEmpty()) return "invalid name";
            JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(UUIDJson);
            return UUIDObject.get("id").toString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return "error";
    }

}