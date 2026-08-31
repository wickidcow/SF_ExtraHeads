<div align="center">

# 💀 ExtraHeads — Slimefun Legacy

**Collectible mob heads for Slimefun, maintained for modern Minecraft and Paper servers.**

![Slimefun Legacy](https://img.shields.io/badge/Slimefun-Legacy-6bd425?style=for-the-badge)
![Slimefun United](https://img.shields.io/badge/Slimefun-United-compatible-6bd425?style=for-the-badge)
![Minecraft 1.21.11](https://img.shields.io/badge/Minecraft-1.21.11-62b47a?style=for-the-badge)
![Paper 26.1.x / 26.2](https://img.shields.io/badge/Paper-26.1.x%20%7C%2026.2-blue?style=for-the-badge)
![Java 21+](https://img.shields.io/badge/Java-21%2B-orange?style=for-the-badge)
![License: MIT](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

</div>

> [!IMPORTANT]
> ExtraHeads Legacy is an **unofficial community-maintained fork** of ExtraHeads. It preserves the existing Slimefun head IDs and gameplay while updating the addon for **Slimefun Legacy**, **Slimefun United**, Minecraft **1.21.11**, and modern Paper builds.

## 💀 What ExtraHeads does

ExtraHeads adds collectible Slimefun mob heads that can drop when supported mobs are killed.

Each registered head appears in the Slimefun guide under the **Extra Heads** category. Drop chances are configurable per mob, and Slimefun's **Sword of Beheading** can multiply the configured chance.

## ✨ New in 1.0.2

This maintenance release adds current mob coverage and updates addon for Slimefun Legacy and Slimefun United

New heads:

- **Happy Ghast**
- **Copper Golem**
- **Nautilus**
- **Zombie Nautilus**
- **Camel Husk**
- **Parched**

The existing Creaking, Breeze, Bogged, Armadillo, Sniffer, Camel, Frog, Allay, and older ExtraHeads content is preserved.

## 🧪 Compatibility targets

| Component | Target |
| --- | --- |
| Minecraft | **1.21.11** |
| Paper | **26.1.x and 26.2** |
| Java | **21+ bytecode**, CI on Java 25 |
| Primary Slimefun | **Slimefun Legacy** |
| Secondary compatibility | **Slimefun United** |

GitHub Actions compiles the addon against both Slimefun implementations and validates both supported Paper generations before the release job is allowed to publish.

## 🛠️ Slimefun Legacy maintenance

Version `1.0.2` includes:

- removal of the `GuizhanLibPlugin` runtime requirement;
- removal of the `guizhanlib-all` build dependency;
- replacement of GuizhanLib Minecraft-version/entity compatibility helpers with local Paper-safe entity resolution;
- modern `EntityType` handling that does not assume `EntityType` is an enum;
- Bukkit-native configuration handling instead of Slimefun's relocated Dough configuration wrapper;
- removal of the obsolete Blob Builds auto-updater;
- preservation of existing Slimefun item IDs such as `CREEKING_HEAD`;
- build validation against Slimefun Legacy and Slimefun United;
- Paper 26.1.x and 26.2 compile validation;
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
  CREEKING: 5.0
  HAPPY_GHAST: 5.0
  COPPER_GOLEM: 5.0
  NAUTILUS: 5.0
```

Values are percentages.

## 📦 Current release

**Version:** `1.0.2`

Release builds are published as the raw JAR:

`SF_ExtraHeads1.0.2.jar`

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
