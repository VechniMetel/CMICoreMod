package top.nebula.cmi.mixin;

import top.nebula.cmi.config.CommonConfig;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FluidType.class)
public abstract class FluidTypeMixin {

    @Shadow(remap = false)
    public abstract String getDescriptionId();

    @Inject(method = "move", at = @At("HEAD"), remap = false)
    public void move(FluidState state, LivingEntity entity, Vec3 movementVector, double gravity, CallbackInfoReturnable<Boolean> cir) {
        String[] idComponents = this.getDescriptionId().split("\\.");
        String fluidID = idComponents[1] + ":" + idComponents[2];
        if (CommonConfig.BURNING_FLUIDS.get().contains(fluidID)) {
            entity.lavaHurt();
        }
    }

    @Inject(method = "setItemMovement", at = @At("HEAD"), remap = false)
    public void setItemMovement(ItemEntity entity, CallbackInfo ci) {
        String[] idComponents = this.getDescriptionId().split("\\.");
        String fluidID = idComponents[1] + ":" + idComponents[2];
        if (CommonConfig.BURNING_FLUIDS.get().contains(fluidID)) {
            entity.lavaHurt();
        }
    }
}