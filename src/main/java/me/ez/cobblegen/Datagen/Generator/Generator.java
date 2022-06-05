package me.ez.cobblegen.Datagen.Generator;

import me.ez.cobblegen.Datagen.BlockStateProvider;
import me.ez.cobblegen.Main;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Generator {
    @SubscribeEvent
    public static void onGatheringData(GatherDataEvent e){
        e.getGenerator().addProvider(new BlockStateProvider(e.getGenerator(), Main.MOD_ID, e.getExistingFileHelper()));
    }
}
