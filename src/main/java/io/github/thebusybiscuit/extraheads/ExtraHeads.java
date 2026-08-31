package io.github.thebusybiscuit.extraheads;

import javax.annotation.Nonnull;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.extraheads.listeners.HeadListener;
import io.github.thebusybiscuit.extraheads.setup.ItemSetup;
import io.github.thebusybiscuit.extraheads.setup.Registry;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;

public class ExtraHeads extends JavaPlugin implements SlimefunAddon {

    private static ExtraHeads instance;

    private Registry registry;

    public static ExtraHeads getInstance() {
        return instance;
    }

    public static Registry getRegistry() {
        return getInstance().registry;
    }

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        registry = new Registry(getConfig());

        new Metrics(this, 5650);

        ItemSetup.setup();
        new HeadListener(this);

        getLogger().info("ExtraHeads Legacy 1.0.2 enabled with Slimefun compatibility and no GuizhanLibPlugin dependency.");
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    @Override
    @Nonnull
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Override
    @Nonnull
    public String getBugTrackerURL() {
        return "https://github.com/wickidcow/SF_ExtraHeads/issues";
    }
}
