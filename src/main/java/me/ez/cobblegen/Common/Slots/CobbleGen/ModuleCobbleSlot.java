package me.ez.cobblegen.Common.Slots.CobbleGen;

import me.ez.cobblegen.Common.Items.SpeedModule;
import me.ez.cobblegen.Init;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class ModuleCobbleSlot extends SlotItemHandler {

    public ModuleCobbleSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return stack.getItem() instanceof SpeedModule;
//        return stack.is(Init.SPEED_MODULE_IRON_TIRE.get()) || stack.is(Init.SPEED_MODULE_GOLD_TIRE.get()) || stack.is(Init.SPEED_MODULE_DIAMOND_TIRE.get());
    }
}
