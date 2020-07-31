package com.bun133.bigsword;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class Register {
    public static List<Item> itemList=new ArrayList<>();
    public static List<ItemSword> SwordList=new ArrayList<>();
    public static List<ItemTool> ToolList=new ArrayList<>();
    @SubscribeEvent
    public static void onItemInit(RegistryEvent.Register<Item> event){
        ModMain.Logger.info("onIteminit");
        List<Item> items=new ArrayList<>();
        items.addAll(SwordList);
        items.addAll(itemList);
        items.addAll(ToolList);

        event.getRegistry().registerAll(items.toArray(new Item[0]));

    }

    public static List<Block> blockList=new ArrayList<>();

    @SubscribeEvent
    public static void onBlockInit(RegistryEvent.Register<Block> event){
        ModMain.Logger.info("onBlockInit");
        event.getRegistry().registerAll(blockList.toArray(new Block[0]));

        TileEntityHandler.registerTileEntity();
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event){
        ModMain.Logger.info("onModelRegister");
        List<Item> items=new ArrayList<>();
        items.addAll(SwordList);
        items.addAll(itemList);

        for(Item item:items){
            if(item instanceof IHasModel){
                ((IHasModel) item).RegisterModel();
            }
        }

        for(Block block:blockList){
            if(block instanceof IHasModel){
                ((IHasModel)block).RegisterModel();
            }
        }
    }
}
