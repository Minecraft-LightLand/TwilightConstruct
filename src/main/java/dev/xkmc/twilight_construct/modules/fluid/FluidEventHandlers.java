package dev.xkmc.twilight_construct.modules.fluid;

import dev.xkmc.twilight_construct.init.TwilightConstruct;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.fluids.util.ConstantFluidContainerWrapper;
import twilightforest.data.tags.ItemTagGenerator;
import twilightforest.init.TFItems;

@Mod.EventBusSubscriber(modid = TwilightConstruct.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FluidEventHandlers {

	@SubscribeEvent
	public static void attachCapability(AttachCapabilitiesEvent<ItemStack> event) {
		if (event.getObject().is(ItemTagGenerator.FIERY_VIAL))
			event.addCapability(new ResourceLocation(TwilightConstruct.MODID, "fiery"),
					new ConstantFluidContainerWrapper(
							new FluidStack(TCFluids.FIERY_BLOOD.getStill(), 250),
							Items.GLASS_BOTTLE.getDefaultInstance()
					));
		if (event.getObject().is(TFItems.MEEF_STROGANOFF.get()))
			event.addCapability(new ResourceLocation(TwilightConstruct.MODID, "meef"),
					new ConstantFluidContainerWrapper(
							new FluidStack(TCFluids.MEEF_STROGANOFF.getStill(), 250),
							Items.BOWL.getDefaultInstance()
					));
	}

}
