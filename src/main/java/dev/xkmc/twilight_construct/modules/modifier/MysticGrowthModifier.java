package dev.xkmc.twilight_construct.modules.modifier;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class MysticGrowthModifier extends Modifier implements InventoryTickModifierHook {

	public MysticGrowthModifier() {
	}

	public int getPriority() {
		return 85;
	}

	protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
		hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK);
	}

	public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
		if (world.isClientSide || holder.getUseItem() == stack) return;
		if (tool.getDamage() == 0) return;
		if (!TCUtils.isTwilight(world)) return;
		float lc = modifier.getEffectiveLevel();
		if (lc < 0.1f) return;
		if (holder.tickCount % (100 / modifier.getEffectiveLevel()) != 0) return;
		tool.setDamage(Math.max(0, tool.getDamage() - 1));
	}

}
