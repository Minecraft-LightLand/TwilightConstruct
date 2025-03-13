package dev.xkmc.twilight_construct.modules.modifier;

import dev.xkmc.twilight_construct.init.TCModConfig;
import net.minecraft.core.Direction;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.mining.BreakSpeedModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class TwilightModifier extends Modifier implements MeleeDamageModifierHook, BreakSpeedModifierHook {

	public TwilightModifier() {
	}

	public int getPriority() {
		return 85;
	}

	protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
		hookBuilder.addHook(this, ModifierHooks.MELEE_DAMAGE, ModifierHooks.BREAK_SPEED);
	}

	public void onBreakSpeed(IToolStackView tool, ModifierEntry modifier, PlayerEvent.BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		if (TCUtils.isTwilight(event.getEntity().level())) {
			if (isEffective) {
				double rate = TCModConfig.COMMON.twilightMiningSpeedBonus.get();
				event.setNewSpeed(event.getNewSpeed() * (1 + (float) rate * modifier.getEffectiveLevel()));
			}
		}
	}

	@Override
	public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext ctx, float base, float total) {
		if (!TCUtils.isTwilight(ctx.getLevel())) {
			double rate = TCModConfig.COMMON.twilightAttackDamageBonus.get();
			return total * (1 + (float) rate * modifier.getLevel());
		}
		return total;
	}

}
