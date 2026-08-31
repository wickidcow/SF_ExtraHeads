package io.github.thebusybiscuit.extraheads.setup;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.thebusybiscuit.extraheads.ExtraHeads;
import io.github.thebusybiscuit.extraheads.items.MobHead;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;

public final class ItemSetup {

    private static final String[][] HEADS = {
        {"BAT", "2796aa6d18edc5b724bd89e983bc3215a41bf775d112635e9b5835d1b8ad20cb"},
        {"BLAZE", "b78ef2e4cf2c41a2d14bfde9caff10219f5b1bf5b35a49eb51c6467882cb5f0"},
        {"CAVE_SPIDER", "41645dfd77d09923107b3496e94eeb5c30329f97efc96ed76e226e98224"},
        {"CHICKEN", "1638469a599ceef7207537603248a9ab11ff591fd378bea4735b346a7fae893"},
        {"COW", "5d6c6eda942f7f5f71c3161c7306f4aed307d82895f9d2b07ab4525718edc5"},
        {"DOLPHIN", "cefe7d803a45aa2af1993df2544a28df849a762663719bfefc58bf389ab7f5"},
        {"DROWNED", "c84df79c49104b198cdad6d99fd0d0bcf1531c92d4ab6269e40b7d3cbbb8e98c"},
        {"ELDER_GUARDIAN", "4adc4a6f53afa116027b51d6f2e433ee7afa5d59b2ffa04780be464fa5d61a"},
        {"ENDERMAN", "7a59bb0a7a32965b3d90d8eafa899d1835f424509eadd4e6b709ada50b9cf"},
        {"EVOKER", "d954135dc82213978db478778ae1213591b93d228d36dd54f1ea1da48e7cba6"},
        {"GHAST", "8b6a72138d69fbbd2fea3fa251cabd87152e4f1c97e5f986bf685571db3cc0"},
        {"GUARDIAN", "932c24524c82ab3b3e57c2052c533f13dd8c0beb8bdd06369bb2554da86c123"},
        {"HORSE", "61902898308730c4747299cb5a5da9c25838b1d059fe46fc36896fee662729"},
        {"HUSK", "d674c63c8db5f4ca628d69a3b1f8a36e29d8fd775e1a6bdb6cabb4be4db121"},
        {"ILLUSIONER", "2f2882dd09723e47c0ab9663eab083d6a5969273706110c82910e61bf8a8f07e"},
        {"IRON_GOLEM", "89091d79ea0f59ef7ef94d7bba6e5f17f2f7d4572c44f90f76c4819a714"},
        {"LLAMA", "2a5f10e6e6232f182fe966f501f1c3799d45ae19031a1e4941b5dee0feff059b"},
        {"MAGMA_CUBE", "38957d5023c937c4c41aa2412d43410bda23cf79a9f6ab36b76fef2d7c429"},
        {"MOOSHROOM", "d0bc61b9757a7b83e03cd2507a2157913c2cf016e7c096a4d6cf1fe1b8db"},
        {"OCELOT", "5657cd5c2989ff97570fec4ddcdc6926a68a3393250c1be1f0b114a1db1"},
        {"PARROT", "a4ba8d66fecb1992e94b8687d6ab4a5320ab7594ac194a2615ed4df818edbc3"},
        {"PIG", "621668ef7cb79dd9c22ce3d1f3f4cb6e2559893b6df4a469514e667c16aa4"},
        {"POLAR_BEAR", "442123ac15effa1ba46462472871b88f1b09c1db467621376e2f71656d3fbc"},
        {"RABBIT", "ff1559194a175935b8b4fea6614bec60bf81cf524af6f564333c555e657bc"},
        {"SHEEP", "f31f9ccc6b3e32ecf13b8a11ac29cd33d18c95fc73db8a66c5d657ccb8be70"},
        {"SHULKER", "b1d3534d21fe8499262de87affbeac4d25ffde35c8bdca069e61e1787ff2f"},
        {"SLIME", "16ad20fc2d579be250d3db659c832da2b478a73a698b7ea10d18c9162e4d9b5"},
        {"SPIDER", "cd541541daaff50896cd258bdbdd4cf80c3ba816735726078bfe393927e57f1"},
        {"SQUID", "01433be242366af126da434b8735df1eb5b3cb2cede39145974e9c483607bac"},
        {"STRAY", "78ddf76e555dd5c4aa8a0a5fc584520cd63d489c253de969f7f22f85a9a2d56"},
        {"TURTLE", "0a4050e7aacc4539202658fdc339dd182d7e322f9fbcc4d5f99b5718a"},
        {"VEX", "c2ec5a516617ff1573cd2f9d5f3969f56d5575c4ff4efefabd2a18dc7ab98cd"},
        {"VILLAGER", "822d8e751c8f2fd4c8942c44bdb2f5ca4d8ae8e575ed3eb34c18a86e93b"},
        {"VINDICATOR", "6deaec344ab095b48cead7527f7dee61b063ff791f76a8fa76642c8676e2173"},
        {"WITCH", "ddedbee42be472e3eb791e7dbdfaf18c8fe593c638ba1396c9ef68f555cbce"},
        {"WITHER", "cdf74e323ed41436965f5c57ddf2815d5332fe999e68fbb9d6cf5c8bd4139f"},
        {"ZOMBIE_VILLAGER", "a6224941314bca2ebbb66b10ffd94680cc98c3435eeb71a228a08fd42c24db"},
        {"RAVAGER", "1cb9f139f9489d86e410a06d8cbc670c8028137508e3e4bef612fe32edd60193"},
        {"PILLAGER", "4aee6bb37cbfc92b0d86db5ada4790c64ff4468d68b84942fde04405e8ef5333"},
        {"FOX", "46cff7a19e683a08e4587ea1457880313d5f341f346ceb5b0551195d810e3"},
        {"PANDA", "7818b681cace1c641919f53edadecb142330d089a826b56219138c33b7a5e0db"},
        {"WANDERING_TRADER", "5f1379a82290d7abe1efaabbc70710ff2ec02dd34ade386bc00c930c461cf932"},
        {"PIGLIN", "11d18bbd0d795b9ac8efaad655e3d0c59fcbb9b964c2a9948ef537f4a3fbbf87"},
        {"ZOMBIFIED_PIGLIN", "e935842af769380f78e8b8a88d1ea6ca2807c1e5693c2cf797456620833e936f"},
        {"STRIDER", "18a9adf780ec7dd4625c9c0779052e6a15a451866623511e4c82e9655714b3c1"},
        {"AXOLOTL", "5c138f401c67fc2e1e387d9c90a9691772ee486e8ddbf2ed375fc8348746f936"},
        {"GLOW_SQUID", "57327ee11812b764c7ade70b282cce4c58e635b2015244081d1490543da7280e"},
        {"GOAT", "457a0d538fa08a7affe312903468861720f9fa34e86d44b89dcec5639265f03"},
        {"ALLAY", "df5de940bfe499c59ee8dac9f9c3919e7535eff3a9acb16f4842bf290f4c679f"},
        {"FROG", "45852a95928897746012988fbd5dbaa1b70b7a5fb65157016f4ff3f245374c08"},
        {"TADPOLE", "987035f5352334c2cba6ac4c65c2b9059739d6d0e839c1dd98d75d2e77957847"},
        {"CAMEL", "3642c9f71131b5df4a8c21c8c6f10684f22abafb8cd68a1d55ac4bf263a53a31"},
        {"SNIFFER", "fe5a8341c478a134302981e6a7758ea4ecfd8d62a0df4067897e75502f9b25de"},
        {"ARMADILLO", "9852b33ba294f560090752d113fe728cbc7dd042029a38d5382d65a2146068b7"},
        {"BOGGED", "a3b9003ba2d05562c75119b8a62185c67130e9282f7acbac4bc2824c21eb95d9"},
        {"BREEZE", "a275728af7e6a29c88125b675a39d88ae9919bb61fdc200337fed6ab0c49d65c"},
        {"CREAKING", "3630e03391db6c0e9c8643a59754c2d19a1f938a787150af3b3d516ba8094cda"},
        {"HAPPY_GHAST", "82698aa7274ee241d31362e2faa48cfa733d25010516568d40f3f967247da11b"},
        {"COPPER_GOLEM", "248e0f9eb24a10600729438dd99997839e1a99e276d76e32fd434bdb5f54296c"},
        {"NAUTILUS", "3bb340dd3302615348de5162fe1670b9c5c9c616cd92d2de9d8398cb33e842ae"},
        {"ZOMBIE_NAUTILUS", "fd9a933376da44c3391307cb9f4cf03f16f3a54f495fd5a11bad8a373f9d5720"},
        {"CAMEL_HUSK", "750bfc9b2cc40f4d8d0224ccbabac26b338aa947d99dcde769f859b59b8d0b0e"},
        {"PARCHED", "24aeceff5f26dd8413c5c03547c234ac03108d187af0b9cd834a8ce12598591c"},
    };

