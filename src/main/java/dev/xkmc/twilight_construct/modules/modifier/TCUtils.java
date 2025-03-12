package dev.xkmc.twilight_construct.modules.modifier;

import net.minecraft.world.level.Level;
import twilightforest.init.TFDimensionSettings;

public class TCUtils {

	public static boolean isTwilight(Level level) {
		return level.dimension().location().equals(TFDimensionSettings.TWILIGHT_LEVEL_STEM.location());
	}

}
