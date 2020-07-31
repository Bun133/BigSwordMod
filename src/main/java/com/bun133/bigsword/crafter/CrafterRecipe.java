package com.bun133.bigsword.crafter;

import com.bun133.bigsword.ModMain;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemStackHandler;

import java.util.*;

@SuppressWarnings("SpellCheckingInspection")
public class CrafterRecipe {
    public static Map<ItemStack[], Item> recipeList = new HashMap<>();

    static {
        recipeList.put(recipes.big_diamond_sword(),ModMain.BIG_DIAMOND_SWORD);
        recipeList.put(recipes.black_sword_recipe(),ModMain.BIG_BLACK_SWORD);
        recipeList.put(recipes.multi_tool_recipe(),ModMain.MULTI_TOOL);
    }

    public static ItemStack[] getRecipe(ItemStack stack){
        for(Map.Entry<ItemStack[],Item> entry:recipeList.entrySet()){
            if (entry.getValue().equals(stack.getItem())){
//                ModMain.Logger.info("Recipe Found!");
                return entry.getKey();
            }
        }
//        ModMain.Logger.info("Recipe Not Found!");
        return recipes.base();
    }

    public static ItemStack getRecipe(ItemStackHandler inventory) {
        List<ItemStack> inventory_list =  forceMapping(inventory,49);
        for(Map.Entry<ItemStack[],Item> entry:recipeList.entrySet()){
            if(isEqual(entry.getKey(),inventory_list)){
//                ModMain.Logger.info("Recipe Found!");
                return new ItemStack(entry.getValue());
            }
        }
//        ModMain.Logger.info("Recipe Not Found!");
        return ItemStack.EMPTY;
    }

    private static List<ItemStack> forceMapping(ItemStackHandler inventory, int i) {
        List<ItemStack> returnable=new ArrayList<>();
        for(int c=0;c<i;c++){
            returnable.add(inventory.getStackInSlot(c));
        }
        return returnable;
    }

    private static List<ItemStack> toList(ItemStackHandler in) {
        List<ItemStack> returnable = new ArrayList<>();
        for (int i = 0; i < in.getSlots(); i++) {
            returnable.add(in.getStackInSlot(i));
        }
        return returnable;
    }

    private static boolean isEqual(List<ItemStack> one, List<ItemStack> two) {
        if (one.size() != two.size()) return false;
        for (int i = 0; i < one.size(); i++) {
            if (!one.get(i).getItem().equals(two.get(i).getItem())) {
                return false;
            }
        }
        return true;
    }


    private static boolean isEqual(ItemStack[] one, List<ItemStack> two) {
        if (one.length != two.size()) return false;
        for (int i = 0; i < one.length; i++) {
            if (!one[i].getItem().equals(two.get(i).getItem())) {
                return false;
            }
        }
        return true;
    }


    private static class recipes{
        private static ItemStack[] test_sword(){
            ItemStack[] returnable=base();
            returnable[3]=new ItemStack(Items.DIAMOND);
            returnable[10]=new ItemStack(Items.DIAMOND);
            returnable[17]=new ItemStack(Items.DIAMOND);
            returnable[24]=new ItemStack(Items.DIAMOND);
            returnable[31]=new ItemStack(Items.DIAMOND);
            returnable[38]=new ItemStack(Items.STICK);
            returnable[45]=new ItemStack(Items.STICK);
            return returnable;
        }

        private static ItemStack[] base(){
            ItemStack[] returnable = new ItemStack[49];
            Arrays.fill(returnable, ItemStack.EMPTY);
            return returnable;
        }

        public static ItemStack[] big_diamond_sword() {
            ItemStack[] returnable=base();
            returnable[2]=new ItemStack(Items.DIAMOND);
            returnable[3]=new ItemStack(Items.DIAMOND);
            returnable[4]=new ItemStack(Items.DIAMOND);
            returnable[9]=new ItemStack(Items.DIAMOND);
            returnable[10]=new ItemStack(Items.DIAMOND);
            returnable[11]=new ItemStack(Items.DIAMOND);
            returnable[16]=new ItemStack(Items.DIAMOND);
            returnable[17]=new ItemStack(Items.DIAMOND);
            returnable[18]=new ItemStack(Items.DIAMOND);
            returnable[23]=new ItemStack(Items.DIAMOND);
            returnable[24]=new ItemStack(Items.DIAMOND);
            returnable[25]=new ItemStack(Items.DIAMOND);
            returnable[30]=new ItemStack(Items.DIAMOND);
            returnable[31]=new ItemStack(Items.DIAMOND);
            returnable[32]=new ItemStack(Items.DIAMOND);
            returnable[37]=new ItemStack(Items.STICK);
            returnable[38]=new ItemStack(Items.STICK);
            returnable[39]=new ItemStack(Items.STICK);
            returnable[44]=new ItemStack(Items.STICK);
            returnable[45]=new ItemStack(Items.STICK);
            returnable[46]=new ItemStack(Items.STICK);
            return returnable;
        }

        public static ItemStack[] black_sword_recipe(){
            ItemStack[] returnable=base();
            returnable[3]=new ItemStack(Blocks.OBSIDIAN);
            returnable[9]=new ItemStack(Blocks.OBSIDIAN);
            returnable[10]=new ItemStack(Blocks.OBSIDIAN);
            returnable[11]=new ItemStack(Blocks.OBSIDIAN);
            returnable[16]=new ItemStack(Blocks.OBSIDIAN);
            returnable[17]=new ItemStack(Blocks.OBSIDIAN);
            returnable[18]=new ItemStack(Blocks.OBSIDIAN);
            returnable[23]=new ItemStack(Blocks.OBSIDIAN);
            returnable[24]=new ItemStack(Blocks.OBSIDIAN);
            returnable[25]=new ItemStack(Blocks.OBSIDIAN);
            returnable[30]=new ItemStack(Blocks.OBSIDIAN);
            returnable[31]=new ItemStack(Blocks.OBSIDIAN);
            returnable[32]=new ItemStack(Blocks.OBSIDIAN);
            returnable[38]=new ItemStack(Items.STICK);
            returnable[45]=new ItemStack(Items.STICK);
            return returnable;
        }

        public static ItemStack[] multi_tool_recipe(){
            ItemStack[] returnable=base();
            returnable[24]=new ItemStack(ModMain.BIG_DIAMOND_SWORD);
            returnable[23]=new ItemStack(Items.GOLDEN_AXE);
            returnable[25]=new ItemStack(Items.GOLDEN_SHOVEL);
            returnable[22]=new ItemStack(Items.IRON_HOE);
            returnable[26]=new ItemStack(Items.IRON_PICKAXE);
            return returnable;
        }
    }
}
