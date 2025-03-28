package dev.xkmc.twilight_construct.init;

import dev.xkmc.l2library.base.L2Registrate;
import dev.xkmc.l2library.init.data.L2TagGen;
import dev.xkmc.twilight_construct.modules.fluid.TCFluids;
import dev.xkmc.twilight_construct.modules.material.TCMaterials;
import dev.xkmc.twilight_construct.modules.modifier.TCModifiers;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TwilightConstruct.MODID)
@Mod.EventBusSubscriber(modid = TwilightConstruct.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TwilightConstruct {

	public static final String MODID = "twilight_construct";
	private static final L2Registrate REGISTRATE = new L2Registrate(MODID);

	public TwilightConstruct() {
		ForgeMod.enableMilkFluid();
		TCModule.initRegisters();
		var bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.register(new TCFluids());
		bus.register(new TCMaterials());
		bus.register(new TCModifiers());
		TCModConfig.init();
	}

	@SubscribeEvent
	public static void onCommonSetup(FMLCommonSetupEvent event) {

	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void onGatherData(GatherDataEvent event) {
		REGISTRATE.addDataGenerator(L2TagGen.EFF_TAGS, e -> e.addTag(L2TagGen.TRACKED_EFFECTS).add(TCModifiers.RED_MARKER_EFFECT.get()));
	}


	public static String makeDescriptionId(String type, String name) {
		return type + "." + MODID + "." + name;
	}

}
