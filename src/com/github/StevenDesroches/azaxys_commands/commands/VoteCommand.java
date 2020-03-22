package com.github.StevenDesroches.azaxys_commands.commands;

import com.github.StevenDesroches.azaxys_commands.Main;
import com.github.StevenDesroches.azaxys_commands.runnables.VoteApiPlayerRunnable;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class VoteCommand implements CommandExecutor {

    private Main instance;

    public VoteCommand(Main instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        //commandSender.sendMessage("https://discord.gg/QQQp7Kn");
        TextComponent message = new TextComponent("https://www.serveurs-minecraft.org/vote.php?id=57686");
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.serveurs-minecraft.org/vote.php?id=57686"));
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Votez pour le site !").create()));
        commandSender.spigot().sendMessage(message);

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            System.out.println(instance.playerWhoVoted);
            if (!instance.playerWhoVoted.contains(player.getUniqueId())) {
                instance.playerWhoVoted.add(player.getUniqueId());
                BukkitTask task = new VoteApiPlayerRunnable(player, instance).runTaskLater(this.instance, 5000);
            }
        }

        return true;
    }
}
