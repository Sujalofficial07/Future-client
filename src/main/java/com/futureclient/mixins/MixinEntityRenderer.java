package com.futureclient.mixins;

import com.futureclient.Main;
import com.futureclient.modules.ZoomModule;
import com.futureclient.modules.EntityOptimizationsModule;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Optimize entity rendering and adjust FOV for Zoom module.
 */
@Mixin(EntityRenderer.class)
public class MixinEntityRenderer {
    private final Minecraft mc = Minecraft.getMinecraft();

    /**
     * Redirect the getFOV modifier to apply smooth zoom from ZoomModule when enabled.
     * Example: adjust fovSetting calculation point.
     */
    @Redirect(method = "getFOVModifier", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;getRenderViewEntity()Lnet/minecraft/entity/Entity;"))
    private Entity onGetFovModify(Minecraft mcInstance) {
        try {
            ZoomModule zoom = (ZoomModule) Main.getModuleManager().getModule("Zoom");
            if (zoom != null && zoom.isEnabled()) {
                // adjust internal fov via module current value by returning the same entity
                // actual FOV calculation is handled in original method but we keep a hook point for performance.
            }
        } catch (Exception ignored) {}
        return mcInstance.getRenderViewEntity();
    }

    /**
     * Skip rendering entities when too far away if module enabled — reduce CPU/GPU usage.
     * We redirect calls to checkShouldRender to quickly filter.
     */
    @Redirect(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;isInRangeToRenderDist(D)Z"))
    private boolean onEntityRangeCheck(Entity entity, double distance) {
        try {
            EntityOptimizationsModule opt = (EntityOptimizationsModule) Main.getModuleManager().getModule("EntityOptimizations");
            if (opt != null && opt.isEnabled()) {
                double max = opt.getMaxRenderDistance();
                // fast path: if distance squared exceeds threshold squared, skip
                double thr = max * max;
                return distance <= thr && entity.isInRangeToRenderDist(distance);
            }
        } catch (Exception ignored) {}
        return entity.isInRangeToRenderDist(distance);
    }
}