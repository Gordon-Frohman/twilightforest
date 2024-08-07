package twilightforest.structures.courtyard;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraftforge.common.util.ForgeDirection;

import twilightforest.block.BlockTFNagastone2.Yaw;
import twilightforest.structures.StructureTFComponent;

public class ComponentTFNagaCourtyardRotatedAbstract extends StructureTFComponent {

    protected int horizontalColumnsOrient4;
    protected int horizontalColumnsOrient8;

    protected int rotatedStairs0;
    protected int rotatedStairs1;
    protected int rotatedStairs2;
    protected int rotatedStairs3;
    protected int rotatedStairs4;
    protected int rotatedStairs5;
    protected int rotatedStairs6;
    protected int rotatedStairs7;

    protected ForgeDirection EtchedNagastoneNorth = ForgeDirection.NORTH;
    protected ForgeDirection EtchedNagastoneSouth = ForgeDirection.SOUTH;
    protected ForgeDirection EtchedNagastoneWest = ForgeDirection.WEST;
    protected ForgeDirection EtchedNagastoneEast = ForgeDirection.EAST;

    protected Yaw NagastoneNorth = Yaw.NORTH;
    protected Yaw NagastoneSouth = Yaw.SOUTH;
    protected Yaw NagastoneWest = Yaw.WEST;
    protected Yaw NagastoneEast = Yaw.EAST;

    public ComponentTFNagaCourtyardRotatedAbstract() {
        super();
    }

    public ComponentTFNagaCourtyardRotatedAbstract(int i, int x, int y, int z, int rotation) {
        super(i);
        this.coordBaseMode = rotation;

        this.initRotation();
    }

    /**
     * Initialize all of our fancy rotation object thingys based on coordBaseMode.
     */
    private void initRotation() {
        switch (coordBaseMode) {
            default:
            case 0:
                horizontalColumnsOrient4 = 8;
                horizontalColumnsOrient8 = 4;

                rotatedStairs0 = 3;
                rotatedStairs1 = 2;
                rotatedStairs2 = 0;
                rotatedStairs3 = 1;
                rotatedStairs4 = 7;
                rotatedStairs5 = 6;
                rotatedStairs6 = 4;
                rotatedStairs7 = 5;

                EtchedNagastoneNorth = ForgeDirection.WEST;
                EtchedNagastoneSouth = ForgeDirection.EAST;
                EtchedNagastoneWest = ForgeDirection.SOUTH;
                EtchedNagastoneEast = ForgeDirection.NORTH;

                NagastoneNorth = Yaw.WEST;
                NagastoneSouth = Yaw.EAST;
                NagastoneWest = Yaw.SOUTH;
                NagastoneEast = Yaw.NORTH;
                break;
            case 1:
                horizontalColumnsOrient4 = 4;
                horizontalColumnsOrient8 = 8;

                rotatedStairs0 = 0;
                rotatedStairs1 = 1;
                rotatedStairs2 = 2;
                rotatedStairs3 = 3;
                rotatedStairs4 = 4;
                rotatedStairs5 = 5;
                rotatedStairs6 = 6;
                rotatedStairs7 = 7;

                EtchedNagastoneNorth = ForgeDirection.SOUTH;
                EtchedNagastoneSouth = ForgeDirection.NORTH;
                EtchedNagastoneWest = ForgeDirection.EAST;
                EtchedNagastoneEast = ForgeDirection.WEST;

                NagastoneNorth = Yaw.NORTH;
                NagastoneSouth = Yaw.SOUTH;
                NagastoneWest = Yaw.WEST;
                NagastoneEast = Yaw.EAST;
                break;
            case 2:
                horizontalColumnsOrient4 = 8;
                horizontalColumnsOrient8 = 4;

                rotatedStairs0 = 2;
                rotatedStairs1 = 3;
                rotatedStairs2 = 1;
                rotatedStairs3 = 0;
                rotatedStairs4 = 6;
                rotatedStairs5 = 7;
                rotatedStairs6 = 5;
                rotatedStairs7 = 4;

                EtchedNagastoneNorth = ForgeDirection.EAST;
                EtchedNagastoneSouth = ForgeDirection.WEST;
                EtchedNagastoneWest = ForgeDirection.NORTH;
                EtchedNagastoneEast = ForgeDirection.SOUTH;

                NagastoneNorth = Yaw.EAST;
                NagastoneSouth = Yaw.WEST;
                NagastoneWest = Yaw.NORTH;
                NagastoneEast = Yaw.SOUTH;
                break;
            case 3:
                horizontalColumnsOrient4 = 4;
                horizontalColumnsOrient8 = 8;

                rotatedStairs0 = 1;
                rotatedStairs1 = 0;
                rotatedStairs2 = 3;
                rotatedStairs3 = 2;
                rotatedStairs4 = 5;
                rotatedStairs5 = 4;
                rotatedStairs6 = 7;
                rotatedStairs7 = 6;

                EtchedNagastoneNorth = ForgeDirection.NORTH;
                EtchedNagastoneSouth = ForgeDirection.SOUTH;
                EtchedNagastoneWest = ForgeDirection.WEST;
                EtchedNagastoneEast = ForgeDirection.EAST;

                NagastoneNorth = Yaw.SOUTH;
                NagastoneSouth = Yaw.NORTH;
                NagastoneWest = Yaw.EAST;
                NagastoneEast = Yaw.WEST;
                break;
        }
    }

    @Override
    public boolean addComponentParts(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Load from NBT
     */
    @Override
    protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
        super.func_143011_b(par1NBTTagCompound);
        // We should be good to go as coordBaseMode is loaded by
        // net.minecraft.world.gen.structure.StructureComponent
        // #func_143009_a(World, NBTTagCompound) (the caller of func_143011_b)
        this.initRotation();
    }
}
