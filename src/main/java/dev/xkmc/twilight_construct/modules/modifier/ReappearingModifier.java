package dev.xkmc.twilight_construct.modules.modifier;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class ReappearingModifier extends Modifier implements ModifyDamageModifierHook {

	public ReappearingModifier() {
	}

	protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
		super.registerHooks(hookBuilder);
		hookBuilder.addHook(this, ModifierHooks.MODIFY_DAMAGE);
	}

	@Override
	public float modifyDamageTaken(IToolStackView iToolStackView, ModifierEntry modifier, EquipmentContext ctx, EquipmentSlot equipmentSlot, DamageSource damageSource, float v, boolean b) {
		ctx.getEntity().invulnerableTime += modifier.getLevel() * 5;
		return 0;
	}


}
