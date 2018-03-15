package gregtech.common.blocks;

import java.util.List;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GT_Item_Casings5 extends GT_Item_Casings_Abstract {

    public GT_Item_Casings5(Block par1) {
        super(par1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void func_77624_a(ItemStack aStack, EntityPlayer aPlayer, List<String> aList, boolean aF3_H) {
        super.func_77624_a(aStack, aPlayer, aList, aF3_H);
        switch (getDamage(aStack)) {
            case 0:
                aList.add(this.mCoil01Tooltip);
                break;
            case 1:
                aList.add(this.mCoil02Tooltip);
                break;
            case 2:
                aList.add(this.mCoil03Tooltip);
                break;
            case 3:
                aList.add(this.mCoil04Tooltip);
                break;
            case 4:
                aList.add(this.mCoil05Tooltip);
                break;
            case 5:
                aList.add(this.mCoil06Tooltip);
                break;
            case 6:
                aList.add(this.mCoil07Tooltip);
                break;
        }
    }
}
