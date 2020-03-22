package com.github.StevenDesroches.azaxys_commands.objects;


import com.sun.istack.internal.NotNull;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;

public class SponsorGui implements InventoryHolder, Listener {
    private final Inventory inv;
    private final ConsoleCommandSender console;
    PluginManager pm;

    public SponsorGui() {
        inv = Bukkit.createInventory(this, 9, "Sponsor");
        initializeItems();
        pm = Bukkit.getPluginManager();
        console = Bukkit.getServer().getConsoleSender();
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return inv;
    }

    public void initializeItems() {
        inv.addItem(createGuiItem(Material.IRON_CHESTPLATE, "§bEff_1", "§aLa puissance des arcanes"));
        inv.addItem(createGuiItem(Material.DIAMOND_CHESTPLATE, "§bEff_2", "§aLes herbes meurent sous vos pas"));
        inv.addItem(createGuiItem(Material.GOLD_CHESTPLATE, "§bEff_3", "§aQue l'esprit de noel vous accompagne"));
        inv.addItem(createGuiItem(Material.IRON_SWORD, "§bkill_eff_1", "§aExplosion de neige"));
        inv.addItem(createGuiItem(Material.DIAMOND_SWORD, "§bkill_eff_2", "§aL'éclair"));
        inv.addItem(createGuiItem(Material.GOLD_SWORD, "§bkill_eff_3", "§aExplosion de feu"));
        inv.addItem(createGuiItem(Material.BEDROCK, "§bReset", "§aReset"));
    }

    private ItemStack createGuiItem(Material material, String name, String...lore) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        ArrayList<String> metaLore = new ArrayList<String>();

        for(String loreComments : lore) {
            metaLore.add(loreComments);
        }

        meta.setLore(metaLore);
        item.setItemMeta(meta);
        return item;
    }

    public void openInventory(Player p) {
        p.openInventory(inv);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().getHolder() != this) {
            return;
        }
            if (e.getClick().equals(ClickType.NUMBER_KEY)) {
                e.setCancelled(true);
            }
            e.setCancelled(true);

            Player p = (Player) e.getWhoClicked();
            ItemStack clickedItem = e.getCurrentItem();

            if (clickedItem == null || clickedItem.getType() == Material.AIR) return;


            switch (e.getRawSlot()) {
                case 0:
                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    p.sendMessage("/c Eff_1");
                    Bukkit.dispatchCommand(console, "c teach " + p.getName() + " Eff_1");
                    break;
                case 1:
                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    p.sendMessage("/c Eff_2");
                    Bukkit.dispatchCommand(console, "c teach " + p.getName() + " Eff_2");
                    break;
                case 2:
                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    p.sendMessage("/c Eff_3");
                    Bukkit.dispatchCommand(console, "c teach " + p.getName() + " Eff_3");
                    break;
                case 3:
                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    p.sendMessage("/c kill_eff_1");
                    Bukkit.dispatchCommand(console, "c teach " + p.getName() + " kill_eff_1");
                    break;
                case 4:
                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    p.sendMessage("/c kill_eff_2");
                    Bukkit.dispatchCommand(console, "c teach " + p.getName() + " kill_eff_2");
                    break;
                case 5:
                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    p.sendMessage("/c kill_eff_3");
                    Bukkit.dispatchCommand(console, "c teach " + p.getName() + " kill_eff_3");
                    break;
                case 6:
                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    Bukkit.dispatchCommand(console, "c forget " + p.getName() + " Eff_1");
                    Bukkit.dispatchCommand(console, "c forget " + p.getName() + " Eff_2");
                    Bukkit.dispatchCommand(console, "c forget " + p.getName() + " Eff_3");
                    Bukkit.dispatchCommand(console, "c forget " + p.getName() + " kill_eff_1");
                    Bukkit.dispatchCommand(console, "c forget " + p.getName() + " kill_eff_2");
                    Bukkit.dispatchCommand(console, "c forget " + p.getName() + " kill_eff_3");
                    p.sendMessage("Reset");
                    break;
            }
            p.closeInventory();
        //}
    }
}