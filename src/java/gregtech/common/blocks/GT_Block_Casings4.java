package gregtech.common.blocks;

import gregtech.api.enums.ItemList;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.IIconContainer;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_LanguageManager;
import gregtech.common.tileentities.machines.multi.GT_MetaTileEntity_LargeTurbine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GT_Block_Casings4 extends GT_Block_Casings_Abstract {

    public static boolean mConnectedMachineTextures = true;

    public GT_Block_Casings4() {
        super(GT_Item_Casings4.class, "gt.blockcasings4", GT_Material_Casings.INSTANCE);

        GT_LanguageManager.addStringLocalization(func_149739_a() + ".0.name", "Robust Tungstensteel Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".1.name", "Clean Stainless Steel Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".2.name", "Stable Titanium Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".3.name", "Titanium Firebox Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".4.name", "Fusion Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".5.name", "Fusion Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".6.name", "Fusion Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".7.name", "Fusion Coil");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".8.name", "Fusion Casing MK II");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".9.name", "Turbine Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".10.name", "Stainless Steel Turbine Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".11.name", "Titanium Turbine Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".12.name", "Tungstensteel Turbine Casing");
        GT_LanguageManager.addStringLocalization(func_149739_a() + ".13.name", "Engine Intake Casing");

        ItemList.Casing_RobustTungstenSteel.set(new ItemStack(this, 1, 0));
        ItemList.Casing_CleanStainlessSteel.set(new ItemStack(this, 1, 1));
        ItemList.Casing_StableTitanium.set(new ItemStack(this, 1, 2));
        ItemList.Casing_Firebox_Titanium.set(new ItemStack(this, 1, 3));
        ItemList.Casing_Fusion.set(new ItemStack(this, 1, 6));
        ItemList.Casing_Fusion_Coil.set(new ItemStack(this, 1, 7));
        ItemList.Casing_Fusion2.set(new ItemStack(this, 1, 8));
        ItemList.Casing_Turbine.set(new ItemStack(this, 1, 9));
        ItemList.Casing_Turbine1.set(new ItemStack(this, 1, 10));
        ItemList.Casing_Turbine2.set(new ItemStack(this, 1, 11));
        ItemList.Casing_Turbine3.set(new ItemStack(this, 1, 12));
        ItemList.Casing_EngineIntake.set(new ItemStack(this, 1, 13));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getIcon(EnumFacing aSide, int aMeta) {
        return getIconContainer(aSide, aMeta).getIcon();
    }
    
    public static IIconContainer getIconContainer(EnumFacing aSide, int aMeta) {
        switch (aMeta) {
            case 0:
                return Textures.BlockIcons.MACHINE_CASING_ROBUST_TUNGSTENSTEEL;
            case 1:
                return Textures.BlockIcons.MACHINE_CASING_CLEAN_STAINLESSSTEEL;
            case 2:
                return Textures.BlockIcons.MACHINE_CASING_STABLE_TITANIUM;
            case 3:
                return aSide.func_176745_a() > 1 ? Textures.BlockIcons.MACHINE_CASING_FIREBOX_TITANIUM : Textures.BlockIcons.MACHINE_CASING_STABLE_TITANIUM;
            case 4:
                return Textures.BlockIcons.MACHINE_CASING_FUSION_GLASS_YELLOW;
            case 5:
                return Textures.BlockIcons.MACHINE_CASING_FUSION_GLASS;
            case 6:
                return Textures.BlockIcons.MACHINE_CASING_FUSION;
            case 7:
                return Textures.BlockIcons.MACHINE_CASING_FUSION_COIL;
            case 8:
                return Textures.BlockIcons.MACHINE_CASING_FUSION_2;
            case 9:
                return Textures.BlockIcons.MACHINE_CASING_TURBINE;
            case 10:
                return Textures.BlockIcons.MACHINE_CASING_CLEAN_STAINLESSSTEEL;
            case 11:
                return Textures.BlockIcons.MACHINE_CASING_STABLE_TITANIUM;
            case 12:
                return Textures.BlockIcons.MACHINE_CASING_ROBUST_TUNGSTENSTEEL;
            case 13:
                return Textures.BlockIcons.MACHINE_CASING_ENGINE_INTAKE;
            case 14:
                return Textures.BlockIcons.MACHINE_CASING_ROBUST_TUNGSTENSTEEL;
            case 15:
                return Textures.BlockIcons.MACHINE_CASING_ROBUST_TUNGSTENSTEEL;
        }
        return Textures.BlockIcons.MACHINE_CASING_SOLID_STEEL;
    }

    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTurbineCasing(int meta, int iconIndex, boolean active, EnumFacing aSide) {
        switch (meta) {
            case 9:
                return active ? Textures.BlockIcons.TURBINE_ACTIVE[iconIndex].getIcon() : Textures.BlockIcons.TURBINE[iconIndex].getIcon();
            case 10:
                return active ? Textures.BlockIcons.TURBINE_ACTIVE1[iconIndex].getIcon() : Textures.BlockIcons.TURBINE1[iconIndex].getIcon();
            case 11:
                return active ? Textures.BlockIcons.TURBINE_ACTIVE2[iconIndex].getIcon() : Textures.BlockIcons.TURBINE2[iconIndex].getIcon();
            case 12:
                return active ? Textures.BlockIcons.TURBINE_ACTIVE3[iconIndex].getIcon() : Textures.BlockIcons.TURBINE3[iconIndex].getIcon();
            default:
                return active ? Textures.BlockIcons.TURBINE_ACTIVE[iconIndex].getIcon() : Textures.BlockIcons.TURBINE[iconIndex].getIcon();
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite getWorldIcon(IBlockAccess aWorld, BlockPos pos, IBlockState aState, EnumFacing aSide) {
        int tMeta = aState.func_177229_b(METADATA);
        if(pos == null) {
            return getIcon(aSide, tMeta);
        }
        if ((tMeta != 6) && (tMeta != 8) && (tMeta != 9) && (tMeta != 10) && (tMeta != 11) && (tMeta != 12) || (!mConnectedMachineTextures)) {
            return getIcon(aSide, tMeta);
        }
        int tStartIndex = tMeta == 6 ? 1 : 13;
        if ((tMeta == 9) || (tMeta == 10) || (tMeta == 11) || (tMeta == 12)) {
            if ((aSide == EnumFacing.NORTH) || (aSide == EnumFacing.SOUTH)) {
                TileEntity tTileEntity;
                IMetaTileEntity tMetaTileEntity;
                if ((null != (tTileEntity = aWorld.func_175625_s(pos.func_177982_a(aSide == EnumFacing.SOUTH ? 1 : -1, -1, 0)))) && ((tTileEntity instanceof IGregTechTileEntity)) && (((IGregTechTileEntity) tTileEntity).getFrontFacing() == aSide.func_176745_a()) && (null != (tMetaTileEntity = ((IGregTechTileEntity) tTileEntity).getMetaTileEntity())) && ((tMetaTileEntity instanceof GT_MetaTileEntity_LargeTurbine))) {
                    if (((IGregTechTileEntity) tTileEntity).isActive()) {
                        return getTurbineCasing(tMeta, 0, true, aSide);
                    }
                    return getTurbineCasing(tMeta, 0, false, aSide);
                }
                if ((null != (tTileEntity = aWorld.func_175625_s(pos.func_177982_a(aSide == EnumFacing.SOUTH ? 1 : -1, 0, 0)))) && ((tTileEntity instanceof IGregTechTileEntity)) && (((IGregTechTileEntity) tTileEntity).getFrontFacing() == aSide.func_176745_a()) && (null != (tMetaTileEntity = ((IGregTechTileEntity) tTileEntity).getMetaTileEntity())) && ((tMetaTileEntity instanceof GT_MetaTileEntity_LargeTurbine))) {
                    if (((IGregTechTileEntity) tTileEntity).isActive()) {
                        return getTurbineCasing(tMeta, 3, true, aSide);
                    }
                    return getTurbineCasing(tMeta, 3, false, aSide);
                }
                if ((null != (tTileEntity = aWorld.func_175625_s(pos.func_177982_a(aSide == EnumFacing.SOUTH ? 1 : -1, 1, 0)))) && ((tTileEntity instanceof IGregTechTileEntity)) && (((IGregTechTileEntity) tTileEntity).getFrontFacing() == aSide.func_176745_a()) && (null != (tMetaTileEntity = ((IGregTechTileEntity) tTileEntity).getMetaTileEntity())) && ((tMetaTileEntity instanceof GT_MetaTileEntity_LargeTurbine))) {
                    if (((IGregTechTileEntity) tTileEntity).isActive()) {
                        return getTurbineCasing(tMeta, 6, true, aSide);
                    }
                    return getTurbineCasing(tMeta, 6, false, aSide);
                }
                if ((null != (tTileEntity = aWorld.func_175625_s(pos.func_177977_b()))) && ((tTileEntity instanceof IGregTechTileEntity)) && (((IGregTechTileEntity) tTileEntity).getFrontFacing() == aSide.func_176745_a()) && (null != (tMetaTileEntity = ((IGregTechTileEntity) tTileEntity).getMetaTileEntity())) && ((tMetaTileEntity instanceof GT_MetaTileEntity_LargeTurbine))) {
                    if (((IGregTechTileEntity) tTileEntity).isActive()) {
                        return getTurbineCasing(tMeta, 1, true, aSide);
                    }
                    return getTurbineCasing(tMeta, 1, false, aSide);
                }
                if ((null != (tTileEntity = aWorld.func_175625_s(pos.func_177984_a()))) && ((tTileEntity instanceof IGregTechTileEntity)) && (((IGregTechTileEntity) tTileEntity).getFrontFacing() == aSide.func_176745_a()) && (null != (tMetaTileEntity = ((IGregTechTileEntity) tTileEntity).getMetaTileEntity())) && ((tMetaTileEntity instanceof GT_MetaTileEntity_LargeTurbine))) {
                    if (((IGregTechTileEntity) tTileEntity).isActive()) {
                        return getTurbineCasing(tMeta, 7, true, aSide);
                    }
                    return getTurbineCasing(tMeta, 7, false, aSide);
                }
                if ((null != (tTileEntity = aWorld.func_175625_s(pos.func_177982_a(aSide == EnumFacing.NORTH ? 1 : -1, 1, 0)))) && ((tTileEntity instanceof IGregTechTileEntity)) && (((IGregTechTileEntity) tTileEntity).getFrontFacing() == aSide.func_176745_a()) && (null != (tMetaTileEntity = ((IGregTechTileEntity) tTileEntity).getMetaTileEntity())) && ((tMetaTileEntity instanceof GT_MetaTileEntity_LargeTurbine))) {
                    if (((IGregTechTileEntity) tTileEntity).isActive()) {
                        return getTurbineCasing(tMeta, 8, true, aSide);
                    }
                    return getTurbineCasing(tMeta, 8, false, aSide);
                }
                if ((null != (tTileEntity = aWorld.func_175625_s(pos.func_177982_a(aSide == EnumFacing.NORTH ? 1 : -1, 0, 0)))) && ((tTileEntity instanceof IGregTechTileEntity)) && (((IGregTechTileEntity) tTileEntity).getFrontFacing() == aSide.func_176745_a()) && (null != (tMetaTileEntity = ((IGregTechTileEntity) tTileEntity).getMetaTileEntity())) && ((tMetaTileEntity instanceof GT_MetaTileEntity_LargeTurbine))) {
                    if (((IGregTechTileEntity) tTileEntity).isActive()) {
                        return getTurbineCasing(tMeta, 5, true, aSide);
                    }
                    return getTurbineCasing(tMeta, 5, false, aSide);
                }
                if ((null != (tTileEntity = aWorld.func_175625_s(pos.func_177982_a(aSide == EnumFacing.NORTH ? 1 : -1, -1, 0)))) && ((tTileEntity instanceof IGregTechTileEntity)) && (((IGregTechTileEntity) tTileEntity).getFrontFacing() == aSide.func_176745_a()) && (null != (tMetaTileEntity = ((IGregTechTileEntity) tTileEntity).getMetaTileEntity())) && ((tMetaTileEntity instanceof GT_MetaTileEntity_LargeTurbine))) {
                    if (((IGregTechTileEntity) tTileEntity).isActive()) {
                        return getTurbineCasing(tMeta, 2, true, aSide);
                    }
                    return getTurbineCasing(tMeta, 2, false, aSide);
                }
            } else if ((aSide == EnumFacing.WEST) || (aSide == EnumFacing.EAST)) {
                TileEntity tTileEntity;
                Object tMetaTileEntity;
                if ((null != (tTileEntity = aWorld.func_175625_s(pos.func_177982_a(0, -1, aSide == EnumFacing.WEST ? 1 : -1)))) && ((tTileEntity instanceof IGregTechTileEntity)) && (((IGregTechTileEntity) tTileEntity).getFrontFacing() == aSide.func_176745_a()) && (null != (tMetaTileEntity = ((IGregTechTileEntity) tTileEntity).getMetaTileEntity())) && ((tMetaTileEntity instanceof GT_MetaTileEntity_LargeTurbine))) {
                    if (((IGregTechTileEntity) tTileEntity).isActive()) {
                        return getTurbineCasing(tMeta, 0, true, aSide);
                    }
                    return getTurbineCasing(tMeta, 0, false, aSide);
                }
                if ((null != (tTileEntity = aWorld.func_175625_s(pos.func_177982_a(0, 0, aSide == EnumFacing.WEST ? 1 : -1)))) && ((tTileEntity instanceof IGregTechTileEntity)) && (((IGregTechTileEntity) tTileEntity).getFrontFacing() == aSide.func_176745_a()) && (null != (tMetaTileEntity = ((IGregTechTileEntity) tTileEntity).getMetaTileEntity())) && ((tMetaTileEntity instanceof GT_MetaTileEntity_LargeTurbine))) {
                    if (((IGregTechTileEntity) tTileEntity).isActive()) {
                        return getTurbineCasing(tMeta, 3, true, aSide);
                    }
                    return getTurbineCasing(tMeta, 3, false, aSide);
                }
                if ((null != (tTileEntity = aWorld.func_175625_s(pos.func_177982_a(0, 1, aSide == EnumFacing.WEST ? 1 : -1)))) && ((tTileEntity instanceof IGregTechTileEntity)) && (((IGregTechTileEntity) tTileEntity).getFrontFacing() == aSide.func_176745_a()) && (null != (tMetaTileEntity = ((IGregTechTileEntity) tTileEntity).getMetaTileEntity())) && ((tMetaTileEntity instanceof GT_MetaTileEntity_LargeTurbine))) {
                    if (((IGregTechTileEntity) tTileEntity).isActive()) {
                        return getTurbineCasing(tMeta, 6, true, aSide);
                    }
                    return getTurbineCasing(tMeta, 6, false, aSide);
                }
                if ((null != (tTileEntity = aWorld.func_175625_s(pos.func_177977_b()))) && ((tTileEntity instanceof IGregTechTileEntity)) && (((IGregTechTileEntity) tTileEntity).getFrontFacing() == aSide.func_176745_a()) && (null != (tMetaTileEntity = ((IGregTechTileEntity) tTileEntity).getMetaTileEntity())) && ((tMetaTileEntity instanceof GT_MetaTileEntity_LargeTurbine))) {
                    if (((IGregTechTileEntity) tTileEntity).isActive()) {
                        return getTurbineCasing(tMeta, 1, true, aSide);
                    }
                    return getTurbineCasing(tMeta, 1, false, aSide);
                }
                if ((null != (tTileEntity = aWorld.func_175625_s(pos.func_177984_a()))) && ((tTileEntity instanceof IGregTechTileEntity)) && (((IGregTechTileEntity) tTileEntity).getFrontFacing() == aSide.func_176745_a()) && (null != (tMetaTileEntity = ((IGregTechTileEntity) tTileEntity).getMetaTileEntity())) && ((tMetaTileEntity instanceof GT_MetaTileEntity_LargeTurbine))) {
                    if (((IGregTechTileEntity) tTileEntity).isActive()) {
                        return getTurbineCasing(tMeta, 7, true, aSide);
                    }
                    return getTurbineCasing(tMeta, 7, false, aSide);
                }
                if ((null != (tTileEntity = aWorld.func_175625_s(pos.func_177982_a(0, 1, aSide == EnumFacing.EAST ? 1 : -1)))) && ((tTileEntity instanceof IGregTechTileEntity)) && (((IGregTechTileEntity) tTileEntity).getFrontFacing() == aSide.func_176745_a()) && (null != (tMetaTileEntity = ((IGregTechTileEntity) tTileEntity).getMetaTileEntity())) && ((tMetaTileEntity instanceof GT_MetaTileEntity_LargeTurbine))) {
                    if (((IGregTechTileEntity) tTileEntity).isActive()) {
                        return getTurbineCasing(tMeta, 8, true, aSide);
                    }
                    return getTurbineCasing(tMeta, 8, false, aSide);
                }
                if ((null != (tTileEntity = aWorld.func_175625_s(pos.func_177982_a(0, 0, aSide == EnumFacing.EAST ? 1 : -1)))) && ((tTileEntity instanceof IGregTechTileEntity)) && (((IGregTechTileEntity) tTileEntity).getFrontFacing() == aSide.func_176745_a()) && (null != (tMetaTileEntity = ((IGregTechTileEntity) tTileEntity).getMetaTileEntity())) && ((tMetaTileEntity instanceof GT_MetaTileEntity_LargeTurbine))) {
                    if (((IGregTechTileEntity) tTileEntity).isActive()) {
                        return getTurbineCasing(tMeta, 5, true, aSide);
                    }
                    return getTurbineCasing(tMeta, 5, false, aSide);
                }
                if ((null != (tTileEntity = aWorld.func_175625_s(pos.func_177982_a(0, -1, aSide == EnumFacing.EAST ? 1 : -1)))) && ((tTileEntity instanceof IGregTechTileEntity)) && (((IGregTechTileEntity) tTileEntity).getFrontFacing() == aSide.func_176745_a()) && (null != (tMetaTileEntity = ((IGregTechTileEntity) tTileEntity).getMetaTileEntity())) && ((tMetaTileEntity instanceof GT_MetaTileEntity_LargeTurbine))) {
                    if (((IGregTechTileEntity) tTileEntity).isActive()) {
                        return getTurbineCasing(tMeta, 2, true, aSide);
                    }
                    return getTurbineCasing(tMeta, 2, false, aSide);
                }
            }
            switch (tMeta) {
                case 9:
                    return Textures.BlockIcons.MACHINE_CASING_SOLID_STEEL.getIcon();
                case 10:
                    return Textures.BlockIcons.MACHINE_CASING_CLEAN_STAINLESSSTEEL.getIcon();
                case 11:
                    return Textures.BlockIcons.MACHINE_CASING_STABLE_TITANIUM.getIcon();
                case 12:
                    return Textures.BlockIcons.MACHINE_CASING_ROBUST_TUNGSTENSTEEL.getIcon();
                default:
                    return Textures.BlockIcons.MACHINE_CASING_SOLID_STEEL.getIcon();
            }
        }
        boolean[] tConnectedSides = {
                stateAndMetaEquals(aWorld, pos.func_177977_b(), tMeta),
                stateAndMetaEquals(aWorld, pos.func_177984_a(), tMeta),
                stateAndMetaEquals(aWorld, pos.func_177978_c(), tMeta),
                stateAndMetaEquals(aWorld, pos.func_177968_d(), tMeta),
                stateAndMetaEquals(aWorld, pos.func_177976_e(), tMeta),
                stateAndMetaEquals(aWorld, pos.func_177974_f(), tMeta)};
        switch (aSide) {
            case DOWN:
                if (tConnectedSides[0]) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 7)].getIcon();
                }
                if ((tConnectedSides[4]) && (tConnectedSides[5]) && (tConnectedSides[2]) && (tConnectedSides[3])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 6)].getIcon();
                }
                if ((!tConnectedSides[4]) && (tConnectedSides[5]) && (tConnectedSides[2]) && (tConnectedSides[3])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 2)].getIcon();
                }
                if ((tConnectedSides[4]) && (!tConnectedSides[5]) && (tConnectedSides[2]) && (tConnectedSides[3])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 3)].getIcon();
                }
                if ((tConnectedSides[4]) && (tConnectedSides[5]) && (!tConnectedSides[2]) && (tConnectedSides[3])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 4)].getIcon();
                }
                if ((tConnectedSides[4]) && (tConnectedSides[5]) && (tConnectedSides[2]) && (!tConnectedSides[3])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 5)].getIcon();
                }
                if ((!tConnectedSides[4]) && (!tConnectedSides[5]) && (tConnectedSides[2]) && (tConnectedSides[3])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 8)].getIcon();
                }
                if ((tConnectedSides[4]) && (!tConnectedSides[5]) && (!tConnectedSides[2]) && (tConnectedSides[3])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 9)].getIcon();
                }
                if ((tConnectedSides[4]) && (tConnectedSides[5]) && (!tConnectedSides[2]) && (!tConnectedSides[3])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 10)].getIcon();
                }
                if ((!tConnectedSides[4]) && (tConnectedSides[5]) && (tConnectedSides[2]) && (!tConnectedSides[3])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 11)].getIcon();
                }
                if ((!tConnectedSides[4]) && (!tConnectedSides[5]) && (!tConnectedSides[2]) && (!tConnectedSides[3])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 7)].getIcon();
                }
                if ((!tConnectedSides[4]) && (!tConnectedSides[2])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 1)].getIcon();
                }
                if ((!tConnectedSides[5]) && (!tConnectedSides[3])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex)].getIcon();
                }
            case UP:
                if (tConnectedSides[1]) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 7)].getIcon();
                }
                if ((tConnectedSides[4]) && (tConnectedSides[5]) && (tConnectedSides[2]) && (tConnectedSides[3])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 6)].getIcon();
                }
                if ((!tConnectedSides[4]) && (tConnectedSides[5]) && (tConnectedSides[2]) && (tConnectedSides[3])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 2)].getIcon();
                }
                if ((tConnectedSides[4]) && (!tConnectedSides[5]) && (tConnectedSides[2]) && (tConnectedSides[3])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 3)].getIcon();
                }
                if ((tConnectedSides[4]) && (tConnectedSides[5]) && (!tConnectedSides[2]) && (tConnectedSides[3])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 4)].getIcon();
                }
                if ((tConnectedSides[4]) && (tConnectedSides[5]) && (tConnectedSides[2]) && (!tConnectedSides[3])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 5)].getIcon();
                }
                if ((!tConnectedSides[4]) && (!tConnectedSides[5]) && (tConnectedSides[2]) && (tConnectedSides[3])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 8)].getIcon();
                }
                if ((tConnectedSides[4]) && (!tConnectedSides[5]) && (!tConnectedSides[2]) && (tConnectedSides[3])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 9)].getIcon();
                }
                if ((tConnectedSides[4]) && (tConnectedSides[5]) && (!tConnectedSides[2]) && (!tConnectedSides[3])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 10)].getIcon();
                }
                if ((!tConnectedSides[4]) && (tConnectedSides[5]) && (tConnectedSides[2]) && (!tConnectedSides[3])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 11)].getIcon();
                }
                if ((!tConnectedSides[4]) && (!tConnectedSides[5]) && (!tConnectedSides[2]) && (!tConnectedSides[3])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 7)].getIcon();
                }
                if ((!tConnectedSides[2]) && (!tConnectedSides[4])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 1)].getIcon();
                }
                if ((!tConnectedSides[3]) && (!tConnectedSides[5])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex)].getIcon();
                }
            case NORTH:
                if (tConnectedSides[5]) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 7)].getIcon();
                }
                if ((tConnectedSides[2]) && (tConnectedSides[0]) && (tConnectedSides[4]) && (tConnectedSides[1])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 6)].getIcon();
                }
                if ((!tConnectedSides[2]) && (tConnectedSides[0]) && (tConnectedSides[4]) && (tConnectedSides[1])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 2)].getIcon();
                }
                if ((tConnectedSides[2]) && (!tConnectedSides[0]) && (tConnectedSides[4]) && (tConnectedSides[1])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 5)].getIcon();
                }
                if ((tConnectedSides[2]) && (tConnectedSides[0]) && (!tConnectedSides[4]) && (tConnectedSides[1])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 4)].getIcon();
                }
                if ((tConnectedSides[2]) && (tConnectedSides[0]) && (tConnectedSides[4]) && (!tConnectedSides[1])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 3)].getIcon();
                }
                if ((!tConnectedSides[2]) && (!tConnectedSides[0]) && (tConnectedSides[4]) && (tConnectedSides[1])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 11)].getIcon();
                }
                if ((tConnectedSides[2]) && (!tConnectedSides[0]) && (!tConnectedSides[4]) && (tConnectedSides[1])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 10)].getIcon();
                }
                if ((tConnectedSides[2]) && (tConnectedSides[0]) && (!tConnectedSides[4]) && (!tConnectedSides[1])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 9)].getIcon();
                }
                if ((!tConnectedSides[2]) && (tConnectedSides[0]) && (tConnectedSides[4]) && (!tConnectedSides[1])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 8)].getIcon();
                }
                if ((!tConnectedSides[2]) && (!tConnectedSides[0]) && (!tConnectedSides[4]) && (!tConnectedSides[1])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 7)].getIcon();
                }
                if ((!tConnectedSides[2]) && (!tConnectedSides[4])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 1)].getIcon();
                }
                if ((!tConnectedSides[0]) && (!tConnectedSides[1])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex)].getIcon();
                }
            case SOUTH:
                if (tConnectedSides[3]) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 7)].getIcon();
                }
                if ((tConnectedSides[2]) && (tConnectedSides[0]) && (tConnectedSides[4]) && (tConnectedSides[1])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 6)].getIcon();
                }
                if ((!tConnectedSides[2]) && (tConnectedSides[0]) && (tConnectedSides[4]) && (tConnectedSides[1])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 4)].getIcon();
                }
                if ((tConnectedSides[2]) && (!tConnectedSides[0]) && (tConnectedSides[4]) && (tConnectedSides[1])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 5)].getIcon();
                }
                if ((tConnectedSides[2]) && (tConnectedSides[0]) && (!tConnectedSides[4]) && (tConnectedSides[1])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 2)].getIcon();
                }
                if ((tConnectedSides[2]) && (tConnectedSides[0]) && (tConnectedSides[4]) && (!tConnectedSides[1])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 3)].getIcon();
                }
                if ((!tConnectedSides[2]) && (!tConnectedSides[0]) && (tConnectedSides[4]) && (tConnectedSides[1])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 10)].getIcon();
                }
                if ((tConnectedSides[2]) && (!tConnectedSides[0]) && (!tConnectedSides[4]) && (tConnectedSides[1])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 11)].getIcon();
                }
                if ((tConnectedSides[2]) && (tConnectedSides[0]) && (!tConnectedSides[4]) && (!tConnectedSides[1])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 8)].getIcon();
                }
                if ((!tConnectedSides[2]) && (tConnectedSides[0]) && (tConnectedSides[4]) && (!tConnectedSides[1])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 9)].getIcon();
                }
                if ((!tConnectedSides[2]) && (!tConnectedSides[0]) && (!tConnectedSides[4]) && (!tConnectedSides[1])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 7)].getIcon();
                }
                if ((!tConnectedSides[2]) && (!tConnectedSides[4])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 1)].getIcon();
                }
                if ((!tConnectedSides[0]) && (!tConnectedSides[1])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex)].getIcon();
                }
            case WEST:
                if (tConnectedSides[4]) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 7)].getIcon();
                }
                if ((tConnectedSides[0]) && (tConnectedSides[3]) && (tConnectedSides[1]) && (tConnectedSides[5])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 6)].getIcon();
                }
                if ((!tConnectedSides[0]) && (tConnectedSides[3]) && (tConnectedSides[1]) && (tConnectedSides[5])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 5)].getIcon();
                }
                if ((tConnectedSides[0]) && (!tConnectedSides[3]) && (tConnectedSides[1]) && (tConnectedSides[5])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 4)].getIcon();
                }
                if ((tConnectedSides[0]) && (tConnectedSides[3]) && (!tConnectedSides[1]) && (tConnectedSides[5])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 3)].getIcon();
                }
                if ((tConnectedSides[0]) && (tConnectedSides[3]) && (tConnectedSides[1]) && (!tConnectedSides[5])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 2)].getIcon();
                }
                if ((!tConnectedSides[0]) && (!tConnectedSides[3]) && (tConnectedSides[1]) && (tConnectedSides[5])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 10)].getIcon();
                }
                if ((tConnectedSides[0]) && (!tConnectedSides[3]) && (!tConnectedSides[1]) && (tConnectedSides[5])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 9)].getIcon();
                }
                if ((tConnectedSides[0]) && (tConnectedSides[3]) && (!tConnectedSides[1]) && (!tConnectedSides[5])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 8)].getIcon();
                }
                if ((!tConnectedSides[0]) && (tConnectedSides[3]) && (tConnectedSides[1]) && (!tConnectedSides[5])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 11)].getIcon();
                }
                if ((!tConnectedSides[0]) && (!tConnectedSides[3]) && (!tConnectedSides[1]) && (!tConnectedSides[5])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 7)].getIcon();
                }
                if ((!tConnectedSides[0]) && (!tConnectedSides[1])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex)].getIcon();
                }
                if ((!tConnectedSides[3]) && (!tConnectedSides[5])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 1)].getIcon();
                }
            case EAST:
                if (tConnectedSides[2]) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 7)].getIcon();
                }
                if ((tConnectedSides[0]) && (tConnectedSides[3]) && (tConnectedSides[1]) && (tConnectedSides[5])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 6)].getIcon();
                }
                if ((!tConnectedSides[0]) && (tConnectedSides[3]) && (tConnectedSides[1]) && (tConnectedSides[5])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 5)].getIcon();
                }
                if ((tConnectedSides[0]) && (!tConnectedSides[3]) && (tConnectedSides[1]) && (tConnectedSides[5])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 2)].getIcon();
                }
                if ((tConnectedSides[0]) && (tConnectedSides[3]) && (!tConnectedSides[1]) && (tConnectedSides[5])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 3)].getIcon();
                }
                if ((tConnectedSides[0]) && (tConnectedSides[3]) && (tConnectedSides[1]) && (!tConnectedSides[5])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 4)].getIcon();
                }
                if ((!tConnectedSides[0]) && (!tConnectedSides[3]) && (tConnectedSides[1]) && (tConnectedSides[5])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 11)].getIcon();
                }
                if ((tConnectedSides[0]) && (!tConnectedSides[3]) && (!tConnectedSides[1]) && (tConnectedSides[5])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 8)].getIcon();
                }
                if ((tConnectedSides[0]) && (tConnectedSides[3]) && (!tConnectedSides[1]) && (!tConnectedSides[5])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 9)].getIcon();
                }
                if ((!tConnectedSides[0]) && (tConnectedSides[3]) && (tConnectedSides[1]) && (!tConnectedSides[5])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 10)].getIcon();
                }
                if ((!tConnectedSides[0]) && (!tConnectedSides[3]) && (!tConnectedSides[1]) && (!tConnectedSides[5])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 7)].getIcon();
                }
                if ((!tConnectedSides[0]) && (!tConnectedSides[1])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex)].getIcon();
                }
                if ((!tConnectedSides[3]) && (!tConnectedSides[5])) {
                    return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 1)].getIcon();
                }
                break;
        }
        return Textures.BlockIcons.CONNECTED_HULLS[(tStartIndex + 7)].getIcon();
    }

    public boolean stateAndMetaEquals(IBlockAccess access, BlockPos blockPos, int meta) {
        IBlockState state = access.func_180495_p(blockPos);
        return state.func_177230_c() == this && state.func_177229_b(METADATA) == meta;
    }

}
