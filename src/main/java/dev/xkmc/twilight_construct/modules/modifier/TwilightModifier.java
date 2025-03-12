package dev.xkmc.twilight_construct.modules.modifier;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.mining.BreakSpeedModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class TwilightModifier extends Modifier implements ConditionalStatModifierHook, BreakSpeedModifierHook {

	public TwilightModifier() {
	}

	public int getPriority() {
		return 85;
	}

	protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
		hookBuilder.addHook(this, ModifierHooks.CONDITIONAL_STAT, ModifierHooks.BREAK_SPEED);
	}

	public void onBreakSpeed(IToolStackView tool, ModifierEntry modifier, PlayerEvent.BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		if (TCUtils.isTwilight(event.getEntity().level())) {
			if (isEffective) {
				event.setNewSpeed(event.getNewSpeed() * (1 + 0.2f * tool.getMultiplier(ToolStats.MINING_SPEED) * modifier.getEffectiveLevel()));
			}
		}
	}

	public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity living, FloatToolStat stat, float baseValue, float multiplier) {
		if (stat == ToolStats.ATTACK_DAMAGE || stat == ToolStats.PROJECTILE_DAMAGE) {
			if (!TCUtils.isTwilight(living.level())) {
				return baseValue * (1 + 0.1f * modifier.getEffectiveLevel() * multiplier);
			}
		}
		return baseValue;
	}

}
