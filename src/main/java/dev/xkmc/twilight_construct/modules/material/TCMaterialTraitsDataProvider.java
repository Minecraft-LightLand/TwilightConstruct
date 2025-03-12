
package dev.xkmc.twilight_construct.modules.material;

import dev.xkmc.twilight_construct.modules.modifier.TCModifiers;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.data.ModifierIds;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;
import slimeknights.tconstruct.tools.stats.PlatingMaterialStats;

public class TCMaterialTraitsDataProvider extends AbstractMaterialTraitDataProvider {
	public TCMaterialTraitsDataProvider(PackOutput packOutput, AbstractMaterialDataProvider materials) {
		super(packOutput, materials);
	}

	public String getName() {
		return "Tinker's Construct Material Traits";
	}

	protected void addMaterialTraits() {
		addDefaultTraits(TCMaterials.FIERY, TinkerModifiers.fiery);
		addTraits(TCMaterials.FIERY, HeadMaterialStats.ID, TinkerModifiers.fiery, TinkerModifiers.autosmelt);

		addDefaultTraits(TCMaterials.KNIGHTMETAL, ModifierIds.heavy);
		addTraits(TCMaterials.KNIGHTMETAL, HeadMaterialStats.ID, ModifierIds.heavy, TCModifiers.TWILIGHT.getId());
		PlatingMaterialStats.TYPES.forEach(e -> addTraits(TCMaterials.KNIGHTMETAL, e.getId(), ModifierIds.heavy, TinkerModifiers.thorns.getId()));

		addDefaultTraits(TCMaterials.STEELEAF, TCModifiers.SYNERGY);
		addTraits(TCMaterials.STEELEAF, HeadMaterialStats.ID, ModifierIds.heavy, TCModifiers.TWILIGHT.getId());
		PlatingMaterialStats.TYPES.forEach(e -> addTraits(TCMaterials.STEELEAF, e.getId(), TCModifiers.SYNERGY, TinkerModifiers.thorns));

		addDefaultTraits(TCMaterials.IRONWOOD, TCModifiers.GROWTH);
		addTraits(TCMaterials.IRONWOOD, HeadMaterialStats.ID, TCModifiers.GROWTH, TCModifiers.TWILIGHT);

	}
}
