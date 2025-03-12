package dev.xkmc.twilight_construct.modules.modifier;

import dev.xkmc.twilight_construct.init.TCModule;
import dev.xkmc.twilight_construct.init.TwilightConstruct;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

public class TCModifiers extends TCModule {

	private static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(TwilightConstruct.MODID);
	public static final StaticModifier<TwilightModifier> TWILIGHT = MODIFIERS.register("twilight", TwilightModifier::new);
	public static final StaticModifier<MysticGrowthModifier> GROWTH = MODIFIERS.register("mystic_growth", MysticGrowthModifier::new);
	public static final StaticModifier<SteeleafSynegyModifier> SYNERGY = MODIFIERS.register("synergy", SteeleafSynegyModifier::new);

	public TCModifiers() {
		MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

}
