package moe.sylvi.bitexchange.compat.modern_industrialization;

import aztech.modern_industrialization.machines.recipe.MachineRecipe;
import com.google.common.collect.Lists;
import moe.sylvi.bitexchange.BitRegistries;
import moe.sylvi.bitexchange.bit.BitResource;
import moe.sylvi.bitexchange.bit.info.ItemBitInfo;
import moe.sylvi.bitexchange.bit.registry.builder.recipe.RecipeHandler;
import moe.sylvi.bitexchange.bit.registry.builder.recipe.ResourceIngredient;
import moe.sylvi.bitexchange.bit.registry.builder.recipe.RecipeHandlerOutput;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class MIRecipeHandler<R extends MachineRecipe> implements RecipeHandler<R> {
    private final long fluidRatio;

    public MIRecipeHandler() {
        this(FluidConstants.BUCKET);
    }
    public MIRecipeHandler(long fluidRatio) {
        this.fluidRatio = fluidRatio;
    }

    @Override
    public boolean isAutomatable(MachineRecipe recipe) {
        return false;
    }

    @Override
    public List<ResourceIngredient<?, ?>> getIngredients(MachineRecipe recipe) {
        List<ResourceIngredient<?, ?>> result = new ArrayList<>();
        for (var input : recipe.itemInputs) {
            List<BitResource<Item, ItemBitInfo>> resources = new ArrayList<>();
            for (var item : input.getInputItems()) {
                resources.add(BitResource.of(BitRegistries.ITEM, item, input.amount * input.probability));
            }
            if (!resources.isEmpty()) {
                result.add(ResourceIngredient.of(resources));
            }
        }
        for (var input : recipe.fluidInputs) {
            result.add(ResourceIngredient.of(List.of(
                    BitResource.of(BitRegistries.FLUID, input.fluid, input.amount * input.probability)
            )));
        }
        return result;
    }

    @Override
    public List<RecipeHandlerOutput<?, ?>> getOutputs(MachineRecipe recipe) {
        List<RecipeHandlerOutput<?, ?>> result = new ArrayList<>();
        for (var output : recipe.itemOutputs) {
            result.add(new RecipeHandlerOutput<>(BitResource.of(BitRegistries.ITEM, output.item, output.amount * output.probability)));
        }
        for (var output : recipe.fluidOutputs) {
            result.add(new RecipeHandlerOutput<>(BitResource.of(BitRegistries.FLUID, output.fluid, output.amount * output.probability), fluidRatio));
        }
        return result;
    }
}
