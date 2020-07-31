package com.bun133.bigsword.tool;

import com.bun133.bigsword.IHasModel;
import com.bun133.bigsword.ModMain;
import com.bun133.bigsword.Register;
import net.minecraft.creativetab.CreativeTabs;

public class TestMultipleItem extends MultipleItemBase implements IHasModel {
    String name;
    public TestMultipleItem(String name, CreativeTabs tabs, float attackDamageIn, float attackSpeedIn, ToolMaterial materialIn) {
        super(attackDamageIn, attackSpeedIn, materialIn);
        this.name=name;
        this.setTranslationKey(name);
        this.setRegistryName(name);
        this.setCreativeTab(tabs);
        Register.itemList.add(this);
    }

    public TestMultipleItem(String name, CreativeTabs tabs, ToolMaterial materialIn) {
        super(materialIn);
        this.name=name;
        this.setTranslationKey(name);
        this.setRegistryName(name);
        this.setCreativeTab(tabs);
        Register.itemList.add(this);
    }

    @Override
    public void RegisterModel() {
        ModMain.INSTANCE.registerItem(this,name);
    }
}
