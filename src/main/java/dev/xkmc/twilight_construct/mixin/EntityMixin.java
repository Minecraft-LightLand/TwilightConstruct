package dev.xkmc.twilight_construct.mixin;

import dev.xkmc.twilight_construct.modules.modifier.TCUtils;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {

	@Inject(method = "isCurrentlyGlowing", cancellable = true, at = @At("HEAD"))
	public void twilight_construct$isCurrentlyGlowing$redMarker(CallbackInfoReturnable<Boolean> cir) {
		Entity self = (Entity) (Object) this;
		if (TCUtils.isRedMarked(self))
			cir.setReturnValue(true);
	}

	@Inject(method = "getTeamColor", cancellable = true, at = @At("HEAD"))
	public void twilight_construct$getTeamColor$redMarker(CallbackInfoReturnable<Integer> cir) {
		Entity self = (Entity) (Object) this;
		if (TCUtils.isRedMarked(self))
			cir.setReturnValue(0xffff0000);
	}

}
