package me.ez.cobblegen;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Main.MOD_ID)
public class Main
{
    public static final String MOD_ID = "cobblegen";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Main() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        Init.ITEMS.register(bus);
        Init.BLOCKS.register(bus);
        Init.BLOCKENTITY_TYPE.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
    }

}
