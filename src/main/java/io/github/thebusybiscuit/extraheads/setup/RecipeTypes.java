package io.github.thebusybiscuit.extraheads.setup;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.thebusybiscuit.extraheads.ExtraHeads;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

public final class RecipeTypes {

    public static final RecipeType DECAPITATION = new RecipeType(
        new NamespacedKey(ExtraHeads.getInstance(), "decapitation"),
        namedItem(new ItemStack(Material.IRON_SWORD), "&6Kill the specified Mob")
    );

    private RecipeTypes() {}

    private static ItemStack namedItem(ItemStack item, String name) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        item.setItemMeta(meta);
        return item;
    }
}
