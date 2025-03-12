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
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.recipe.data.ICommonRecipeHelper;
import slimeknights.mantle.registration.object.FlowingFluidObject;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.library.recipe.alloying.AlloyRecipeBuilder;
import slimeknights.tconstruct.library.recipe.fuel.MeltingFuelBuilder;
import slimeknights.tconstruct.library.recipe.melting.MeltingRecipeBuilder;
import twilightforest.TwilightForestMod;
import twilightforest.init.TFBlocks;
import twilightforest.init.TFItems;

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
		var fiery = new RecipeStruct(TwilightForestMod.ID, "fiery", TCFluids.MOLTEN_FIERY).genRecipe(this, consumer);
		var knight = new RecipeStruct(TwilightForestMod.ID, "knightmetal", TCFluids.MOLTEN_KNIGHTMETAL).genRecipe(this, consumer);
		var steeleaf = new RecipeStruct(TwilightForestMod.ID, "steeleaf", TCFluids.MOLTEN_STEELEAF).genRecipe(this, consumer);
		var ironwood = new RecipeStruct(TwilightForestMod.ID, "ironwood", TCFluids.MOLTEN_IRONWOOD).genRecipe(this, consumer);
		var phantom = new RecipeStruct(TwilightForestMod.ID, "phantom", TCFluids.MOLTEN_PHANTOM);

		AlloyRecipeBuilder.alloy(TCFluids.MOLTEN_FIERY, 120)
				.addInput(TCFluids.FIERY_BLOOD.ingredient(250))
				.addInput(TinkerFluids.moltenIron.ingredient(90))
				.save(consumer, new ResourceLocation(TwilightConstruct.MODID,"smeltery/alloying/molten_fiery"));

		phantom.genMeltTool(this, consumer, "/helmet", 5 * 90, TFItems.PHANTOM_HELMET.get());
		phantom.genMeltTool(this, consumer, "/chestplate", 8 * 90, TFItems.PHANTOM_CHESTPLATE.get());

		ironwood.genMeltItem(this, consumer, "/raw", 120, TFItems.RAW_IRONWOOD.get());
		knight.genMeltItem(this, consumer, "/shard", 10, TFItems.ARMOR_SHARD.get());
		knight.genMeltItem(this, consumer, "/cluster", 90, TFItems.ARMOR_SHARD_CLUSTER.get());
		knight.genMeltItem(this, consumer, "/loop", 4 * 90, TFItems.KNIGHTMETAL_RING.get());
		knight.genMeltItem(this, consumer, "/maze_breaker", 5 * 90, TFItems.MAZEBREAKER_PICKAXE.get());
		knight.genMeltTool(this, consumer, "/shield", 7 * 90, TFItems.KNIGHTMETAL_SHIELD.get());
		knight.genMeltTool(this, consumer, "/block_and_chain", 16 * 90, TFItems.BLOCK_AND_CHAIN.get());

		String folder = "smeltery/melting/";
		String meltingFolder = folder + "metal/";

		MeltingRecipeBuilder.melting(Ingredient.of(TFBlocks.IRON_LADDER.get()), TinkerFluids.moltenIron, 30)
				.save(consumer, location(meltingFolder + "iron/ladder"));

		MeltingRecipeBuilder.melting(Ingredient.of(TFItems.GOLDEN_MINOTAUR_AXE.get()), TinkerFluids.moltenGold, 5 * 90)
				.save(consumer, location(meltingFolder + "gold/minotaur_axe"));

		meltingFolder = "smeltery/melting/gems/";
		MeltingRecipeBuilder.melting(Ingredient.of(TFItems.DIAMOND_MINOTAUR_AXE.get()), TinkerFluids.moltenDiamond, 5 * 100)
				.save(consumer, location(meltingFolder + "diamond/minotaur_axe"));

		meltingFolder = "smeltery/melting/obsidian/";
		MeltingRecipeBuilder.melting(Ingredient.of(TFBlocks.GIANT_OBSIDIAN.get()), TinkerFluids.moltenObsidian, 64 * 1000)
				.save(consumer, location(meltingFolder + "obsidian/giant_block"));

		MeltingFuelBuilder.fuel(new FluidStack(TCFluids.FIERY_BLOOD.get(), 10), 400).save(consumer, this.location(folder + "fuel/fiery"));

	}

	public record RecipeStruct(
			String mod, String id, FlowingFluidObject<ForgeFlowingFluid> fluid
	) {

		public RecipeStruct genRecipe(TCFluidRecipeProvider pvd, Consumer<FinishedRecipe> consumer) {
			String meltingFolder = "smeltery/melting/metal/";
			pvd.metalMelting(consumer, fluid, id, false, false, meltingFolder, true);
			MeltingRecipeBuilder.melting(Ingredient.of(get("helmet")), fluid, 450).setDamagable(10).save(consumer, pvd.location(meltingFolder + id + "/helmet"));
			MeltingRecipeBuilder.melting(Ingredient.of(get("chestplate")), fluid, 720).setDamagable(10).save(consumer, pvd.location(meltingFolder + id + "/chestplate"));
			MeltingRecipeBuilder.melting(Ingredient.of(get("leggings")), fluid, 630).setDamagable(10).save(consumer, pvd.location(meltingFolder + id + "/leggings"));
			MeltingRecipeBuilder.melting(Ingredient.of(get("boots")), fluid, 360).setDamagable(10).save(consumer, pvd.location(meltingFolder + id + "/boots"));

			genMeltTool(pvd, consumer, "/axes", 270, get("axe"), get("pickaxe"));
			genMeltTool(pvd, consumer, "/weapon", 180, get("sword"), get("hoe"));
			genMeltTool(pvd, consumer, "/small", 90, get("shovel"));
			String castingFolder = "smeltery/casting/metal/";
			pvd.metalTagCasting(consumer, fluid, id, castingFolder, false);
			return this;
		}


		private RecipeStruct genMeltItem(TCFluidRecipeProvider pvd, Consumer<FinishedRecipe> consumer, String loc, int amount, Item... items) {
			String meltingFolder = "smeltery/melting/metal/" + id + loc;
			var ing = Ingredient.of(Stream.of(items).filter(Objects::nonNull).map(ItemStack::new));
			if (ing.isEmpty()) return this;
			MeltingRecipeBuilder.melting(ing, fluid, amount).save(consumer, pvd.location(meltingFolder));
			return this;
		}

		private RecipeStruct genMeltTool(TCFluidRecipeProvider pvd, Consumer<FinishedRecipe> consumer, String loc, int amount, Item... items) {
			String meltingFolder = "smeltery/melting/metal/" + id + loc;
			var ing = Ingredient.of(Stream.of(items).filter(Objects::nonNull).map(ItemStack::new));
			if (ing.isEmpty()) return this;
			MeltingRecipeBuilder.melting(ing, fluid, amount).setDamagable(10).save(consumer, pvd.location(meltingFolder));
			return this;
		}

		private @Nullable Item get(String name) {
			var ans = ForgeRegistries.ITEMS.getValue(new ResourceLocation(mod, id + "_" + name));
			if (ans == null || ans == Items.AIR) return null;
			return ans;
		}

	}


}