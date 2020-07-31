package com.bun133.bigsword.tool;

import com.bun133.bigsword.Register;
import com.google.common.collect.Multimap;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class MultipleItemBase extends ItemTool {
    protected MultipleItemBase(float attackDamageIn, float attackSpeedIn, ToolMaterial materialIn) {
        super(attackDamageIn, attackSpeedIn, materialIn, null);
        init();
        attackDamage=toolMaterial.getAttackDamage();
    }

    protected MultipleItemBase(ToolMaterial materialIn) {
        super(materialIn, null);
        init();
        attackDamage=toolMaterial.getAttackDamage();
    }

    private void init() {
        for (String toolClass : getToolClasses(null)) {
            setHarvestLevel(toolClass, toolMaterial.getHarvestLevel() + 1);
        }
        Register.ToolList.add(this);
    }

    private final float attackDamage;


    @NotNull
    @Override
    public Set<String> getToolClasses(@NotNull ItemStack stack) {
        Set<String> set = new HashSet<>();
        set.add("pickaxe");
        set.add("shovel");
        set.add("axe");
        set.add("hoe");
        return set;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        if (stack != null && isToolEffective(stack, state.getBlock(), state)) {
            return efficiency;
        }
        return 0f;
    }

    private boolean isToolEffective(ItemStack stack, Block block, IBlockState state) {
        return true;
    }

    @Override
    public boolean canHarvestBlock(IBlockState blockIn) {
        return true;
    }


    /**
     * Below is SwordSection
     */

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean isFull3D() {
        return true;
    }

    @Override
    public int getItemEnchantability() {
        return toolMaterial.getEnchantability();
    }

    @Override
    public String getToolMaterialName() {
        return toolMaterial.name();
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        ItemStack mat = toolMaterial.getRepairItemStack();
        if (!mat.isEmpty() && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, repair, false)) return true;
        return super.getIsRepairable(toRepair, repair);
    }

    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.attackDamage, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));
        }

        return multimap;
    }
}
