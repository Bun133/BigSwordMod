package com.bun133.bigsword.crafter;

import com.bun133.bigsword.ModMain;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class TileEntityCrafter extends TileEntity implements ITickable {
    public ItemStackHandler handler = new ItemStackHandler(50);//8*8+1
    private boolean isSetted = false;
    private ItemStack current_item = ItemStack.EMPTY;

    public TileEntityCrafter() {
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return true;
        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) this.handler;
        return super.getCapability(capability, facing);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.handler.deserializeNBT(compound);
        this.isSetted = compound.getBoolean("isSetted");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("Inventory", this.handler.serializeNBT());
        compound.setBoolean("isSetted", this.isSetted);
        return super.writeToNBT(compound);
    }

    @SuppressWarnings({"NoTranslation", "InjectedReferences"})
    @Nullable
    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentTranslation("container.crafting");
    }

    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double) this.pos.getX(), (double) this.pos.getY(), (double) this.pos.getZ()) <= 64;
    }

    @Override
    public void update() {
//        ModMain.Logger.info("Update");
        if (isSetted) {
            if (this.handler.getStackInSlot(49).isEmpty()) {
                deqItems(CrafterRecipe.getRecipe(current_item));
                isSetted=false;
            }
        }

        if (!isEmpty(CrafterRecipe.getRecipe(this.handler))) {
            this.handler.setStackInSlot(49, CrafterRecipe.getRecipe(this.handler));
            this.current_item = CrafterRecipe.getRecipe(this.handler);
            this.isSetted=true;
        }
    }

    private boolean isEmpty(ItemStack recipe) {
        return recipe.equals(ItemStack.EMPTY);
    }


    private void deqItems(ItemStack[] stacks) {
        if (stacks.length != this.handler.getSlots() - 1) {
            ModMain.Logger.error("NotMatch!!!");
            return;
        }
        for (int i = 0; i < stacks.length; i++) {
            if (stacks[i].isEmpty() || this.handler.getStackInSlot(i).isEmpty()) {

            } else {
                this.handler.getStackInSlot(i).grow(-stacks[i].getCount());
            }
        }
    }

    public void OnGuiClose(EntityPlayer entityPlayer) {
        ModMain.Logger.info("Closed!");
//        this.dropItems(entityPlayer);
    }

    private void dropItems(EntityPlayer player) {
        List<ItemStack> dropable=new ArrayList<>();
        for(int i=0;i<this.handler.getSlots();i++){
            if(i!=49){
                dropable.add(this.handler.getStackInSlot(i));
                this.handler.setStackInSlot(i,ItemStack.EMPTY);
            }
        }

        for(ItemStack stack:dropable){
            player.inventory.addItemStackToInventory(stack);
        }
    }
}
