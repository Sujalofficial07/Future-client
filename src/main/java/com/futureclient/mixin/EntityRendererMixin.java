package com.futureclient.mixin;

import com.futureclient.FutureClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin<T extends Entity> {

    @Shadow public abstract TextRenderer getTextRenderer();

    @Inject(method = "renderLabelIfPresent", at = @At("HEAD"), cancellable = true)
    protected void onRenderLabel(T entity, Text text, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        // 1. Agar entity sneak kar rahi hai toh vanilla render hone do (Fair Play)
        if (entity.isSneaking()) return;

        // 2. Custom Rendering shuru karein (Vanilla Cancel)
        ci.cancel();

        TextRenderer textRenderer = this.getTextRenderer();
        MinecraftClient mc = MinecraftClient.getInstance();

        // 3. Distance Check (Dur wale players ke naam na dikhein)
        double d = mc.getEntityRenderDispatcher().getSquaredDistanceToCamera(entity);
        if (d > 4096.0D) return;

        // 4. Name String Modify karein (Add Health if Player)
        String originalName = text.getString();
        String finalName = originalName;
        int healthColor = 0xFFFFFF; // Default White

        if (entity instanceof PlayerEntity player) {
            // Calculate Health
            float health = player.getHealth() + player.getAbsorptionAmount();
            int hp = (int) Math.ceil(health);
            
            // Health Text Color based on HP (Green > Yellow > Red)
            String hpColorCode = "§a"; // Green
            if (hp < 15) hpColorCode = "§e"; // Yellow
            if (hp < 10) hpColorCode = "§c"; // Red
            
            // Format: "Name <❤ 20>"
            finalName = originalName + " " + hpColorCode + "❤ " + hp;
        }

        float height = entity.getHeight() + 0.5F;
        
        matrices.push();
        matrices.translate(0.0F, height, 0.0F);
        
        // Rotate text to face camera
        matrices.multiply(mc.getEntityRenderDispatcher().getRotation());
        matrices.scale(-0.025F, -0.025F, 0.025F);

        Matrix4f matrix4f = matrices.peek().getPositionMatrix();
        float backgroundOpacity = 0.25F; // Lunar style transparent dark
        int backgroundColor = (int)(backgroundOpacity * 255.0F) << 24;

        float xOffset = (float)(-textRenderer.getWidth(finalName) / 2);

        // 5. Draw Lunar Style Background (Black Transparent Box)
        textRenderer.draw(
            finalName, 
            xOffset, 
            0, 
            0xFFFFFFFF, // Text Color
            false, // Shadow (False for manual handling below)
            matrix4f, 
            vertexConsumers, 
            TextRenderer.TextLayerType.SEE_THROUGH, 
            backgroundColor, // Background Box Color
            light
        );

        // 6. Draw Text on top
        textRenderer.draw(
            finalName, 
            xOffset, 
            0, 
            -1, // White text
            false, 
            matrix4f, 
            vertexConsumers, 
            TextRenderer.TextLayerType.NORMAL, 
            0, 
            light
        );

        matrices.pop();
    }
}
