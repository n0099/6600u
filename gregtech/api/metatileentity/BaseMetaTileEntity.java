package gregtech.api.metatileentity;

import gregtech.GT_Mod;
import gregtech.api.GregTech_API;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IEnergyConnected;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_BasicMachine;
import gregtech.api.net.GT_Packet_TileEntity;
import gregtech.api.objects.GT_ItemStack;
import gregtech.api.util.*;
import gregtech.common.GT_Pollution;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.info.Info;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.FluidTankProperties;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static gregtech.api.enums.GT_Values.NW;
import static gregtech.api.enums.GT_Values.V;

/**
 * NEVER INCLUDE THIS FILE IN YOUR MOD!!!
 * <p/>
 * This is the main TileEntity for EVERYTHING.
 */
public class BaseMetaTileEntity extends BaseTileEntity implements IGregTechTileEntity {
    private final GT_CoverBehavior[] mCoverBehaviors = new GT_CoverBehavior[]{GregTech_API.sNoBehavior, GregTech_API.sNoBehavior, GregTech_API.sNoBehavior, GregTech_API.sNoBehavior, GregTech_API.sNoBehavior, GregTech_API.sNoBehavior};
    protected MetaTileEntity mMetaTileEntity;
    protected long mStoredEnergy = 0, mStoredSteam = 0;
    protected int mAverageEUInputIndex = 0, mAverageEUOutputIndex = 0;
    protected boolean mReleaseEnergy = false;
    protected int[] mAverageEUInput = new int[]{0, 0, 0, 0, 0}, mAverageEUOutput = new int[]{0, 0, 0, 0, 0};
    private boolean[] mActiveEUInputs = new boolean[]{false, false, false, false, false, false}, mActiveEUOutputs = new boolean[]{false, false, false, false, false, false};
    private byte[] mSidedRedstone = new byte[]{15, 15, 15, 15, 15, 15};
    private int[] mCoverSides = new int[]{0, 0, 0, 0, 0, 0}, mCoverData = new int[]{0, 0, 0, 0, 0, 0}, mTimeStatistics = new int[GregTech_API.TICKS_FOR_LAG_AVERAGING];
    private boolean mHasEnoughEnergy = true, mRunningThroughTick = false, mInputDisabled = false, mOutputDisabled = false, mMuffler = false, mLockUpgrade = false, mActive = false, mRedstone = false, mWorkUpdate = false, mSteamConverter = false, mInventoryChanged = false, mWorks = true, mNeedsUpdate = true, mNeedsBlockUpdate = true, mSendClientData = false, oRedstone = false;
    private byte mColor = 0, oColor = 0, mStrongRedstone = 0, oRedstoneData = 63, oTextureData = 0, oUpdateData = 0, oLightValueClient = -1, oLightValue = -1, mLightValue = 0, mOtherUpgrades = 0, mFacing = 0, oFacing = 0, mWorkData = 0;
    private int mDisplayErrorCode = 0, oX = 0, oY = 0, oZ = 0, mTimeStatisticsIndex = 0, mLagWarningCount = 0;
    private short mID = 0;
    private long mTickTimer = 0, oOutput = 0, mAcceptedAmperes = Long.MAX_VALUE;
    private String mOwnerName = "";
    private NBTTagCompound mRecipeStuff = new NBTTagCompound();

    public BaseMetaTileEntity() {
    }

