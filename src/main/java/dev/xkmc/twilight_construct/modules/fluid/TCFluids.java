package dev.xkmc.twilight_construct.modules.fluid;

import dev.xkmc.twilight_construct.init.TwilightConstruct;
import dev.xkmc.twilight_construct.init.TCModule;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.FluidType.Properties;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import slimeknights.mantle.registration.object.FlowingFluidObject;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.fluids.block.BurningLiquidBlock;
import slimeknights.tconstruct.fluids.data.FluidBlockstateModelProvider;
import slimeknights.tconstruct.fluids.data.FluidBucketModelProvider;

public final class TCFluids extends TCModule {
	public static final FlowingFluidObject<ForgeFlowingFluid> MEEF_STROGANOFF, FIERY_BLOOD;
	public static final FlowingFluidObject<ForgeFlowingFluid> MOLTEN_FIERY, MOLTEN_KNIGHTMETAL, MOLTEN_STEELEAF, MOLTEN_IRONWOOD;
	public static final FlowingFluidObject<ForgeFlowingFluid> MOLTEN_PHANTOM, MOLTEN_CARMINITE;

	static {
		MEEF_STROGANOFF = FLUIDS.register("meef_stroganoff").type(cool("meef_stroganoff")
				.temperature(400)).bucket().block(MapColor.DIRT, 0).commonTag().flowing();

		FIERY_BLOOD = FLUIDS.register("fiery_blood").type(hot("fiery_blood")
						.temperature(2000).lightLevel(15).density(3500))
				.block(BurningLiquidBlock.createBurning(MapColor.COLOR_RED, 15, 15, 5.0F))
				.bucket().flowing();

		MOLTEN_FIERY = FLUIDS.register("molten_fiery").type(hot("molten_fiery")
						.temperature(1800).lightLevel(15))
				.block(BurningLiquidBlock.createBurning(MapColor.COLOR_RED, 15, 15, 10.0F))
				.bucket().commonTag().flowing();

		MOLTEN_KNIGHTMETAL = FLUIDS.register("molten_knightmetal").type(hot("molten_knightmetal")
						.temperature(1300).lightLevel(12))
				.block(BurningLiquidBlock.createBurning(MapColor.COLOR_GRAY, 12, 10, 5.0F))
				.bucket().commonTag().flowing();

		MOLTEN_STEELEAF = FLUIDS.register("molten_steeleaf").type(hot("molten_steeleaf")
						.temperature(1200).lightLevel(12))
				.block(BurningLiquidBlock.createBurning(MapColor.COLOR_GREEN, 12, 10, 5.0F))
				.bucket().commonTag().flowing();

		MOLTEN_IRONWOOD = FLUIDS.register("molten_ironwood").type(hot("molten_ironwood")
						.temperature(1200).lightLevel(12))
				.block(BurningLiquidBlock.createBurning(MapColor.COLOR_GRAY, 12, 10, 5.0F))
				.bucket().commonTag().flowing();

		MOLTEN_PHANTOM = FLUIDS.register("molten_phantom").type(hot("molten_phantom")
						.temperature(2000).lightLevel(15))
				.block(BurningLiquidBlock.createBurning(MapColor.COLOR_GREEN, 15, 15, 15.0F))
				.bucket().commonTag().flowing();

		MOLTEN_CARMINITE = FLUIDS.register("molten_carminite").type(hot("molten_carminite")
						.temperature(1200).lightLevel(12))
				.block(BurningLiquidBlock.createBurning(MapColor.COLOR_RED, 12, 10, 5.0F))
				.bucket().commonTag().flowing();
	}

	private static FluidType.Properties cool() {
		return Properties.create().sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL).sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY).motionScale(0.0023333333333333335).canExtinguish(true);
	}

	private static FluidType.Properties cool(String name) {
		return cool().descriptionId(TwilightConstruct.makeDescriptionId("fluid", name)).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL).sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY);
	}

	private static FluidType.Properties slime(String name) {
		return cool(name).density(1600).viscosity(1600);
	}

	private static FluidType.Properties powder(String name) {
		return Properties.create().descriptionId(TwilightConstruct.makeDescriptionId("fluid", name)).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_POWDER_SNOW).sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_POWDER_SNOW);
	}

	private static FluidType.Properties hot(String name) {
		return Properties.create().density(2000).viscosity(10000).temperature(1000).descriptionId(TwilightConstruct.makeDescriptionId("fluid", name)).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA).sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA).motionScale(0.0023333333333333335).canSwim(false).canDrown(false).pathType(BlockPathTypes.LAVA).adjacentPathType(null);
	}

	@SubscribeEvent
	void modityTabs(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey().equals(TinkerFluids.tabFluids.getKey())) {
			event.accept(MEEF_STROGANOFF);
			event.accept(FIERY_BLOOD);
			event.accept(MOLTEN_FIERY);
			event.accept(MOLTEN_KNIGHTMETAL);
			event.accept(MOLTEN_STEELEAF);
			event.accept(MOLTEN_IRONWOOD);
			event.accept(MOLTEN_PHANTOM);
			event.accept(MOLTEN_CARMINITE);
		}
	}

	@SubscribeEvent
	void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		boolean client = event.includeClient();
		generator.addProvider(client, new TCFluidTextureProvider(packOutput));
		generator.addProvider(client, new FluidBucketModelProvider(packOutput, TwilightConstruct.MODID));
		generator.addProvider(client, new FluidBlockstateModelProvider(packOutput, TwilightConstruct.MODID));

		generator.addProvider(event.includeServer(), new TCFluidRecipeProvider(packOutput));
		generator.addProvider(event.includeServer(), new TCFluidTagProvider(packOutput, event.getLookupProvider(), event.getExistingFileHelper()));
	}

	@SubscribeEvent
	void commonSetup(FMLCommonSetupEvent event) {
		DispenseItemBehavior dispenseBucket = new DefaultDispenseItemBehavior() {
			private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

			public ItemStack execute(BlockSource source, ItemStack stack) {
				DispensibleContainerItem container = (DispensibleContainerItem) stack.getItem();
				BlockPos blockpos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
				Level level = source.getLevel();
				if (container.emptyContents(null, level, blockpos, null, stack)) {
					container.checkExtraContent(null, level, stack, blockpos);
					return new ItemStack(Items.BUCKET);
				} else {
					return this.defaultDispenseItemBehavior.dispense(source, stack);
				}
			}
		};
		event.enqueueWork(() -> {
			DispenserBlock.registerBehavior(MEEF_STROGANOFF, dispenseBucket);
			DispenserBlock.registerBehavior(FIERY_BLOOD, dispenseBucket);
			DispenserBlock.registerBehavior(MOLTEN_FIERY, dispenseBucket);
			DispenserBlock.registerBehavior(MOLTEN_KNIGHTMETAL, dispenseBucket);
			DispenserBlock.registerBehavior(MOLTEN_STEELEAF, dispenseBucket);
			DispenserBlock.registerBehavior(MOLTEN_IRONWOOD, dispenseBucket);
			DispenserBlock.registerBehavior(MOLTEN_PHANTOM, dispenseBucket);
			DispenserBlock.registerBehavior(MOLTEN_CARMINITE, dispenseBucket);
		});
	}

}
