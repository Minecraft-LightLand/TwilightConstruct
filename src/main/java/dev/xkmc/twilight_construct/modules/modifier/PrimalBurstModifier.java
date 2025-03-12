package dev.xkmc.twilight_construct.modules.modifier;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.common.TinkerTags.Items;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.mining.BreakSpeedModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.Util;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class PrimalBurstModifier extends Modifier implements ConditionalStatModifierHook, TooltipModifierHook, BreakSpeedModifierHook {
	private static final Component MINING_SPEED = TConstruct.makeTranslation("armor_stat", "mining_speed");
	private final float multiplier;
	private final Lazy<UUID> uuid = Lazy.of(() -> UUID.nameUUIDFromBytes(this.getId().toString().getBytes()));
	private final Lazy<String> attributeName = Lazy.of(() -> {
		ResourceLocation id = this.getId();
		return id.getPath() + "." + id.getNamespace() + ".attack_damage";
	});

	protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
		super.registerHooks(hookBuilder);
		hookBuilder.addHook(this, ModifierHooks.TOOLTIP, ModifierHooks.CONDITIONAL_STAT, ModifierHooks.BREAK_SPEED);
	}

	public PrimalBurstModifier(float multiplier) {
		this.multiplier = multiplier;
	}

	private float getMultiplier(IToolStackView tool, float level) {
		return (float) tool.getDamage() / tool.getStats().getInt(ToolStats.DURABILITY) * this.multiplier * level;
	}

	public void addTooltip(IToolStackView tool, ModifierEntry modifier, @Nullable Player player, List<Component> tooltip, TooltipKey tooltipKey, TooltipFlag tooltipFlag) {
		double boost = this.getMultiplier(tool, modifier.getLevel());
		if (boost != 0.0 && tool.hasTag(Items.HARVEST)) {
			tooltip.add(this.applyStyle(Component.literal(Util.PERCENT_BOOST_FORMAT.format(-boost)).append(" ").append(MINING_SPEED)));
		}
	}

	@Override
	public float modifyStat(IToolStackView tool, ModifierEntry modifier, LivingEntity livingEntity, FloatToolStat stat, float base, float mult) {
		if (stat == ToolStats.ATTACK_SPEED || stat == ToolStats.DRAW_SPEED)
			return base * (1 + this.getMultiplier(tool, modifier.getEffectiveLevel() * mult));
		return base;
	}


	public void onBreakSpeed(IToolStackView tool, ModifierEntry modifier, PlayerEvent.BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		if (isEffective) {
			event.setNewSpeed((float) ((double) event.getNewSpeed() * (1.0 - this.getMultiplier(tool, modifier.getLevel()))));
		}

	}
}
