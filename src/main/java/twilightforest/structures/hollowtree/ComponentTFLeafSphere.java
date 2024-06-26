package twilightforest.structures.hollowtree;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

import twilightforest.block.TFBlocks;
import twilightforest.structures.StructureTFComponent;

/**
 * A blob of leaves used to make trees
 * 
 * @author Ben
 *
 */
public class ComponentTFLeafSphere extends StructureTFComponent {

    int radius; // radius

    public ComponentTFLeafSphere() {
        super();
    }

    /**
     * Make a blob of leaves
     * 
     * @param index
     * @param x
     * @param y
     * @param z
     * @param radius
     */
    protected ComponentTFLeafSphere(int index, int x, int y, int z, int radius) {
        super(index);

        this.setCoordBaseMode(0);

        boundingBox = new StructureBoundingBox(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius);
        this.radius = radius;
    }

    /**
     * Save to NBT
     */
    @Override
    protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
        super.func_143012_a(par1NBTTagCompound);

        par1NBTTagCompound.setInteger("leafRadius", this.radius);

    }

    /**
     * Load from NBT
     */
    @Override
    protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
        super.func_143011_b(par1NBTTagCompound);

        this.radius = par1NBTTagCompound.getInteger("leafRadius");
    }

    /**
     * Draw a giant blob of whatevs (okay, it's going to be leaves).
     */
    @Override
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox sbb) {

        int sx = radius;
        int sy = radius;
        int sz = radius;

        // then trace out a quadrant
        for (byte dx = 0; dx <= radius; dx++) {
            for (byte dy = 0; dy <= radius; dy++) {
                for (byte dz = 0; dz <= radius; dz++) {
                    // determine how far we are from the center.
                    byte dist = 0;

                    if (dx >= dy && dx >= dz) {
                        dist = (byte) (dx + (byte) ((Math.max(dy, dz) * 0.5) + (Math.min(dy, dz) * 0.25)));
                    } else if (dy >= dx && dy >= dz) {
                        dist = (byte) (dy + (byte) ((Math.max(dx, dz) * 0.5) + (Math.min(dx, dz) * 0.25)));
                    } else {
                        dist = (byte) (dz + (byte) ((Math.max(dx, dy) * 0.5) + (Math.min(dx, dy) * 0.25)));
                    }

                    // if we're inside the blob, fill it
                    if (dist <= radius) {
                        // do eight at a time for easiness!
                        placeBlockIfEmpty(world, TFBlocks.leaves, 0, sx + dx, sy + dy, sz + dz, sbb);
                        placeBlockIfEmpty(world, TFBlocks.leaves, 0, sx + dx, sy + dy, sz - dz, sbb);
                        placeBlockIfEmpty(world, TFBlocks.leaves, 0, sx - dx, sy + dy, sz + dz, sbb);
                        placeBlockIfEmpty(world, TFBlocks.leaves, 0, sx - dx, sy + dy, sz - dz, sbb);
                        placeBlockIfEmpty(world, TFBlocks.leaves, 0, sx + dx, sy - dy, sz + dz, sbb);
                        placeBlockIfEmpty(world, TFBlocks.leaves, 0, sx + dx, sy - dy, sz - dz, sbb);
                        placeBlockIfEmpty(world, TFBlocks.leaves, 0, sx - dx, sy - dy, sz + dz, sbb);
                        placeBlockIfEmpty(world, TFBlocks.leaves, 0, sx - dx, sy - dy, sz - dz, sbb);

                    }
                }
            }
        }

        return true;
    }

    /**
     * Puts a block only if the block that's there is air.
     * 
     * TODO: This could be more efficient by combining the duplicate logic of the getBlockAtCurrentPosition and
     * placeBlockAtCurrentPosition functions.
     */
    protected void placeBlockIfEmpty(World world, Block blockID, int meta, int x, int y, int z,
            StructureBoundingBox sbb) {
        if (getBlockAtCurrentPosition(world, x, y, z, sbb) == Blocks.air) {
            placeBlockAtCurrentPosition(world, blockID, meta, x, y, z, sbb);
        }
    }

}
