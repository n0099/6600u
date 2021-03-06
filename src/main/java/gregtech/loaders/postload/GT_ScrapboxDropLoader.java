package gregtech.loaders.postload;

import gregtech.GT_Mod;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_Log;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import ic2.core.item.type.CraftingItemType;
import ic2.core.ref.ItemName;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GT_ScrapboxDropLoader
        implements Runnable {
    public void run() {
        GT_Log.out.println("GT_Mod: (re-)adding Scrapbox Drops.");

        GT_ModHandler.addScrapboxDrop(9.5F, new ItemStack(Items.WOODEN_HOE));
        GT_ModHandler.addScrapboxDrop(2.0F, new ItemStack(Items.SIGN));
        GT_ModHandler.addScrapboxDrop(9.5F, new ItemStack(Items.STICK));
        GT_ModHandler.addScrapboxDrop(5.0F, new ItemStack(Blocks.DIRT));
        GT_ModHandler.addScrapboxDrop(3.0F, new ItemStack(Blocks.GRASS));
        GT_ModHandler.addScrapboxDrop(3.0F, new ItemStack(Blocks.GRAVEL));
        GT_ModHandler.addScrapboxDrop(0.5F, new ItemStack(Blocks.PUMPKIN));
        GT_ModHandler.addScrapboxDrop(1.0F, new ItemStack(Blocks.SOUL_SAND));
        GT_ModHandler.addScrapboxDrop(2.0F, new ItemStack(Blocks.NETHERRACK));
        GT_ModHandler.addScrapboxDrop(1.0F, new ItemStack(Items.BONE));
        GT_ModHandler.addScrapboxDrop(9.0F, new ItemStack(Items.ROTTEN_FLESH));
        GT_ModHandler.addScrapboxDrop(0.4F, new ItemStack(Items.COOKED_PORKCHOP));
        GT_ModHandler.addScrapboxDrop(0.4F, new ItemStack(Items.COOKED_BEEF));
        GT_ModHandler.addScrapboxDrop(0.4F, new ItemStack(Items.COOKED_CHICKEN));
        GT_ModHandler.addScrapboxDrop(0.5F, new ItemStack(Items.APPLE));
        GT_ModHandler.addScrapboxDrop(0.5F, new ItemStack(Items.BREAD));
        GT_ModHandler.addScrapboxDrop(0.1F, new ItemStack(Items.CAKE));
        GT_ModHandler.addScrapboxDrop(1.0F, ItemList.IC2_Food_Can_Filled.get(1));
        GT_ModHandler.addScrapboxDrop(2.0F, ItemList.IC2_Food_Can_Spoiled.get(1));
        GT_ModHandler.addScrapboxDrop(0.2F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Silicon, 1));
        GT_ModHandler.addScrapboxDrop(1.0F, GT_OreDictUnificator.get(OrePrefixes.cell, Materials.Water, 1));
        GT_ModHandler.addScrapboxDrop(2.0F, ItemList.Cell_Empty.get(1));
        GT_ModHandler.addScrapboxDrop(5.0F, GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Paper, 1));
        GT_ModHandler.addScrapboxDrop(1.0F, new ItemStack(Items.LEATHER));
        GT_ModHandler.addScrapboxDrop(1.0F, new ItemStack(Items.FEATHER));
        GT_ModHandler.addScrapboxDrop(0.7F, GT_ModHandler.getIC2Item(ItemName.crafting, CraftingItemType.plant_ball, 1));
        GT_ModHandler.addScrapboxDrop(3.8F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Wood, 1));
        GT_ModHandler.addScrapboxDrop(0.6F, new ItemStack(Items.SLIME_BALL));
        GT_ModHandler.addScrapboxDrop(0.8F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Rubber, 1));
        GT_ModHandler.addScrapboxDrop(2.7F, GT_ModHandler.getIC2Item(ItemName.single_use_battery, 1));
        GT_ModHandler.addScrapboxDrop(3.6F, ItemList.Circuit_Primitive.get(1));
        GT_ModHandler.addScrapboxDrop(0.8F, ItemList.Circuit_Parts_Advanced.get(1));
        GT_ModHandler.addScrapboxDrop(1.8F, ItemList.Circuit_Board_Basic.get(1));
        GT_ModHandler.addScrapboxDrop(0.4F, ItemList.Circuit_Board_Advanced.get(1));
        GT_ModHandler.addScrapboxDrop(0.2F, ItemList.Circuit_Board_Elite.get(1));
        GT_ModHandler.addScrapboxDrop(0.9F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Redstone, 1));
        GT_ModHandler.addScrapboxDrop(0.8F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Glowstone, 1));
        GT_ModHandler.addScrapboxDrop(0.8F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Coal, 1));
        GT_ModHandler.addScrapboxDrop(2.5F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Charcoal, 1));
        GT_ModHandler.addScrapboxDrop(1.0F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Iron, 1));
        GT_ModHandler.addScrapboxDrop(1.0F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Gold, 1));
        GT_ModHandler.addScrapboxDrop(0.5F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Silver, 1));
        GT_ModHandler.addScrapboxDrop(0.5F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Electrum, 1));
        GT_ModHandler.addScrapboxDrop(1.2F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Tin, 1));
        GT_ModHandler.addScrapboxDrop(1.2F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Copper, 1));
        GT_ModHandler.addScrapboxDrop(0.5F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Bauxite, 1));
        GT_ModHandler.addScrapboxDrop(0.5F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Aluminium, 1));
        GT_ModHandler.addScrapboxDrop(0.5F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Lead, 1));
        GT_ModHandler.addScrapboxDrop(0.5F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Nickel, 1));
        GT_ModHandler.addScrapboxDrop(0.5F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Zinc, 1));
        GT_ModHandler.addScrapboxDrop(0.5F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Brass, 1));
        GT_ModHandler.addScrapboxDrop(0.5F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Steel, 1));
        GT_ModHandler.addScrapboxDrop(1.5F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Obsidian, 1));
        GT_ModHandler.addScrapboxDrop(1.5F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sulfur, 1));
        GT_ModHandler.addScrapboxDrop(2.0F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Saltpeter, 1));
        GT_ModHandler.addScrapboxDrop(2.0F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Lazurite, 1));
        GT_ModHandler.addScrapboxDrop(2.0F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Pyrite, 1));
        GT_ModHandler.addScrapboxDrop(2.0F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Calcite, 1));
        GT_ModHandler.addScrapboxDrop(2.0F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Sodalite, 1));
        GT_ModHandler.addScrapboxDrop(4.0F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Netherrack, 1));
        GT_ModHandler.addScrapboxDrop(4.0F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Flint, 1));
        GT_ModHandler.addScrapboxDrop(0.03F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Platinum, 1));
        GT_ModHandler.addScrapboxDrop(0.03F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Tungsten, 1));
        GT_ModHandler.addScrapboxDrop(0.03F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Chrome, 1));
        GT_ModHandler.addScrapboxDrop(0.03F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Titanium, 1));
        GT_ModHandler.addScrapboxDrop(0.03F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Magnesium, 1));
        GT_ModHandler.addScrapboxDrop(0.03F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.Endstone, 1));
        GT_ModHandler.addScrapboxDrop(0.5F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.GarnetRed, 1));
        GT_ModHandler.addScrapboxDrop(0.5F, GT_OreDictUnificator.get(OrePrefixes.dust, Materials.GarnetYellow, 1));
        GT_ModHandler.addScrapboxDrop(0.05F, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Olivine, 1));
        GT_ModHandler.addScrapboxDrop(0.05F, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Ruby, 1));
        GT_ModHandler.addScrapboxDrop(0.05F, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Sapphire, 1));
        GT_ModHandler.addScrapboxDrop(0.05F, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.GreenSapphire, 1));
        GT_ModHandler.addScrapboxDrop(0.05F, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Emerald, 1));
        GT_ModHandler.addScrapboxDrop(0.05F, GT_OreDictUnificator.get(OrePrefixes.gem, Materials.Diamond, 1));
    }
}
