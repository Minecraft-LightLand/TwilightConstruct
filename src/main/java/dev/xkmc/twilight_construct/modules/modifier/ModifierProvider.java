package dev.xkmc.twilight_construct.modules.modifier;

import dev.xkmc.twilight_construct.init.TwilightConstruct;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.json.RandomLevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.modifiers.modules.combat.MobEffectModule;
import slimeknights.tconstruct.library.recipe.partbuilder.Pattern;

public class ModifierProvider extends AbstractModifierProvider implements IConditionBuilder {
	public ModifierProvider(PackOutput packOutput) {
		super(packOutput);
	}

	protected void addModifiers() {
		this.buildModifier(TCModifiers.RED_MARKER)
				.addModule(MobEffectModule.builder(TCModifiers.RED_MARKER_EFFECT.get())
						.level(RandomLevelingValue.flat(0))
						.time(RandomLevelingValue.flat(3600))
						.build(), ModifierHooks.MELEE_HIT, ModifierHooks.PROJECTILE_HIT, ModifierHooks.ON_ATTACKED);
	}

	public String getName() {
		return "Twilight Construct Modifiers";
	}

	private static ModifierId id(String name) {
		return new ModifierId(TwilightConstruct.MODID, name);
	}

	private static Pattern pattern(String name) {
		return new Pattern(TwilightConstruct.MODID, name);
	}

}
