package com.bun133.bigsword;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ModTab extends CreativeTabs {
    public ModTab(String bigSword) {
        super(bigSword);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModMain.BIG_DIAMOND_SWORD);
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(ModMain.BIG_DIAMOND_SWORD);
    }
}
