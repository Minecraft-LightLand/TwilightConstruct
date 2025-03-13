package dev.xkmc.twilight_construct.modules.modifier;

import dev.xkmc.twilight_construct.init.TCModConfig;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import twilightforest.init.TFBlocks;
import twilightforest.init.TFItems;

public class SteeleafSynegyModifier extends Modifier implements InventoryTickModifierHook {

	private static double chance(int count) {
		double chance = TCModConfig.COMMON.synergyRepairChance.get();
		return (1 + Mth.log2(count)) * chance;
	}

	@Override
	protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
		hookBuilder.addHook(this, ModifierHooks.INVENTORY_TICK);
	}

	public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
		if (world.isClientSide || holder.getUseItem() == stack) return;
		if (holder.tickCount % 20 != 0) return;
		if (tool.getDamage() == 0) return;
		int count = 0;
		{
			ItemStack mainhand = holder.getMainHandItem();
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
		count *= modifier.getLevel();
		double chance = chance(count) * modifier.getLevel();
		for (int i = 0; i < 3; i++) {
			if (chance < 0.45) {
				time *= 2;
				chance *= 2;
			} else break;
		}
		if (holder.tickCount % time != 0) return;
		int repair = (int) chance;
		chance -= repair;
		if (holder.getRandom().nextDouble() < chance)
			repair++;
		tool.setDamage(Math.max(0, tool.getDamage() - repair));
	}
}
