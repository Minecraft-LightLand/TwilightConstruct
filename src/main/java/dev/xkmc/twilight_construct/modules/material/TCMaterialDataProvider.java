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
		this.addMaterial(TCMaterials.FIERY, 4, 60, false);
		this.addMaterial(TCMaterials.KNIGHTMETAL, 3, 60, false);
		this.addMaterial(TCMaterials.STEELEAF, 2, 60, false);
		this.addMaterial(TCMaterials.IRONWOOD, 2, 60, false);
		this.addMaterial(TCMaterials.PHANTOM, 4, 60, false);
		this.addMaterial(TCMaterials.CARMINITE, 2, 60, false);
		this.addMaterial(TCMaterials.NAGA, 1, 60, true);
		this.addMaterial(TCMaterials.RED_THREAD, 1, 60, true);
	}

}
