package moe.sylvi.bitexchange.bit.info;

import moe.sylvi.bitexchange.BitComponents;
import moe.sylvi.bitexchange.BitRegistries;
import moe.sylvi.bitexchange.bit.research.*;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;

import java.util.List;

public class FluidBitInfo implements BitInfoResearchable<Fluid> {
    protected final Fluid fluid;
    protected final double value;
    protected final long research;
    protected final long ratio;
    protected final boolean researchable;
    protected final List<ResearchRequirement> researchRequirements;

    public FluidBitInfo(Fluid fluid, double value, long research, long ratio, boolean researchable, List<ResearchRequirement> researchRequirements) {
        this.fluid = fluid;
        this.value = value;
        this.research = research;
        this.ratio = ratio;
        this.researchable = researchable;
        this.researchRequirements = researchRequirements;
    }

    @Override
    public Fluid getResource() {
        return fluid;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public long getResearch() {
        return research;
    }

    @Override
    public long getRatio() {
        return ratio;
    }

    @Override
    public boolean isResearchable() {
        return researchable;
    }

    @Override
    public ResearchRequirement createResearchRequirement() {
        return new BitResearchRequirement(fluid, BitRegistries.FLUID);
    }

    @Override
    public List<ResearchRequirement> getResearchRequirements() {
        return researchRequirements;
    }

    @Override
    public <V> BitKnowledge<Fluid> getKnowledge(V provider) {
        return BitComponents.FLUID_KNOWLEDGE.get(provider);
    }

    @Override
    public Text getDisplayName() {
        return fluid.getDefaultState().getBlockState().getBlock().getName();
    }

    @Override
    public void showResearchMessage(PlayerEntity player) {
        var tooltipText = Texts.join(FluidVariantRendering.getTooltip(FluidVariant.of(fluid)), Text.literal("\n"));
        var hoverText = Texts.bracketed(getDisplayName()).formatted(Formatting.WHITE).styled((style) ->
                style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, tooltipText)));
        player.sendMessage(Text.literal("Researched fluid: ").formatted(Formatting.LIGHT_PURPLE).append(hoverText), false);
    }

    @Override
    public FluidBitInfo withResource(Fluid resource) {
        return new FluidBitInfo(resource, value, research, ratio, researchable, researchRequirements);
    }
}
