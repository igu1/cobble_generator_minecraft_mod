package me.ez.cobblegen.Common.Items;

import me.ez.cobblegen.Init;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpeedModule extends Item{

    public SpeedModule() {
        super(new Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level p_41422_, List<Component> list, TooltipFlag p_41424_) {
        if (Screen.hasShiftDown()) {
            if(stack.is(Init.SPEED_MODULE_IRON_TIRE.get())) {
                list.add(new TextComponent("1 x Cobblestone per 8 second").withStyle(ChatFormatting.GREEN));
            }else if(stack.is(Init.SPEED_MODULE_GOLD_TIRE.get())) {
                list.add(new TextComponent("1 x Cobblestone per 4 second").withStyle(ChatFormatting.GOLD));
            }else if(stack.is(Init.SPEED_MODULE_DIAMOND_TIRE.get())) {
                list.add(new TextComponent("1 x Cobblestone per 1 second").withStyle(ChatFormatting.LIGHT_PURPLE));
            }
        }else {
            list.add(new TextComponent("Hold Shift For Information").withStyle(ChatFormatting.GRAY));
        }
    }
}
