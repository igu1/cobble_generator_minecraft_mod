package me.ez.cobblegen;

import me.ez.cobblegen.Client.Screens.CobbleGenScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Events {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        MenuScreens.register(Init.COBBLE_GEN_CONTAINER_REGISTRY_OBJECT.get(), CobbleGenScreen::new);
    }
}
