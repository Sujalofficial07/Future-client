ackage com.futureclient.mixins;

import com.futureclient.FutureClient;
import com.futureclient.api.Module;
import com.futureclient.events.RenderEvent;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngame.class)
public class MixinGuiIngame {
    
    @Inject(method = "renderGameOverlay", at = @At("RETURN"))
    private void onRenderGameOverlay(float partialTicks, CallbackInfo ci) {
        if (FutureClient.getInstance() != null) {
            // Fire render event
            FutureClient.getInstance().getEventBus().post(new RenderEvent(partialTicks));
            
            // Render all enabled modules
            for (Module module : FutureClient.getInstance().getModuleManager().getEnabledModules()) {
                module.onRender(partialTicks);
            }
        }
    }
}