    private ItemSetup() {}

    public static void setup() {
        for (String[] definition : HEADS) {
            registerHead(definition[0], definition[1]);
        }

        ExtraHeads.getInstance().saveConfig();
    }

    private static void registerHead(String entityKey, String texture) {
        EntityType type = resolveEntityType(entityKey);
        if (type == null) {
            ExtraHeads.getInstance().getLogger().fine(
                "Skipping head for entity type not present on this Paper version: " + entityKey
            );
            return;
        }

        try {
            String chancePath = "chances." + entityKey;
            double defaultChance = ExtraHeads.getRegistry()
                .getConfig()
                .getDouble("options.default-drop-chance", 5.0);

            if (!ExtraHeads.getRegistry().getConfig().contains(chancePath)) {
                ExtraHeads.getRegistry().getConfig().set(chancePath, defaultChance);
            }

            double chance = ExtraHeads.getRegistry().getConfig().getDouble(chancePath, defaultChance);
            String displayName = humanize(entityKey);

            SlimefunItemStack item = new SlimefunItemStack(
                entityKey + "_HEAD",
                texture,
                "&f" + displayName + " Head"
            );

            new MobHead(
                type,
                entityKey,
                item,
                createRecipeDisplay(item, displayName, chance)
            ).register(ExtraHeads.getInstance());
        } catch (Exception exception) {
            ExtraHeads.getInstance().getLogger().log(
                Level.WARNING,
                "Could not load Mob Head for Entity: " + entityKey,
                exception
            );
        }
    }

