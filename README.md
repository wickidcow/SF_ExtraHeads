<div align="center">

# 💀 ExtraHeads — Slimefun Legacy

**Collectible mob heads for Slimefun, maintained for modern Minecraft and Paper servers.**

![Slimefun Legacy](https://img.shields.io/badge/Slimefun-Legacy-6bd425?style=for-the-badge)
![Slimefun United](https://img.shields.io/badge/Slimefun-United-compatible-6bd425?style=for-the-badge)
![Minecraft 1.21.11+](https://img.shields.io/badge/Minecraft-1.21.11%2B-62b47a?style=for-the-badge)
![Paper 26.x](https://img.shields.io/badge/Paper-26.x-blue?style=for-the-badge)
![Java 21+](https://img.shields.io/badge/Java-21%2B-orange?style=for-the-badge)
![License: MIT](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

</div>

> [!IMPORTANT]
> ExtraHeads Legacy is an **unofficial community-maintained fork** of ExtraHeads. It preserves existing Slimefun head IDs and gameplay while maintaining the addon for **Slimefun Legacy**, **Slimefun United**, Minecraft **1.21.11+**, and modern Paper builds.

## 💀 What ExtraHeads does

ExtraHeads adds collectible Slimefun mob heads that can drop when supported mobs are killed.

Each registered head appears in the Slimefun guide under the **Extra Heads** category. Drop chances are configurable per mob, and Slimefun's **Sword of Beheading** can multiply the configured chance.

## ✨ New in 1.0.4

Version 1.0.4 finishes the missing normal-mob head coverage without changing existing IDs or drop behavior.

New heads:

- **Bee**
- **Cat**
- **Cod**
- **Donkey**
- **Endermite**
- **Hoglin**
- **Mule**
- **Phantom**
- **Piglin Brute**
- **Pufferfish**
- **Salmon**
- **Silverfish**
- **Skeleton Horse**
- **Snow Golem**
- **Sulfur Cube**
- **Trader Llama**
- **Tropical Fish**
- **Warden**
- **Wolf**
- **Zoglin**
- **Zombie Horse**

The existing ExtraHeads collection — including Creaking, Breeze, Bogged, Armadillo, Sniffer, Happy Ghast, Copper Golem, Nautilus, Zombie Nautilus, Camel Husk, Parched, and all older heads — remains unchanged.

Vanilla mobs that already have their own native Minecraft mob-head/skull item are intentionally not duplicated with a second ExtraHeads item.

## 🧪 Compatibility targets

| Component | Target |
| --- | --- |
| Minecraft | **1.21.11+** |
| Paper | **1.21.11 API floor, Paper 26.x validation** |
| Java | **21+ bytecode**, CI on Java 25 |
| Primary Slimefun | **Slimefun Legacy** |
| Secondary compatibility | **Slimefun United** |

GitHub Actions compiles the addon against both Slimefun implementations and validates the supported Paper compatibility range before a release JAR is published.

## 🛠️ Slimefun Legacy maintenance

The maintained fork includes:

- removal of the `GuizhanLibPlugin` runtime requirement;
- removal of the `guizhanlib-all` build dependency;
- local Paper-safe entity resolution;
- modern `EntityType` handling that does not assume `EntityType` is an enum;
- automatic skipping of heads for entity types not present on the running Paper version;
- Bukkit-native configuration handling;
- preservation of every existing Slimefun head ID;
- Slimefun Legacy and Slimefun United build validation;
- Paper 1.21.11+ / 26.x compatibility validation;
- raw versioned JAR releases.

## ⚙️ Configuration

Default configuration:

```yaml
options:
  default-drop-chance: 5.0
  sword-of-beheading-multiplier: 1.8

chances: {}
```

On first startup, ExtraHeads automatically adds a `chances.<MOB>` entry for every supported mob available on the running Paper version.

Example:

```yaml
chances:
  COD: 5.0
  SALMON: 5.0
  WARDEN: 5.0
  SULFUR_CUBE: 5.0
```

Values are percentages.

## 📦 Current release

**Version:** `1.0.4`

Release builds are published as the raw JAR:

`SF_ExtraHeads1.0.4.jar`

Install either **Slimefun Legacy** or a compatible **Slimefun United** build first, place the ExtraHeads JAR in the server's `plugins` directory, and restart the server.

**GuizhanLibPlugin is not required.**

## ❤️ Credits & project lineage

- **TheBusyBiscuit** — original ExtraHeads creator.
- **ybw0014 / Slimefun Addon Community** — upstream maintenance and modernization.
- **Slimefun-Addon-Community/ExtraHeads** — immediate upstream source used for this fork.
- **Minecraft-Heads.com contributors** — custom head textures used by ExtraHeads.
- **wickidcow / Slimefun Legacy** — current compatibility and preservation work.

This fork keeps upstream attribution visible and does not claim authorship of the original addon.

## 📜 License

ExtraHeads is distributed under the **MIT License**. See `LICENSE` for the full license text.

## ⚖️ Independence notice

**NOT AN OFFICIAL MINECRAFT PRODUCT. NOT APPROVED BY OR ASSOCIATED WITH MOJANG OR MICROSOFT.**

ExtraHeads, Slimefun Legacy, Slimefun United, and this maintenance fork are independent community projects.

---

<div align="center">

**💀 More mobs. More trophies. Modern Slimefun support.**

</div>
