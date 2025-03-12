package dev.xkmc.twilight_construct.modules.material;

import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;

public class TCMaterialDataProvider extends AbstractMaterialDataProvider {
	public TCMaterialDataProvider(PackOutput packOutput) {
		super(packOutput);
	}

	public String getName() {
		return "Tinker's Construct Materials";
	}

	protected void addMaterials() {
		this.addMaterial(TCMaterials.FIERY, 4, 25, false);
		this.addMaterial(TCMaterials.KNIGHTMETAL, 3, 25, false);
		this.addMaterial(TCMaterials.STEELEAF, 3, 25, false);
		this.addMaterial(TCMaterials.IRONWOOD, 3, 25, false);
	}

}
