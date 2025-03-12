package dev.xkmc.twilight_construct.modules.material;

import dev.xkmc.twilight_construct.init.TCModule;
import dev.xkmc.twilight_construct.init.TwilightConstruct;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

public class TCMaterials extends TCModule {

	public static final MaterialId FIERY = id("fiery");
	public static final MaterialId KNIGHTMETAL = id("knightmetal");
	public static final MaterialId STEELEAF = id("steeleaf");
	public static final MaterialId IRONWOOD = id("ironwood");

	private static MaterialId id(String name) {
		return new MaterialId(TwilightConstruct.MODID, name);
	}

	@SubscribeEvent
	void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		boolean client = event.includeClient();
		var data = new TCMaterialDataProvider(packOutput);
		generator.addProvider(client, data);
		generator.addProvider(client, new TCMaterialRecipeProvider(packOutput));
		generator.addProvider(client, new TCMaterialStatsDataProvider(packOutput, data));
		generator.addProvider(client, new TCMaterialTraitsDataProvider(packOutput, data));
		generator.addProvider(client, new TCMaterialRenderInfoProvider(packOutput, new TCMaterialSpriteProvider(), event.getExistingFileHelper()));
	}

}
