package com.bun133.bigsword;

import com.bun133.bigsword.crafter.TileEntityCrafter;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
    public static void registerTileEntity(){
        GameRegistry.registerTileEntity(TileEntityCrafter.class,new ResourceLocation(ModMain.MOD_ID,"crafting"));
    }
}
