package gregtech.api.objects;

import gregtech.api.util.GT_Utility;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GT_ItemStack {
    public final Item mItem;
    public final byte mStackSize;
    public final short mMetaData;

    public GT_ItemStack(Item aItem, long aStackSize, long aMetaData) {
        mItem = aItem;
        mStackSize = (byte) aStackSize;
        mMetaData = (short) aMetaData;
    }

    public GT_ItemStack(ItemStack aStack) {
        this(aStack == null ? null : aStack.func_77973_b(), aStack == null ? 0 : aStack.field_77994_a, aStack == null ? 0 : Items.field_151008_G.getDamage(aStack));
    }

    public ItemStack toStack() {
        if (mItem == null) return null;
        return new ItemStack(mItem, 1, mMetaData);
    }

    public boolean isStackEqual(ItemStack aStack) {
        return GT_Utility.areStacksEqual(toStack(), aStack);
    }

    public boolean isStackEqual(GT_ItemStack aStack) {
        return GT_Utility.areStacksEqual(toStack(), aStack.toStack());
    }

    @Override
    public boolean equals(Object aStack) {
        return aStack == this || aStack instanceof GT_ItemStack && ((GT_ItemStack) aStack).mItem == mItem && ((GT_ItemStack) aStack).mMetaData == mMetaData;
    }

    @Override
    public int hashCode() {
        return GT_Utility.stackToIntHash(toStack());
    }
}
