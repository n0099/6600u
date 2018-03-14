package gregtech.common.blocks;

import gregtech.api.enums.ItemList;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.IIconContainer;
import gregtech.api.util.GT_LanguageManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GT_Block_Casings3 extends GT_Block_Casings_Abstract {

    public GT_Block_Casings3() {
        super(GT_Item_Casings3.class, "gt.blockcasings3", GT_Material_Casings.INSTANCE);

        GT_LanguageManager.addStringLocalization(func_149739_a() + ".0.name", "Yellow Stripes Block");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".1.name", "Yellow Stripes Block");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".2.name", "Radioactive Hazard Sign Block");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".3.name", "Bio Hazard Sign Block");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".4.name", "Explosion Hazard Sign Block");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".5.name", "Fire Hazard Sign Block");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".6.name", "Acid Hazard Sign Block");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".7.name", "Magic Hazard Sign Block");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".8.name", "Frost Hazard Sign Block");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".9.name", "Noise Hazard Sign Block");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".10.name", "Grate Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".11.name", "Vent Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".12.name", "Radiation Proof Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".13.name", "Bronze Firebox Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".14.name", "Steel Firebox Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".15.name", "Tungstensteel Firebox Casing");
        ItemList.Casing_Stripes_A.set(new ItemStack(this, 1, 0));
        ItemList.Casing_Stripes_B.set(new ItemStack(this, 1, 1));
        ItemList.Casing_RadioactiveHazard.set(new ItemStack(this, 1, 2));
        ItemList.Casing_BioHazard.set(new ItemStack(this, 1, 3));
        ItemList.Casing_ExplosionHazard.set(new ItemStack(this, 1, 4));
        ItemList.Casing_FireHazard.set(new ItemStack(this, 1, 5));
        ItemList.Casing_AcidHazard.set(new ItemStack(this, 1, 6));
        ItemList.Casing_MagicHazard.set(new ItemStack(this, 1, 7));
        ItemList.Casing_FrostHazard.set(new ItemStack(this, 1, 8));
        ItemList.Casing_NoiseHazard.set(new ItemStack(this, 1, 9));
        ItemList.Casing_Grate.set(new ItemStack(this, 1, 10));
        ItemList.Casing_Vent.set(new ItemStack(this, 1, 11));
        ItemList.Casing_RadiationProof.set(new ItemStack(this, 1, 12));
        ItemList.Casing_Firebox_Bronze.set(new ItemStack(this, 1, 13));
        ItemList.Casing_Firebox_Steel.set(new ItemStack(this, 1, 14));
        ItemList.Casing_Firebox_TungstenSteel.set(new ItemStack(this, 1, 15));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getIcon(EnumFacing aSide, int aMeta) {
        return getIconContainer(aSide, aMeta).getIcon();
    }
    
    public static IIconContainer getIconContainer(EnumFacing aSide, int aMeta) {
        switch (aMeta) {
            case 0:
                return Textures.BlockIcons.MACHINE_CASING_STRIPES_A;
            case 1:
                return Textures.BlockIcons.MACHINE_CASING_STRIPES_B;
            case 2:
                return Textures.BlockIcons.MACHINE_CASING_RADIOACTIVEHAZARD;
            case 3:
                return Textures.BlockIcons.MACHINE_CASING_BIOHAZARD;
            case 4:
                return Textures.BlockIcons.MACHINE_CASING_EXPLOSIONHAZARD;
            case 5:
                return Textures.BlockIcons.MACHINE_CASING_FIREHAZARD;
            case 6:
                return Textures.BlockIcons.MACHINE_CASING_ACIDHAZARD;
            case 7:
                return Textures.BlockIcons.MACHINE_CASING_MAGICHAZARD;
            case 8:
                return Textures.BlockIcons.MACHINE_CASING_FROSTHAZARD;
            case 9:
                return Textures.BlockIcons.MACHINE_CASING_NOISEHAZARD;
            case 10:
                return Textures.BlockIcons.MACHINE_CASING_GRATE;
            case 11:
                return Textures.BlockIcons.MACHINE_CASING_VENT;
            case 12:
                return Textures.BlockIcons.MACHINE_CASING_RADIATIONPROOF;
            case 13:
                return aSide.func_176745_a() > 1 ? Textures.BlockIcons.MACHINE_CASING_FIREBOX_BRONZE : Textures.BlockIcons.MACHINE_BRONZEPLATEDBRICKS;
            case 14:
                return aSide.func_176745_a() > 1 ? Textures.BlockIcons.MACHINE_CASING_FIREBOX_STEEL : Textures.BlockIcons.MACHINE_CASING_SOLID_STEEL;
            case 15:
                return aSide.func_176745_a() > 1 ? Textures.BlockIcons.MACHINE_CASING_FIREBOX_TUNGSTENSTEEL : Textures.BlockIcons.MACHINE_CASING_ROBUST_TUNGSTENSTEEL;
        }
        return Textures.BlockIcons.MACHINE_CASING_SOLID_STEEL;
    }
    
}
