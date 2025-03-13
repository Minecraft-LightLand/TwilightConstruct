package dev.xkmc.twilight_construct.modules.modifier;

import dev.xkmc.l2library.base.effects.ClientEffectCap;
import dev.xkmc.twilight_construct.init.TCModConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import twilightforest.init.TFDimensionSettings;

public class TCUtils {

	public static boolean isTwilight(Level level) {
		return level.dimension().location().equals(TFDimensionSettings.TWILIGHT_LEVEL_STEM.location());
	}

	public static boolean isRedMarked(Entity self) {
		if (!(self instanceof LivingEntity le)) return false;
		if (!le.level().isClientSide()) return false;
		if (!TCModConfig.CLIENT.enableRedMarkerEffect.get()) return false;
		return le.isAlive() && ClientEffectCap.HOLDER.get(le).map.containsKey(TCModifiers.RED_MARKER_EFFECT.get());
	}

}
