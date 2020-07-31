package com.bun133.bigsword;

import com.bun133.bigsword.crafter.BlockCrafter;
import com.bun133.bigsword.sword.SwordBase;
import com.bun133.bigsword.sword.SwordThreeD;
import com.bun133.bigsword.tool.TestMultipleItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = ModMain.MOD_ID,name = ModMain.MOD_NAME,version = ModMain.MOD_VERSION)
public class ModMain {
    public static final String MOD_ID="bigsword";
    public static final String MOD_NAME="BigSword";
    public static final String MOD_VERSION="1.2.a";
    public static final int CRAFTER_GUI = 11;

    @Mod.Instance(MOD_ID)
    public static ModMain INSTANCE;
    /**
     * Creative Tab
     */
    public static final CreativeTabs CREATIVE_TAB=new ModTab("BigSword");

    /*static {
        new Items();
        new Blocks();
        new materials();
    }*/
    /**
     * Materials
     */
        public static final Item.ToolMaterial testMaterial = Item.ToolMaterial.DIAMOND;
        public static Item.ToolMaterial big_diamond_material =  EnumHelper.addToolMaterial("big_diamond_material",4,1000,12.0F,15.0F,20);
        public static final Item.ToolMaterial multi_tool_material = EnumHelper.addToolMaterial("multi_tool_material",4,10000,100.0F,30.0F,200);

    /**Items*/

        //public static final ItemSword TestSword=new SwordBase(testMaterial,"test_sword",CREATIVE_TAB);
        public static final ItemSword BIG_DIAMOND_SWORD = new SwordThreeD(big_diamond_material,"big_diamond_sword",CREATIVE_TAB);
        public static final ItemSword BIG_BLACK_SWORD = new SwordThreeD(big_diamond_material,"big_black_sword",CREATIVE_TAB){
            @Override
            public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
                ItemStack itemStack=playerIn.getHeldItem(handIn);
                switch (handIn){
                    case MAIN_HAND:
                        playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem,new ItemStack(BIG_BLACK_SWORD_LIT,itemStack.getCount(),itemStack.getMetadata()));
                        break;
                    case OFF_HAND:
                        playerIn.inventory.offHandInventory.set(0,new ItemStack(BIG_BLACK_SWORD_LIT,itemStack.getCount(),itemStack.getMetadata()));
                        break;
                }
                Logger.info("Changed!");
                return super.onItemRightClick(worldIn, playerIn, handIn);
            }
        };

        public static final ItemSword BIG_BLACK_SWORD_LIT = new SwordThreeD(big_diamond_material,"big_black_sword_lit",CREATIVE_TAB){
            @Override
            public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
                ItemStack itemStack=playerIn.getHeldItem(handIn);
                switch (handIn){
                    case MAIN_HAND:
                        playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem,new ItemStack(BIG_BLACK_SWORD,itemStack.getCount(),itemStack.getMetadata()));
                        break;
                    case OFF_HAND:
                        playerIn.inventory.offHandInventory.set(0,new ItemStack(BIG_BLACK_SWORD,itemStack.getCount(),itemStack.getMetadata()));
                        break;
                }
                Logger.info("Changed!");
                return super.onItemRightClick(worldIn, playerIn, handIn);
            }
        };

//        public static final ItemTool TEST_MULTIPLE_TOOL = new TestMultipleItem("test_multiple_item",CREATIVE_TAB,big_diamond_material);
        public static final ItemTool MULTI_TOOL = new TestMultipleItem("multi_tool",CREATIVE_TAB,30.0F,1.0F,multi_tool_material);
//        public static final ItemTool MULTI_TOOL_NON_Fliped = new TestMultipleItem("multi_tool_non_fliped",CREATIVE_TAB,big_diamond_material);
    /**
     * Blocks
     */

        public static final Block CRAFTING = new BlockCrafter("crafting", Material.WOOD, CREATIVE_TAB,1.0f);


    public static org.apache.logging.log4j.Logger Logger;


    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event){
        Logger=event.getModLog();
        if(event.getSide().isClient()) {
            OBJLoader.INSTANCE.addDomain(MOD_ID);
        }
//        ModelLoader.setCustomModelResourceLocation();
    }

    public void registerItem(Item item,String name){
        ModelLoader.setCustomModelResourceLocation(item,0,new ModelResourceLocation(new ResourceLocation(MOD_ID,name),"inventory"));
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        NetworkRegistry.INSTANCE.registerGuiHandler(ModMain.INSTANCE,new GUIHandler());
    }


}