    @Override
    public NBTTagCompound func_189515_b(NBTTagCompound aNBT) {
        try {
            super.func_189515_b(aNBT);
        } catch (Throwable e) {
            GT_Log.err.println("Encountered CRITICAL ERROR while saving MetaTileEntity, the Chunk whould've been corrupted by now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
            e.printStackTrace(GT_Log.err);
        }
        try {
            aNBT.func_74768_a("mID", mID);
            aNBT.func_74772_a("mStoredSteam", mStoredSteam);
            aNBT.func_74772_a("mStoredEnergy", mStoredEnergy);
            aNBT.func_74783_a("mCoverData", mCoverData);
            aNBT.func_74783_a("mCoverSides", mCoverSides);
            aNBT.func_74773_a("mRedstoneSided", mSidedRedstone);
            aNBT.func_74774_a("mColor", mColor);
            aNBT.func_74774_a("mLightValue", mLightValue);
            aNBT.func_74774_a("mOtherUpgrades", mOtherUpgrades);
            aNBT.func_74774_a("mWorkData", mWorkData);
            aNBT.func_74774_a("mStrongRedstone", mStrongRedstone);
            aNBT.func_74777_a("mFacing", mFacing);
            aNBT.func_74778_a("mOwnerName", mOwnerName);
            aNBT.func_74757_a("mLockUpgrade", mLockUpgrade);
            aNBT.func_74757_a("mMuffler", mMuffler);
            aNBT.func_74757_a("mSteamConverter", mSteamConverter);
            aNBT.func_74757_a("mActive", mActive);
            aNBT.func_74757_a("mRedstone", mRedstone);
            aNBT.func_74757_a("mWorks", !mWorks);
            aNBT.func_74757_a("mInputDisabled", mInputDisabled);
            aNBT.func_74757_a("mOutputDisabled", mOutputDisabled);
            aNBT.func_74782_a("GT.CraftingComponents", mRecipeStuff);
        } catch (Throwable e) {
            GT_Log.err.println("Encountered CRITICAL ERROR while saving MetaTileEntity, the Chunk whould've been corrupted by now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
            e.printStackTrace(GT_Log.err);
        }
        try {
            if (hasValidMetaTileEntity()) {
                NBTTagList tItemList = new NBTTagList();
                for (int i = 0; i < mMetaTileEntity.getRealInventory().length; i++) {
                    ItemStack tStack = mMetaTileEntity.getRealInventory()[i];
                    if (tStack != null) {
                        NBTTagCompound tTag = new NBTTagCompound();
                        tTag.func_74768_a("IntSlot", i);
                        tStack.func_77955_b(tTag);
                        tItemList.func_74742_a(tTag);
                    }
                }
                aNBT.func_74782_a("Inventory", tItemList);

                try {
                    mMetaTileEntity.saveNBTData(aNBT);
                } catch (Throwable e) {
                    GT_Log.err.println("Encountered CRITICAL ERROR while saving MetaTileEntity, the Chunk whould've been corrupted by now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
                    e.printStackTrace(GT_Log.err);
                }
            }
        } catch (Throwable e) {
            GT_Log.err.println("Encountered CRITICAL ERROR while saving MetaTileEntity, the Chunk whould've been corrupted by now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
            e.printStackTrace(GT_Log.err);
        }
        return aNBT;
    }

    @Override
    public void func_145839_a(NBTTagCompound aNBT) {
        super.func_145839_a(aNBT);
        setInitialValuesAsNBT(aNBT, (short) 0);
        issueClientUpdate();
    }

    @Override
    public void setInitialValuesAsNBT(NBTTagCompound aNBT, short aID) {
        if (aNBT == null) {
            if (aID > 0) mID = aID;
            else mID = mID > 0 ? mID : 0;
            if (mID != 0) createNewMetatileEntity(mID);
            mSidedRedstone = (hasValidMetaTileEntity() && mMetaTileEntity.hasSidedRedstoneOutputBehavior() ? new byte[]{0, 0, 0, 0, 0, 0} : new byte[]{15, 15, 15, 15, 15, 15});
        } else {
            if (aID <= 0) mID = (short) aNBT.func_74762_e("mID");
            else mID = aID;
            mStoredSteam = aNBT.func_74762_e("mStoredSteam");
            mStoredEnergy = aNBT.func_74762_e("mStoredEnergy");
            mColor = aNBT.func_74771_c("mColor");
            mLightValue = aNBT.func_74771_c("mLightValue");
            mWorkData = aNBT.func_74771_c("mWorkData");
            mStrongRedstone = aNBT.func_74771_c("mStrongRedstone");
            mFacing = oFacing = (byte) aNBT.func_74765_d("mFacing");
            mOwnerName = aNBT.func_74779_i("mOwnerName");
            mLockUpgrade = aNBT.func_74767_n("mLockUpgrade");
            mMuffler = aNBT.func_74767_n("mMuffler");
            mSteamConverter = aNBT.func_74767_n("mSteamConverter");
            mActive = aNBT.func_74767_n("mActive");
            mRedstone = aNBT.func_74767_n("mRedstone");
            mWorks = !aNBT.func_74767_n("mWorks");
            mInputDisabled = aNBT.func_74767_n("mInputDisabled");
            mOutputDisabled = aNBT.func_74767_n("mOutputDisabled");
            mOtherUpgrades = (byte) (aNBT.func_74771_c("mOtherUpgrades") + aNBT.func_74771_c("mBatteries") + aNBT.func_74771_c("mLiBatteries"));
            mCoverSides = aNBT.func_74759_k("mCoverSides");
            mCoverData = aNBT.func_74759_k("mCoverData");
            mSidedRedstone = aNBT.func_74770_j("mRedstoneSided");
            mRecipeStuff = aNBT.func_74775_l("GT.CraftingComponents");

            if (mCoverData.length != 6) mCoverData = new int[]{0, 0, 0, 0, 0, 0};
            if (mCoverSides.length != 6) mCoverSides = new int[]{0, 0, 0, 0, 0, 0};
            if (mSidedRedstone.length != 6)
                if (hasValidMetaTileEntity() && mMetaTileEntity.hasSidedRedstoneOutputBehavior())
                    mSidedRedstone = new byte[]{0, 0, 0, 0, 0, 0};
                else mSidedRedstone = new byte[]{15, 15, 15, 15, 15, 15};

            for (byte i = 0; i < 6; i++) mCoverBehaviors[i] = GregTech_API.getCoverBehavior(mCoverSides[i]);

            if (mID != 0 && createNewMetatileEntity(mID)) {
                NBTTagList tItemList = aNBT.func_150295_c("Inventory", 10);
                for (int i = 0; i < tItemList.func_74745_c(); i++) {
                    NBTTagCompound tTag = tItemList.func_150305_b(i);
                    int tSlot = tTag.func_74762_e("IntSlot");
                    if (tSlot >= 0 && tSlot < mMetaTileEntity.getRealInventory().length) {
                        mMetaTileEntity.getRealInventory()[tSlot] = GT_Utility.loadItem(tTag);
                    }
                }

                try {
                    mMetaTileEntity.loadNBTData(aNBT);
                } catch (Throwable e) {
                    GT_Log.err.println("Encountered Exception while loading MetaTileEntity, the Server should've crashed now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
                    e.printStackTrace(GT_Log.err);
                }
            }
        }

        if (mCoverData.length != 6) mCoverData = new int[]{0, 0, 0, 0, 0, 0};
        if (mCoverSides.length != 6) mCoverSides = new int[]{0, 0, 0, 0, 0, 0};
        if (mSidedRedstone.length != 6)
            if (hasValidMetaTileEntity() && mMetaTileEntity.hasSidedRedstoneOutputBehavior())
                mSidedRedstone = new byte[]{0, 0, 0, 0, 0, 0};
            else mSidedRedstone = new byte[]{15, 15, 15, 15, 15, 15};

        for (byte i = 0; i < 6; i++) mCoverBehaviors[i] = GregTech_API.getCoverBehavior(mCoverSides[i]);
    }

    private boolean createNewMetatileEntity(short aID) {
        if (aID <= 0 || aID >= GregTech_API.METATILEENTITIES.length || GregTech_API.METATILEENTITIES[aID] == null) {
            GT_Log.err.println("MetaID " + aID + " not loadable => locking TileEntity!");
        } else {
            if (hasValidMetaTileEntity()) mMetaTileEntity.setBaseMetaTileEntity(null);
            GregTech_API.METATILEENTITIES[aID].newMetaEntity(this).setBaseMetaTileEntity(this);
            mTickTimer = 0;
            mID = aID;
            return true;
        }
        return false;
    }

    /**
     * Used for ticking special BaseMetaTileEntities, which need that for Energy Conversion
     * It's called right before onPostTick()
     */
    public void updateStatus() {
        //
    }

    /**
     * Called when trying to charge Items
     */
    public void chargeItem(ItemStack aStack) {
        decreaseStoredEU(GT_ModHandler.chargeElectricItem(aStack, (int) Math.min(Integer.MAX_VALUE, getStoredEU()), (int) Math.min(Integer.MAX_VALUE, mMetaTileEntity.getOutputTier()), false, false), true);
    }

    /**
     * Called when trying to discharge Items
     */
    public void dischargeItem(ItemStack aStack) {
        increaseStoredEnergyUnits(GT_ModHandler.dischargeElectricItem(aStack, (int) Math.min(Integer.MAX_VALUE, getEUCapacity() - getStoredEU()), (int) Math.min(Integer.MAX_VALUE, mMetaTileEntity.getInputTier()), false, false, false), true);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return super.hasCapability(capability, facing) || capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return (T) this;
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void func_73660_a() {
        super.func_73660_a();

        if (!hasValidMetaTileEntity()) {
            if (mMetaTileEntity == null) return;
            mMetaTileEntity.setBaseMetaTileEntity(this);
        }

        mRunningThroughTick = true;
        long tTime = System.currentTimeMillis();
        boolean aSideServer = isServerSide();
        boolean aSideClient = isClientSide();

        if (aSideServer && mTickTimer % 40 == 0) {
            NW.sendToAllAround(field_145850_b, new GT_Packet_TileEntity(
                            getXCoord(), getYCoord(), getZCoord(), mID,
                            mCoverSides[0], mCoverSides[1], mCoverSides[2],
                            mCoverSides[3], mCoverSides[4], mCoverSides[5],
                            oTextureData = (byte) ((mFacing & 7) | (mActive ? 8 : 0) |
                                    (mRedstone ? 16 : 0) | (mLockUpgrade ? 32 : 0)),
                            oUpdateData = hasValidMetaTileEntity() ? mMetaTileEntity.getUpdateData() : 0,
                            oRedstoneData = (byte) (((mSidedRedstone[0] > 0) ? 1 : 0) | ((mSidedRedstone[1] > 0) ? 2 : 0) |
                                    ((mSidedRedstone[2] > 0) ? 4 : 0) | ((mSidedRedstone[3] > 0) ? 8 : 0) |
                                    ((mSidedRedstone[4] > 0) ? 16 : 0) | ((mSidedRedstone[5] > 0) ? 32 : 0)), oColor = mColor, false),
                    getXCoord(), getYCoord(), getZCoord());
        }

        for (int tCode = 0; hasValidMetaTileEntity() && tCode >= 0; ) {
            try {
                switch (tCode) {
                    case 0:
                        tCode++;
                        if (mTickTimer++ == 0) {
                            oX = getXCoord();
                            oY = getYCoord();
                            oZ = getZCoord();
                            if (aSideServer) for (byte i = 0; i < 6; i++)
                                if (getCoverIDAtSide(i) != 0)
                                    if (!mMetaTileEntity.allowCoverOnSide(i, GregTech_API.getCoverItem(getCoverIDAtSide(i))))
                                        dropCover(i, i, true);

                            field_145850_b.func_175646_b(func_174877_v(), this);

                            mMetaTileEntity.onFirstTick(this);
                            if (!hasValidMetaTileEntity()) {
                                mRunningThroughTick = false;
                                return;
                            }
                        }
                    case 1:
                        tCode++;
                        if (aSideClient) {
                            if (mColor != oColor) {
                                mMetaTileEntity.onColorChangeClient(oColor = mColor);
                                issueTextureUpdate();
                            }

                            if (mLightValue != oLightValueClient) {
                                field_145850_b.func_175653_a(EnumSkyBlock.BLOCK, func_174877_v(), mLightValue);
                                field_145850_b.func_175679_n(func_174877_v());
                                field_145850_b.func_175679_n(func_174877_v().func_177982_a(1, 0, 0));
                                field_145850_b.func_175679_n(func_174877_v().func_177982_a(-1, 0, 0));
                                field_145850_b.func_175679_n(func_174877_v().func_177982_a(0, 1, 0));
                                field_145850_b.func_175679_n(func_174877_v().func_177982_a(0, -1, 0));
                                field_145850_b.func_175679_n(func_174877_v().func_177982_a(0, 0, 1));
                                field_145850_b.func_175679_n(func_174877_v().func_177982_a(0, 0, -1));
                                oLightValueClient = mLightValue;
                                issueTextureUpdate();
                            }

                            if (mNeedsUpdate) {
                                causeChunkUpdate();
                                mNeedsUpdate = false;
                            }

                        }
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        if (aSideServer && mTickTimer > 10) {
                            for (byte i = (byte) (tCode - 2); i < 6; i++)
                                if (getCoverIDAtSide(i) != 0) {
                                    tCode++;
                                    GT_CoverBehavior tCover = getCoverBehaviorAtSide(i);
                                    int tCoverTickRate = tCover.getTickRate(i, getCoverIDAtSide(i), mCoverData[i], this);
                                    if (tCoverTickRate > 0 && mTickTimer % tCoverTickRate == 0) {
                                        mCoverData[i] = tCover.doCoverThings(i, getInputRedstoneSignal(i), getCoverIDAtSide(i), mCoverData[i], this, mTickTimer);
                                        if (!hasValidMetaTileEntity()) {
                                            mRunningThroughTick = false;
                                            return;
                                        }
                                    }
                                }

                        }
                    case 8:
                        tCode = 9;
                        if (aSideServer) {
                            if (++mAverageEUInputIndex >= mAverageEUInput.length) mAverageEUInputIndex = 0;
                            if (++mAverageEUOutputIndex >= mAverageEUOutput.length) mAverageEUOutputIndex = 0;

                            mAverageEUInput[mAverageEUInputIndex] = 0;
                            mAverageEUOutput[mAverageEUOutputIndex] = 0;
                        }
                    case 9:
                        tCode++;
                        mMetaTileEntity.onPreTick(this, mTickTimer);
                        if (!hasValidMetaTileEntity()) {
                            mRunningThroughTick = false;
                            return;
                        }
                    case 10:
                        tCode++;
                        if (aSideServer) {
                            if (mRedstone != oRedstone || mTickTimer == 10) {
                                for (byte i = 0; i < 6; i++)
                                    mCoverBehaviors[i] = GregTech_API.getCoverBehavior(mCoverSides[i]);
                                oRedstone = mRedstone;
                                issueBlockUpdate();
                            }

                            if (getXCoord() != oX || getYCoord() != oY || getZCoord() != oZ) {
                                oX = getXCoord();
                                oY = getYCoord();
                                oZ = getZCoord();
                                issueClientUpdate();
                                clearTileEntityBuffer();
                            }

                            if (mFacing != oFacing) {
                                oFacing = mFacing;
                                for (byte i = 0; i < 6; i++)
                                    if (getCoverIDAtSide(i) != 0)
                                        if (!mMetaTileEntity.allowCoverOnSide(i, GregTech_API.getCoverItem(getCoverIDAtSide(i))))
                                            dropCover(i, i, true);
                                issueBlockUpdate();
                            }

                            if (mTickTimer > 20 && mMetaTileEntity.isElectric()) {
                                mAcceptedAmperes = 0;

                                if (getOutputVoltage() != oOutput) {
                                    oOutput = getOutputVoltage();
                                }

                                if (mMetaTileEntity.isEnetOutput() || mMetaTileEntity.isEnetInput()) {
                                    for (byte i = 0; i < 6; i++) {
                                        boolean temp = isEnergyInputSide(i);

                                        if (temp != mActiveEUInputs[i]) {
                                            mActiveEUInputs[i] = temp;
                                        }
                                        temp = isEnergyOutputSide(i);
                                        if (temp != mActiveEUOutputs[i]) {
                                            mActiveEUOutputs[i] = temp;
                                        }

                                    }
                                }

                                if (mMetaTileEntity.isEnetOutput() && oOutput > 0) {
                                    long tOutputVoltage = Math.max(oOutput, oOutput + (1 << GT_Utility.getTier(oOutput))), tUsableAmperage = Math.min(getOutputAmperage(), (getStoredEU() - mMetaTileEntity.getMinimumStoredEU()) / tOutputVoltage);
                                    if (tUsableAmperage > 0) {
                                        long tEU = tOutputVoltage * IEnergyConnected.Util.emitEnergyToNetwork(oOutput, tUsableAmperage, this);
                                        mAverageEUOutput[mAverageEUOutputIndex] += tEU;
                                        decreaseStoredEU(tEU, true);
                                    }
                                }
                                if (getEUCapacity() > 0) {
                                    if (GregTech_API.sMachineFireExplosions && getRandomNumber(1000) == 0) {
                                        Block tBlock = getBlockAtSide((byte) getRandomNumber(6));
                                        if (tBlock != null && tBlock instanceof BlockFire) doEnergyExplosion();
                                    }

                                    if (!hasValidMetaTileEntity()) {
                                        mRunningThroughTick = false;
                                        return;
                                    }

                                    if (getRandomNumber(1000) == 0) {
                                        if ((getCoverIDAtSide((byte) 1) == 0 && field_145850_b.func_175725_q(func_174877_v()).func_177956_o() - 2 < getYCoord())
                                                || (getCoverIDAtSide((byte) 2) == 0 && field_145850_b.func_175725_q(func_174877_v().func_177982_a(0, 0, 1)).func_177956_o() - 1 < getYCoord())
                                                || (getCoverIDAtSide((byte) 3) == 0 && field_145850_b.func_175725_q(func_174877_v().func_177982_a(0, 0, -1)).func_177956_o() - 1 < getYCoord())
                                                || (getCoverIDAtSide((byte) 4) == 0 && field_145850_b.func_175725_q(func_174877_v().func_177982_a(-1, 0, 0)).func_177956_o() - 1 < getYCoord())
                                                || (getCoverIDAtSide((byte) 5) == 0 && field_145850_b.func_175725_q(func_174877_v().func_177982_a(1, 0, 0)).func_177956_o() - 1 < getYCoord())) {
                                            if (GregTech_API.sMachineRainExplosions && field_145850_b.func_72896_J() && getBiome().func_76727_i() > 0) {
                                                if (getRandomNumber(10) == 0) {
                                                    try {
                                                        GT_Mod.achievements.issueAchievement(this.func_145831_w().func_72924_a(mOwnerName), "badweather");
                                                    } catch (Exception e){}
                                                    doEnergyExplosion();
                                                } else setOnFire();
                                            }
                                            if (!hasValidMetaTileEntity()) {
                                                mRunningThroughTick = false;
                                                return;
                                            }
                                            if (GregTech_API.sMachineThunderExplosions && field_145850_b.func_72911_I() && getBiome().func_76727_i() > 0 && getRandomNumber(3) == 0) {
                                                try {
                                                    GT_Mod.achievements.issueAchievement(this.func_145831_w().func_72924_a(mOwnerName), "badweather");
                                                } catch (Exception e){}
                                                doEnergyExplosion();
                                            }
                                        }
                                    }
                                }
                            }

                            if (!hasValidMetaTileEntity()) {
                                mRunningThroughTick = false;
                                return;
                            }
                        }
                    case 11:
                        tCode++;
                        if (aSideServer) {
                            if (mMetaTileEntity.dechargerSlotCount() > 0 && getStoredEU() < getEUCapacity()) {
                                for (int i = mMetaTileEntity.dechargerSlotStartIndex(), k = mMetaTileEntity.dechargerSlotCount() + i; i < k; i++) {
                                    if (mMetaTileEntity.mInventory[i] != null && getStoredEU() < getEUCapacity()) {
                                        dischargeItem(mMetaTileEntity.mInventory[i]);
                                        if (Info.itemInfo.getEnergyValue(mMetaTileEntity.mInventory[i]) > 0) {
                                            if ((getStoredEU() + Info.itemInfo.getEnergyValue(mMetaTileEntity.mInventory[i])) < getEUCapacity()) {
                                                increaseStoredEnergyUnits((long) Info.itemInfo.getEnergyValue(mMetaTileEntity.mInventory[i]), false);
                                                mMetaTileEntity.mInventory[i].field_77994_a--;
                                            }
                                        }
                                        if (mMetaTileEntity.mInventory[i].field_77994_a <= 0)
                                            mMetaTileEntity.mInventory[i] = null;
                                        mInventoryChanged = true;
                                    }
                                }
                            }
                        }
                    case 12:
                        tCode++;
                        if (aSideServer) {
                            if (mMetaTileEntity.rechargerSlotCount() > 0 && getStoredEU() > 0) {
                                for (int i = mMetaTileEntity.rechargerSlotStartIndex(), k = mMetaTileEntity.rechargerSlotCount() + i; i < k; i++) {
                                    if (getStoredEU() > 0 && mMetaTileEntity.mInventory[i] != null) {
                                        chargeItem(mMetaTileEntity.mInventory[i]);
                                        if (mMetaTileEntity.mInventory[i].field_77994_a <= 0)
                                            mMetaTileEntity.mInventory[i] = null;
                                        mInventoryChanged = true;
                                    }
                                }
                            }
                        }
                    case 13:
                        tCode++;
                        updateStatus();
                        if (!hasValidMetaTileEntity()) {
                            mRunningThroughTick = false;
                            return;
                        }
                    case 14:
                        tCode++;
                        mMetaTileEntity.onPostTick(this, mTickTimer);
                        if (!hasValidMetaTileEntity()) {
                            mRunningThroughTick = false;
                            return;
                        }
                    case 15:
                        tCode++;
                        if (aSideServer) {
                            if (mTickTimer > 10) {
                                byte tData = (byte) ((mFacing & 7) | (mActive ? 8 : 0) | (mRedstone ? 16 : 0) | (mLockUpgrade ? 32 : 0));
                                if (tData != oTextureData) sendBlockEvent((byte) 0, oTextureData = tData);
                                tData = mMetaTileEntity.getUpdateData();
                                if (tData != oUpdateData) sendBlockEvent((byte) 1, oUpdateData = tData);
                                if (mColor != oColor) sendBlockEvent((byte) 2, oColor = mColor);
                                tData = (byte) (((mSidedRedstone[0] > 0) ? 1 : 0) | ((mSidedRedstone[1] > 0) ? 2 : 0) | ((mSidedRedstone[2] > 0) ? 4 : 0) | ((mSidedRedstone[3] > 0) ? 8 : 0) | ((mSidedRedstone[4] > 0) ? 16 : 0) | ((mSidedRedstone[5] > 0) ? 32 : 0));
                                if (tData != oRedstoneData) sendBlockEvent((byte) 3, oRedstoneData = tData);
                                if (mLightValue != oLightValue) {
                                    field_145850_b.func_175653_a(EnumSkyBlock.BLOCK, func_174877_v(), mLightValue);
                                    field_145850_b.func_175679_n(func_174877_v());
                                    field_145850_b.func_175679_n(func_174877_v().func_177982_a(1, 0, 0));
                                    field_145850_b.func_175679_n(func_174877_v().func_177982_a(-1, 0, 0));
                                    field_145850_b.func_175679_n(func_174877_v().func_177982_a(0, 0, 1));
                                    field_145850_b.func_175679_n(func_174877_v().func_177982_a(0, 0, -1));
                                    field_145850_b.func_175679_n(func_174877_v().func_177982_a(0, 1, 0));
                                    field_145850_b.func_175679_n(func_174877_v().func_177982_a(0, -1, 0));
                                    issueTextureUpdate();
                                    sendBlockEvent((byte) 7, oLightValue = mLightValue);
                                }
                            }

                            if (mNeedsBlockUpdate) {
                                field_145850_b.func_175685_c(func_174877_v(), getBlockOffset(0, 0, 0));
                                mNeedsBlockUpdate = false;
                            }
                        }
                    default:
                        tCode = -1;
                        break;
                }
            } catch (Throwable e) {
                GT_Log.err.println("Encountered Exception while ticking MetaTileEntity in Step " + (tCode - 1) + ". The Game should've crashed now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
                e.printStackTrace(GT_Log.err);
            }
        }

        if (aSideServer && hasValidMetaTileEntity()) {
            tTime = System.currentTimeMillis() - tTime;
            if (mTimeStatistics.length > 0)
                mTimeStatistics[mTimeStatisticsIndex = (mTimeStatisticsIndex + 1) % mTimeStatistics.length] = (int) tTime;
            if (tTime > 0 && tTime > GregTech_API.MILLISECOND_THRESHOLD_UNTIL_LAG_WARNING && mTickTimer > 1000 && getMetaTileEntity().doTickProfilingMessageDuringThisTick() && mLagWarningCount++ < 10)
                System.out.println("WARNING: Possible Lag Source at [" + getXCoord() + ", " + getYCoord() + ", " + getZCoord() + "] in Dimension " + field_145850_b.field_73011_w.getDimension() + " with " + tTime + "ms caused by an instance of " + getMetaTileEntity().getClass());
        }

        mWorkUpdate = mInventoryChanged = mRunningThroughTick = false;
    }

    @SideOnly(Side.CLIENT)
    public void causeChunkUpdate() {
        int minX = field_174879_c.func_177958_n() - 5;
        int minY = field_174879_c.func_177956_o() - 5;
        int minZ = field_174879_c.func_177952_p() - 5;
        int maxX = field_174879_c.func_177958_n() + 5;
        int maxY = field_174879_c.func_177956_o() + 5;
        int maxZ = field_174879_c.func_177952_p() + 5;
        Minecraft.func_71410_x().field_71438_f.func_147585_a(minX, minY, minZ, maxX, maxY, maxZ);
    }

    public final void receiveMetaTileEntityData(short aID, int aCover0, int aCover1, int aCover2, int aCover3, int aCover4, int aCover5, byte aTextureData, byte aUpdateData, byte aRedstoneData, byte aColorData) {
        issueTextureUpdate();
        if (mID != aID && aID > 0) {
            mID = aID;
            createNewMetatileEntity(mID);
        }


        mCoverSides[0] = aCover0;
        mCoverSides[1] = aCover1;
        mCoverSides[2] = aCover2;
        mCoverSides[3] = aCover3;
        mCoverSides[4] = aCover4;
        mCoverSides[5] = aCover5;

        for (byte i = 0; i < 6; i++) mCoverBehaviors[i] = GregTech_API.getCoverBehavior(mCoverSides[i]);

        func_145842_c(0, aTextureData);
        func_145842_c(1, aUpdateData);
        func_145842_c(2, aColorData);
        func_145842_c(3, aRedstoneData);
    }

    @Override
    public boolean func_145842_c(int aEventID, int aValue) {
        super.func_145842_c(aEventID, aValue);

        if (hasValidMetaTileEntity()) {
            try {
                mMetaTileEntity.receiveClientEvent((byte) aEventID, (byte) aValue);
            } catch (Throwable e) {
                GT_Log.err.println("Encountered Exception while receiving Data from the Server, the Client should've been crashed by now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
                e.printStackTrace(GT_Log.err);
            }
        }

        if (isClientSide()) {
            issueTextureUpdate();
            switch (aEventID) {
                case 0:
                    mFacing = (byte) (aValue & 7);
                    mActive = ((aValue & 8) != 0);
                    mRedstone = ((aValue & 16) != 0);
     				mLockUpgrade = ((aValue&32) != 0);
                    break;
                case 1:
                    if (hasValidMetaTileEntity()) mMetaTileEntity.onValueUpdate((byte) aValue);
                    break;
                case 2:
                    if (aValue > 16 || aValue < 0) aValue = 0;
                    mColor = (byte) aValue;
                    break;
                case 3:
                    mSidedRedstone[0] = (byte) ((aValue & 1) > 0 ? 15 : 0);
                    mSidedRedstone[1] = (byte) ((aValue & 2) > 0 ? 15 : 0);
                    mSidedRedstone[2] = (byte) ((aValue & 4) > 0 ? 15 : 0);
                    mSidedRedstone[3] = (byte) ((aValue & 8) > 0 ? 15 : 0);
                    mSidedRedstone[4] = (byte) ((aValue & 16) > 0 ? 15 : 0);
                    mSidedRedstone[5] = (byte) ((aValue & 32) > 0 ? 15 : 0);
                    break;
                case 4:
                    if (hasValidMetaTileEntity() && mTickTimer > 20)
                        mMetaTileEntity.doSound((byte) aValue, getXCoord() + 0.5, getYCoord() + 0.5, getZCoord() + 0.5);
                    break;
                case 5:
                    if (hasValidMetaTileEntity() && mTickTimer > 20)
                        mMetaTileEntity.startSoundLoop((byte) aValue, getXCoord() + 0.5, getYCoord() + 0.5, getZCoord() + 0.5);
                    break;
                case 6:
                    if (hasValidMetaTileEntity() && mTickTimer > 20)
                        mMetaTileEntity.stopSoundLoop((byte) aValue, getXCoord() + 0.5, getYCoord() + 0.5, getZCoord() + 0.5);
                    break;
                case 7:
                    mLightValue = (byte) aValue;
                    break;
            }
        }
        return true;
    }

    public ArrayList<String> getDebugInfo(EntityPlayer aPlayer, int aLogLevel) {
        ArrayList<String> tList = new ArrayList<String>();
        if (aLogLevel > 2) {
            tList.add("Meta-ID: " + mID + (canAccessData() ? " valid" : " invalid") + (mMetaTileEntity == null ? " MetaTileEntity == null!" : " "));
        }
        if (aLogLevel > 1) {
            if (mTimeStatistics.length > 0) {
                double tAverageTime = 0;
                for (int tTime : mTimeStatistics) tAverageTime += tTime;
                tList.add("This particular TileEntity has caused an average CPU-load of ~" + (tAverageTime / mTimeStatistics.length) + "ms over the last " + mTimeStatistics.length + " ticks.");
            }
            if (mLagWarningCount > 0) {
                tList.add("This TileEntity has also caused " + (mLagWarningCount >= 10 ? "more than 10" : mLagWarningCount) + " Lag Spike Warnings (anything taking longer than " + GregTech_API.MILLISECOND_THRESHOLD_UNTIL_LAG_WARNING + "ms) on the Server.");
            }
            tList.add("Is" + (mMetaTileEntity.isAccessAllowed(aPlayer) ? " " : " not ") + "accessible for you");
        }
        if (aLogLevel > 0) {
            if (getSteamCapacity() > 0 && hasSteamEngineUpgrade())
                tList.add(getStoredSteam() + " of " + getSteamCapacity() + " Steam");
            tList.add("Machine is " + (mActive ? "active" : "inactive"));
            if (!mHasEnoughEnergy)
                tList.add("ATTENTION: This Device consumes Energy at a higher Rate than you input. You could insert more to speed up the process.");
        }
        return mMetaTileEntity.getSpecialDebugInfo(this, aPlayer, aLogLevel, tList);
    }

    @Override
    public void issueTextureUpdate() {
        mNeedsUpdate = true;
    }

    @Override
    public void issueBlockUpdate() {
        mNeedsBlockUpdate = true;
    }

    @Override
    public void issueClientUpdate() {
        mSendClientData = true;
    }

    @Override
    public void issueCoverUpdate(byte aSide) {
        issueClientUpdate();
    }

    @Override
    public byte getStrongestRedstone() {
        return (byte) Math.max(getInternalInputRedstoneSignal((byte) 0), Math.max(getInternalInputRedstoneSignal((byte) 1), Math.max(getInternalInputRedstoneSignal((byte) 2), Math.max(getInternalInputRedstoneSignal((byte) 3), Math.max(getInternalInputRedstoneSignal((byte) 4), getInternalInputRedstoneSignal((byte) 5))))));
    }

    @Override
    public boolean getRedstone() {
        return getRedstone((byte) 0) || getRedstone((byte) 1) || getRedstone((byte) 2) || getRedstone((byte) 3) || getRedstone((byte) 4) || getRedstone((byte) 5);
    }

    @Override
    public boolean getRedstone(byte aSide) {
        return getInternalInputRedstoneSignal(aSide) > 0;
    }

    @Override
    public boolean canOutputRedstone(byte aSide) {
        return (getCoverBehaviorAtSide(aSide).manipulatesSidedRedstoneOutput(aSide, getCoverIDAtSide(aSide), getCoverDataAtSide(aSide), this)
                || getCoverBehaviorAtSide(aSide).letsRedstoneGoOut(aSide, getCoverIDAtSide(aSide), getCoverDataAtSide(aSide), this));
    }

    public ITexture getCoverTexture(byte aSide) {
        return GregTech_API.sCovers.get(getCoverIDAtSide(aSide));
    }

    @Override
    public boolean isGivingInformation() {
        if (canAccessData()) return mMetaTileEntity.isGivingInformation();
        return false;
    }

    @Override
    public boolean isValidFacing(byte aSide) {
        if (canAccessData()) return mMetaTileEntity.isFacingValid(aSide);
        return false;
    }

    @Override
    public byte getBackFacing() {
        return GT_Utility.getOppositeSide(mFacing);
    }

    @Override
    public byte getFrontFacing() {
        return mFacing;
    }

    @Override
    public void setFrontFacing(byte aFacing) {
        if (isValidFacing(aFacing)) {
            mFacing = aFacing;
            mMetaTileEntity.onFacingChange();
            onMachineBlockUpdate();
        }
    }

    @Override
    public int func_70302_i_() {
        if (canAccessData()) return mMetaTileEntity.func_70302_i_();
        return 0;
    }

    @Override
    public ItemStack func_70301_a(int aIndex) {
        if (canAccessData()) return mMetaTileEntity.func_70301_a(aIndex);
        return null;
    }

    @Override
    public void func_70299_a(int aIndex, ItemStack aStack) {
        mInventoryChanged = true;
        if (canAccessData())
            mMetaTileEntity.func_70299_a(aIndex, field_145850_b.field_72995_K ? aStack : GT_OreDictUnificator.setStack(true, aStack));
    }

    @Override
    public int func_70297_j_() {
        if (hasValidMetaTileEntity()) return mMetaTileEntity.func_70297_j_();
        return 64;
    }

    @Override
    public void func_174889_b(EntityPlayer player) {
        if (canAccessData()) mMetaTileEntity.onOpenGUI();
    }

    @Override
    public void func_174886_c(EntityPlayer player) {
        if (canAccessData()) mMetaTileEntity.onCloseGUI();
    }

    @Override
    public boolean func_70300_a(EntityPlayer aPlayer) {
        return canAccessData() && playerOwnsThis(aPlayer, false) && mTickTimer > 40 && getTileEntityOffset(0, 0, 0) == this && aPlayer.func_70092_e(getXCoord() + 0.5, getYCoord() + 0.5, getZCoord() + 0.5) < 64 && mMetaTileEntity.isAccessAllowed(aPlayer);
    }

    @Override
    public void func_145829_t() {
        super.func_145829_t();
        mTickTimer = 0;
    }

    @Override
    public void func_145843_s() {
        field_145846_f = false;
        if (canAccessData()) {
            mMetaTileEntity.onRemoval();
            //no need to detach metatileentity
            //mMetaTileEntity.setBaseMetaTileEntity(null);

        }
        super.func_145843_s();
    }

    @Override
    public void onMachineBlockUpdate() {
        if (canAccessData()) mMetaTileEntity.onMachineBlockUpdate();
    }

    @Override
    public int getProgress() {
        return canAccessData() ? mMetaTileEntity.getProgresstime() : 0;
    }

    @Override
    public int getMaxProgress() {
        return canAccessData() ? mMetaTileEntity.maxProgresstime() : 0;
    }

    @Override
    public boolean increaseProgress(int aProgressAmountInTicks) {
        return canAccessData() ? mMetaTileEntity.increaseProgress(aProgressAmountInTicks) != aProgressAmountInTicks : false;
    }

    @Override
    public boolean hasThingsToDo() {
        return getMaxProgress() > 0;
    }

    @Override
    public void enableWorking() {
        if (!mWorks) mWorkUpdate = true;
        mWorks = true;
    }

    @Override
    public void disableWorking() {
        mWorks = false;
    }

    @Override
    public boolean isAllowedToWork() {
        return mWorks;
    }

    @Override
    public boolean hasWorkJustBeenEnabled() {
        return mWorkUpdate;
    }

    @Override
    public byte getWorkDataValue() {
        return mWorkData;
    }

    @Override
    public void setWorkDataValue(byte aValue) {
        mWorkData = aValue;
    }

    @Override
    public int getMetaTileID() {
        return mID;
    }

    @Override
    public int setMetaTileID(short aID) {
        return mID = aID;
    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void setActive(boolean aActive) {
        field_145850_b.func_175704_b(func_174877_v(), func_174877_v());
        mActive = aActive;
    }

    @Override
    public long getTimer() {
        return mTickTimer;
    }

    @Override
    public boolean decreaseStoredEnergyUnits(long aEnergy, boolean aIgnoreTooLessEnergy) {
        if (!canAccessData()) return false;
        return mHasEnoughEnergy = decreaseStoredEU(aEnergy, aIgnoreTooLessEnergy) || decreaseStoredSteam(aEnergy, false) || (aIgnoreTooLessEnergy && (decreaseStoredSteam(aEnergy, true)));
    }

    @Override
    public boolean increaseStoredEnergyUnits(long aEnergy, boolean aIgnoreTooMuchEnergy) {
        if (!canAccessData()) return false;
        if (getStoredEU() < getEUCapacity() || aIgnoreTooMuchEnergy) {
            setStoredEU(mMetaTileEntity.getEUVar() + aEnergy);
            return true;
        }
        return false;
    }

    @Override
    public boolean inputEnergyFrom(byte aSide) {
        if (aSide == 6) return true;
        if (isServerSide()) return (aSide >= 0 && aSide < 6 ? mActiveEUInputs[aSide] : false) && !mReleaseEnergy;
        return isEnergyInputSide(aSide);
    }

    @Override
    public boolean outputsEnergyTo(byte aSide) {
        if (aSide == 6) return true;
        if (isServerSide()) return (aSide >= 0 && aSide < 6 ? mActiveEUOutputs[aSide] : false) || mReleaseEnergy;
        return isEnergyOutputSide(aSide);
    }

    @Override
    public long getOutputAmperage() {
        if (canAccessData() && mMetaTileEntity.isElectric()) return mMetaTileEntity.maxAmperesOut();
        return 0;
    }

    @Override
    public long getOutputVoltage() {
        if (canAccessData() && mMetaTileEntity.isElectric() && mMetaTileEntity.isEnetOutput())
            return mMetaTileEntity.maxEUOutput();
        return 0;
    }

    @Override
    public long getInputAmperage() {
        if (canAccessData() && mMetaTileEntity.isElectric()) return mMetaTileEntity.maxAmperesIn();
        return 0;
    }

    @Override
    public long getInputVoltage() {
        if (canAccessData() && mMetaTileEntity.isElectric()) return mMetaTileEntity.maxEUInput();
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean increaseStoredSteam(long aEnergy, boolean aIgnoreTooMuchEnergy) {
        if (!canAccessData()) return false;
        if (mMetaTileEntity.getSteamVar() < getSteamCapacity() || aIgnoreTooMuchEnergy) {
            setStoredSteam(mMetaTileEntity.getSteamVar() + aEnergy);
            return true;
        }
        return false;
    }

    @Override
    public String[] getDescription() {
        if (canAccessData()) return mMetaTileEntity.getDescription();
        return new String[0];
    }

    @Override
    public boolean isValidSlot(int aIndex) {
        if (canAccessData()) return mMetaTileEntity.isValidSlot(aIndex);
        return false;
    }

    @Override
    public long getUniversalEnergyStored() {
        return Math.max(getStoredEU(), getStoredSteam());
    }

    @Override
    public long getUniversalEnergyCapacity() {
        return Math.max(getEUCapacity(), getSteamCapacity());
    }

    @Override
    public long getStoredEU() {
        if (canAccessData()) return Math.min(mMetaTileEntity.getEUVar(), getEUCapacity());
        return 0;
    }

    @Override
    public long getEUCapacity() {
        if (canAccessData()) return mMetaTileEntity.maxEUStore();
        return 0;
    }

    @Override
    public long getStoredSteam() {
        if (canAccessData()) return Math.min(mMetaTileEntity.getSteamVar(), getSteamCapacity());
        return 0;
    }

    @Override
    public long getSteamCapacity() {
        if (canAccessData()) return mMetaTileEntity.maxSteamStore();
        return 0;
    }

    @Override
    public ITexture[] getTexture(Block aBlock, byte aSide) {
        ITexture rIcon = getCoverTexture(aSide);
        if (rIcon != null) return new ITexture[]{rIcon};
        if (hasValidMetaTileEntity())
            return mMetaTileEntity.getTexture(this, aSide, mFacing, (byte) (mColor - 1), mActive, getOutputRedstoneSignal(aSide) > 0);
        return Textures.BlockIcons.ERROR_RENDERING;
    }

    private boolean isEnergyInputSide(byte aSide) {
        if (aSide >= 0 && aSide < 6) {
            if (!getCoverBehaviorAtSide(aSide).letsEnergyIn(aSide, getCoverIDAtSide(aSide), getCoverDataAtSide(aSide), this))
                return false;
            if (func_145837_r() || mReleaseEnergy) return false;
            if (canAccessData() && mMetaTileEntity.isElectric() && mMetaTileEntity.isEnetInput())
                return mMetaTileEntity.isInputFacing(aSide);
        }
        return false;
    }

    private boolean isEnergyOutputSide(byte aSide) {
        if (aSide >= 0 && aSide < 6) {
            if (!getCoverBehaviorAtSide(aSide).letsEnergyOut(aSide, getCoverIDAtSide(aSide), getCoverDataAtSide(aSide), this))
                return false;
            if (func_145837_r() || mReleaseEnergy) return mReleaseEnergy;
            if (canAccessData() && mMetaTileEntity.isElectric() && mMetaTileEntity.isEnetOutput())
                return mMetaTileEntity.isOutputFacing(aSide);
        }
        return false;
    }

    protected boolean hasValidMetaTileEntity() {
        return mMetaTileEntity != null && mMetaTileEntity.getBaseMetaTileEntity() == this;
    }

    //we can access dead tile entities data
    protected boolean canAccessData() {
        return hasValidMetaTileEntity();
    }

    public boolean setStoredEU(long aEnergy) {
        if (!canAccessData()) return false;
        if (aEnergy < 0) aEnergy = 0;
        mMetaTileEntity.setEUVar(aEnergy);
        return true;
    }

    public boolean setStoredSteam(long aEnergy) {
        if (!canAccessData()) return false;
        if (aEnergy < 0) aEnergy = 0;
        mMetaTileEntity.setSteamVar(aEnergy);
        return true;
    }

    public boolean decreaseStoredEU(long aEnergy, boolean aIgnoreTooLessEnergy) {
        if (!canAccessData()) {
            return false;
        }
        if (mMetaTileEntity.getEUVar() - aEnergy >= 0 || aIgnoreTooLessEnergy) {
            setStoredEU(mMetaTileEntity.getEUVar() - aEnergy);
            if (mMetaTileEntity.getEUVar() < 0) {
                setStoredEU(0);
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean decreaseStoredSteam(long aEnergy, boolean aIgnoreTooLessEnergy) {
        if (!canAccessData()) return false;
        if (mMetaTileEntity.getSteamVar() - aEnergy >= 0 || aIgnoreTooLessEnergy) {
            setStoredSteam(mMetaTileEntity.getSteamVar() - aEnergy);
            if (mMetaTileEntity.getSteamVar() < 0) {
                setStoredSteam(0);
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean playerOwnsThis(EntityPlayer aPlayer, boolean aCheckPrecicely) {
        if (!canAccessData()) return false;
        if (aCheckPrecicely || privateAccess() || mOwnerName.equals(""))
            if (mOwnerName.equals("") && isServerSide()) setOwnerName(aPlayer.func_70005_c_());
            else if (privateAccess() && !aPlayer.func_70005_c_().equals("Player") && !mOwnerName.equals("Player") && !mOwnerName.equals(aPlayer.func_70005_c_()))
                return false;
        return true;
    }

    public boolean privateAccess() {
        if (!canAccessData()) return mLockUpgrade;
        return mLockUpgrade || mMetaTileEntity.ownerControl();
    }

    public void doEnergyExplosion() {
        if (getUniversalEnergyCapacity() > 0 && getUniversalEnergyStored() >= getUniversalEnergyCapacity() / 5) {
            doExplosion(oOutput * (getUniversalEnergyStored() >= getUniversalEnergyCapacity() ? 4 : getUniversalEnergyStored() >= getUniversalEnergyCapacity() / 2 ? 2 : 1));
            GT_Mod.achievements.issueAchievement(this.func_145831_w().func_72924_a(mOwnerName), "electricproblems");
        }
    }

    @Override
    public void doExplosion(long aAmount) {
        if (canAccessData()) {
            // This is only for Electric Machines
            if (GregTech_API.sMachineWireFire && mMetaTileEntity.isElectric()) {
                try {
                    mReleaseEnergy = true;
                    IEnergyConnected.Util.emitEnergyToNetwork(V[5], Math.max(1, getStoredEU() / V[5]), this);
                } catch (Exception e) {/* Fun Fact: all these "do nothing" Comments you see in my Code, are just there to let Eclipse shut up about the intended empty Brackets, but I need eclipse to yell at me in some of the regular Cases where I forget to add Code */}
            }
            mReleaseEnergy = false;
            // Normal Explosion Code
            mMetaTileEntity.onExplosion();
            if(GT_Mod.gregtechproxy.mExplosionItemDrop){
                for (int i = 0; i < this.func_70302_i_(); i++) {
                    ItemStack tItem = this.func_70301_a(i);
                    if ((tItem != null) && (tItem.field_77994_a > 0) && (this.isValidSlot(i))) {
                        dropItems(tItem);
                        this.func_70299_a(i, null); }
                }
            }
            if (mRecipeStuff != null) {
                for (int i = 0; i < 9; i++) {
                    if (this.getRandomNumber(100) < 50) {
                        dropItems(GT_Utility.loadItem(mRecipeStuff, "Ingredient." + i));
                    }
                }
            }
            GT_Pollution.addPollution(func_174877_v(), 100000);
            mMetaTileEntity.doExplosion(aAmount);
        }
    }

    public void dropItems(ItemStack tItem){
        if(tItem==null)return;
        Random tRandom = new Random();
        EntityItem tItemEntity = new EntityItem(this.field_145850_b, getXCoord() + tRandom.nextFloat() * 0.8F + 0.1F, getYCoord() + tRandom.nextFloat() * 0.8F + 0.1F, getZCoord() + tRandom.nextFloat() * 0.8F + 0.1F, new ItemStack(tItem.func_77973_b(), tItem.field_77994_a, tItem.func_77952_i()));
        if (tItem.func_77942_o()) {
            tItemEntity.func_92059_d().func_77982_d((NBTTagCompound) tItem.func_77978_p().func_74737_b());
        }
        tItemEntity.field_70159_w = (tRandom.nextGaussian() * 0.0500000007450581D);
        tItemEntity.field_70181_x = (tRandom.nextGaussian() * 0.0500000007450581D + 0.2000000029802322D);
        tItemEntity.field_70179_y = (tRandom.nextGaussian() * 0.0500000007450581D);
        tItemEntity.field_70172_ad = 999999;
        tItemEntity.lifespan = 60000;
        try {
            Field tField = tItemEntity.getClass().getDeclaredField("health");
            tField.setAccessible(true);
            tField.setInt(tItemEntity, 99999999);
        } catch (Exception e) {e.printStackTrace();}
        this.field_145850_b.func_72838_d(tItemEntity);
        tItem.field_77994_a = 0;
    }

    @Override
    public ArrayList<ItemStack> getDrops() {
        ItemStack rStack = new ItemStack(GregTech_API.sBlockMachines, 1, mID);
        NBTTagCompound tNBT = new NBTTagCompound();
        if (mRecipeStuff != null && !mRecipeStuff.func_82582_d()) tNBT.func_74782_a("GT.CraftingComponents", mRecipeStuff);
        if (mMuffler) tNBT.func_74757_a("mMuffler", mMuffler);
        if (mLockUpgrade) tNBT.func_74757_a("mLockUpgrade", mLockUpgrade);
        if (mSteamConverter) tNBT.func_74757_a("mSteamConverter", mSteamConverter);
        if (mColor > 0) tNBT.func_74774_a("mColor", mColor);
        if (mOtherUpgrades > 0) tNBT.func_74774_a("mOtherUpgrades", mOtherUpgrades);
        if (mStrongRedstone > 0) tNBT.func_74774_a("mStrongRedstone", mStrongRedstone);
        for (byte i = 0; i < mCoverSides.length; i++) {
            if (mCoverSides[i] != 0) {
                tNBT.func_74783_a("mCoverData", mCoverData);
                tNBT.func_74783_a("mCoverSides", mCoverSides);
                break;
            }
        }
        if (hasValidMetaTileEntity()) mMetaTileEntity.setItemNBT(tNBT);
        if (!tNBT.func_82582_d()) rStack.func_77982_d(tNBT);
        return new ArrayList<>(Arrays.asList(rStack));
    }

    public int getUpgradeCount() {
        return (mMuffler ? 1 : 0) + (mLockUpgrade ? 1 : 0) + (mSteamConverter ? 1 : 0) + mOtherUpgrades;
    }

    @Override
    public boolean onRightclick(EntityPlayer aPlayer, byte aSide, float aX, float aY, float aZ, EnumHand hand) {
        if (isClientSide()) {
            if (getCoverBehaviorAtSide(aSide).onCoverRightclickClient(aSide, this, aPlayer, aX, aY, aZ)) return true;
            if (!getCoverBehaviorAtSide(aSide).isGUIClickable(aSide, getCoverIDAtSide(aSide), getCoverDataAtSide(aSide), this))
                return false;
        }
        if (isServerSide()) {
            if (!privateAccess() || aPlayer.func_70005_c_().equalsIgnoreCase(getOwnerName())) {
                ItemStack tCurrentItem = aPlayer.field_71071_by.func_70448_g();
                if (tCurrentItem != null) {
                    if (getColorization() >= 0 && GT_Utility.areStacksEqual(new ItemStack(Items.field_151131_as, 1), tCurrentItem)) {
                        tCurrentItem.func_150996_a(Items.field_151133_ar);
                        setColorization((byte) (getColorization() >= 16 ? -2 : -1));
                        return true;
                    }
                    if (GT_Utility.isStackInList(tCurrentItem, GregTech_API.sWrenchList)) {
                        if(aPlayer.func_70093_af() && mMetaTileEntity instanceof GT_MetaTileEntity_BasicMachine && ((GT_MetaTileEntity_BasicMachine)mMetaTileEntity).setMainFacing(GT_Utility.determineWrenchingSide(aSide, aX, aY, aZ))){
                            GT_ModHandler.damageOrDechargeItem(tCurrentItem, 1, 1000, aPlayer);
                            GT_Utility.sendSoundToPlayers(field_145850_b, GregTech_API.sSoundList.get(100), 1.0F, -1, getXCoord(), getYCoord(), getZCoord());
                        } else if (mMetaTileEntity.onWrenchRightClick(aSide, GT_Utility.determineWrenchingSide(aSide, aX, aY, aZ), aPlayer, aX, aY, aZ)) {
                            GT_ModHandler.damageOrDechargeItem(tCurrentItem, 1, 1000, aPlayer);
                            GT_Utility.sendSoundToPlayers(field_145850_b, GregTech_API.sSoundList.get(100), 1.0F, -1, getXCoord(), getYCoord(), getZCoord());
                        }
                        return true;
                    }

                    if (GT_Utility.isStackInList(tCurrentItem, GregTech_API.sScrewdriverList)) {
                        if (GT_ModHandler.damageOrDechargeItem(tCurrentItem, 1, 200, aPlayer)) {
                            setCoverDataAtSide(aSide, getCoverBehaviorAtSide(aSide).onCoverScrewdriverclick(aSide, getCoverIDAtSide(aSide), getCoverDataAtSide(aSide), this, aPlayer, aX, aY, aZ));
                            mMetaTileEntity.onScrewdriverRightClick(aSide, aPlayer, aX, aY, aZ);
                            GT_Utility.sendSoundToPlayers(field_145850_b, GregTech_API.sSoundList.get(100), 1.0F, -1, getXCoord(), getYCoord(), getZCoord());
                        }
                        return true;
                    }

                    if (GT_Utility.isStackInList(tCurrentItem, GregTech_API.sHardHammerList)) {
                        if (GT_ModHandler.damageOrDechargeItem(tCurrentItem, 1, 1000, aPlayer)) {
                            mInputDisabled = !mInputDisabled;
                            if (mInputDisabled) mOutputDisabled = !mOutputDisabled;
                            GT_Utility.sendChatToPlayer(aPlayer, "Auto-Input: " + (mInputDisabled ? "Disabled" : "Enabled") + "  Auto-Output: " + (mOutputDisabled ? "Disabled" : "Enabled"));
                            GT_Utility.sendSoundToPlayers(field_145850_b, GregTech_API.sSoundList.get(1), 1.0F, -1, getXCoord(), getYCoord(), getZCoord());
                        }
                        return true;
                    }

                    if (GT_Utility.isStackInList(tCurrentItem, GregTech_API.sSoftHammerList)) {
                        if (GT_ModHandler.damageOrDechargeItem(tCurrentItem, 1, 1000, aPlayer)) {
                            if (mWorks) disableWorking();
                            else enableWorking();
                            GT_Utility.sendChatToPlayer(aPlayer, "Machine Processing: " + (isAllowedToWork() ? "Enabled" : "Disabled"));
                            GT_Utility.sendSoundToPlayers(field_145850_b, GregTech_API.sSoundList.get(101), 1.0F, -1, getXCoord(), getYCoord(), getZCoord());
                        }
                        return true;
                    }

                    if (GT_Utility.isStackInList(tCurrentItem, GregTech_API.sSolderingToolList)) {
                        byte tSide = GT_Utility.determineWrenchingSide(aSide, aX, aY, aZ);
                        if (GT_ModHandler.useSolderingIron(tCurrentItem, aPlayer)) {
                            mStrongRedstone ^= (1 << tSide);
                            GT_Utility.sendChatToPlayer(aPlayer, "Redstone Output at Side " + tSide + " set to: " + ((mStrongRedstone & (1 << tSide)) != 0 ? "Strong" : "Weak"));
                            GT_Utility.sendSoundToPlayers(field_145850_b, GregTech_API.sSoundList.get(103), 3.0F, -1, getXCoord(), getYCoord(), getZCoord());
                        }
                        return true;
                    }

                    if (getCoverIDAtSide(aSide) == 0) {
                        GT_ItemStack gtItemStack = new GT_ItemStack(tCurrentItem);
                        if (GregTech_API.sCoverItems.containsKey(gtItemStack)) {
                            GT_CoverBehavior behavior = GregTech_API.getCoverBehavior(gtItemStack);
                            if (behavior.isCoverPlaceable(aSide, gtItemStack, this) && mMetaTileEntity.allowCoverOnSide(aSide, gtItemStack)) {
                                setCoverItemAtSide(aSide, tCurrentItem);
                                if (!aPlayer.field_71075_bZ.field_75098_d) tCurrentItem.field_77994_a--;
                                GT_Utility.sendSoundToPlayers(field_145850_b, GregTech_API.sSoundList.get(100), 1.0F, -1, getXCoord(), getYCoord(), getZCoord());
                            }
                            return true;
                        }
                    } else {
                        if (GT_Utility.isStackInList(tCurrentItem, GregTech_API.sCrowbarList)) {
                            if (GT_ModHandler.damageOrDechargeItem(tCurrentItem, 1, 1000, aPlayer)) {
                                GT_Utility.sendSoundToPlayers(field_145850_b, GregTech_API.sSoundList.get(0), 1.0F, -1, getXCoord(), getYCoord(), getZCoord());
                                dropCover(aSide, aSide, false);
                            }
                            return true;
                        }
                    }
                }

                if (getCoverBehaviorAtSide(aSide).onCoverRightclick(aSide, getCoverIDAtSide(aSide), getCoverDataAtSide(aSide), this, aPlayer, aX, aY, aZ))
                    return true;

                if (!getCoverBehaviorAtSide(aSide).isGUIClickable(aSide, getCoverIDAtSide(aSide), getCoverDataAtSide(aSide), this))
                    return false;

                if (isUpgradable() && aPlayer.field_71071_by.func_70448_g() != null) {/*
                    if (ItemList.Upgrade_SteamEngine.isStackEqual(aPlayer.inventory.getCurrentItem())) {
						if (addSteamEngineUpgrade()) {
							GT_Utility.sendSoundToPlayers(worldObj, GregTech_API.sSoundList.get(3), 1.0F, -1, getXCoord(), getYCoord(), getZCoord());
							if (!aPlayer.capabilities.isCreativeMode) aPlayer.inventory.getCurrentItem().stackSize--;
						}
						return true;
					}*/
                    if (ItemList.Upgrade_Muffler.isStackEqual(aPlayer.field_71071_by.func_70448_g())) {
                        if (addMufflerUpgrade()) {
                            GT_Utility.sendSoundToPlayers(field_145850_b, GregTech_API.sSoundList.get(3), 1.0F, -1, getXCoord(), getYCoord(), getZCoord());
                            if (!aPlayer.field_71075_bZ.field_75098_d) aPlayer.field_71071_by.func_70448_g().field_77994_a--;
                        }
                        return true;
                    }
                    if (ItemList.Upgrade_Lock.isStackEqual(aPlayer.field_71071_by.func_70448_g())) {
                        if (isUpgradable() && !mLockUpgrade) {
                            mLockUpgrade = true;
                            setOwnerName(aPlayer.func_70005_c_());
                            GT_Utility.sendSoundToPlayers(field_145850_b, GregTech_API.sSoundList.get(3), 1.0F, -1, getXCoord(), getYCoord(), getZCoord());
                            if (!aPlayer.field_71075_bZ.field_75098_d) aPlayer.field_71071_by.func_70448_g().field_77994_a--;
                        }
                        return true;
                    }
                }
            }
        }

        try {
            if (hasValidMetaTileEntity())
                return mMetaTileEntity.onRightclick(this, aPlayer, aSide, aX, aY, aZ, hand);
        } catch (Throwable e) {
            GT_Log.err.println("Encountered Exception while rightclicking TileEntity, the Game should've crashed now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
            e.printStackTrace(GT_Log.err);
        }

        return true;
    }

    @Override
    public void onLeftclick(EntityPlayer aPlayer) {
        try {
            if (aPlayer != null && hasValidMetaTileEntity()) mMetaTileEntity.onLeftclick(this, aPlayer, EnumHand.MAIN_HAND);
        } catch (Throwable e) {
            GT_Log.err.println("Encountered Exception while leftclicking TileEntity, the Game should've crashed now, but I prevented that. Please report immidietly to GregTech Intergalactical!!!");
            e.printStackTrace(GT_Log.err);
        }
    }

    @Override
    public boolean isDigitalChest() {
        if (canAccessData()) return mMetaTileEntity.isDigitalChest();
        return false;
    }

    @Override
    public ItemStack[] getStoredItemData() {
        if (canAccessData()) return mMetaTileEntity.getStoredItemData();
        return null;
    }

    @Override
    public void setItemCount(int aCount) {
        if (canAccessData()) mMetaTileEntity.setItemCount(aCount);
    }

    @Override
    public int getMaxItemCount() {
        if (canAccessData()) return mMetaTileEntity.getMaxItemCount();
        return 0;
    }

    /**
     * Can put aStack into Slot
     */
    @Override
    public boolean func_94041_b(int aIndex, ItemStack aStack) {
        return canAccessData() && mMetaTileEntity.func_94041_b(aIndex, aStack);
    }

    @Override
    public int func_174887_a_(int id) {
        return 0;
    }

    @Override
    public void func_174885_b(int id, int value) {

    }

    @Override
    public int func_174890_g() {
        return 0;
    }

    @Override
    public void func_174888_l() {

    }

    /**
     * returns all valid Inventory Slots, no matter which Side (Unless it's covered).
     * The Side Stuff is done in the following two Functions.
     */
    @Override
    public int[] func_180463_a(EnumFacing aSide) {
        if (canAccessData() && (getCoverBehaviorAtSide((byte) aSide.func_176745_a()).letsItemsOut((byte) aSide.func_176745_a(), getCoverIDAtSide((byte) aSide.func_176745_a()), getCoverDataAtSide((byte) aSide.func_176745_a()), -1, this) || getCoverBehaviorAtSide((byte) aSide.func_176745_a()).letsItemsIn((byte) aSide.func_176745_a(), getCoverIDAtSide((byte) aSide.func_176745_a()), getCoverDataAtSide((byte) aSide.func_176745_a()), -1, this)))
            return mMetaTileEntity.func_180463_a(aSide);
        return new int[0];
    }

    /**
     * Can put aStack into Slot at Side
     */
    @Override
    public boolean func_180462_a(int aIndex, ItemStack aStack, EnumFacing aSide) {
        return canAccessData() && (mRunningThroughTick || !mInputDisabled) && getCoverBehaviorAtSide((byte) aSide.func_176745_a()).letsItemsIn((byte) aSide.func_176745_a(), getCoverIDAtSide((byte) aSide.func_176745_a()), getCoverDataAtSide((byte) aSide.func_176745_a()), aIndex, this) && mMetaTileEntity.func_180462_a(aIndex, aStack, aSide);
    }

    /**
     * Can pull aStack out of Slot from Side
     */
    @Override
    public boolean func_180461_b(int aIndex, ItemStack aStack, EnumFacing aSide) {
        return canAccessData() && (mRunningThroughTick || !mOutputDisabled) && getCoverBehaviorAtSide((byte) aSide.func_176745_a()).letsItemsOut((byte) aSide.func_176745_a(), getCoverIDAtSide((byte) aSide.func_176745_a()), getCoverDataAtSide((byte) aSide.func_176745_a()), aIndex, this) && mMetaTileEntity.func_180461_b(aIndex, aStack, aSide);
    }

    @Override
    public boolean isUpgradable() {
        return canAccessData() && getUpgradeCount() < 8;
    }

    @Override
    public byte getInternalInputRedstoneSignal(byte aSide) {
        return (byte) (getCoverBehaviorAtSide(aSide).getRedstoneInput(aSide, getInputRedstoneSignal(aSide), getCoverIDAtSide(aSide), getCoverDataAtSide(aSide), this) & 15);
    }

    @Override
    public byte getInputRedstoneSignal(byte aSide) {
        return (byte) (field_145850_b.func_175651_c(func_174877_v().func_177972_a(EnumFacing.field_82609_l[aSide]), EnumFacing.field_82609_l[aSide]) & 15);
    }

    @Override
    public byte getOutputRedstoneSignal(byte aSide) {
        return getCoverBehaviorAtSide(aSide).manipulatesSidedRedstoneOutput(aSide, getCoverIDAtSide(aSide), getCoverDataAtSide(aSide), this) ? mSidedRedstone[aSide] : 0;
    }

    @Override
    public void setInternalOutputRedstoneSignal(byte aSide, byte aStrength) {
        if (!getCoverBehaviorAtSide(aSide).manipulatesSidedRedstoneOutput(aSide, getCoverIDAtSide(aSide), getCoverDataAtSide(aSide), this))
            setOutputRedstoneSignal(aSide, aStrength);
    }

    @Override
    public void setOutputRedstoneSignal(byte aSide, byte aStrength) {
        aStrength = (byte) Math.min(Math.max(0, aStrength), 15);
        if (aSide >= 0 && aSide < 6 && mSidedRedstone[aSide] != aStrength) {
            mSidedRedstone[aSide] = aStrength;
            issueBlockUpdate();
        }
    }

    @Override
    public boolean isSteamEngineUpgradable() {
        return isUpgradable() && !hasSteamEngineUpgrade() && getSteamCapacity() > 0;
    }

    @Override
    public boolean addSteamEngineUpgrade() {
        if (isSteamEngineUpgradable()) {
            issueBlockUpdate();
            mSteamConverter = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean hasSteamEngineUpgrade() {
        if (canAccessData() && mMetaTileEntity.isSteampowered()) return true;
        return mSteamConverter;
    }

    @Override
    public boolean hasMufflerUpgrade() {
        return mMuffler;
    }

    @Override
    public boolean isMufflerUpgradable() {
        return isUpgradable() && !hasMufflerUpgrade();
    }

    @Override
    public boolean addMufflerUpgrade() {
        if (isMufflerUpgradable()) return mMuffler = true;
        return false;
    }

    @Override
    public boolean hasInventoryBeenModified() {
        return mInventoryChanged;
    }

    @Override
    public void setGenericRedstoneOutput(boolean aOnOff) {
        mRedstone = aOnOff;
    }

    @Override
    public int getErrorDisplayID() {
        return mDisplayErrorCode;
    }

    @Override
    public void setErrorDisplayID(int aErrorID) {
        mDisplayErrorCode = aErrorID;
    }

    @Override
    public IMetaTileEntity getMetaTileEntity() {
        return hasValidMetaTileEntity() ? mMetaTileEntity : null;
    }

    @Override
    public void setMetaTileEntity(IMetaTileEntity aMetaTileEntity) {
        mMetaTileEntity = (MetaTileEntity) aMetaTileEntity;
    }

    @Override
    public GT_CoverBehavior getCoverBehaviorAtSide(byte aSide) {
        return aSide >= 0 && aSide < mCoverBehaviors.length ? mCoverBehaviors[aSide] : GregTech_API.sNoBehavior;
    }

    @Override
    public void setCoverIDAtSide(byte aSide, int aID) {
        if (aSide >= 0 && aSide < 6) {
            mCoverSides[aSide] = aID;
            mCoverData[aSide] = 0;
            mCoverBehaviors[aSide] = GregTech_API.getCoverBehavior(aID);
            issueCoverUpdate(aSide);
            issueBlockUpdate();
        }
    }

    @Override
    public void setCoverItemAtSide(byte aSide, ItemStack aStack) {
        int coverId = GregTech_API.getCoverId(new GT_ItemStack(aStack));
        GT_CoverBehavior behavior = GregTech_API.getCoverBehavior(coverId);
        behavior.placeCover(aSide, coverId, aStack, this);
    }

    @Override
    public int getCoverIDAtSide(byte aSide) {
        if (aSide >= 0 && aSide < 6) return mCoverSides[aSide];
        return 0;
    }

    @Override
    public boolean canPlaceCoverIDAtSide(byte aSide, int aID) {
        return getCoverIDAtSide(aSide) == 0;
    }

    @Override
    public boolean canPlaceCoverItemAtSide(byte aSide, ItemStack aCover) {
        return getCoverIDAtSide(aSide) == 0;
    }

    @Override
    public void setCoverDataAtSide(byte aSide, int aData) {
        if (aSide >= 0 && aSide < 6) mCoverData[aSide] = aData;
    }

    @Override
    public int getCoverDataAtSide(byte aSide) {
        if (aSide >= 0 && aSide < 6) return mCoverData[aSide];
        return 0;
    }

    public byte getLightValue() {
        return mLightValue;
    }

    @Override
    public void setLightValue(byte aLightValue) {
        mLightValue = (byte) (aLightValue & 15);
    }

    @Override
    public long getAverageElectricInput() {
        int rEU = 0;
        for (int tEU : mAverageEUInput) rEU += tEU;
        return rEU / mAverageEUInput.length;
    }

    @Override
    public long getAverageElectricOutput() {
        int rEU = 0;
        for (int tEU : mAverageEUOutput) rEU += tEU;
        return rEU / mAverageEUOutput.length;
    }

    @Override
    public boolean dropCover(byte aSide, byte aDroppedSide, boolean aForced) {
        if (getCoverBehaviorAtSide(aSide).onCoverRemoval(aSide, getCoverIDAtSide(aSide), mCoverData[aSide], this, aForced) || aForced) {
            ItemStack tStack = getCoverBehaviorAtSide(aSide).getDrop(aSide, getCoverIDAtSide(aSide), getCoverDataAtSide(aSide), this);
            if (tStack != null) {
                EntityItem tEntity = new EntityItem(field_145850_b, getOffsetX(aDroppedSide, 1) + 0.5, getOffsetY(aDroppedSide, 1) + 0.5, getOffsetZ(aDroppedSide, 1) + 0.5, tStack);
                tEntity.field_70159_w = 0;
                tEntity.field_70181_x = 0;
                tEntity.field_70179_y = 0;
                field_145850_b.func_72838_d(tEntity);
            }
            setCoverIDAtSide(aSide, 0);
            if (mMetaTileEntity.hasSidedRedstoneOutputBehavior()) {
                setOutputRedstoneSignal(aSide, (byte) 0);
            } else {
                setOutputRedstoneSignal(aSide, (byte) 15);
            }
            return true;
        }
        return false;
    }

    @Override
    public String getOwnerName() {
        if (GT_Utility.isStringInvalid(mOwnerName)) return "Player";
        return mOwnerName;
    }

    @Override
    public String setOwnerName(String aName) {
        if (GT_Utility.isStringInvalid(aName)) return mOwnerName = "Player";
        return mOwnerName = aName;
    }

    @Override
    public byte getComparatorValue(byte aSide) {
        return canAccessData() ? mMetaTileEntity.getComparatorValue(aSide) : 0;
    }

    @Override
    public byte getStrongOutputRedstoneSignal(byte aSide) {
        return aSide >= 0 && aSide < 6 && (mStrongRedstone & (1 << aSide)) != 0 ? (byte) (mSidedRedstone[aSide] & 15) : 0;
    }

    @Override
    public void setStrongOutputRedstoneSignal(byte aSide, byte aStrength) {
        mStrongRedstone |= (1 << aSide);
        setOutputRedstoneSignal(aSide, aStrength);
    }

    @Override
    public ItemStack func_70298_a(int aIndex, int aAmount) {
        if (canAccessData()) {
            mInventoryChanged = true;
            return mMetaTileEntity.func_70298_a(aIndex, aAmount);
        }
        return null;
    }

    @Nullable
    @Override
    public ItemStack func_70304_b(int index) {
        return null;
    }

    @Override
    public long injectEnergyUnits(byte aSide, long aVoltage, long aAmperage) {
        if (!canAccessData() || !mMetaTileEntity.isElectric() || !inputEnergyFrom(aSide) || aAmperage <= 0 || aVoltage <= 0 || getStoredEU() >= getEUCapacity() || mMetaTileEntity.maxAmperesIn() <= mAcceptedAmperes)
            return 0;
        if (aVoltage > getInputVoltage()) {
            doExplosion(aVoltage);
            return 0;
        }
        if (increaseStoredEnergyUnits(aVoltage * (aAmperage = Math.min(aAmperage, Math.min(mMetaTileEntity.maxAmperesIn() - mAcceptedAmperes, 1 + ((getEUCapacity() - getStoredEU()) / aVoltage)))), true)) {
            mAverageEUInput[mAverageEUInputIndex] += aVoltage * aAmperage;
            mAcceptedAmperes += aAmperage;
            return aAmperage;
        }
        return 0;
    }

    @Override
    public boolean drainEnergyUnits(byte aSide, long aVoltage, long aAmperage) {
        if (!canAccessData() || !mMetaTileEntity.isElectric() || !outputsEnergyTo(aSide) || getStoredEU() - (aVoltage * aAmperage) < mMetaTileEntity.getMinimumStoredEU())
            return false;
        if (decreaseStoredEU(aVoltage * aAmperage, false)) {
            mAverageEUOutput[mAverageEUOutputIndex] += aVoltage * aAmperage;
            return true;
        }
        return false;
    }

    @Override
    public boolean acceptsRotationalEnergy(byte aSide) {
        if (!canAccessData() || getCoverIDAtSide(aSide) != 0) return false;
        return mMetaTileEntity.acceptsRotationalEnergy(aSide);
    }

    @Override
    public boolean injectRotationalEnergy(byte aSide, long aSpeed, long aEnergy) {
        if (!canAccessData() || getCoverIDAtSide(aSide) != 0) return false;
        return mMetaTileEntity.injectRotationalEnergy(aSide, aSpeed, aEnergy);
    }

    @Override
    public int fill(EnumFacing aSide, FluidStack aFluid, boolean doFill) {
        if (mTickTimer > 5 && canAccessData() && (mRunningThroughTick || !mInputDisabled) && (aSide == null || (mMetaTileEntity.isLiquidInput((byte) aSide.ordinal()) && getCoverBehaviorAtSide((byte) aSide.ordinal()).letsFluidIn((byte) aSide.ordinal(), getCoverIDAtSide((byte) aSide.ordinal()), getCoverDataAtSide((byte) aSide.ordinal()), aFluid == null ? null : aFluid.getFluid(), this))))
            return mMetaTileEntity.fill(aSide, aFluid, doFill);
        return 0;
    }

    @Override
    public FluidStack drain(EnumFacing aSide, int maxDrain, boolean doDrain) {
        if (mTickTimer > 5 && canAccessData() && (mRunningThroughTick || !mOutputDisabled) && (aSide == null || (mMetaTileEntity.isLiquidOutput((byte) aSide.ordinal()) && getCoverBehaviorAtSide((byte) aSide.ordinal()).letsFluidOut((byte) aSide.ordinal(), getCoverIDAtSide((byte) aSide.ordinal()), getCoverDataAtSide((byte) aSide.ordinal()), mMetaTileEntity.getFluid() == null ? null : mMetaTileEntity.getFluid().getFluid(), this))))
            return mMetaTileEntity.drain(aSide, maxDrain, doDrain);
        return null;
    }

    @Override
    public FluidStack drain(EnumFacing aSide, FluidStack aFluid, boolean doDrain) {
        if (mTickTimer > 5 && canAccessData() && (mRunningThroughTick || !mOutputDisabled) && (aSide == null || (mMetaTileEntity.isLiquidOutput((byte) aSide.ordinal()) && getCoverBehaviorAtSide((byte) aSide.ordinal()).letsFluidOut((byte) aSide.ordinal(), getCoverIDAtSide((byte) aSide.ordinal()), getCoverDataAtSide((byte) aSide.ordinal()), aFluid == null ? null : aFluid.getFluid(), this))))
            return mMetaTileEntity.drain(aSide, aFluid, doDrain);
        return null;
    }

    @Override
    public boolean canFill(EnumFacing aSide, Fluid aFluid) {
        if (mTickTimer > 5 && canAccessData() && (mRunningThroughTick || !mInputDisabled) && (aSide == null || (mMetaTileEntity.isLiquidInput((byte) aSide.ordinal()) && getCoverBehaviorAtSide((byte) aSide.ordinal()).letsFluidIn((byte) aSide.ordinal(), getCoverIDAtSide((byte) aSide.ordinal()), getCoverDataAtSide((byte) aSide.ordinal()), aFluid, this))))
            return mMetaTileEntity.canFill(aSide, aFluid);
        return false;
    }

    @Override
    public boolean canDrain(EnumFacing aSide, Fluid aFluid) {
        if (mTickTimer > 5 && canAccessData() && (mRunningThroughTick || !mOutputDisabled) && (aSide == null || (mMetaTileEntity.isLiquidOutput((byte) aSide.ordinal()) && getCoverBehaviorAtSide((byte) aSide.ordinal()).letsFluidOut((byte) aSide.ordinal(), getCoverIDAtSide((byte) aSide.ordinal()), getCoverDataAtSide((byte) aSide.ordinal()), aFluid, this))))
            return mMetaTileEntity.canDrain(aSide, aFluid);
        return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(EnumFacing aSide) {
        if (canAccessData() && ((mMetaTileEntity.isLiquidInput((byte) aSide.ordinal()) && getCoverBehaviorAtSide((byte) aSide.ordinal()).letsFluidIn((byte) aSide.ordinal(), getCoverIDAtSide((byte) aSide.ordinal()), getCoverDataAtSide((byte) aSide.ordinal()), null, this)) || (mMetaTileEntity.isLiquidOutput((byte) aSide.ordinal()) && getCoverBehaviorAtSide((byte) aSide.ordinal()).letsFluidOut((byte) aSide.ordinal(), getCoverIDAtSide((byte) aSide.ordinal()), getCoverDataAtSide((byte) aSide.ordinal()), null, this))))
            return mMetaTileEntity.getTankInfo(aSide);
        return new FluidTankInfo[]{};
    }

    public double getOutputEnergyUnitsPerTick() {
        return oOutput;
    }

    public boolean isTeleporterCompatible(EnumFacing aSide) {
        return canAccessData() && mMetaTileEntity.isTeleporterCompatible();
    }

    public double demandedEnergyUnits() {
        if (mReleaseEnergy || !canAccessData() || !mMetaTileEntity.isEnetInput()) return 0;
        return getEUCapacity() - getStoredEU();
    }

    public double injectEnergyUnits(EnumFacing aDirection, double aAmount) {
        return injectEnergyUnits((byte) aDirection.ordinal(), (int) aAmount, 1) > 0 ? 0 : aAmount;
    }

    @Override
    public boolean acceptsEnergyFrom(IEnergyEmitter aEmitter, EnumFacing aDirection) {
        return inputEnergyFrom((byte) aDirection.ordinal());
    }

    @Override
    public boolean emitsEnergyTo(IEnergyAcceptor aReceiver, EnumFacing aDirection) {
        return outputsEnergyTo((byte) aDirection.ordinal());
    }


    public double getOfferedEnergy() {
        return (canAccessData() && getStoredEU() - mMetaTileEntity.getMinimumStoredEU() >= oOutput) ? Math.max(0, oOutput) : 0;
    }

    public void drawEnergy(double amount) {
        mAverageEUOutput[mAverageEUOutputIndex] += amount;
        decreaseStoredEU((int) amount, true);
    }

    public int addEnergy(int aEnergy) {
        if (!canAccessData()) return 0;
        if (aEnergy > 0)
            increaseStoredEnergyUnits(aEnergy, true);
        else
            decreaseStoredEU(-aEnergy, true);
        return (int) Math.min(Integer.MAX_VALUE, mMetaTileEntity.getEUVar());
    }

    public boolean isAddedToEnergyNet() {
        return false;
    }

    public int demandsEnergy() {
        if (mReleaseEnergy || !canAccessData() || !mMetaTileEntity.isEnetInput()) return 0;
        return getCapacity() - getStored();
    }

    public int getCapacity() {
        return (int) Math.min(Integer.MAX_VALUE, getEUCapacity());
    }

    public int getStored() {
        return (int) Math.min(Integer.MAX_VALUE, Math.min(getStoredEU(), getCapacity()));
    }

    public void setStored(int aEU) {
        if (canAccessData()) setStoredEU(aEU);
    }

    public int getMaxSafeInput() {
        return (int) Math.min(Integer.MAX_VALUE, getInputVoltage());
    }

    public int getMaxEnergyOutput() {
        if (mReleaseEnergy) return Integer.MAX_VALUE;
        return getOutput();
    }

    public int getOutput() {
        return (int) Math.min(Integer.MAX_VALUE, oOutput);
    }


    @Override
    public boolean isInvalidTileEntity() {
        return func_145837_r();
    }

    @Override
    public boolean addStackToSlot(int aIndex, ItemStack aStack) {
        if (GT_Utility.isStackInvalid(aStack)) return true;
        if (aIndex < 0 || aIndex >= func_70302_i_()) return false;
        ItemStack tStack = func_70301_a(aIndex);
        if (GT_Utility.isStackInvalid(tStack)) {
            func_70299_a(aIndex, aStack);
            return true;
        }
        aStack = GT_OreDictUnificator.get(aStack);
        if (GT_Utility.areStacksEqual(tStack, aStack) && tStack.field_77994_a + aStack.field_77994_a <= Math.min(aStack.func_77976_d(), func_70297_j_())) {
            tStack.field_77994_a += aStack.field_77994_a;
            return true;
        }
        return false;
    }

    @Override
    public boolean addStackToSlot(int aIndex, ItemStack aStack, int aAmount) {
        return addStackToSlot(aIndex, GT_Utility.copyAmount(aAmount, aStack));
    }

    @Override
    public byte getColorization() {
        return (byte) (mColor - 1);
    }

    @Override
    public byte setColorization(byte aColor) {
        if (aColor > 15 || aColor < -1) aColor = -1;
        if (canAccessData()) mMetaTileEntity.onColorChangeServer(aColor);
        return mColor = (byte) (aColor + 1);
    }

    @Override
    public float getBlastResistance(byte aSide) {
        return canAccessData() ? Math.max(0, getMetaTileEntity().getExplosionResistance(aSide)) : 10.0F;
    }

    @Override
    public boolean isUniversalEnergyStored(long aEnergyAmount) {
        if (getUniversalEnergyStored() >= aEnergyAmount) return true;
        mHasEnoughEnergy = false;
        return false;
    }

    @Override
    public String[] getInfoData() {
        {
            if (canAccessData()) return getMetaTileEntity().getInfoData();
            return new String[]{};
        }
    }

    @Override
    public void func_70296_d() {
        super.func_70296_d();
        mInventoryChanged = true;
    }

    @Override
    public int getLightOpacity() {
        return mMetaTileEntity == null ? getLightValue() > 0 ? 0 : 255 : mMetaTileEntity.getLightOpacity();
    }

    @Override
    public void addCollisionBoxesToList(World aWorld, int aX, int aY, int aZ, AxisAlignedBB inputAABB, List<AxisAlignedBB> outputAABB, Entity collider) {
        mMetaTileEntity.addCollisionBoxesToList(aWorld, aX, aY, aZ, inputAABB, outputAABB, collider);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World aWorld, int aX, int aY, int aZ) {
        return mMetaTileEntity.getCollisionBoundingBoxFromPool(aWorld, aX, aY, aZ);
    }

    @Override
    public void onEntityCollidedWithBlock(World aWorld, int aX, int aY, int aZ, Entity collider) {
        mMetaTileEntity.onEntityCollidedWithBlock(aWorld, aX, aY, aZ, collider);
    }

    @Override
    public String func_70005_c_() {
        return mMetaTileEntity.func_70005_c_();
    }

    @Override
    public boolean func_145818_k_() {
        return false;
    }

    @Override
    public IFluidTankProperties[] getTankProperties() {
        if(mMetaTileEntity != null) {
            return new IFluidTankProperties[]{
                    new FluidTankProperties(mMetaTileEntity.getFluid(), mMetaTileEntity.getCapacity())
            };
        }
        return new IFluidTankProperties[0];
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        return this.fill(EnumFacing.DOWN, resource, doFill);
    }

    @Nullable
    @Override
    public FluidStack drain(FluidStack resource, boolean doDrain) {
        if(resource != null && resource.isFluidEqual(mMetaTileEntity.getFluid())) {
            return this.drain(resource.amount, doDrain);
        }
        return null;
    }

    @Nullable
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        return this.drain(EnumFacing.DOWN, maxDrain, doDrain);
    }

}