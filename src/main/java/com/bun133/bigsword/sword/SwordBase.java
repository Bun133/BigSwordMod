package com.bun133.bigsword.sword;

import com.bun133.bigsword.IHasModel;
import com.bun133.bigsword.ModMain;
import com.bun133.bigsword.Register;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class SwordBase extends ItemSword implements IHasModel {
    public SwordBase(ToolMaterial material, String name, CreativeTabs tabs) {
        super(material);
        this.name=name;
        this.setTranslationKey(name);
        this.setRegistryName(name);
        this.setCreativeTab(tabs);
        Register.SwordList.add(this);
    }
    protected String name;

    @Override
    public void RegisterModel() {
        ModelLoader.setCustomModelResourceLocation(this,0,new ModelResourceLocation(new ResourceLocation(ModMain.MOD_NAME,name),"inventory"));
    }
}
