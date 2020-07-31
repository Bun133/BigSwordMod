package com.bun133.bigsword;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class BlockBase extends Block implements IHasModel{
    String name;
    public BlockBase(String name, Material material, CreativeTabs tabs, float blockHardness) {
        super(material);
        setTranslationKey(name);
        setRegistryName(ModMain.MOD_ID,name);
        setCreativeTab(tabs);
        setHardness(blockHardness);
        setResistance(1.0F);
        this.name=name;

        Register.blockList.add(this);
        Register.itemList.add(new ItemBlock(this).setRegistryName(ModMain.MOD_ID,name));
    }

    @Override
    public void RegisterModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(new ResourceLocation(ModMain.MOD_NAME,name) ,"inventory"));
    }
}
