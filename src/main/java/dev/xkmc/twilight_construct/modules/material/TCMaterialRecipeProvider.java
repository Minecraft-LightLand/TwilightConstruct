
package dev.xkmc.twilight_construct.modules.material;

import dev.xkmc.twilight_construct.modules.fluid.TCFluids;
import dev.xkmc.twilight_construct.init.TwilightConstruct;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;

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
		this.metalMaterialRecipe(consumer, TCMaterials.PHANTOM, folder, "phantom", true);
	}

	private void addMaterialSmeltery(Consumer<FinishedRecipe> consumer) {
		String folder = "tools/materials/";
		this.materialMeltingCasting(consumer, TCMaterials.FIERY, TCFluids.MOLTEN_FIERY, folder);
		this.materialMeltingCasting(consumer, TCMaterials.KNIGHTMETAL, TCFluids.MOLTEN_KNIGHTMETAL, folder);
		this.materialMeltingCasting(consumer, TCMaterials.STEELEAF, TCFluids.MOLTEN_STEELEAF, folder);
		this.materialMeltingCasting(consumer, TCMaterials.IRONWOOD, TCFluids.MOLTEN_IRONWOOD, folder);
		this.materialMeltingCasting(consumer, TCMaterials.PHANTOM, TCFluids.MOLTEN_PHANTOM, folder);
	}

}
