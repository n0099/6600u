package gregtech.common.blocks;

import java.util.List;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_Item_Casings1 extends GT_Item_Casings_Abstract {

    public GT_Item_Casings1(Block par1) {
        super(par1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack aStack, EntityPlayer aPlayer, List<String> aList, boolean aF3_H) {
        super.func_77624_a(aStack, aPlayer, aList, aF3_H);
        int tMeta = getDamage(aStack);
        if (tMeta >= 12 && tMeta <= 14) {
            aList.add(TextFormatting.ITALIC + this.mCoilOverheated1Tooltip);
            aList.add(TextFormatting.ITALIC + this.mCoilOverheated2Tooltip);
        }
    }

}
