package dev.xkmc.twilight_construct.modules.modifier;

import dev.xkmc.twilight_construct.init.TCModule;
import dev.xkmc.twilight_construct.init.TwilightConstruct;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.common.TinkerEffect;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;

public class TCModifiers extends TCModule {

	private static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(TwilightConstruct.MODID);
	public static final StaticModifier<TwilightModifier> TWILIGHT = MODIFIERS.register("twilight", TwilightModifier::new);
	public static final StaticModifier<MysticGrowthModifier> GROWTH = MODIFIERS.register("mystic_growth", MysticGrowthModifier::new);
	public static final StaticModifier<SteeleafSynegyModifier> SYNERGY = MODIFIERS.register("synergy", SteeleafSynegyModifier::new);
	public static final StaticModifier<PrimalBurstModifier> PRIMAL_BURST = MODIFIERS.register("primal_burst", PrimalBurstModifier::new);
	public static final StaticModifier<PrimalInstinctModifier> PRIMAL_INSTINCT = MODIFIERS.register("primal_instinct", PrimalInstinctModifier::new);
	public static final StaticModifier<VanishingModifier> VANISHING = MODIFIERS.register("vanishing", VanishingModifier::new);
	public static final StaticModifier<ReappearingModifier> REAPPEARING = MODIFIERS.register("reappearing", ReappearingModifier::new);
	public static final StaticModifier<RedMarkerModifier> RED_MARKER = MODIFIERS.register("red_marker", RedMarkerModifier::new);

	public static final RegistryObject<TinkerEffect> RED_MARKER_EFFECT = TCModule.MOB_EFFECTS.register("red_marker", () ->
			new NoMilkEffect(MobEffectCategory.NEUTRAL, 0xffff0000, false));

	public TCModifiers() {
		MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	private static ModifierId id(String name) {
		return new ModifierId(TwilightConstruct.MODID, name);
	}

}
