package dev.xkmc.twilight_construct.modules.modifier;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import twilightforest.init.TFBlocks;
import twilightforest.init.TFItems;

public class SteeleafSynegyModifier extends Modifier implements InventoryTickModifierHook {

	public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
		if (world.isClientSide || holder.getUseItem() == stack) return;
		if (holder.tickCount % 20 != 0) return;
		if (tool.getDamage() == 0) return;
		int count = 0;
		{
			ItemStack mainhand = holder.getOffhandItem();
			if (mainhand.is(TFItems.STEELEAF_INGOT.get())) {
				count += mainhand.getCount();
			}
			if (mainhand.is(TFBlocks.STEELEAF_BLOCK.get().asItem())) {
				count += mainhand.getCount() * 9;
			}
		}
		{
			ItemStack offhand = holder.getOffhandItem();
			if (offhand.is(TFItems.STEELEAF_INGOT.get())) {
				count += offhand.getCount();
			}
			if (offhand.is(TFBlocks.STEELEAF_BLOCK.get().asItem())) {
				count += offhand.getCount() * 9;
			}
		}
		if (count <= 0) return;
		int time = 20;
		for (int i = 0; i < 3; i++) {
			if (count < 30) {
				time *= 2;
				count *= 2;
			} else break;
		}
		if (holder.tickCount % time != 0) return;
		double chance = count * 0.025 * modifier.getEffectiveLevel();
		int repair = (int) chance;
		chance -= repair;
		if (holder.getRandom().nextDouble() < chance)
			repair++;
		tool.setDamage(Math.max(0, tool.getDamage() - repair));
	}
}
