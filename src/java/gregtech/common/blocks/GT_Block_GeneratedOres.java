package gregtech.common.blocks;

import gregtech.GT_Mod;
import gregtech.api.GregTech_API;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.StoneTypes;
import gregtech.api.items.GT_Generic_Block;
import gregtech.api.util.GT_LanguageManager;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import gregtech.common.GT_Proxy;
import gregtech.common.render.RenderGeneratedOres;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GT_Block_GeneratedOres extends GT_Generic_Block {

    public static final int MATERIALS_META_OFFSET = Math.min(16, StoneTypes.mTypes.length);
    public static final int MATERIALS_PER_BLOCK = Math.max(1, 16 / MATERIALS_META_OFFSET);


    private static int sNextId;

    public static IBlockState[][] sGeneratedBlocks;
    public static IBlockState[][] sGeneratedSmallBlocks;

    public static IBlockState[][] getStates(boolean small) {
        return small ? sGeneratedSmallBlocks : sGeneratedBlocks;
    }

    public static void doOreThings() {
        System.out.println("MATERIALS META OFFSET: " + MATERIALS_META_OFFSET);
        System.out.println("MATERIALS/BLOCK: " + MATERIALS_PER_BLOCK);
        sGeneratedBlocks = new IBlockState[GregTech_API.sGeneratedMaterials.length][];
        sGeneratedSmallBlocks = new IBlockState[GregTech_API.sGeneratedMaterials.length][];
        Materials[] lastMats = new Materials[MATERIALS_PER_BLOCK];
        int length = 0;
        for(Materials aMaterial : Materials.values()) {
            if(aMaterial != null && aMaterial.mMetaItemSubID >= 0 && (aMaterial.mTypes & 0x08) != 0) {
                lastMats[length++] = aMaterial;
                if(length == MATERIALS_PER_BLOCK) {
                    new GT_Block_GeneratedOres(lastMats, false);
                    new GT_Block_GeneratedOres(lastMats, true);
                    lastMats = new Materials[MATERIALS_PER_BLOCK];
                    length = 0;
                }
            }
        }
        if(length != 0) {
            for(int i = 0; i < MATERIALS_PER_BLOCK; i++) {
                if(lastMats[i] == null)
                    lastMats[i] = Materials.Air;
            }
            new GT_Block_GeneratedOres(lastMats, false);
            new GT_Block_GeneratedOres(lastMats, true);
        }
        System.out.println("ORE BLOCKS REGISTERED: " + sNextId);
    }

    public static boolean setOreBlock(World world, BlockPos pos, int materialSubId, boolean small) {
        IBlockState prevState = world.func_180495_p(pos);
        Block block = prevState.func_177230_c();
        int metadata = block.func_176201_c(prevState);

        int variantId = 0;

        if(block == Blocks.field_150348_b) {
            variantId = StoneTypes.STONE.mId;
        } else if(block == GregTech_API.sBlockGranites) {
            if(metadata == 0)
                variantId = StoneTypes.BLACK_GRANITE.mId;
            else if(metadata == 8) variantId = StoneTypes.RED_GRANITE.mId;
        } else if(block == GregTech_API.sBlockStones) {
            if(metadata == 0)
                variantId = StoneTypes.MARBLE.mId;
            else if(metadata == 8) variantId = StoneTypes.BASALT.mId;
        } else if(block == Blocks.field_150424_aL) {
            variantId = StoneTypes.NETHERRACK.mId;
        } else if(block == Blocks.field_150377_bs) {
            variantId = StoneTypes.ENDSTONE.mId;
        } else {
            return false;
        }

        IBlockState blockState = getStates(small)[materialSubId][variantId];
        return world.func_175656_a(pos, blockState);
    }

    public final Materials[] mMaterials;
    public final boolean mSmall;
    public int mId;

    protected GT_Block_GeneratedOres(Materials[] materials, boolean small) {
        super(GT_Item_GeneratedOres.class, "gt.blockores." + (sNextId), Material.field_151576_e);
        this.mId = sNextId;
        this.mMaterials = materials;
        this.mSmall = small;
        for(int i = 0; i < materials.length; i++) {
            Materials aMaterial = materials[i];
            IBlockState[] variants = new IBlockState[StoneTypes.mTypes.length];
            for(int j = 0; j < StoneTypes.mTypes.length; j++) {
                variants[j] = func_176203_a(i * MATERIALS_META_OFFSET + j);
                ItemStack itemStack = new ItemStack(this, 1, i * MATERIALS_META_OFFSET + j);
                GT_OreDictUnificator.registerOre(StoneTypes.mTypes[j].processingPrefix.get(aMaterial), itemStack);
                GT_LanguageManager.addStringLocalization(mUnlocalizedName + "." + itemStack.func_77952_i() + ".name", (small ? "Small " : "") + getLocalizedName(aMaterial));
            }
            (small ? sGeneratedSmallBlocks : sGeneratedBlocks)[aMaterial.mMetaItemSubID] = variants;
        }
        func_149672_a(SoundType.field_185851_d);
        func_149647_a(GregTech_API.TAB_GREGTECH_ORES);
        sNextId++;
    }

    public Materials getMaterialSafe(IBlockState state) {
        int index = state.func_177229_b(METADATA) / MATERIALS_META_OFFSET;
        if(mMaterials.length > index) {
            return mMaterials[index];
        }
        return Materials.Air;
    }

    public StoneTypes getStoneTypeSafe(IBlockState state) {
        int index = state.func_177229_b(METADATA) % MATERIALS_META_OFFSET;
        if(StoneTypes.mTypes.length > index) {
            return StoneTypes.mTypes[index];
        }
        return StoneTypes.STONE;
    }

    public Materials getMaterialSafe(ItemStack state) {
        int index =  state.func_77960_j() / MATERIALS_META_OFFSET;
        if(mMaterials.length > index) {
            return mMaterials[index];
        }
        return Materials.Air;
    }

    public StoneTypes getStoneTypeSafe(ItemStack state) {
        int index = state.func_77960_j() % MATERIALS_META_OFFSET;
        if(StoneTypes.mTypes.length > index) {
            return StoneTypes.mTypes[index];
        }
        return StoneTypes.STONE;
    }

    public IBlockState overrideStoneType(IBlockState state, StoneTypes stoneTypes) {
        return getStates(mSmall)[getMaterialSafe(state).mMetaItemSubID][stoneTypes.mId];
    }

    @Override
    public void func_149666_a(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        for(int i = 0; i < mMaterials.length; i++) {
            for(int j = 0; j < StoneTypes.mTypes.length; j++) {
                list.add(new ItemStack(this, 1, i * 7 + j));
            }
        }
    }

    @Override
    public String getHarvestTool(IBlockState state) {
        return "pickaxe";
    }

    @Override
    public int getHarvestLevel(IBlockState state) {
        return GT_Mod.gregtechproxy.mHarvestLevel[getMaterialSafe(state).mMetaItemSubID];
    }

    @Override
    public float func_176195_g(IBlockState blockState, World worldIn, BlockPos pos) {
        return 1.25F * (getHarvestLevel(blockState) + 1);
    }

    @Override
    public float getExplosionResistance(World world, BlockPos pos, Entity exploder, Explosion explosion) {
        return 1.75F * (getHarvestLevel(world.func_180495_p(pos)) + 1);
    }

    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        return false; //never allow silk touch
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getParticleSprite(IBlockAccess worldObj, BlockPos aPos, EnumFacing side) {
        return getStoneTypeSafe(worldObj.func_180495_p(aPos)).mIconContainer.getIcon();
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        ArrayList<ItemStack> rList = new ArrayList<>();
        int aMetaData = state.func_177229_b(METADATA);
        if (!mSmall) {
            rList.add(new ItemStack(this, 1, aMetaData));
            return rList;
        }
        Materials aMaterial = getMaterialSafe(state);
        Materials aBaseMaterial = getStoneTypeSafe(state).stoneMaterial;
        if (aMaterial != null) {
            Random tRandom = new Random(pos.hashCode());
            ArrayList<ItemStack> tSelector = new ArrayList<>();

            ItemStack tStack = GT_OreDictUnificator.get(OrePrefixes.gemExquisite, aMaterial, GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial, 1L), 1L);
            if (tStack != null) {
                for (int i = 0; i < 1; i++) {
                    tSelector.add(tStack);
                }
            }
            tStack = GT_OreDictUnificator.get(OrePrefixes.gemFlawless, aMaterial, GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial, 1L), 1L);
            if (tStack != null) {
                for (int i = 0; i < 2; i++) {
                    tSelector.add(tStack);
                }
            }
            tStack = GT_OreDictUnificator.get(OrePrefixes.gem, aMaterial, 1L);
            if (tStack != null) {
                for (int i = 0; i < 12; i++) {
                    tSelector.add(tStack);
                }
            }
            tStack = GT_OreDictUnificator.get(OrePrefixes.gemFlawed, aMaterial, GT_OreDictUnificator.get(OrePrefixes.crushed, aMaterial, 1L), 1L);
            if (tStack != null) {
                for (int i = 0; i < 5; i++) {
                    tSelector.add(tStack);
                }
            }
            tStack = GT_OreDictUnificator.get(OrePrefixes.crushed, aMaterial, 1L);
            if (tStack != null) {
                for (int i = 0; i < 10; i++) {
                    tSelector.add(tStack);
                }
            }
            tStack = GT_OreDictUnificator.get(OrePrefixes.gemChipped, aMaterial, GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial, 1L), 1L);
            if (tStack != null) {
                for (int i = 0; i < 5; i++) {
                    tSelector.add(tStack);
                }
            }
            tStack = GT_OreDictUnificator.get(OrePrefixes.dustImpure, aMaterial, 1L);
            if (tStack != null) {
                for (int i = 0; i < 10; i++) {
                    tSelector.add(tStack);
                }
            }

            tStack = GT_OreDictUnificator.get(OrePrefixes.dustImpure, aBaseMaterial, 1L);
            if(tStack != null && tRandom.nextInt(4) == 0) {
                rList.add(tStack);
            }

            rList.add(GT_Utility.copyAmount(1L, tSelector.get(tRandom.nextInt(tSelector.size()))));
        }
        return rList;
    }

    public String getLocalizedName(Materials aMaterial) {
        switch (aMaterial.mName) {

            case "InfusedAir":
            case "InfusedDull":
            case "InfusedEarth":
            case "InfusedEntropy":
            case "InfusedFire":
            case "InfusedOrder":
            case "InfusedVis":
            case "InfusedWater":
                return aMaterial.mDefaultLocalName + " Infused Stone";

            case "Vermiculite":
            case "Bentonite":
            case "Kaolinite":
            case "Talc":
            case "BasalticMineralSand":
            case "GraniticMineralSand":
            case "GlauconiteSand":
            case "CassiteriteSand":
            case "GarnetSand":
            case "QuartzSand":
            case "Pitchblende":
            case "FullersEarth":
                return aMaterial.mDefaultLocalName;

            default:
                return aMaterial.mDefaultLocalName + OrePrefixes.ore.mLocalizedMaterialPost;
        }
    }

    @Override
    public EnumBlockRenderType func_149645_b(IBlockState state) {
        return RenderGeneratedOres.INSTANCE.renderType;
    }

    @Override
    public BlockRenderLayer func_180664_k() {
        return BlockRenderLayer.CUTOUT;
    }
}
