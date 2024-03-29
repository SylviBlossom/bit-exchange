package moe.sylvi.bitexchange.item;

import moe.sylvi.bitexchange.BitRegistries;
import moe.sylvi.bitexchange.bit.BitHelper;
import moe.sylvi.bitexchange.bit.BitResource;
import moe.sylvi.bitexchange.bit.info.BitInfo;
import moe.sylvi.bitexchange.bit.info.BitInfoResearchable;
import moe.sylvi.bitexchange.bit.registry.ResearchableBitRegistry;
import moe.sylvi.bitexchange.bit.research.ResearchableItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtElement;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BitInscriptionItem extends Item implements ResearchableItem {
    public BitInscriptionItem() {
        super(new FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.UNCOMMON));
    }

    public static List<BitResource> getResearch(ItemStack stack) {
        var tag = stack.getNbt();
        List<BitResource> result = new ArrayList<>();
        if (tag != null && tag.contains("Research")) {
            List<String> ids = new ArrayList<>();
            if (tag.contains("Research", NbtElement.STRING_TYPE)) {
                ids.add(tag.getString("Research"));
            } else {
                var list = tag.getList("Research", NbtElement.STRING_TYPE);
                for (var element : list) {
                    ids.add(element.asString());
                }
            }
            for (var id : ids) {
                try {
                    result.addAll(BitHelper.parseMultiResourceId(id));
                } catch (Throwable ignored) { }
            }
        }
        return result;
    }

    public static BitResource getRandomUnlearned(PlayerEntity player) {
        var list = new ArrayList<BitResource>();
        for (var registry : BitRegistries.REGISTRY) {
            if (registry instanceof ResearchableBitRegistry researchRegistry) {
                var knowledge = researchRegistry.getKnowledge(player);
                for (var info : registry) {
                    var resource = ((BitInfo)info).getResource();
                    if (!knowledge.hasLearned(resource)) {
                        list.add(BitResource.of(registry, resource, 1));
                    }
                }
            }
        }
        if (list.size() > 0) {
            return list.get(new Random().nextInt(list.size()));
        }
        return null;
    }

    @Override
    public boolean canResearch(ItemStack stack, PlayerEntity player) {
        return true;
    }

    @Override
    public boolean hasResearched(ItemStack stack, PlayerEntity player) {
        var researchList = getResearch(stack);
        if (researchList.isEmpty()) {
            return false;
        }
        for (var research : researchList) {
            var knowledge = ((ResearchableBitRegistry)research.getRegistry()).getKnowledge(player);
            if (!knowledge.hasLearned(research.getResource())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack research(ItemStack stack, PlayerEntity player) {
        var researchList = getResearch(stack);
        var learnedAny = false;
        if (researchList.isEmpty()) {
            var research = getRandomUnlearned(player);

            if (research != null) {
                var registry = (ResearchableBitRegistry)research.getRegistry();
                var knowledge = registry.getKnowledge(player);
                if (knowledge.learn(research.getResource())) {
                    learnedAny = true;
                    ((BitInfoResearchable)registry.get(research.getResource())).showResearchMessage(player);
                }
            }
        } else {
            for (var research : researchList) {
                var registry = (ResearchableBitRegistry)research.getRegistry();
                var knowledge = registry.getKnowledge(player);
                if (knowledge.learn(research.getResource())) {
                    learnedAny = true;
                    ((BitInfoResearchable)registry.get(research.getResource())).showResearchMessage(player);
                }
            }
        }
        if (learnedAny) {
            stack.decrement(1);
        }
        return stack;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("Research Item").formatted(Formatting.DARK_PURPLE));

        PlayerEntity player = null;
        if (world != null && world.isClient()) {
            player = MinecraftClient.getInstance().player;
        }

        var researchList = getResearch(stack);
        if (researchList.isEmpty()) {
            tooltip.add(Text.literal("Contains the bit sequence").formatted(Formatting.GRAY));
            tooltip.add(Text.literal("for a random item you").formatted(Formatting.GRAY));
            tooltip.add(Text.literal("haven't learned.").formatted(Formatting.GRAY));
        } else if (researchList.size() == 1) {
            var research = researchList.get(0);
            var registry = (ResearchableBitRegistry)research.getRegistry();
            var learned = player != null && registry.getKnowledge(player).hasLearned(research.getResource());
            tooltip.add(Text.literal("Contains the bit sequence").formatted(Formatting.GRAY));
            tooltip.add(Text.literal("for a ").formatted(Formatting.GRAY)
                    .append(registry.get(research.getResource()).getDisplayName().copy().formatted(learned ? Formatting.DARK_GRAY : Formatting.LIGHT_PURPLE))
                    .append(Text.literal(".").formatted(Formatting.GRAY)));
        } else {
            tooltip.add(Text.literal("Contains a series of").formatted(Formatting.GRAY));
            tooltip.add(Text.literal("bit sequences for:").formatted(Formatting.GRAY));
            for (var research : researchList) {
                var registry = (ResearchableBitRegistry)research.getRegistry();
                var learned = player != null && registry.getKnowledge(player).hasLearned(research.getResource());
                tooltip.add(Text.literal("- ").formatted(Formatting.GRAY)
                        .append(registry.get(research.getResource()).getDisplayName().copy().formatted(learned ? Formatting.DARK_GRAY : Formatting.LIGHT_PURPLE)));
            }
        }

        super.appendTooltip(stack, world, tooltip, context);
    }
}
