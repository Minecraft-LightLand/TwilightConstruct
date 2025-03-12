
package dev.xkmc.twilight_construct.modules.material;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialRenderInfoProvider;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;

public class TCMaterialRenderInfoProvider extends AbstractMaterialRenderInfoProvider {
	public TCMaterialRenderInfoProvider(PackOutput packOutput, AbstractMaterialSpriteProvider spriteProvider, ExistingFileHelper existingFileHelper) {
		super(packOutput, spriteProvider, existingFileHelper);
	}

	protected void addMaterialRenderInfo() {
		this.buildRenderInfo(TCMaterials.FIERY).color(0x662D08).fallbacks("metal");
		this.buildRenderInfo(TCMaterials.KNIGHTMETAL).color(0xC3D6AE).fallbacks("metal");
		this.buildRenderInfo(TCMaterials.STEELEAF).color(0x6DA25E).fallbacks("metal");
		this.buildRenderInfo(TCMaterials.IRONWOOD).color(0x83764A).fallbacks("metal");
		this.buildRenderInfo(TCMaterials.PHANTOM).color(0x526042).fallbacks("metal");
		this.buildRenderInfo(TCMaterials.NAGA).color(0x315D25);
		this.buildRenderInfo(TCMaterials.CARMINITE).color(0x9A0000);
		this.buildRenderInfo(TCMaterials.RED_THREAD).color(0xB70000);
	}

	public String getName() {
		return "Tinkers' Construct Material Render Info Provider";
	}
}
