package dev.xkmc.twilight_construct.modules.fluid;

import dev.xkmc.twilight_construct.init.TwilightConstruct;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.recipe.data.ICommonRecipeHelper;
import slimeknights.mantle.registration.object.FlowingFluidObject;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.library.recipe.alloying.AlloyRecipeBuilder;
import slimeknights.tconstruct.library.recipe.melting.MeltingRecipeBuilder;
import twilightforest.TwilightForestMod;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class TCFluidRecipeProvider extends RecipeProvider implements ISmelteryRecipeHelper, ICommonRecipeHelper {

	public TCFluidRecipeProvider(PackOutput packOutput) {
		super(packOutput);
	}

	@Override
	public String getModId() {
		return TwilightConstruct.MODID;
	}

	public String getName() {
		return "Twilight Construct Recipes";
	}

	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
		new RecipeStruct(TwilightForestMod.ID, "fiery", TCFluids.MOLTEN_FIERY).genRecipe(this, consumer);
		new RecipeStruct(TwilightForestMod.ID, "knightmetal", TCFluids.MOLTEN_KNIGHTMETAL).genRecipe(this, consumer);
		new RecipeStruct(TwilightForestMod.ID, "steeleaf", TCFluids.MOLTEN_STEELEAF).genRecipe(this, consumer);
		new RecipeStruct(TwilightForestMod.ID, "ironwood", TCFluids.MOLTEN_IRONWOOD).genRecipe(this, consumer);

		AlloyRecipeBuilder.alloy(TCFluids.MOLTEN_FIERY, 90)
				.addInput(TCFluids.FIERY_BLOOD.ingredient(250))
				.addInput(TinkerFluids.moltenIron.ingredient(90))
				.save(consumer, new ResourceLocation(TwilightConstruct.MODID,"smeltery/alloying/molten_fiery"));

	}

	public record RecipeStruct(
			String mod, String id, FlowingFluidObject<ForgeFlowingFluid> fluid
	) {

		public void genRecipe(TCFluidRecipeProvider pvd, Consumer<FinishedRecipe> consumer) {
			String meltingFolder = "smeltery/melting/metal/";
			pvd.metalMelting(consumer, fluid, id, false, false, meltingFolder, true);
			MeltingRecipeBuilder.melting(Ingredient.of(get("helmet")), fluid, 450).setDamagable(10).save(consumer, pvd.location(meltingFolder + id + "/helmet"));
			MeltingRecipeBuilder.melting(Ingredient.of(get("chestplate")), fluid, 720).setDamagable(10).save(consumer, pvd.location(meltingFolder + id + "/chestplate"));
			MeltingRecipeBuilder.melting(Ingredient.of(get("leggings")), fluid, 630).setDamagable(10).save(consumer, pvd.location(meltingFolder + id + "/leggings"));
			MeltingRecipeBuilder.melting(Ingredient.of(get("boots")), fluid, 360).setDamagable(10).save(consumer, pvd.location(meltingFolder + id + "/boots"));

			genMelt(pvd, consumer, meltingFolder + id + "/axes", 270, get("axe"), get("pickaxe"));
			genMelt(pvd, consumer, meltingFolder + id + "/weapon", 180, get("sword"), get("hoe"));
			genMelt(pvd, consumer, meltingFolder + id + "/small", 90, get("shovel"));
			String castingFolder = "smeltery/casting/metal/";
			pvd.metalTagCasting(consumer, fluid, id, castingFolder, false);

		}

		private void genMelt(TCFluidRecipeProvider pvd, Consumer<FinishedRecipe> consumer, String folder, int amount, Item... items) {
			var ing = Ingredient.of(Stream.of(items).filter(Objects::nonNull).map(ItemStack::new));
			if (ing.isEmpty()) return;
			MeltingRecipeBuilder.melting(ing, fluid, amount).setDamagable(10).save(consumer, pvd.location(folder));
		}

		private @Nullable Item get(String name) {
			var ans = ForgeRegistries.ITEMS.getValue(new ResourceLocation(mod, id + "_" + name));
			if (ans == null || ans == Items.AIR) return null;
			return ans;
		}

	}


}