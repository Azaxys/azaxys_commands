package com.github.StevenDesroches.azaxys_commands.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class InformationsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        TextComponent message = new TextComponent("https://discord.gg/QQQp7Kn");
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/QQQp7Kn"));
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Venez sur le discord !").create()));
        commandSender.spigot().sendMessage(message);

        TextComponent message2 = new TextComponent("https://azaxys.fr/forum/");
        message2.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://azaxys.fr/forum/"));
        message2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Venez sur le forum !").create()));
        commandSender.spigot().sendMessage(message2);

        TextComponent message3 = new TextComponent("https://azaxys.fr/");
        message3.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://azaxys.fr/"));
        message3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Venez sur le site !").create()));
        commandSender.spigot().sendMessage(message3);
        return true;
    }
}
