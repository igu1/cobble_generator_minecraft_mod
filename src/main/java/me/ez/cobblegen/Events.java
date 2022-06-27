package me.ez.cobblegen;

import me.ez.cobblegen.Client.Screens.CobbleGenScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Events {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        MenuScreens.register(Init.COBBLE_GEN_CONTAINER_REGISTRY_OBJECT.get(), CobbleGenScreen::new);
        ItemBlockRenderTypes.setRenderLayer(Init.COBBLE_GEN_BLOCK_REGISTRY_OBJECT.get(), RenderType.translucent());
    }
}
