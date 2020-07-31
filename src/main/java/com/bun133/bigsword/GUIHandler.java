package com.bun133.bigsword;

import com.bun133.bigsword.crafter.ContainerCrafter;
import com.bun133.bigsword.crafter.GuiCrafter;
import com.bun133.bigsword.crafter.TileEntityCrafter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;
import java.util.Objects;

public class GUIHandler implements IGuiHandler {
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID==ModMain.CRAFTER_GUI) return new ContainerCrafter(player.inventory,(TileEntityCrafter) world.getTileEntity(new BlockPos(x, y, z)));
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID==ModMain.CRAFTER_GUI) return new GuiCrafter(player,(TileEntityCrafter) world.getTileEntity(new BlockPos(x,y,z)));
        return null;
    }
}
