
package dev.xkmc.twilight_construct.modules.material;

import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToColorMapping;

public class TCMaterialSpriteProvider extends AbstractMaterialSpriteProvider {
	public TCMaterialSpriteProvider() {
	}

	public String getName() {
		return "Tinkers' Construct Materials";
	}

	protected void addAllMaterials() {
		buildMaterial(TCMaterials.FIERY).ranged().meleeHarvest().armor().fallbacks("metal")
				.colorMapper(GreyToColorMapping.builderFromBlack()
						.addARGB(63, 0xFFFEFFFF)
						.addARGB(102, 0xFFFEFA95)
						.addARGB(140, 0xFFFBAD24)
						.addARGB(178, 0xFF461E03)//FB9624
						.addARGB(216, 0xFF662D08)
						.addARGB(255, 0xFF773511)
						.build());

		buildMaterial(TCMaterials.KNIGHTMETAL).ranged().meleeHarvest().armor().fallbacks("metal")
				.colorMapper(GreyToColorMapping.builderFromBlack()
						.addARGB(63, 0xFF333232)
						.addARGB(102, 0xFF6A735E)
						.addARGB(140, 0xFF808C72)
						.addARGB(178, 0xFFA3B391)
						.addARGB(216, 0xFFC3D6AE)
						.addARGB(255, 0xFFE7FBCD)
						.build());

		buildMaterial(TCMaterials.STEELEAF).ranged().meleeHarvest().armor().fallbacks("metal")
				.colorMapper(GreyToColorMapping.builderFromBlack()
						.addARGB(63, 0xFF1D3215)
						.addARGB(102, 0xFF27401D)
						.addARGB(140, 0xFF416130)
						.addARGB(178, 0xFF51873A)
						.addARGB(216, 0xFF51873A)
						.addARGB(255, 0xFF6DA25E)
						.build());

		buildMaterial(TCMaterials.IRONWOOD).ranged().meleeHarvest().armor().fallbacks("metal")
				.colorMapper(GreyToColorMapping.builderFromBlack()
						.addARGB(63, 0xFF352B24)
						.addARGB(102, 0xFF5F4D40)
						.addARGB(140, 0xFF5E574B)
						.addARGB(178, 0xFF83764A)
						.addARGB(216, 0xFF887C7C)
						.addARGB(255, 0xFF8A8E3A)
						.build());


	}

}
