package com.github.StevenDesroches.azaxys_commands.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class JojoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        boolean state = false;
        if (commandSender.hasPermission("azaxys.commands.jojo")) {
            if (args.length != 0) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                switch (args[0]) {
                    case "start":
                        state = true;
                        Bukkit.dispatchCommand(console, "lp user " + commandSender.getName() + " parent add jojo");
                        break;
                    case "quit":
                        state = true;
                        Bukkit.dispatchCommand(console, "lp user " + commandSender.getName() + " parent remove jojo");
                        Bukkit.dispatchCommand(console, "warp sorts " + commandSender.getName());
                        break;
                }
            }
        }
        return state;
    }
}
