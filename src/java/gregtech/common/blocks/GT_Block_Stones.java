package gregtech.common.blocks;

import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_LanguageManager;
import gregtech.api.util.GT_OreDictUnificator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GT_Block_Stones extends GT_Block_Stones_Abstract {
    public GT_Block_Stones() {
        super(GT_Item_Granites.class, "gt.blockstones");
        func_149752_b(60.0F);
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".0.name", "Marble");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".1.name", "Marble Cobblestone");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".2.name", "Mossy Marble Cobblestone");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".3.name", "Marble Bricks");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".4.name", "Cracked Marble Bricks");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".5.name", "Mossy Marble Bricks");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".6.name", "Chiseled Marble");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".7.name", "Smooth Marble");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".8.name", "Basalt");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".9.name", "Basalt Cobblestone");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".10.name", "Mossy Basalt Cobblestone");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".11.name", "Basalt Bricks");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".12.name", "Cracked Basalt Bricks");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".13.name", "Mossy Basalt Bricks");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".14.name", "Chiseled Basalt");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".15.name", "Smooth Basalt");
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(this, 1, 0));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(this, 1, 1));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(this, 1, 2));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(this, 1, 3));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(this, 1, 4));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(this, 1, 5));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(this, 1, 6));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Marble, new ItemStack(this, 1, 7));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(this, 1, 8));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(this, 1, 9));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(this, 1, 10));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(this, 1, 11));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(this, 1, 12));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(this, 1, 13));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(this, 1, 14));
        GT_OreDictUnificator.registerOre(OrePrefixes.stone, Materials.Basalt, new ItemStack(this, 1, 15));
    }

    @Override
    public int getHarvestLevel(IBlockState aMeta) {
        return 1;
    }

    @Override
    public float func_176195_g(IBlockState blockState, World worldIn, BlockPos pos) {
        return Blocks.field_150348_b.func_176195_g(blockState, worldIn, pos);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getIcon(EnumFacing aSide, int aMeta) {
        if ((aMeta >= 0) && (aMeta < 16)) {
            return gregtech.api.enums.Textures.BlockIcons.STONES[aMeta].getIcon();
        }
        return gregtech.api.enums.Textures.BlockIcons.STONES[0].getIcon();
    }

}
