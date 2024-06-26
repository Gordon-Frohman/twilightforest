package twilightforest.world.layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

import twilightforest.biomes.TFBiomeBase;

public class GenLayerTFBiomeBorders extends GenLayer {

    public GenLayerTFBiomeBorders(long l, GenLayer genlayer) {
        super(l);
        parent = genlayer;
    }

    /**
     * Turn specific biomes into other biomes along their borders
     */
    @Override
    public int[] getInts(int x, int z, int width, int depth) {
        int nx = x - 1;
        int nz = z - 1;
        int nwidth = width + 2;
        int ndepth = depth + 2;
        int[] input = parent.getInts(nx, nz, nwidth, ndepth);
        int[] output = IntCache.getIntCache(width * depth);
        for (int dz = 0; dz < depth; dz++) {
            for (int dx = 0; dx < width; dx++) {
                int right = input[dx + 0 + (dz + 1) * nwidth];
                int left = input[dx + 2 + (dz + 1) * nwidth];
                int up = input[dx + 1 + (dz + 0) * nwidth];
                int down = input[dx + 1 + (dz + 2) * nwidth];
                int center = input[dx + 1 + (dz + 1) * nwidth];
                if (onBorder(TFBiomeBase.tfLake.biomeID, center, right, left, up, down)) {
                    output[dx + dz * width] = TFBiomeBase.fireflyForest.biomeID;
                } else if (onBorder(TFBiomeBase.clearing.biomeID, center, right, left, up, down)) {
                    output[dx + dz * width] = TFBiomeBase.oakSavanna.biomeID;
                } else if (onBorder(TFBiomeBase.deepMushrooms.biomeID, center, right, left, up, down)) {
                    output[dx + dz * width] = TFBiomeBase.mushrooms.biomeID;
                } else if (onBorder(TFBiomeBase.glacier.biomeID, center, right, left, up, down)) {
                    output[dx + dz * width] = TFBiomeBase.tfSnow.biomeID;
                } else {
                    output[dx + dz * width] = center;
                }
            }

        }

        return output;
    }

    /**
     * Returns true if the center biome is the specified biome and any of the surrounding biomes are not
     */
    boolean onBorder(int biome, int center, int right, int left, int up, int down) {

        if (center != biome) {
            return false;
        }

        if (right != biome) {
            return true;
        }
        if (left != biome) {
            return true;
        }
        if (up != biome) {
            return true;
        }
        if (down != biome) {
            return true;
        }

        return false;
    }

}