    private static EntityType resolveEntityType(String fieldName) {
        EntityType resolved = getEntityTypeField(fieldName);
        if (resolved == null && "MOOSHROOM".equals(fieldName)) {
            resolved = getEntityTypeField("MUSHROOM_COW");
        }
        return resolved;
    }

    private static EntityType getEntityTypeField(String fieldName) {
        try {
            Field field = EntityType.class.getField(fieldName);
            Object value = field.get(null);
            return value instanceof EntityType ? (EntityType) value : null;
        } catch (ReflectiveOperationException ignored) {
            return null;
        }
    }

    private static ItemStack createRecipeDisplay(
        SlimefunItemStack item,
        String displayName,
        double chance
    ) {
        ItemStack display = item.clone();
        ItemMeta meta = display.getItemMeta();
        meta.setLore(List.of(
            ChatColor.RESET + "Kill 1 " + displayName,
            ChatColor.GRAY + "Chance: " + ChatColor.YELLOW + chance + "%"
        ));
        display.setItemMeta(meta);
        return display;
    }

    private static String humanize(String input) {
        String[] words = input.toLowerCase(Locale.ROOT).split("_");
        List<String> result = new ArrayList<>(words.length);

        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }
            result.add(Character.toUpperCase(word.charAt(0)) + word.substring(1));
        }

        return String.join(" ", result);
    }
}
