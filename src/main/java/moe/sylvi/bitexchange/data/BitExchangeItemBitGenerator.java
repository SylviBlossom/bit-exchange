package moe.sylvi.bitexchange.data;

import appeng.core.definitions.AEBlocks;
import appeng.core.definitions.AEItems;
import moe.sylvi.bitexchange.BitExchange;
import moe.sylvi.bitexchange.bit.research.ResearchTier;
import moe.sylvi.bitexchange.data.api.BitProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BitExchangeItemBitGenerator extends BitProvider {
    //// Vanilla common tags
    private static final TagKey<Item> C_SHULKER_BOXES   = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "shulker_boxes"));
    private static final TagKey<Item> C_COPPER_INGOTS   = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "copper_ingots"));
    private static final TagKey<Item> C_IRON_INGOTS     = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "iron_ingots"));
    private static final TagKey<Item> C_GOLD_INGOTS     = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "gold_ingots"));

    //// Modded common tags
    // Ingots
    private static final TagKey<Item> C_TIN_INGOTS      = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "tin_ingots"));
    private static final TagKey<Item> C_LEAD_INGOTS     = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "lead_ingots"));
    private static final TagKey<Item> C_ALUMINUM_INGOTS = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "aluminum_ingots"));
    private static final TagKey<Item> C_NICKEL_INGOTS   = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "nickel_ingots"));
    private static final TagKey<Item> C_SILVER_INGOTS   = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "silver_ingots"));
    private static final TagKey<Item> C_URANIUM_INGOTS  = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "uranium_ingots"));
    private static final TagKey<Item> C_TUNGSTEN_INGOTS = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "tungsten_ingots"));
    private static final TagKey<Item> C_IRIDIUM_INGOTS  = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "iridium_ingots"));
    private static final TagKey<Item> C_TITANIUM_INGOTS = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "titanium_ingots"));
    private static final TagKey<Item> C_PLATINUM_INGOTS = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "platinum_ingots"));
    // Dusts
    private static final TagKey<Item> C_COPPER_DUSTS    = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "copper_dusts"));
    private static final TagKey<Item> C_IRON_DUSTS      = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "iron_dusts"));
    private static final TagKey<Item> C_GOLD_DUSTS      = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "gold_dusts"));

    private static final TagKey<Item> C_BAUXITE_DUSTS   = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "bauxite_dusts"));
    private static final TagKey<Item> C_SALTS           = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "salts"));
    private static final TagKey<Item> C_SALT_DUSTS      = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "salt_dusts"));

    //// Modern Industrialization common tags
    private static final TagKey<Item> C_LIGNITE_COAL    = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "lignite_coal"));
    private static final TagKey<Item> C_ANTIMONY_INGOTS = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "antimony_ingots"));
    private static final TagKey<Item> C_MOZANITE_DUSTS  = TagKey.of(Registry.ITEM_KEY, new Identifier("c", "mozanite_dusts"));

    public BitExchangeItemBitGenerator(FabricDataGenerator generator) {
        super(generator);
    }

    @Override
    public void build() {
        var builder = itemBuilder(new Identifier(BitExchange.MOD_ID, "items/bit_exchange")).noOverride();
        builder.register(BitExchange.BIT_ITEM,      1,       8   );
        builder.register(BitExchange.BYTE_ITEM,     8,       64  );
        builder.register(BitExchange.KILOBIT_ITEM,  64,      64  );
        builder.register(BitExchange.MEGABIT_ITEM,  512,     64  );
        builder.register(BitExchange.GIGABIT_ITEM,  4096,    64  );
        builder.register(BitExchange.TERABIT_ITEM,  32768,   64  );
        builder.register(BitExchange.PETABIT_ITEM,  262144,  64  );
        builder.register(BitExchange.EXABIT_ITEM,   2097152, 64  );
        builder.register(BitExchange.ITTY_BIT_ITEM, 0.01,    1000);

        builder = itemBuilder(new Identifier(BitExchange.MOD_ID, "items/minecraft")).noOverride();
        // concrete.json
        builder.register(Items.WHITE_CONCRETE     ).valueRef(Items.WHITE_CONCRETE_POWDER,      1).research(ResearchTier.CRAFTED).nonAutomatable();
        builder.register(Items.ORANGE_CONCRETE    ).valueRef(Items.ORANGE_CONCRETE_POWDER,     1).research(ResearchTier.CRAFTED).nonAutomatable();
        builder.register(Items.MAGENTA_CONCRETE   ).valueRef(Items.MAGENTA_CONCRETE_POWDER,    1).research(ResearchTier.CRAFTED).nonAutomatable();
        builder.register(Items.LIGHT_BLUE_CONCRETE).valueRef(Items.LIGHT_BLUE_CONCRETE_POWDER, 1).research(ResearchTier.CRAFTED).nonAutomatable();
        builder.register(Items.YELLOW_CONCRETE    ).valueRef(Items.YELLOW_CONCRETE_POWDER,     1).research(ResearchTier.CRAFTED).nonAutomatable();
        builder.register(Items.LIME_CONCRETE      ).valueRef(Items.LIME_CONCRETE_POWDER,       1).research(ResearchTier.CRAFTED).nonAutomatable();
        builder.register(Items.PINK_CONCRETE      ).valueRef(Items.PINK_CONCRETE_POWDER,       1).research(ResearchTier.CRAFTED).nonAutomatable();
        builder.register(Items.GRAY_CONCRETE      ).valueRef(Items.GRAY_CONCRETE_POWDER,       1).research(ResearchTier.CRAFTED).nonAutomatable();
        builder.register(Items.LIGHT_GRAY_CONCRETE).valueRef(Items.LIGHT_GRAY_CONCRETE_POWDER, 1).research(ResearchTier.CRAFTED).nonAutomatable();
        builder.register(Items.CYAN_CONCRETE      ).valueRef(Items.CYAN_CONCRETE_POWDER,       1).research(ResearchTier.CRAFTED).nonAutomatable();
        builder.register(Items.PURPLE_CONCRETE    ).valueRef(Items.PURPLE_CONCRETE_POWDER,     1).research(ResearchTier.CRAFTED).nonAutomatable();
        builder.register(Items.BLUE_CONCRETE      ).valueRef(Items.BLUE_CONCRETE_POWDER,       1).research(ResearchTier.CRAFTED).nonAutomatable();
        builder.register(Items.BROWN_CONCRETE     ).valueRef(Items.BROWN_CONCRETE_POWDER,      1).research(ResearchTier.CRAFTED).nonAutomatable();
        builder.register(Items.GREEN_CONCRETE     ).valueRef(Items.GREEN_CONCRETE_POWDER,      1).research(ResearchTier.CRAFTED).nonAutomatable();
        builder.register(Items.RED_CONCRETE       ).valueRef(Items.RED_CONCRETE_POWDER,        1).research(ResearchTier.CRAFTED).nonAutomatable();
        builder.register(Items.BLACK_CONCRETE     ).valueRef(Items.BLACK_CONCRETE_POWDER,      1).research(ResearchTier.CRAFTED).nonAutomatable();
        // copper_blocks.json
        builder.register(Items.EXPOSED_COPPER                   ).copy(Items.COPPER_BLOCK);
        builder.register(Items.WEATHERED_COPPER                 ).copy(Items.COPPER_BLOCK);
        builder.register(Items.OXIDIZED_COPPER                  ).copy(Items.COPPER_BLOCK);
        builder.register(Items.WAXED_EXPOSED_COPPER             ).copy(Items.WAXED_COPPER_BLOCK);
        builder.register(Items.WAXED_WEATHERED_COPPER           ).copy(Items.WAXED_COPPER_BLOCK);
        builder.register(Items.WAXED_OXIDIZED_COPPER            ).copy(Items.WAXED_COPPER_BLOCK);
        builder.register(Items.EXPOSED_CUT_COPPER               ).copy(Items.CUT_COPPER);
        builder.register(Items.WEATHERED_CUT_COPPER             ).copy(Items.CUT_COPPER);
        builder.register(Items.OXIDIZED_CUT_COPPER              ).copy(Items.CUT_COPPER);
        builder.register(Items.WAXED_EXPOSED_CUT_COPPER         ).copy(Items.WAXED_CUT_COPPER);
        builder.register(Items.WAXED_WEATHERED_CUT_COPPER       ).copy(Items.WAXED_CUT_COPPER);
        builder.register(Items.WAXED_OXIDIZED_CUT_COPPER        ).copy(Items.WAXED_CUT_COPPER);
        builder.register(Items.EXPOSED_CUT_COPPER_STAIRS        ).copy(Items.CUT_COPPER_STAIRS);
        builder.register(Items.WEATHERED_CUT_COPPER_STAIRS      ).copy(Items.CUT_COPPER_STAIRS);
        builder.register(Items.OXIDIZED_CUT_COPPER_STAIRS       ).copy(Items.CUT_COPPER_STAIRS);
        builder.register(Items.WAXED_EXPOSED_CUT_COPPER_STAIRS  ).copy(Items.WAXED_CUT_COPPER_STAIRS);
        builder.register(Items.WAXED_WEATHERED_CUT_COPPER_STAIRS).copy(Items.WAXED_CUT_COPPER_STAIRS);
        builder.register(Items.WAXED_OXIDIZED_CUT_COPPER_STAIRS ).copy(Items.WAXED_CUT_COPPER_STAIRS);
        builder.register(Items.EXPOSED_CUT_COPPER_SLAB          ).copy(Items.CUT_COPPER_SLAB);
        builder.register(Items.WEATHERED_CUT_COPPER_SLAB        ).copy(Items.CUT_COPPER_SLAB);
        builder.register(Items.OXIDIZED_CUT_COPPER_SLAB         ).copy(Items.CUT_COPPER_SLAB);
        builder.register(Items.WAXED_EXPOSED_CUT_COPPER_SLAB    ).copy(Items.WAXED_CUT_COPPER_SLAB);
        builder.register(Items.WAXED_WEATHERED_CUT_COPPER_SLAB  ).copy(Items.WAXED_CUT_COPPER_SLAB);
        builder.register(Items.WAXED_OXIDIZED_CUT_COPPER_SLAB   ).copy(Items.WAXED_CUT_COPPER_SLAB);
        // end.json
        builder.register(Items.END_STONE,              1,      ResearchTier.ABUNDANT);
        builder.register(Items.CHORUS_FLOWER,          96,     ResearchTier.UNCOMMON);
        builder.register(Items.CHORUS_FRUIT,           192,    ResearchTier.COMMON);
        builder.register(Items.ELYTRA,                 131072, ResearchTier.UNIQUE);
        // misc_blocks.json
        //builder.register(Items.CLAY).valueRef(Items.CLAY_BALL,  4).research(ResearchTier.CRAFTED).nonAutomatable();
        builder.register(Items.BELL).valueRef(Items.GOLD_INGOT, 7).research(ResearchTier.CRAFTED);
        builder.register(Items.CARVED_PUMPKIN).valueRef(Items.PUMPKIN, 1).research(ResearchTier.CRAFTED);
        builder.register(Items.OBSIDIAN,               64,     ResearchTier.COMMON);
        //builder.register(Items.MELON,                  144,    ResearchTier.UNCOMMON);
        builder.register(Items.PUMPKIN,                144,    ResearchTier.UNCOMMON);
        builder.register(Items.COBWEB,                 12,     ResearchTier.UNCOMMON);
        // misc_items.json
        builder.register(Items.MILK_BUCKET         ).valueRef(Items.BUCKET,       1).value(64).research(ResearchTier.CRAFTED);
        builder.register(Items.POWDER_SNOW_BUCKET  ).valueRef(Items.BUCKET,       1).value(1).research(ResearchTier.CRAFTED);
        builder.register(Items.PUFFERFISH_BUCKET   ).valueRef(Items.WATER_BUCKET, 1).valueRef(Items.PUFFERFISH,    1).research(ResearchTier.CRAFTED);
        builder.register(Items.SALMON_BUCKET       ).valueRef(Items.WATER_BUCKET, 1).valueRef(Items.SALMON,        1).research(ResearchTier.CRAFTED);
        builder.register(Items.COD_BUCKET          ).valueRef(Items.WATER_BUCKET, 1).valueRef(Items.COD,           1).research(ResearchTier.CRAFTED);
        builder.register(Items.TROPICAL_FISH_BUCKET).valueRef(Items.WATER_BUCKET, 1).valueRef(Items.TROPICAL_FISH, 1).research(ResearchTier.CRAFTED);
        builder.register(Items.AXOLOTL_BUCKET      ).valueRef(Items.WATER_BUCKET, 1).value(64).research(ResearchTier.CRAFTED);
        builder.register(Items.TADPOLE_BUCKET      ).valueRef(Items.WATER_BUCKET, 1).value(64).research(ResearchTier.CRAFTED);
        builder.register(Items.HONEY_BOTTLE        ).valueRef(Items.SUGAR,        3).valueRef(Items.GLASS_BOTTLE,  1).research(ResearchTier.CRAFTED).requireResearch(Items.GLASS_BOTTLE);
        builder.register(Items.IRON_HORSE_ARMOR    ).valueRef(Items.IRON_INGOT,   8).research(ResearchTier.EPIC);
        builder.register(Items.GOLDEN_HORSE_ARMOR  ).valueRef(Items.GOLD_INGOT,   8).research(ResearchTier.EPIC);
        builder.register(Items.DIAMOND_HORSE_ARMOR ).valueRef(Items.DIAMOND,      8).research(ResearchTier.EPIC);
        builder.register(Items.FLINT,                  4,      ResearchTier.COMMON);
        builder.register(Items.APPLE,                  128,    ResearchTier.COMMON);
        builder.register(Items.WHEAT_SEEDS,            16,     ResearchTier.COMMON);
        builder.register(Items.MELON_SEEDS,            16,     ResearchTier.COMMON);
        builder.register(Items.PUMPKIN_SEEDS,          16,     ResearchTier.COMMON);
        builder.register(Items.BEETROOT_SEEDS,         16,     ResearchTier.COMMON);
        builder.register(Items.WHEAT,                  24,     ResearchTier.COMMON);
        builder.register(Items.BEETROOT,               24,     ResearchTier.COMMON);
        builder.register(Items.CARROT,                 24,     ResearchTier.COMMON);
        builder.register(Items.POTATO,                 24,     ResearchTier.COMMON);
        builder.register(Items.POISONOUS_POTATO,       24,     ResearchTier.RARE);
        builder.register(Items.MELON_SLICE,            16,     ResearchTier.COMMON);
        builder.register(Items.SWEET_BERRIES,          16,     ResearchTier.COMMON);
        builder.register(Items.GLOW_BERRIES,           16,     ResearchTier.COMMON);
        builder.register(Items.COCOA_BEANS,            64,     ResearchTier.COMMON);
        builder.register(Items.CLAY_BALL,              16,     ResearchTier.ABUNDANT);
        builder.register(Items.ENCHANTED_GOLDEN_APPLE, 131072, ResearchTier.EPIC);
        builder.register(Items.SADDLE,                 192,    ResearchTier.RARE);
        builder.register(Items.NAME_TAG,               192,    ResearchTier.RARE);
        builder.register(Items.HONEYCOMB,              32,     ResearchTier.UNCOMMON);
        builder.register(Items.DRAGON_BREATH,          32,     ResearchTier.UNCOMMON).valueRef(Items.GLASS_BOTTLE, 1);
        builder.register(Items.EXPERIENCE_BOTTLE,      384,    ResearchTier.RARE);
        builder.register(Items.ECHO_SHARD,             864,    ResearchTier.UNCOMMON);
        builder.register(Items.DISC_FRAGMENT_5,        0,      ResearchTier.UNCOMMON).valueRef(Items.MUSIC_DISC_5, 1.0/9.0).noRequiredResearch();
        builder.register(Items.GOAT_HORN,              1536,   ResearchTier.UNIQUE);
        // mob_drops.json
        builder.register(Items.BEEF,                   64,     ResearchTier.COMMON);
        builder.register(Items.CHICKEN,                64,     ResearchTier.COMMON);
        builder.register(Items.MUTTON,                 64,     ResearchTier.COMMON);
        builder.register(Items.PORKCHOP,               64,     ResearchTier.COMMON);
        builder.register(Items.RABBIT,                 64,     ResearchTier.COMMON);
        builder.register(Items.COD,                    64,     ResearchTier.COMMON);
        builder.register(Items.SALMON,                 64,     ResearchTier.COMMON);
        builder.register(Items.PUFFERFISH,             64,     ResearchTier.COMMON);
        builder.register(Items.TROPICAL_FISH,          64,     ResearchTier.COMMON);
        builder.register(Items.LEATHER,                64,     ResearchTier.COMMON);
        builder.register(Items.RABBIT_HIDE,            16,     ResearchTier.COMMON);
        builder.register(Items.RABBIT_FOOT,            128,    ResearchTier.UNCOMMON);
        builder.register(Items.WHITE_WOOL,             48,     ResearchTier.COMMON);
        builder.register(Items.FEATHER,                48,     ResearchTier.COMMON);
        builder.register(Items.EGG,                    32,     ResearchTier.UNCOMMON);
        builder.register(Items.SLIME_BALL,             32,     ResearchTier.COMMON);
        builder.register(Items.INK_SAC,                16,     ResearchTier.COMMON);
        builder.register(Items.GLOW_INK_SAC,           64,     ResearchTier.COMMON);
        builder.register(Items.SCUTE,                  96,     ResearchTier.UNCOMMON);
        builder.register(Items.TURTLE_EGG,             192,    ResearchTier.UNCOMMON);
        builder.register(Items.OCHRE_FROGLIGHT,        512,    ResearchTier.RARE);
        builder.register(Items.PEARLESCENT_FROGLIGHT,  512,    ResearchTier.RARE);
        builder.register(Items.VERDANT_FROGLIGHT,      512,    ResearchTier.RARE);
        builder.register(Items.SPIDER_EYE,             128,    ResearchTier.COMMON);
        builder.register(Items.STRING,                 12,     ResearchTier.COMMON);
        builder.register(Items.ROTTEN_FLESH,           32,     ResearchTier.COMMON);
        builder.register(Items.BONE,                   144,    ResearchTier.COMMON);
        builder.register(Items.GUNPOWDER,              192,    ResearchTier.COMMON);
        builder.register(Items.PHANTOM_MEMBRANE,       192,    ResearchTier.COMMON);
        builder.register(Items.ENDER_PEARL,            1024,   ResearchTier.UNCOMMON);
        builder.register(Items.NAUTILUS_SHELL,         1024,   ResearchTier.UNCOMMON);
        builder.register(Items.BLAZE_ROD,              1536,   ResearchTier.UNCOMMON);
        builder.register(Items.SHULKER_SHELL,          2048,   ResearchTier.UNCOMMON);
        builder.register(Items.GHAST_TEAR,             4096,   ResearchTier.RARE);
        builder.register(Items.NETHER_STAR,            131072, ResearchTier.EPIC);
        builder.register(Items.TOTEM_OF_UNDYING,       131072, ResearchTier.RARE);
        builder.register(Items.DRAGON_EGG,             262144, 1);
        builder.register(Items.SKELETON_SKULL,         256,    ResearchTier.EPIC);
        builder.register(Items.ZOMBIE_HEAD,            256,    ResearchTier.EPIC);
        builder.register(Items.CREEPER_HEAD,           256,    ResearchTier.EPIC);
        builder.register(Items.TRIDENT,                16384,  ResearchTier.UNIQUE);
        // nether.json
        builder.register(Items.WARPED_WART_BLOCK).valueRef(Items.NETHER_WART, 9).research(ResearchTier.CRAFTED);
        builder.register(Items.NETHERRACK,             1,      ResearchTier.ABUNDANT);
        builder.register(Items.CRIMSON_NYLIUM,         1,      ResearchTier.COMMON);
        builder.register(Items.WARPED_NYLIUM,          1,      ResearchTier.COMMON);
        builder.register(Items.CRIMSON_ROOTS,          1,      ResearchTier.COMMON);
        builder.register(Items.WARPED_ROOTS,           1,      ResearchTier.COMMON);
        builder.register(Items.NETHER_SPROUTS,         1,      ResearchTier.COMMON);
        builder.register(Items.CRIMSON_FUNGUS,         32,     ResearchTier.COMMON);
        builder.register(Items.WARPED_FUNGUS,          32,     ResearchTier.COMMON);
        builder.register(Items.WEEPING_VINES,          8,      ResearchTier.COMMON);
        builder.register(Items.TWISTING_VINES,         8,      ResearchTier.COMMON);
        builder.register(Items.SOUL_SAND,              4,      ResearchTier.ABUNDANT);
        builder.register(Items.SOUL_SOIL,              4,      ResearchTier.ABUNDANT);
        builder.register(Items.GLOWSTONE_DUST,         384,    ResearchTier.ABUNDANT);
        builder.register(Items.BASALT,                 4,      ResearchTier.ABUNDANT);
        builder.register(Items.BLACKSTONE,             4,      ResearchTier.ABUNDANT);
        builder.register(Items.CRYING_OBSIDIAN,        768,    ResearchTier.UNCOMMON);
        builder.register(Items.GILDED_BLACKSTONE,      800,    ResearchTier.UNCOMMON);
        builder.register(Items.NETHER_WART,            24,     ResearchTier.COMMON);
        builder.register(Items.SHROOMLIGHT,            16,     ResearchTier.UNCOMMON);
        // ores.json
        builder.register(Items.COAL,                   32,     ResearchTier.COMMON);
        builder.register(Items.REDSTONE,               64,     ResearchTier.COMMON);
        builder.register(C_COPPER_INGOTS,              128,    ResearchTier.COMMON);
        builder.register(C_IRON_INGOTS,                256,    ResearchTier.COMMON);
        builder.register(Items.QUARTZ,                 256,    ResearchTier.COMMON);
        builder.register(Items.LAPIS_LAZULI,           864,    ResearchTier.COMMON);
        builder.register(Items.AMETHYST_SHARD,         864,    ResearchTier.COMMON);
        builder.register(C_GOLD_INGOTS,                2048,   ResearchTier.COMMON);
        builder.register(Items.EMERALD,                8192,   ResearchTier.UNCOMMON);
        builder.register(Items.DIAMOND,                8192,   ResearchTier.UNCOMMON);
        builder.register(Items.NETHERITE_SCRAP,        32768,  ResearchTier.RARE);
        // surface.json
        builder.register(Items.DIRT,                   1,      ResearchTier.ABUNDANT);
        builder.register(Items.DIRT_PATH,              1,      ResearchTier.COMMON);
        builder.register(Items.ROOTED_DIRT,            1,      ResearchTier.COMMON);
        builder.register(Items.GRASS_BLOCK,            1,      ResearchTier.COMMON);
        builder.register(Items.PODZOL,                 1,      ResearchTier.COMMON);
        builder.register(Items.MYCELIUM,               1,      ResearchTier.COMMON);
        builder.register(Items.MUD,                    1,      ResearchTier.ABUNDANT);
        builder.register(Items.SAND,                   1,      ResearchTier.ABUNDANT);
        builder.register(Items.RED_SAND,               1,      ResearchTier.ABUNDANT);
        builder.register(Items.VINE,                   8,      ResearchTier.COMMON);
        builder.register(Items.MANGROVE_ROOTS,         4,      ResearchTier.COMMON);
        builder.register(Items.LILY_PAD,               16,     ResearchTier.UNCOMMON);
        builder.register(Items.RED_MUSHROOM,           32,     ResearchTier.UNCOMMON);
        builder.register(Items.BROWN_MUSHROOM,         32,     ResearchTier.UNCOMMON);
        builder.register(Items.RED_MUSHROOM_BLOCK,     32,     ResearchTier.UNCOMMON);
        builder.register(Items.BROWN_MUSHROOM_BLOCK,   32,     ResearchTier.UNCOMMON);
        builder.register(Items.MUSHROOM_STEM,          32,     ResearchTier.UNCOMMON);
        builder.register(Items.CACTUS,                 32,     ResearchTier.COMMON);
        builder.register(Items.SUGAR_CANE,             32,     ResearchTier.COMMON);
        builder.register(Items.BAMBOO,                 32,     ResearchTier.COMMON);
        builder.register(Items.GRASS,                  1,      ResearchTier.COMMON);
        builder.register(Items.TALL_GRASS,             1,      ResearchTier.COMMON);
        builder.register(Items.FERN,                   1,      ResearchTier.COMMON);
        builder.register(Items.LARGE_FERN,             1,      ResearchTier.COMMON);
        builder.register(Items.DEAD_BUSH,              1,      ResearchTier.COMMON);
        builder.register(Items.SNOW,                   1,      ResearchTier.COMMON);
        builder.register(Items.SNOWBALL,               1,      ResearchTier.COMMON);
        builder.register(Items.ICE,                    1,      ResearchTier.COMMON);
        // tags.json
        builder.register(ItemTags.LOGS,                32,     ResearchTier.COMMON);
        builder.register(ItemTags.SAPLINGS,            32,     ResearchTier.UNCOMMON);
        builder.register(ItemTags.LEAVES,              4,      ResearchTier.COMMON);
        //builder.register(ItemTags.PLANKS,              8,      16).nonAutomatable();
        builder.register(ItemTags.WOOL,                0,      ResearchTier.UNCOMMON).valueRef(Items.WHITE_WOOL, 1).noRequiredResearch();
        builder.register(C_SHULKER_BOXES,              0,      ResearchTier.CRAFTED).valueRef(Items.SHULKER_BOX, 1).nonAutomatable();
        builder.register(ItemTags.TALL_FLOWERS,        32,     ResearchTier.UNCOMMON);
        builder.register(ItemTags.SMALL_FLOWERS,       16,     ResearchTier.COMMON);
        builder.register(ItemTags.MUSIC_DISCS,         2048,   ResearchTier.UNIQUE);
        // underground.json
        builder.register(Items.COBBLESTONE,            1,      ResearchTier.ABUNDANT);
        builder.register(Items.COBBLED_DEEPSLATE,      1,      ResearchTier.ABUNDANT);
        //builder.register(Items.STONE,                  1,      ResearchTier.COMMON).nonAutomatable();
        //builder.register(Items.DEEPSLATE,              1,      ResearchTier.COMMON).nonAutomatable();
        builder.register(Items.GRANITE,                1,      ResearchTier.ABUNDANT);
        builder.register(Items.DIORITE,                1,      ResearchTier.ABUNDANT);
        builder.register(Items.ANDESITE,               1,      ResearchTier.ABUNDANT);
        builder.register(Items.TUFF,                   1,      ResearchTier.ABUNDANT);
        builder.register(Items.DRIPSTONE_BLOCK,        1,      ResearchTier.COMMON);
        builder.register(Items.GRAVEL,                 4,      ResearchTier.ABUNDANT);
        builder.register(Items.MOSS_BLOCK,             4,      ResearchTier.COMMON);
        builder.register(Items.CALCITE,                4,      ResearchTier.COMMON);
        builder.register(Items.SPORE_BLOSSOM,          16,     ResearchTier.UNCOMMON);
        builder.register(Items.BIG_DRIPLEAF,           16,     ResearchTier.UNCOMMON);
        builder.register(Items.SMALL_DRIPLEAF,         16,     ResearchTier.UNCOMMON);
        builder.register(Items.HANGING_ROOTS,          1,      ResearchTier.COMMON);
        builder.register(Items.GLOW_LICHEN,            1,      ResearchTier.COMMON);
        builder.register(Items.POINTED_DRIPSTONE,      1,      ResearchTier.COMMON);
        builder.register(Items.SCULK,                  48,     ResearchTier.COMMON);
        builder.register(Items.SCULK_VEIN,             1,      ResearchTier.COMMON);
        builder.register(Items.SCULK_SENSOR,           256,    ResearchTier.RARE);
        builder.register(Items.SCULK_SHRIEKER,         512,    ResearchTier.RARE);
        builder.register(Items.SCULK_CATALYST,         2048,   ResearchTier.RARE);
        // underwater.json
        builder.register(Items.PRISMARINE_SHARD,       256,    ResearchTier.COMMON);
        builder.register(Items.PRISMARINE_CRYSTALS,    512,    ResearchTier.COMMON);
        builder.register(Items.HEART_OF_THE_SEA,       32768,  ResearchTier.EPIC);
        builder.register(Items.SEAGRASS,               1,      ResearchTier.COMMON);
        builder.register(Items.KELP,                   1,      ResearchTier.COMMON);
        builder.register(Items.SPONGE,                 128,    ResearchTier.COMMON);
        builder.register(Items.WET_SPONGE,             128,    ResearchTier.COMMON);
        builder.register(Items.SEA_PICKLE,             16,     ResearchTier.COMMON);
        builder.register(Items.TUBE_CORAL,             16,     ResearchTier.COMMON);
        builder.register(Items.BRAIN_CORAL,            16,     ResearchTier.COMMON);
        builder.register(Items.BUBBLE_CORAL,           16,     ResearchTier.COMMON);
        builder.register(Items.FIRE_CORAL,             16,     ResearchTier.COMMON);
        builder.register(Items.HORN_CORAL,             16,     ResearchTier.COMMON);
        builder.register(Items.TUBE_CORAL_FAN,         16,     ResearchTier.COMMON);
        builder.register(Items.BRAIN_CORAL_FAN,        16,     ResearchTier.COMMON);
        builder.register(Items.BUBBLE_CORAL_FAN,       16,     ResearchTier.COMMON);
        builder.register(Items.FIRE_CORAL_FAN,         16,     ResearchTier.COMMON);
        builder.register(Items.HORN_CORAL_FAN,         16,     ResearchTier.COMMON);
        builder.register(Items.TUBE_CORAL_BLOCK,       64,     ResearchTier.COMMON);
        builder.register(Items.BRAIN_CORAL_BLOCK,      64,     ResearchTier.COMMON);
        builder.register(Items.BUBBLE_CORAL_BLOCK,     64,     ResearchTier.COMMON);
        builder.register(Items.FIRE_CORAL_BLOCK,       64,     ResearchTier.COMMON);
        builder.register(Items.HORN_CORAL_BLOCK,       64,     ResearchTier.COMMON);
        builder.register(Items.DEAD_TUBE_CORAL,        1,      ResearchTier.COMMON);
        builder.register(Items.DEAD_BRAIN_CORAL,       1,      ResearchTier.COMMON);
        builder.register(Items.DEAD_BUBBLE_CORAL,      1,      ResearchTier.COMMON);
        builder.register(Items.DEAD_FIRE_CORAL,        1,      ResearchTier.COMMON);
        builder.register(Items.DEAD_HORN_CORAL,        1,      ResearchTier.COMMON);
        builder.register(Items.DEAD_TUBE_CORAL_FAN,    1,      ResearchTier.COMMON);
        builder.register(Items.DEAD_BRAIN_CORAL_FAN,   1,      ResearchTier.COMMON);
        builder.register(Items.DEAD_BUBBLE_CORAL_FAN,  1,      ResearchTier.COMMON);
        builder.register(Items.DEAD_FIRE_CORAL_FAN,    1,      ResearchTier.COMMON);
        builder.register(Items.DEAD_HORN_CORAL_FAN,    1,      ResearchTier.COMMON);
        builder.register(Items.DEAD_TUBE_CORAL_BLOCK,  4,      ResearchTier.COMMON);
        builder.register(Items.DEAD_BRAIN_CORAL_BLOCK, 4,      ResearchTier.COMMON);
        builder.register(Items.DEAD_BUBBLE_CORAL_BLOCK,4,      ResearchTier.COMMON);
        builder.register(Items.DEAD_FIRE_CORAL_BLOCK,  4,      ResearchTier.COMMON);
        builder.register(Items.DEAD_HORN_CORAL_BLOCK,  4,      ResearchTier.COMMON);

        builder = itemBuilder(new Identifier(BitExchange.MOD_ID, "items/common")).noOverride();
        builder.register(C_BAUXITE_DUSTS,              128,    ResearchTier.COMMON);
        builder.register(C_TIN_INGOTS,                 256,    ResearchTier.COMMON);
        builder.register(C_LEAD_INGOTS,                256,    ResearchTier.COMMON);
        builder.register(C_ALUMINUM_INGOTS,            256,    ResearchTier.COMMON);
        builder.register(C_NICKEL_INGOTS,              512,    ResearchTier.COMMON);
        builder.register(C_SILVER_INGOTS,              512,    ResearchTier.COMMON);
        builder.register(C_URANIUM_INGOTS,             1024,   ResearchTier.COMMON);
        builder.register(C_TUNGSTEN_INGOTS,            1024,   ResearchTier.COMMON);
        builder.register(C_IRIDIUM_INGOTS,             2048,   ResearchTier.COMMON);
        builder.register(C_TITANIUM_INGOTS,            4096,   ResearchTier.UNCOMMON);
        builder.register(C_PLATINUM_INGOTS,            4096,   ResearchTier.UNCOMMON);
        builder.register(C_SALTS,                      16,     ResearchTier.COMMON);
        builder.register(C_SALT_DUSTS,                 16,     ResearchTier.COMMON);
        builder.register(C_COPPER_DUSTS).valueRef(Items.COPPER_INGOT, 1).research(ResearchTier.CRAFTED).nonAutomatable();
        builder.register(C_IRON_DUSTS  ).valueRef(Items.IRON_INGOT,   1).research(ResearchTier.CRAFTED).nonAutomatable();
        builder.register(C_GOLD_DUSTS  ).valueRef(Items.GOLD_INGOT,   1).research(ResearchTier.CRAFTED).nonAutomatable();

        builder = itemBuilder(new Identifier(BitExchange.MOD_ID, "items/modern_industrialization")).noOverride();
        builder.register(C_LIGNITE_COAL,               32,     ResearchTier.COMMON);
        builder.register(C_ANTIMONY_INGOTS,            256,    ResearchTier.COMMON);
        builder.register(C_MOZANITE_DUSTS,             8192,   ResearchTier.UNCOMMON);

        builder = itemBuilder(new Identifier(BitExchange.MOD_ID, "items/ae2")).noOverride();
        builder.register( AEItems.CERTUS_QUARTZ_CRYSTAL        .asItem(), 64, ResearchTier.COMMON);
        builder.register( AEItems.CERTUS_QUARTZ_DUST           .asItem(), 64, ResearchTier.COMMON).valueRef(AEItems.CERTUS_QUARTZ_CRYSTAL.asItem(), 1).nonAutomatable();
        builder.register( AEItems.CERTUS_QUARTZ_CRYSTAL_CHARGED.asItem(), 0,  ResearchTier.CRAFTED).valueRef(AEItems.CERTUS_QUARTZ_CRYSTAL.asItem(), 1).nonAutomatable();
        builder.register(AEBlocks.SKY_STONE_BLOCK              .asItem(), 32, ResearchTier.COMMON);
    }
}
