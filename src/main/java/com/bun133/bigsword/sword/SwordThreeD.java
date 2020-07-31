package com.bun133.bigsword.sword;

import com.bun133.bigsword.ModMain;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

/**
 * 名前には突っ込むな
 */
public class SwordThreeD extends SwordBase {
    public SwordThreeD(ToolMaterial material, String name, CreativeTabs tabs) {
        super(material, name,tabs);
    }

    @Override
    public void RegisterModel() {
        ModMain.INSTANCE.registerItem(this,name);
    }
}
