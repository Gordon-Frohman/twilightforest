package twilightforest.structures.lichtower;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class ComponentTFTowerRoofPointy extends ComponentTFTowerRoof {

    public ComponentTFTowerRoofPointy() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ComponentTFTowerRoofPointy(int i, ComponentTFTowerWing wing) {
        super(i, wing);

        // same facing, but it doesn't matter
        this.setCoordBaseMode(wing.getCoordBaseMode());

        this.size = wing.size; // assuming only square towers and roofs right now.
        this.height = size;

        // just hang out at the very top of the tower
        makeCapBB(wing);

    }

    /**
     * Makes a pointy roof out of stuff
     */
    @Override
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox sbb) {
        int slabMeta = 2;

        for (int y = 0; y <= height; y++) {
            int min, mid, max;
            int slopeChange = slopeChangeForSize(size);
            if (y < slopeChange) {
                min = y;
                max = size - y - 1;
            } else {
                min = (y + slopeChange) / 2;
                max = size - ((y + slopeChange) / 2) - 1;
            }
            mid = min + ((max - min) / 2);
            for (int x = min; x <= max; x++) {
                for (int z = min; z <= max; z++) {
                    placeBlockAtCurrentPosition(world, Blocks.planks, slabMeta, x, y, z, sbb);
                    // some of these are unnecessary and will just be overwritten by a normal block, but whatevs.
                    if ((x == min && (z == min || z == max)) || (x == max && (z == min || z == max))) {
                        placeBlockAtCurrentPosition(world, Blocks.wooden_slab, slabMeta, x, y + 1, z, sbb);
                    }
                    // mid blocks
                    if (((((x == min || x == max) && z == mid) && x % 2 == 0)
                            || (((z == min || z == max) && x == mid) && z % 2 == 0)) && mid != min + 1) {
                        placeBlockAtCurrentPosition(world, Blocks.wooden_slab, slabMeta, x, y + 1, z, sbb);
                    }
                }
            }
        }
        return true;
    }

    public int slopeChangeForSize(int pSize) {
        if (size > 10) {
            return 3;
        } else if (size > 6) {
            return 2;
        } else {
            return 1;
        }
    }
}
