package gregtech.common;

import gregtech.api.GregTech_API;
import gregtech.api.enums.Materials;
import gregtech.api.world.GT_Worldgen;
import gregtech.common.blocks.GT_Block_GeneratedOres;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.Random;

public class GT_Worldgen_GT_Ore_SmallPieces
        extends GT_Worldgen {
    public final short mMinY;
    public final short mMaxY;
    public final short mAmount;
    public final short mMeta;
    public final boolean mOverworld;
    public final boolean mNether;
    public final boolean mEnd;
    public final boolean mMoon;
    public final boolean mMars;
    public final boolean mAsteroid;
    public final String mBiome;

    private BlockPos.MutableBlockPos temp = new BlockPos.MutableBlockPos();

    public GT_Worldgen_GT_Ore_SmallPieces(String aName, boolean aDefault, int aMinY, int aMaxY, int aAmount, boolean aOverworld, boolean aNether, boolean aEnd, boolean aMoon, boolean aMars, boolean aAsteroid, Materials aPrimary) {
        super(aName, GregTech_API.sWorldgenList, aDefault);
        this.mOverworld = GregTech_API.sWorldgenFile.get("worldgen." + this.mWorldGenName, "Overworld", aOverworld);
        this.mNether = GregTech_API.sWorldgenFile.get("worldgen." + this.mWorldGenName, "Nether", aNether);
        this.mEnd = GregTech_API.sWorldgenFile.get("worldgen." + this.mWorldGenName, "TheEnd", aEnd);
        this.mMoon = GregTech_API.sWorldgenFile.get("worldgen." + this.mWorldGenName, "Moon", aMoon);
        this.mMars = GregTech_API.sWorldgenFile.get("worldgen." + this.mWorldGenName, "Mars", aMars);
        this.mAsteroid = GregTech_API.sWorldgenFile.get("worldgen." + this.mWorldGenName, "Asteroid", aAsteroid);
        this.mMinY = ((short) GregTech_API.sWorldgenFile.get("worldgen." + this.mWorldGenName, "MinHeight", aMinY));
        this.mMaxY = ((short) Math.max(this.mMinY + 1, GregTech_API.sWorldgenFile.get("worldgen." + this.mWorldGenName, "MaxHeight", aMaxY)));
        this.mAmount = ((short) Math.max(1, GregTech_API.sWorldgenFile.get("worldgen." + this.mWorldGenName, "Amount", aAmount)));
        this.mMeta = ((short) GregTech_API.sWorldgenFile.get("worldgen." + this.mWorldGenName, "Ore", aPrimary.mMetaItemSubID));
        this.mBiome = GregTech_API.sWorldgenFile.get("worldgen." + this.mWorldGenName, "BiomeName", "None");
    }

    @Override
    public boolean executeWorldgen(World aWorld, Random aRandom, String aBiome, int aDimensionType, int aChunkX, int aChunkZ, IChunkGenerator aChunkGenerator, IChunkProvider aChunkProvider) {
        //if (!this.mBiome.equals("None") && !(this.mBiome.equals(aBiome))) {
        //    return false; //Not the correct biome for ore mix
        //}
        if (!isDimensionAllowed(aWorld, aDimensionType, mNether, mOverworld, mEnd, mMoon, mMars)) {
            return false;
        }
        if (this.mMeta > 0) {
            int i = 0;
            for (int j = Math.max(1, this.mAmount / 2 + aRandom.nextInt(this.mAmount) / 2); i < j; i++) {
                temp.func_181079_c(aChunkX + aRandom.nextInt(16), this.mMinY + aRandom.nextInt(Math.max(1, this.mMaxY - this.mMinY)), aChunkZ + aRandom.nextInt(16));
                GT_Block_GeneratedOres.setOreBlock(aWorld, temp, this.mMeta, true);
            }
        }
        return true;
    }
}
