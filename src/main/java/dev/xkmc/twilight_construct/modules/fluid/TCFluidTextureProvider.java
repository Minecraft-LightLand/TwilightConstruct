package dev.xkmc.twilight_construct.modules.fluid;

import dev.xkmc.twilight_construct.init.TwilightConstruct;
import net.minecraft.data.PackOutput;
import slimeknights.mantle.fluid.texture.AbstractFluidTextureProvider;
import slimeknights.mantle.fluid.texture.FluidTexture;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.TConstruct;

public class TCFluidTextureProvider extends AbstractFluidTextureProvider {

	public TCFluidTextureProvider(PackOutput packOutput) {
		super(packOutput, TwilightConstruct.MODID);
	}

	public void addTextures() {
		tintedStew(TCFluids.MEEF_STROGANOFF).color(0xFFD3C05A);
		tintedStew(TCFluids.FIERY_BLOOD).color(0xFF4F1010);

		//FFFF3333

		molten(TCFluids.MOLTEN_FIERY).color(0xFF773511);
		molten(TCFluids.MOLTEN_KNIGHTMETAL).color(0xFFC3D6AE);
		molten(TCFluids.MOLTEN_STEELEAF).color(0xFF51873A);
		molten(TCFluids.MOLTEN_IRONWOOD).color(0xFF8A8E3A);
		molten(TCFluids.MOLTEN_PHANTOM).color(0xFF526042);
		molten(TCFluids.MOLTEN_CARMINITE).color(0xFF9A0000);
	}


	private FluidTexture.Builder named(FluidObject<?> fluid, String name) {
		return this.texture(fluid).textures(TConstruct.getResource("fluid/" + name + "/"), false, false);
	}

	private FluidTexture.Builder tintedStew(FluidObject<?> fluid) {
		return this.named(fluid, "food/stew");
	}

	private FluidTexture.Builder molten(FluidObject<?> fluid) {
		return this.named(fluid, "molten");
	}

	public String getName() {
		return "Twilight Construct Fluid Texture Providers";
	}
}
