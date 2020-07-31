package com.bun133.bigsword.crafter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class ContainerCrafter extends Container {
    private final TileEntityCrafter tileEntity;

    private final int guiXSize = 199;
    public final int guiXCentre = guiXSize / 2;
    private final int guiYSize = 222;
    public final int guiYCentre = guiYSize / 2;
    private final int xSize = 176;
    public final double zoom_x = guiXSize / xSize;
    private final int ySize = 166;
    public final double zoom_y = guiYSize / ySize;
    public ContainerCrafter(InventoryPlayer player, TileEntityCrafter tileEntity) {
        this.tileEntity = tileEntity;
        IItemHandler handler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                this.addSlotToContainer(new SlotItemHandler(handler, x + y * 7, convertPos_x(6 + x * 18), convertPos_y(8 + y * 18)));
            }
        }

        this.addSlotToContainer(new SlotItemHandler(handler,49,convertPos_x(170),convertPos_y(63)){
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return false;
            }
        });

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                this.addSlotToContainer(new Slot(player, x + y * 9 + 9, convertPos_x(6 + x * 18), convertPos_y(143 + y * 18)));
            }
        }

        for (int x = 0; x < 9; x++) {
            this.addSlotToContainer(new Slot(player, x, convertPos_x(6 + x * 18), convertPos_y(201)));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.tileEntity.isUsableByPlayer(playerIn);
    }

    public final int shift_x=11;
    public final int shift_y=28;
    public int convertPos_x(int before_x) {
        return before_x-shift_x;
//        return (int) (guiXCentre + (before_x - guiXCentre) * zoom_x);
//        return before_x/**xSize/guiXSize*/;
    }

    public int convertPos_y(int before_y) {
        return before_y-shift_y;
//        return (int) (guiYCentre + (before_y - guiYCentre) * zoom_y);
//        return before_y/**ySize/guiYSize*/;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        return ItemStack.EMPTY;
    }
}
