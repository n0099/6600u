package gregtech.common.items.behaviors;

import gregtech.api.items.GT_MetaBase_Item;
import gregtech.api.util.GT_LanguageManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class Behaviour_Plunger_Essentia
        extends Behaviour_None {
    private final int mCosts;
    private final String mTooltip = GT_LanguageManager.addStringLocalization("gt.behaviour.plunger.essentia", "Clears Essentia from Containers and Tubes");

    public Behaviour_Plunger_Essentia(int aCosts) {
        this.mCosts = aCosts;
    }

    public boolean onItemUseFirst(GT_MetaBase_Item aItem, ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
        if (aWorld.field_72995_K) {
            return false;
        }
        /*TileEntity aTileEntity = aWorld.getTileEntity(aX, aY, aZ);
        if (((aTileEntity instanceof IEssentiaTransport)) && (
                (aPlayer.capabilities.isCreativeMode) || (((GT_MetaGenerated_Tool) aItem).doDamage(aStack, this.mCosts)))) {
            GT_Utility.sendSoundToPlayers(aWorld, (String) GregTech_API.sSoundList.get(Integer.valueOf(101)), 1.0F, -1.0F, aX, aY, aZ);
            for (ForgeDirection tDirection : ForgeDirection.VALID_DIRECTIONS) {
                ((IEssentiaTransport) aTileEntity).takeEssentia(((IEssentiaTransport) aTileEntity).getEssentiaType(tDirection), ((IEssentiaTransport) aTileEntity).getEssentiaAmount(tDirection), tDirection);
            }
            return true;
        }*/
        return false;
    }

    public List<String> getAdditionalToolTips(GT_MetaBase_Item aItem, List<String> aList, ItemStack aStack) {
        aList.add(this.mTooltip);
        return aList;
    }
}
