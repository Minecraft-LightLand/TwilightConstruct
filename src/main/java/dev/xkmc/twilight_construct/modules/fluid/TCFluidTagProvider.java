package dev.xkmc.twilight_construct.modules.fluid;

import dev.xkmc.twilight_construct.init.TwilightConstruct;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.mantle.registration.object.FlowingFluidObject;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.common.TinkerTags;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class TCFluidTagProvider extends FluidTagsProvider {

	public TCFluidTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper helper) {
		super(packOutput, lookupProvider, TwilightConstruct.MODID, helper);
	}

	protected void addTags(HolderLookup.Provider pProvider) {
		this.fluidTag(TCFluids.MEEF_STROGANOFF);
		this.fluidTag(TCFluids.FIERY_BLOOD);
		this.fluidTag(TCFluids.MOLTEN_FIERY);
		this.fluidTag(TCFluids.MOLTEN_KNIGHTMETAL);
		this.fluidTag(TCFluids.MOLTEN_STEELEAF);
		this.fluidTag(TCFluids.MOLTEN_IRONWOOD);
		this.tag(TinkerTags.Fluids.METAL_TOOLTIPS).addTags(TCFluids.MOLTEN_FIERY.getTag(), TCFluids.MOLTEN_KNIGHTMETAL.getTag(), TCFluids.MOLTEN_STEELEAF.getTag(), TCFluids.MOLTEN_IRONWOOD.getTag());
		this.tag(TinkerTags.Fluids.AVERAGE_METAL_SPILLING).addTags(TCFluids.MOLTEN_FIERY.getTag(), TCFluids.MOLTEN_KNIGHTMETAL.getTag(), TCFluids.MOLTEN_STEELEAF.getTag(), TCFluids.MOLTEN_IRONWOOD.getTag());
	}

	public String getName() {
		return "Twilight Construct Fluid Tags";
	}

	private void fluidTag(FluidObject<?> fluid) {
		this.tag(Objects.requireNonNull(fluid.getCommonTag())).add(fluid.get());
	}

	private void fluidTag(FlowingFluidObject<?> fluid) {
		this.tag(fluid.getLocalTag()).add(fluid.getStill(), fluid.getFlowing());
		TagKey<Fluid> tag = fluid.getCommonTag();
		if (tag != null) {
			this.tag(tag).addTag(fluid.getLocalTag());
		}

	}

}
