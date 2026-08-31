package io.github.thebusybiscuit.extraheads.setup;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.thebusybiscuit.extraheads.ExtraHeads;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;

public final class ItemGroups {

    public static final ItemGroup MAIN = new ItemGroup(
        new NamespacedKey(ExtraHeads.getInstance(), "extra_heads"),
        namedItem(
            SlimefunUtils.getCustomHead("5f1379a82290d7abe1efaabbc70710ff2ec02dd34ade386bc00c930c461cf932"),
            "&7Extra Heads"
        ),
        1
    );

    private ItemGroups() {}

    private static ItemStack namedItem(ItemStack item, String name) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        item.setItemMeta(meta);
        return item;
    }
}
