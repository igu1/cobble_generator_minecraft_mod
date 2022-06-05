package me.ez.cobblegen;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Main.MOD_ID)
public class Main
{
    public static final String MOD_ID = "cobblegen";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Main() {
        Init.BLOCK_DEFERRED_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        Init.ITEM_DEFERRED_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        Init.BLOCK_ENTITY_DEFERRED_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        Init.CONTAINER_DEFERRED_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
        MinecraftForge.EVENT_BUS.register(this);
    }

}
