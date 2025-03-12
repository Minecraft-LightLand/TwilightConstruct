package dev.xkmc.twilight_construct.modules.modifier;

import dev.xkmc.twilight_construct.init.TCModule;
import dev.xkmc.twilight_construct.init.TwilightConstruct;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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

	public static final ModifierId RED_MARKER = id("red_marker");
	public static final StaticModifier<PrimalBurstModifier> PRIMAL_BURST = MODIFIERS.register("primal_burst", () ->
			new PrimalBurstModifier(0.1f));
	public static final StaticModifier<PrimalInstinctModifier> PRIMAL_INSTINCT = MODIFIERS.register("primal_instinct", () ->
			new PrimalInstinctModifier(0.5f));
	public static final StaticModifier<VanishingModifier> VANISHING = MODIFIERS.register("vanishing", VanishingModifier::new);
	public static final StaticModifier<ReappearingModifier> REAPPEARING = MODIFIERS.register("reappearing", ReappearingModifier::new);
	public static final RegistryObject<TinkerEffect> RED_MARKER_EFFECT = TCModule.MOB_EFFECTS.register("red_marker", () ->
			new NoMilkEffect(MobEffectCategory.NEUTRAL, 0xffff0000, false));

	public TCModifiers() {
		MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	@SubscribeEvent
	public void onGatherData(GatherDataEvent event) {
		event.getGenerator().addProvider(event.includeServer(), new ModifierProvider(event.getGenerator().getPackOutput()));
	}

	private static ModifierId id(String name) {
		return new ModifierId(TwilightConstruct.MODID, name);
	}

}
