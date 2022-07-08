package me.ez.cobblegen.Datagen;

import me.ez.cobblegen.Main;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {
    @SubscribeEvent
    public static void onGatheringData(GatherDataEvent e){
        e.getGenerator().addProvider(true, new ModRecipeProvider(e.getGenerator()));
    }
}
