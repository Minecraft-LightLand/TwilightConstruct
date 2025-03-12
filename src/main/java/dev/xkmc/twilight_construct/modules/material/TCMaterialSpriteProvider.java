
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
						.addARGB(63, 0xFFFEFA95)
						.addARGB(102, 0xFFFBAD24)
						//.addARGB(140, 0xFF461E03)
						//.addARGB(178, 0xFF5C2603)
						//.addARGB(216, 0xFF662D08)
						//.addARGB(255, 0xFF773511)

						.addARGB(140, 0xFF080606)
						.addARGB(178, 0xFF080606)
						.addARGB(216, 0xFF191313)
						.addARGB(255, 0xFF3D2323)
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

		buildMaterial(TCMaterials.PHANTOM).ranged().meleeHarvest().armor().fallbacks("metal")
				.colorMapper(GreyToColorMapping.builderFromBlack()
						.addARGB(63, 0xFF191919)
						.addARGB(102, 0xFF262626)
						.addARGB(140, 0xFF404539)
						.addARGB(178, 0xFF526042)
						.addARGB(216, 0xFF526042)
						.addARGB(255, 0xFF648141)
						.build());

	}

}
