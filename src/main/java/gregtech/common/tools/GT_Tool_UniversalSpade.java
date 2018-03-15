package gregtech.common.tools;

import gregtech.GT_Mod;
import gregtech.api.GregTech_API;
import gregtech.api.interfaces.IIconContainer;
import gregtech.api.items.GT_MetaGenerated_Tool;
import gregtech.common.items.behaviors.Behaviour_Crowbar;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class GT_Tool_UniversalSpade extends GT_Tool {

    @Override
    public int getToolDamagePerBlockBreak() {
        return 50;
    }

    @Override
    public int getToolDamagePerDropConversion() {
        return 100;
    }

    @Override
    public int getToolDamagePerContainerCraft() {
        return 400;
    }

    @Override
    public int getToolDamagePerEntityAttack() {
        return 100;
    }

    @Override
    public int getBaseQuality() {
        return 0;
    }

    @Override
    public float getBaseDamage() {
        return 3.0F;
    }

    @Override
    public float getSpeedMultiplier() {
        return 0.75F;
    }

    @Override
    public float getMaxDurabilityMultiplier() {
        return 1.0F;
    }

    @Override
    public String getCraftingSound() {
        return null;
    }

    @Override
    public String getEntityHitSound() {
        return null;
    }

    @Override
    public String getBreakingSound() {
        return GregTech_API.sSoundList.get(0);
    }

    @Override
    public String getMiningSound() {
        return null;
    }

    @Override
    public boolean canBlock() {
        return true;
    }

    @Override
    public boolean isCrowbar() {
        return true;
    }

    @Override
    public boolean isWeapon() {
        return true;
    }

    @Override
    public boolean isMinableBlock(IBlockState aBlock) {
        String tTool = aBlock.func_177230_c().getHarvestTool(aBlock);
        return (tTool != null && (
                        tTool.equals("shovel") ||
                        tTool.equals("axe") ||
                        tTool.equals("saw") ||
                        tTool.equals("sword") ||
                        tTool.equals("crowbar"))) ||
                aBlock.func_185904_a() == Material.field_151595_p ||
                aBlock.func_185904_a() == Material.field_151577_b ||
                aBlock.func_185904_a() == Material.field_151578_c ||
                aBlock.func_185904_a() == Material.field_151597_y ||
                aBlock.func_185904_a() == Material.field_151571_B ||
                aBlock.func_185904_a() == Material.field_151596_z ||
                aBlock.func_185904_a() == Material.field_151584_j ||
                aBlock.func_185904_a() == Material.field_151582_l ||
                aBlock.func_185904_a() == Material.field_151575_d ||
                aBlock.func_185904_a() == Material.field_151570_A ||
                aBlock.func_185904_a() == Material.field_151594_q ||
                aBlock.func_185904_a() == Material.field_151572_C ||
                aBlock.func_185904_a() == Material.field_151569_G ||
                aBlock.func_185904_a() == Material.field_151580_n ||
                aBlock.func_185904_a() == Material.field_151593_r ||
                aBlock.func_185904_a() == Material.field_151585_k ||
                aBlock.func_185904_a() == Material.field_151568_F ||
                aBlock.func_185904_a() == Material.field_151590_u ||
                aBlock.func_185904_a() == Material.field_151583_m;
    }

    @Override
    public ItemStack getBrokenItem(ItemStack aStack) {
        return null;
    }

    @Override
    public IIconContainer getIcon(boolean aIsToolHead, ItemStack aStack) {
        return aIsToolHead ? GT_MetaGenerated_Tool.getPrimaryMaterial(aStack).mIconSet.mTextures[gregtech.api.enums.OrePrefixes.toolHeadUniversalSpade.mTextureIndex] : GT_MetaGenerated_Tool.getSecondaryMaterial(aStack).mIconSet.mTextures[gregtech.api.enums.OrePrefixes.stick.mTextureIndex];
    }

    @Override
    public short[] getRGBa(boolean aIsToolHead, ItemStack aStack) {
        return aIsToolHead ? GT_MetaGenerated_Tool.getPrimaryMaterial(aStack).mRGBa : GT_MetaGenerated_Tool.getSecondaryMaterial(aStack).mRGBa;
    }

    @Override
    public void onStatsAddedToTool(GT_MetaGenerated_Tool aItem, int aID) {
        aItem.addItemBehavior(aID, new Behaviour_Crowbar(2, 2000));
    }

    @Override
    public void onToolCrafted(ItemStack aStack, EntityPlayer aPlayer) {
        super.onToolCrafted(aStack, aPlayer);
        aPlayer.func_71029_a(AchievementList.field_187991_r);
        GT_Mod.achievements.issueAchievement(aPlayer, "unitool");
    }

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase aPlayer, EntityLivingBase aEntity) {
        return new TextComponentString(TextFormatting.RED + "")
                .func_150257_a(aEntity.func_145748_c_())
                .func_150258_a(TextFormatting.WHITE + " has been universal digged by " + TextFormatting.GREEN)
                .func_150257_a(aPlayer.func_145748_c_());
    }

}
