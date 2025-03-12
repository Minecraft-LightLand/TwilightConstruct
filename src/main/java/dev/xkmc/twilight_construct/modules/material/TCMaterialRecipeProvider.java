
package dev.xkmc.twilight_construct.modules.material;

import dev.xkmc.twilight_construct.init.TwilightConstruct;
import dev.xkmc.twilight_construct.modules.fluid.TCFluids;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.crafting.Ingredient;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import twilightforest.init.TFBlocks;
import twilightforest.init.TFItems;

import java.util.function.Consumer;

public class TCMaterialRecipeProvider extends RecipeProvider implements IMaterialRecipeHelper {

	public TCMaterialRecipeProvider(PackOutput packOutput) {
		super(packOutput);
	}

	@Override
	public String getModId() {
		return TwilightConstruct.MODID;
	}

	public String getName() {
		return "Twilight Construct Material Recipe";
	}

	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
		this.addMaterialItems(consumer);
		this.addMaterialSmeltery(consumer);
	}

	private void addMaterialItems(Consumer<FinishedRecipe> consumer) {
		String folder = "tools/materials/";
		this.metalMaterialRecipe(consumer, TCMaterials.FIERY, folder, "fiery", true);
		this.metalMaterialRecipe(consumer, TCMaterials.KNIGHTMETAL, folder, "knightmetal", true);
		this.metalMaterialRecipe(consumer, TCMaterials.STEELEAF, folder, "steeleaf", true);
		this.metalMaterialRecipe(consumer, TCMaterials.IRONWOOD, folder, "ironwood", true);
		materialRecipe(consumer, TCMaterials.CARMINITE, Ingredient.of(TFItems.CARMINITE.get()),
				1, 1, folder + "carminite/gem");
		materialRecipe(consumer, TCMaterials.CARMINITE, Ingredient.of(TFBlocks.CARMINITE_BLOCK.get().asItem()),
				9, 1, folder + "carminite/block");
		materialRecipe(consumer, TCMaterials.NAGA, Ingredient.of(TFItems.NAGA_SCALE.get()),
				1, 1, folder + "naga/scale");
		materialRecipe(consumer, TCMaterials.RED_THREAD, Ingredient.of(TFBlocks.RED_THREAD.get()),
				1, 1, folder + "red_thread/thread");
	}

	private void addMaterialSmeltery(Consumer<FinishedRecipe> consumer) {
		String folder = "tools/materials/";
		this.materialMeltingCasting(consumer, TCMaterials.FIERY, TCFluids.MOLTEN_FIERY, folder);
		this.materialMeltingCasting(consumer, TCMaterials.KNIGHTMETAL, TCFluids.MOLTEN_KNIGHTMETAL, folder);
		this.materialMeltingCasting(consumer, TCMaterials.STEELEAF, TCFluids.MOLTEN_STEELEAF, folder);
		this.materialMeltingCasting(consumer, TCMaterials.IRONWOOD, TCFluids.MOLTEN_IRONWOOD, folder);
		this.materialMeltingCasting(consumer, TCMaterials.PHANTOM, TCFluids.MOLTEN_PHANTOM, folder);
		this.materialMeltingCasting(consumer, TCMaterials.CARMINITE, TCFluids.MOLTEN_CARMINITE, folder);
	}

}
