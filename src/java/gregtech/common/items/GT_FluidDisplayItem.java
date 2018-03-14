package gregtech.common.items;

import gregtech.api.GregTech_API;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.items.GT_Generic_Item;
import gregtech.api.util.GT_Utility;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class GT_FluidDisplayItem extends GT_Generic_Item {
    public GT_FluidDisplayItem() {
        super("GregTech_FluidDisplay", "Fluid Display", null);
        ItemList.Display_Fluid.set(this);
        func_77637_a(GregTech_API.TAB_GREGTECH_MATERIALS);
    }

    @Override
    protected void addAdditionalToolTips(List<String> aList, ItemStack aStack, EntityPlayer aPlayer) {
        NBTTagCompound aNBT = aStack.func_77978_p();
        if (GT_Values.D1) {
            Fluid tFluid = FluidRegistry.getFluid(aStack.func_77952_i());
            if (tFluid != null) {
                aList.add("Registry: " + tFluid.getName());
            }
        }
        if (aNBT != null) {
            long tToolTipAmount = aNBT.func_74763_f("mFluidDisplayAmount");
            if (tToolTipAmount > 0L) {
                aList.add(TextFormatting.BLUE + "Amount: " + tToolTipAmount + TextFormatting.GRAY);
            }
            aList.add(TextFormatting.RED + "Temperature: " + aNBT.func_74763_f("mFluidDisplayHeat") + " K" + TextFormatting.GRAY);
            aList.add(TextFormatting.GREEN + "State: " + (aNBT.func_74767_n("mFluidState") ? "Gas" : "Liquid") + TextFormatting.GRAY);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getIcon(ItemStack stack, int pass) {
        Fluid tFluid = FluidRegistry.getFluid(stack.func_77952_i());
        return GT_Utility.getTexture(tFluid.getStill());
    }

    @Override
    public void registerIcons(TextureMap aIconRegister) {}

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int tintIndex) {
        Fluid tFluid = FluidRegistry.getFluid(stack.func_77952_i());
        return tFluid == null ? 16777215 : tFluid.getColor();
    }

    @Override
    public String func_77667_c(ItemStack aStack) {
        if (aStack != null) {
            return GT_Utility.getFluidName(FluidRegistry.getFluid(aStack.func_77952_i()), false);
        }
        return "";
    }

    @Override
    public String func_77653_i(ItemStack aStack) {
        if (aStack != null) {
            return GT_Utility.getFluidName(FluidRegistry.getFluid(aStack.func_77952_i()), true);
        }
        return "";
    }

    @SideOnly(Side.CLIENT)
    public void func_150895_a(Item aItem, CreativeTabs aTab, List<ItemStack> aList) {
        if (GT_Values.D1) {
            int i = 0;
            for (int j = FluidRegistry.getMaxID(); i < j; i++) {
                ItemStack tStack = GT_Utility.getFluidDisplayStack(FluidRegistry.getFluid(i));
                if (tStack != null) {
                    aList.add(tStack);
                }
            }
        }

    }
    
}
