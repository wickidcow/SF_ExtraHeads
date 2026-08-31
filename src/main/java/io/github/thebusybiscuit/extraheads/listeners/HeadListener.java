package io.github.thebusybiscuit.extraheads.listeners;

import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.extraheads.ExtraHeads;
import io.github.thebusybiscuit.extraheads.setup.Registry;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.weapons.SwordOfBeheading;

public class HeadListener implements Listener {

    public HeadListener(ExtraHeads plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(ignoreCancelled = true)
    public void onKill(EntityDeathEvent event) {
        Registry registry = ExtraHeads.getRegistry();
        EntityType entityType = event.getEntityType();
        SlimefunItem head = registry.getHeads().get(entityType);

        if (head == null) {
            return;
        }

        double chance = getChance(entityType, event.getEntity().getKiller());
        if (ThreadLocalRandom.current().nextDouble(100.0) < chance) {
            event.getDrops().add(head.getItem().clone());
        }
    }

    private double getChance(@Nonnull EntityType type, @Nullable Player killer) {
        Registry registry = ExtraHeads.getRegistry();
        String entityKey = registry.getEntityKey(type);
        if (entityKey == null) {
            return 0.0;
        }

        double chance = Math.max(0.0, registry.getConfig().getDouble("chances." + entityKey));

        if (killer == null) {
            return chance;
        }

        ItemStack item = killer.getInventory().getItemInMainHand();
        if (item.getType().isAir()) {
            return chance;
        }

        SlimefunItem registeredSword = SlimefunItems.SWORD_OF_BEHEADING.getItem();
        if (!(registeredSword instanceof SwordOfBeheading sword)
            || !sword.isItem(item)
            || sword.isDisabledIn(killer.getWorld())) {
            return chance;
        }

        double multiplier = Math.max(
            0.0,
            registry.getConfig().getDouble("options.sword-of-beheading-multiplier", 1.8)
        );
        return chance * multiplier;
    }
}
