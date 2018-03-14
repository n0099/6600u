package gregtech.loaders.oreprocessing;

import gregtech.api.enums.*;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import gregtech.common.GT_Proxy;
import net.minecraft.init.Enchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;

public class ProcessingArrows implements gregtech.api.interfaces.IOreRecipeRegistrator {
    public ProcessingArrows() {
        OrePrefixes.arrowGtWood.add(this);
        OrePrefixes.arrowGtPlastic.add(this);
    }

    public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
        ItemStack tOutput = GT_Utility.copyAmount(1L, aStack);
        GT_Utility.updateItemStack(tOutput);
        GT_Utility.ItemNBT.addEnchantment(tOutput, Enchantments.field_185303_l, EnchantmentHelper.func_77506_a(Enchantments.field_185303_l, tOutput) + 3);
        GT_Values.RA.addChemicalBathRecipe(GT_Utility.copyAmount(1L, aStack), Materials.HolyWater.getFluid(25L), tOutput, null, null, null, 100, 2);

        tOutput = GT_Utility.copyAmount(1L, aStack);
        GT_Utility.updateItemStack(tOutput);
        GT_Utility.ItemNBT.addEnchantment(tOutput, Enchantments.field_77334_n, EnchantmentHelper.func_77506_a(Enchantments.field_77334_n, tOutput) + 3);
        GT_Values.RA.addChemicalBathRecipe(GT_Utility.copyAmount(1L, aStack), Materials.FierySteel.getFluid(25L), tOutput, null, null, null, 100, 2);

        tOutput = GT_Utility.copyAmount(1L, aStack);
        GT_Utility.updateItemStack(tOutput);
        GT_Utility.ItemNBT.addEnchantment(tOutput, Enchantments.field_77334_n, EnchantmentHelper.func_77506_a(Enchantments.field_77334_n, tOutput) + 1);
        GT_Values.RA.addChemicalBathRecipe(GT_Utility.copyAmount(1L, aStack), Materials.Blaze.getMolten(18L), tOutput, null, null, null, 100, 2);

        tOutput = GT_Utility.copyAmount(1L, aStack);
        GT_Utility.updateItemStack(tOutput);
        GT_Utility.ItemNBT.addEnchantment(tOutput, Enchantments.field_180313_o, EnchantmentHelper.func_77506_a(Enchantments.field_180313_o, tOutput) + 1);
        GT_Values.RA.addChemicalBathRecipe(GT_Utility.copyAmount(1L, aStack), Materials.Rubber.getMolten(18L), tOutput, null, null, null, 100, 2);

        tOutput = GT_Utility.copyAmount(1L, aStack);
        GT_Utility.updateItemStack(tOutput);
        GT_Utility.ItemNBT.addEnchantment(tOutput, gregtech.api.enchants.Enchantment_EnderDamage.INSTANCE, EnchantmentHelper.func_77506_a(gregtech.api.enchants.Enchantment_EnderDamage.INSTANCE, tOutput) + 1);
        GT_Values.RA.addChemicalBathRecipe(GT_Utility.copyAmount(1L, aStack), Materials.Mercury.getFluid(25L), tOutput, null, null, null, 100, 2);

        if ((aMaterial.mUnificatable) && (aMaterial.mMaterialInto == aMaterial) && !aMaterial.contains(SubTag.NO_WORKING)) {
            switch (aPrefix) {
                case arrowGtWood:
                    GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.arrowGtWood, aMaterial, 1L), GT_Proxy.tBits, new Object[]{"  A", " S ", "F  ", Character.valueOf('S'), OrePrefixes.stick.get(Materials.Wood), Character.valueOf('F'), OreDictNames.craftingFeather, Character.valueOf('A'), OrePrefixes.toolHeadArrow.get(aMaterial)});
                case arrowGtPlastic:
                    GT_ModHandler.addCraftingRecipe(GT_OreDictUnificator.get(OrePrefixes.arrowGtPlastic, aMaterial, 1L), GT_Proxy.tBits, new Object[]{"  A", " S ", "F  ", Character.valueOf('S'), OrePrefixes.stick.get(Materials.Plastic), Character.valueOf('F'), OreDictNames.craftingFeather, Character.valueOf('A'), OrePrefixes.toolHeadArrow.get(aMaterial)});
            }
        }
    }
}
