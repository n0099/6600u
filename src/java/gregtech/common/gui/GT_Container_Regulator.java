package gregtech.common.gui;

import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.IContainerListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import gregtech.api.gui.GT_ContainerMetaTile_Machine;
import gregtech.api.gui.GT_Slot_Holo;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.util.GT_Utility;
import gregtech.common.tileentities.automation.GT_MetaTileEntity_Regulator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GT_Container_Regulator
        extends GT_ContainerMetaTile_Machine {
    public int[] mTargetSlots = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    public GT_Container_Regulator(InventoryPlayer aInventoryPlayer, IGregTechTileEntity aTileEntity) {
        super(aInventoryPlayer, aTileEntity);
    }

    @Override
    public void addSlots(InventoryPlayer aInventoryPlayer) {
        this.mTargetSlots = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};

        func_75146_a(new Slot(this.mTileEntity, 0, 8, 6));
        func_75146_a(new Slot(this.mTileEntity, 1, 26, 6));
        func_75146_a(new Slot(this.mTileEntity, 2, 44, 6));
        func_75146_a(new Slot(this.mTileEntity, 3, 8, 24));
        func_75146_a(new Slot(this.mTileEntity, 4, 26, 24));
        func_75146_a(new Slot(this.mTileEntity, 5, 44, 24));
        func_75146_a(new Slot(this.mTileEntity, 6, 8, 42));
        func_75146_a(new Slot(this.mTileEntity, 7, 26, 42));
        func_75146_a(new Slot(this.mTileEntity, 8, 44, 42));

        func_75146_a(new GT_Slot_Holo(this.mTileEntity, 9, 64, 7, false, true, 1));
        func_75146_a(new GT_Slot_Holo(this.mTileEntity, 10, 81, 7, false, true, 1));
        func_75146_a(new GT_Slot_Holo(this.mTileEntity, 11, 98, 7, false, true, 1));
        func_75146_a(new GT_Slot_Holo(this.mTileEntity, 12, 64, 24, false, true, 1));
        func_75146_a(new GT_Slot_Holo(this.mTileEntity, 13, 81, 24, false, true, 1));
        func_75146_a(new GT_Slot_Holo(this.mTileEntity, 14, 98, 24, false, true, 1));
        func_75146_a(new GT_Slot_Holo(this.mTileEntity, 15, 64, 41, false, true, 1));
        func_75146_a(new GT_Slot_Holo(this.mTileEntity, 16, 81, 41, false, true, 1));
        func_75146_a(new GT_Slot_Holo(this.mTileEntity, 17, 98, 41, false, true, 1));

        func_75146_a(new GT_Slot_Holo(this.mTileEntity, 18, 119, 7, false, true, 1));
        func_75146_a(new GT_Slot_Holo(this.mTileEntity, 18, 136, 7, false, true, 1));
        func_75146_a(new GT_Slot_Holo(this.mTileEntity, 18, 153, 7, false, true, 1));
        func_75146_a(new GT_Slot_Holo(this.mTileEntity, 18, 119, 24, false, true, 1));
        func_75146_a(new GT_Slot_Holo(this.mTileEntity, 18, 136, 24, false, true, 1));
        func_75146_a(new GT_Slot_Holo(this.mTileEntity, 18, 153, 24, false, true, 1));
        func_75146_a(new GT_Slot_Holo(this.mTileEntity, 18, 119, 41, false, true, 1));
        func_75146_a(new GT_Slot_Holo(this.mTileEntity, 18, 136, 41, false, true, 1));
        func_75146_a(new GT_Slot_Holo(this.mTileEntity, 18, 153, 41, false, true, 1));

        func_75146_a(new GT_Slot_Holo(this.mTileEntity, 18, 8, 63, false, true, 1));
    }

    @Override
    public ItemStack func_184996_a(int aSlotIndex, int aMouseclick, ClickType aShifthold, EntityPlayer aPlayer) {
        if (aSlotIndex < 9) {
            return super.func_184996_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
        }
        Slot tSlot = this.field_75151_b.get(aSlotIndex);
        if (tSlot != null) {
            if (this.mTileEntity.getMetaTileEntity() == null) {
                return null;
            }
            if (aSlotIndex == 27) {
                ((GT_MetaTileEntity_Regulator) this.mTileEntity.getMetaTileEntity()).bOutput = (!((GT_MetaTileEntity_Regulator) this.mTileEntity.getMetaTileEntity()).bOutput);
                if (((GT_MetaTileEntity_Regulator) this.mTileEntity.getMetaTileEntity()).bOutput) {
                    GT_Utility.sendChatToPlayer(aPlayer, "Emit Energy to Outputside");
                } else {
                    GT_Utility.sendChatToPlayer(aPlayer, "Don't emit Energy");
                }
                return null;
            }
            if ((aSlotIndex < 18)) {
                ItemStack tStack = aPlayer.field_71071_by.func_70445_o();
                if (tStack != null) {
                    tSlot.func_75215_d(GT_Utility.copy(tStack));
                } else if (tSlot.func_75211_c() != null) {
                    if (aMouseclick == 0) {
                        tSlot.func_75211_c().field_77994_a -= (aShifthold == ClickType.PICKUP ? 8 : 1);
                        if (tSlot.func_75211_c().field_77994_a <= 0) {
                            tSlot.func_75215_d(null);
                        }
                    } else {
                        tSlot.func_75211_c().field_77994_a += (aShifthold == ClickType.PICKUP ? 8 : 1);
                        if (tSlot.func_75211_c().field_77994_a > tSlot.func_75211_c().func_77976_d()) {
                            tSlot.func_75211_c().field_77994_a = tSlot.func_75211_c().func_77976_d();
                        }
                    }
                }
                return null;
            }
            if ((aSlotIndex < 27)) {
                ((GT_MetaTileEntity_Regulator) this.mTileEntity.getMetaTileEntity()).mTargetSlots[(aSlotIndex - 18)] = Math.min(99, Math.max(0, ((GT_MetaTileEntity_Regulator) this.mTileEntity.getMetaTileEntity()).mTargetSlots[(aSlotIndex - 18)] + (aMouseclick == 0 ? -1 : 1) * (aShifthold == ClickType.QUICK_CRAFT ? 1 : 16)));
                return null;
            }
        }
        return super.func_184996_a(aSlotIndex, aMouseclick, aShifthold, aPlayer);
    }

    @Override
    public void func_75142_b() {
        super.func_75142_b();
        if ((this.mTileEntity.isClientSide()) || (this.mTileEntity.getMetaTileEntity() == null)) {
            return;
        }
        this.mTargetSlots = new int[9];
        for (int i = 0; i < 9; i++) {
            this.mTargetSlots[i] = ((GT_MetaTileEntity_Regulator) this.mTileEntity.getMetaTileEntity()).mTargetSlots[i];
        }
        for (IContainerListener var1 : this.field_75149_d) {
            for (int i = 0; i < 9; i++) {
                var1.func_71112_a(this, 100 + i, this.mTargetSlots[i]);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void func_75137_b(int par1, int par2) {
        super.func_75137_b(par1, par2);
        switch (par1) {
            case 100:
                this.mTargetSlots[0] = par2;
                break;
            case 101:
                this.mTargetSlots[1] = par2;
                break;
            case 102:
                this.mTargetSlots[2] = par2;
                break;
            case 103:
                this.mTargetSlots[3] = par2;
                break;
            case 104:
                this.mTargetSlots[4] = par2;
                break;
            case 105:
                this.mTargetSlots[5] = par2;
                break;
            case 106:
                this.mTargetSlots[6] = par2;
                break;
            case 107:
                this.mTargetSlots[7] = par2;
                break;
            case 108:
                this.mTargetSlots[8] = par2;
        }
    }

    public int getSlotCount() {
        return 9;
    }

    public int getShiftClickSlotCount() {
        return 9;
    }
}
