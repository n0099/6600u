package gregtech.common;

import gregtech.api.world.GT_Worldgen_Constants;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class GT_MinableOreGenerator extends WorldGenerator {
    private Block minableBlockId;
    private Block mBlock;
    private int minableBlockMeta = 0;
    private int numberOfBlocks;
    private boolean allowVoid = false;

    public GT_MinableOreGenerator(Block par1, int par2) {
        this.minableBlockId = par1;
        this.numberOfBlocks = par2;
    }

    public GT_MinableOreGenerator(Block id, int meta, int number, boolean aAllowVoid, Block aBlock) {
        this(id, number);
        this.minableBlockMeta = meta;
        this.allowVoid = aAllowVoid;
        this.mBlock = aBlock;
    }

    public boolean func_180709_b(World par1World, Random par2Random, BlockPos pos) {
        float math_pi = 3.141593F;
        float var6 = par2Random.nextFloat() * math_pi;
        int par3 = pos.func_177958_n(), par4 = pos.func_177956_o(), par5 = pos.func_177952_p();
        float var1s = this.numberOfBlocks / 8.0F;
        int var4s = par4 - 2;int var5s = par3 + 8;int var6s = par5 + 8;
        float var7s = this.numberOfBlocks / 16.0F;
        float var8s = math_pi / this.numberOfBlocks;
        float mh_s_0 = MathHelper.func_76126_a(var6) * var1s;float mh_c_0 = MathHelper.func_76134_b(var6) * var1s;
        float var7 = var5s + mh_s_0;
        float var11 = var6s + mh_c_0;
        int var15r = par2Random.nextInt(3);int var17r = par2Random.nextInt(3);
        int mh_n_4=var17r - var15r;
        int var15 = var4s + var15r;
        float mh_n_0 = -2*mh_s_0;float mh_n_1 = -2*mh_c_0;
        for (int var19 = 0; var19 <= this.numberOfBlocks; var19++) {
            float mh_n_2 = var19 / this.numberOfBlocks;
            float var20 = var7 + mh_n_0 * mh_n_2;
            float var22 = var15 + mh_n_4 * mh_n_2;
            float var24 = var11 + mh_n_1 * mh_n_2;
            float var26 = par2Random.nextFloat() * var7s;
            float var1c = var19 * var8s;
            float var28 = ((MathHelper.func_76126_a(var1c) + 1.0F) * var26 + 1.0F) / 2.0F;
            int var32 = MathHelper.func_76141_d(var20 - var28);
            int var33 = MathHelper.func_76141_d(var22 - var28);
            int var34 = MathHelper.func_76141_d(var24 - var28);
            int var35 = MathHelper.func_76141_d(var20 + var28);
            int var36 = MathHelper.func_76141_d(var22 + var28);
            int var37 = MathHelper.func_76141_d(var24 + var28);
            for (int var38 = var32; var38 <= var35; var38++) {
                float var39 = (var38 + 0.5F - var20) / (var28);
                float var5c = var39 * var39;
                if (var5c < 1.0F) {
                    for (int var41 = var33; var41 <= var36; var41++) {
                        float var42 = (var41 + 0.5F - var22) / (var28);
                        float var7c = var5c + var42 * var42;
                        if (var7c < 1.0F) {
                            for (int var44 = var34; var44 <= var37; var44++) {
                                float var45 = (var44 + 0.5F - var24) / (var28);
                                BlockPos block = new BlockPos(var38, var41, var44);
                                IBlockState blockState = par1World.func_180495_p(block);
                                if ((var39 * var39 + var42 * var42 + var45 * var45 < 1.0D) && ((this.allowVoid && par1World.func_175623_d(block)) ||
                                        (block != null && (blockState.func_177230_c().isReplaceableOreGen(blockState, par1World, pos, GT_Worldgen_Constants.ANY))))) {
                                    par1World.func_175656_a(block, this.minableBlockId.func_176203_a(minableBlockMeta));
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
