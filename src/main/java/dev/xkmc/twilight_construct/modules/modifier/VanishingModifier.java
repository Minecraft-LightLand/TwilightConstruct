package dev.xkmc.twilight_construct.modules.modifier;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class VanishingModifier extends Modifier implements InventoryTickModifierHook {

	public VanishingModifier() {
	}

	protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
		super.registerHooks(hookBuilder);
		hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK);
	}

	@Override
	public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level level, LivingEntity e, int index, boolean sel, boolean correct, ItemStack stack) {
		if (level.isClientSide() || !correct) return;
		var eff = e.getEffect(MobEffects.INVISIBILITY);
		if (eff != null && eff.getDuration() > 21) return;
		e.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 40, 0, true, false, true));

	}

}
