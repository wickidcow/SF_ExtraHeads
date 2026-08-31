package io.github.thebusybiscuit.extraheads.items;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.extraheads.ExtraHeads;
import io.github.thebusybiscuit.extraheads.setup.ItemGroups;
import io.github.thebusybiscuit.extraheads.setup.RecipeTypes;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;

public class MobHead extends SlimefunItem {

    private final EntityType entityType;
    private final String entityKey;

    @ParametersAreNonnullByDefault
    public MobHead(EntityType type, String entityKey, SlimefunItemStack item, ItemStack recipe) {
        super(ItemGroups.MAIN, item, RecipeTypes.DECAPITATION, new ItemStack[] {
            null, null, null,
            null, recipe, null,
            null, null, null
        });

        this.entityType = type;
        this.entityKey = entityKey;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    @Override
    public void postRegister() {
        super.postRegister();

        if (!isDisabled()) {
            ExtraHeads.getRegistry().registerHead(entityType, entityKey, this);
        }
    }
}
