
package dev.xkmc.twilight_construct.modules.material;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Tiers;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.tools.stats.*;

public class TCMaterialStatsDataProvider extends AbstractMaterialStatsDataProvider {

	public TCMaterialStatsDataProvider(PackOutput packOutput, AbstractMaterialDataProvider materials) {
		super(packOutput, materials);
	}

	public String getName() {
		return "Twilight Construct Material Stats";
	}

	protected void addMaterialStats() {
		this.addMeleeHarvest();
		this.addRanged();
		this.addArmor();
	}

	private void addMeleeHarvest() {
		this.addMaterialStats(TCMaterials.FIERY,
				new HeadMaterialStats(1024, 9, Tiers.NETHERITE, 4),
				HandleMaterialStats.multipliers().miningSpeed(1.1F).attackSpeed(1.1F).build(),
				StatlessMaterialStats.BINDING);
		this.addMaterialStats(TCMaterials.KNIGHTMETAL,
				new HeadMaterialStats(512, 8, Tiers.DIAMOND, 3),
				HandleMaterialStats.multipliers().durability(1.2F).attackSpeed(0.9F).attackDamage(1.1F).build(),
				StatlessMaterialStats.BINDING);
		this.addMaterialStats(TCMaterials.STEELEAF,
				new HeadMaterialStats(131, 8, Tiers.NETHERITE, 3),
				HandleMaterialStats.multipliers().durability(0.8f).attackSpeed(1.1F).attackDamage(1.2F).build(),
				StatlessMaterialStats.BINDING);
		this.addMaterialStats(TCMaterials.IRONWOOD,
				new HeadMaterialStats(512, 6.5F, Tiers.IRON, 2),
				HandleMaterialStats.multipliers().durability(1.2F).build(),
				StatlessMaterialStats.BINDING);
		this.addMaterialStats(TCMaterials.PHANTOM,
				new HeadMaterialStats(1280, 8, Tiers.NETHERITE, 3),
				HandleMaterialStats.multipliers().attackSpeed(1.1F).build(),
				StatlessMaterialStats.BINDING);
		this.addMaterialStats(TCMaterials.NAGA,
				new HeadMaterialStats(512, 8, Tiers.DIAMOND, 3),
				HandleMaterialStats.multipliers().attackDamage(1.1F).build());
		this.addMaterialStats(TCMaterials.CARMINITE, HandleMaterialStats.multipliers().durability(0.9f).miningSpeed(0.8f).build());
		this.addMaterialStats(TCMaterials.RED_THREAD, StatlessMaterialStats.BINDING);
	}

	private void addRanged() {
		addMaterialStats(TCMaterials.FIERY,
				new LimbMaterialStats(1024, 0, 0.1F, 0.1F),
				new GripMaterialStats(0.1F, 0, 4)
		);
		addMaterialStats(TCMaterials.KNIGHTMETAL,
				new LimbMaterialStats(512, -0.2f, 0.2F, 0),
				new GripMaterialStats(0.2F, 0, 3)
		);
		addMaterialStats(TCMaterials.STEELEAF,
				new LimbMaterialStats(131, 0.1f, 0.1f, 0.05f),
				new GripMaterialStats(-0.2F, 0.1f, 3)
		);
		addMaterialStats(TCMaterials.IRONWOOD,
				new LimbMaterialStats(512, 0, 0, 0),
				new GripMaterialStats(0.2F, 0, 2)
		);
		addMaterialStats(TCMaterials.PHANTOM,
				new LimbMaterialStats(1280, 0.1F, 0f, 0f),
				new GripMaterialStats(0.1f, 0f, 4)
		);
		addMaterialStats(TCMaterials.NAGA,
				new LimbMaterialStats(512, 0.1f, 0.1f, 0),
				new GripMaterialStats(0.1f, 0, 3)
		);
		addMaterialStats(TCMaterials.CARMINITE,
				new GripMaterialStats(-0.1f, 0.2f, 0)
		);
		addMaterialStats(TCMaterials.RED_THREAD,
				StatlessMaterialStats.BOWSTRING);
	}

	private void addArmor() {
		addArmorShieldStats(TCMaterials.FIERY,
				PlatingMaterialStats.builder().durabilityFactor(25).armor(4, 7, 9, 4)
						.toughness(1.5f),
				StatlessMaterialStats.MAILLE
		);
		addArmorShieldStats(TCMaterials.KNIGHTMETAL,
				PlatingMaterialStats.builder().durabilityFactor(20).armor(3, 6, 8, 3)
						.toughness(1),
				StatlessMaterialStats.MAILLE
		);
		addArmorShieldStats(TCMaterials.STEELEAF,
				PlatingMaterialStats.builder().durabilityFactor(10).armor(3, 6, 8, 3),
				StatlessMaterialStats.MAILLE
		);
		addArmorShieldStats(TCMaterials.IRONWOOD,
				PlatingMaterialStats.builder().durabilityFactor(20).armor(2, 5, 7, 2),
				StatlessMaterialStats.MAILLE
		);
		addArmorShieldStats(TCMaterials.PHANTOM,
				PlatingMaterialStats.builder().durabilityFactor(30).armor(3, 6, 8, 3)
						.toughness(2.5f),
				StatlessMaterialStats.MAILLE
		);
		addArmorShieldStats(TCMaterials.NAGA,
				PlatingMaterialStats.builder().durabilityFactor(21).armor(3, 6, 7, 2)
						.toughness(0.5f),
				StatlessMaterialStats.MAILLE
		);
		addArmorShieldStats(TCMaterials.CARMINITE,
				PlatingMaterialStats.builder().durabilityFactor(10).armor(2, 5, 7, 2),
				StatlessMaterialStats.MAILLE
		);
		addMaterialStats(TCMaterials.RED_THREAD,
				StatlessMaterialStats.MAILLE
		);
	}

}
