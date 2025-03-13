package dev.xkmc.twilight_construct.modules.modifier;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;

public class RedMarkerModifier extends Modifier implements OnAttackedModifierHook, ProjectileHitModifierHook, MeleeHitModifierHook {

	protected RedMarkerModifier() {
		super();
	}

	protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
		super.registerHooks(hookBuilder);
		hookBuilder.addHook(this, ModifierHooks.ON_ATTACKED, ModifierHooks.PROJECTILE_HIT, ModifierHooks.MELEE_HIT);
	}

	@Override
	public void onAttacked(IToolStackView iToolStackView, ModifierEntry modifierEntry, EquipmentContext ctx, EquipmentSlot equipmentSlot, DamageSource damageSource, float v, boolean b) {
		var e = damageSource.getDirectEntity();
		if (e instanceof LivingEntity le) {
			le.addEffect(new MobEffectInstance(TCModifiers.RED_MARKER_EFFECT.get(), 3600, 0, true, false, true), ctx.getEntity());
		}
	}

	@Override
	public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
		var target = context.getLivingTarget();
		var attacker = context.getAttacker();
		if (target == null) return;
		target.addEffect(new MobEffectInstance(TCModifiers.RED_MARKER_EFFECT.get(), 3600, 0, true, false, true), attacker);
	}

	@Override
	public boolean onProjectileHitEntity(ModifierNBT modifiers, ModDataNBT persistentData, ModifierEntry modifier, Projectile projectile, EntityHitResult hit, @Nullable LivingEntity attacker, @Nullable LivingEntity target) {
		if (target == null || attacker == null) return false;
		target.addEffect(new MobEffectInstance(TCModifiers.RED_MARKER_EFFECT.get(), 3600, 0, true, false, true), attacker);
		return false;
	}
}
