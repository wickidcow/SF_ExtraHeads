package io.github.thebusybiscuit.extraheads.setup;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;

public class Registry {

    private final FileConfiguration config;
    private final Map<EntityType, SlimefunItem> heads = new HashMap<>();
    private final Map<EntityType, String> entityKeys = new HashMap<>();

    public Registry(@Nonnull FileConfiguration config) {
        this.config = config;
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public Map<EntityType, SlimefunItem> getHeads() {
        return heads;
    }

    public void registerHead(@Nonnull EntityType entityType, @Nonnull String entityKey, @Nonnull SlimefunItem item) {
        heads.put(entityType, item);
        entityKeys.put(entityType, entityKey);
    }

    public String getEntityKey(@Nonnull EntityType entityType) {
        return entityKeys.get(entityType);
    }
}
