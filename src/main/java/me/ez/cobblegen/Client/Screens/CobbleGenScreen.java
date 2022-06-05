package me.ez.cobblegen.Client.Screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import me.ez.cobblegen.Common.Container.CobbleGenContainer;
import me.ez.cobblegen.Main;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CobbleGenScreen extends AbstractContainerScreen<CobbleGenContainer> {

    private static final ResourceLocation CONTAINER_TEXTURE = new ResourceLocation(Main.MOD_ID, "textures/gui/cobblegen.png");

    public CobbleGenScreen(CobbleGenContainer genContainer, Inventory inventory, Component component) {
        super(genContainer, inventory, component);
    }

    @Override
    public void render(PoseStack poseStack, int i, int p_99251_, float p_99252_) {
        this.renderBackground(poseStack);
        super.render(poseStack, i, p_99251_, p_99252_);
        this.renderTooltip(poseStack, i, p_99251_);
    }
    @Override
    protected void renderBg(PoseStack poseStack, float p_99245_, int p_99246_, int p_99247_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, CONTAINER_TEXTURE);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        this.blit(poseStack, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }
}
