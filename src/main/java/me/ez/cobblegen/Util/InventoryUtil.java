package me.ez.cobblegen.Util;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class InventoryUtil {

    public static void addItemToInventory(IItemHandler h, Item item, int amount) {
        int slots = h.getSlots();
        for (int x = 0; x < slots; x++){
            if (h.getStackInSlot(x).is(item) || h.getStackInSlot(x).isEmpty()) {
                if (h.getStackInSlot(x).getCount() < 64) {
                    h.insertItem(x, new ItemStack(item, amount), false);
                    break;
                }
            }
        }
    }

    public static void addItemToPlayerInventory(Inventory inventory, Item item, int amount) {
        int slots = inventory.getContainerSize();
        for (int x = 0; x < slots; x++){
            if (inventory.getItem(x).is(item) || inventory.getItem(x).isEmpty()) {
                if (inventory.getItem(x).getCount() < 64) {
                    inventory.add(x, new ItemStack(item, amount));
                    break;
                }
            }
        }
    }

    public static boolean hasSpace(IItemHandler h, Item item) {
        int slots = h.getSlots();
        for (int x = 0; x < slots; x++) {
            if (h.getStackInSlot(x).is(item) || h.getStackInSlot(x).isEmpty()) {
                if (h.getStackInSlot(x).getCount() < 64) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkIsItemInInventory(IItemHandler h, Item item){
        boolean found = false;
        int numberOfSlots = h.getSlots();
        for (int x = 0; x < numberOfSlots; x++) {
            if (h.getStackInSlot(x).is(item)){
                found = true;
                break;
            }
        }
        return found;
    }

    public static void removeItemFromInventory(IItemHandler h, Item item, int amount){
        int numberOfSlots = h.getSlots();
        for (int x = 0; x < numberOfSlots; x++) {
            if (h.getStackInSlot(x).is(item)){
                h.getStackInSlot(x).shrink(amount);
                break;
            }
        }
    }
}
