package twilightforest.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.MapGenCaves;

import twilightforest.biomes.TFBiomeHighlands;
import twilightforest.block.TFBlocks;

public class TFGenCaves extends MapGenCaves {

    /**
     * Generates a larger initial cave node than usual. Called 25% of the time.
     * 
     * @param isHighlands
     */
    protected void generateLargeCaveNode(long caveSeed, int centerX, int centerZ, Block[] blockStorage, double randX,
            double randY, double randZ, boolean isHighlands) {
        this.generateCaveNode(
                caveSeed,
                centerX,
                centerZ,
                blockStorage,
                randX,
                randY,
                randZ,
                1.0F + this.rand.nextFloat() * 6.0F,
                0.0F,
                0.0F,
                -1,
                -1,
                0.5D,
                isHighlands);
    }

    /**
     * Generates a node in the current cave system recursion tree.
     * 
     * @param isHighlands
     */
    protected void generateCaveNode(long caveSeed, int centerX, int centerZ, Block[] blockStorage, double randX,
            double randY, double randZ, float caveSize, float randPI, float angleToGenerate, int loopOne, int loopEnd,
            double yScale, boolean isHighlands) {
        double offsetCenterX = centerX * 16 + 8;
        double offsetCenterZ = centerZ * 16 + 8;
        float var23 = 0.0F;
        float var24 = 0.0F;
        Random caveRNG = new Random(caveSeed);
        Random mossRNG = new Random(caveSeed);

        // if (isHighlands) {
        // //System.out.println("Saying highlands and it's not");
        // isHighlands = false;
        // }

        if (isHighlands && caveSize < 6F) {
            caveSize *= 2.5F;
        }

        if (loopEnd <= 0) {
            int rangeInBlocks = this.range * 16 - 16;
            loopEnd = rangeInBlocks - caveRNG.nextInt(rangeInBlocks / 4);
        }

        boolean shouldStop = false;

        if (loopOne == -1) {
            loopOne = loopEnd / 2;
            shouldStop = true;
        }

        int var27 = caveRNG.nextInt(loopEnd / 2) + loopEnd / 4;

        for (boolean var28 = caveRNG.nextInt(6) == 0; loopOne < loopEnd; ++loopOne) {
            double sizeVar = 1.5D
                    + (double) (MathHelper.sin((float) loopOne * (float) Math.PI / (float) loopEnd) * caveSize * 1.0F);
            double scaledSize = sizeVar * yScale;
            float cosAngle = MathHelper.cos(angleToGenerate);
            float sinAngle = MathHelper.sin(angleToGenerate);
            randX += MathHelper.cos(randPI) * cosAngle;
            randY += sinAngle;
            randZ += MathHelper.sin(randPI) * cosAngle;

            if (var28) {
                angleToGenerate *= 0.92F;
            } else {
                angleToGenerate *= 0.7F;
            }

            angleToGenerate += var24 * 0.1F;
            randPI += var23 * 0.1F;
            var24 *= 0.9F;
            var23 *= 0.75F;
            var24 += (caveRNG.nextFloat() - caveRNG.nextFloat()) * caveRNG.nextFloat() * 2.0F;
            var23 += (caveRNG.nextFloat() - caveRNG.nextFloat()) * caveRNG.nextFloat() * 4.0F;

            if (!shouldStop && loopOne == var27 && caveSize > 1.0F && loopEnd > 0) {
                this.generateCaveNode(
                        caveRNG.nextLong(),
                        centerX,
                        centerZ,
                        blockStorage,
                        randX,
                        randY,
                        randZ,
                        caveRNG.nextFloat() * 0.5F + 0.5F,
                        randPI - ((float) Math.PI / 2F),
                        angleToGenerate / 3.0F,
                        loopOne,
                        loopEnd,
                        1.0D,
                        isHighlands);
                this.generateCaveNode(
                        caveRNG.nextLong(),
                        centerX,
                        centerZ,
                        blockStorage,
                        randX,
                        randY,
                        randZ,
                        caveRNG.nextFloat() * 0.5F + 0.5F,
                        randPI + ((float) Math.PI / 2F),
                        angleToGenerate / 3.0F,
                        loopOne,
                        loopEnd,
                        1.0D,
                        isHighlands);
                return;
            }

            if (shouldStop || caveRNG.nextInt(4) != 0) {
                double distX = randX - offsetCenterX;
                double distZ = randZ - offsetCenterZ;
                double var39 = loopEnd - loopOne;
                double sizeSixteen = caveSize + 2.0F + 16.0F;

                if (distX * distX + distZ * distZ - var39 * var39 > sizeSixteen * sizeSixteen) {
                    return;
                }

                if (randX >= offsetCenterX - 16.0D - sizeVar * 2.0D && randZ >= offsetCenterZ - 16.0D - sizeVar * 2.0D
                        && randX <= offsetCenterX + 16.0D + sizeVar * 2.0D
                        && randZ <= offsetCenterZ + 16.0D + sizeVar * 2.0D) {
                    int minX = MathHelper.floor_double(randX - sizeVar) - centerX * 16 - 1;
                    int maxX = MathHelper.floor_double(randX + sizeVar) - centerX * 16 + 1;
                    int maxY = MathHelper.floor_double(randY - scaledSize) - 1;
                    int minY = MathHelper.floor_double(randY + scaledSize) + 1;
                    int minZ = MathHelper.floor_double(randZ - sizeVar) - centerZ * 16 - 1;
                    int maxZ = MathHelper.floor_double(randZ + sizeVar) - centerZ * 16 + 1;

                    if (minX < 0) {
                        minX = 0;
                    }

                    if (maxX > 16) {
                        maxX = 16;
                    }

                    if (maxY < 1) {
                        maxY = 1;
                    }

                    if (minY > 120) {
                        minY = 120;
                    }

                    if (minZ < 0) {
                        minZ = 0;
                    }

                    if (maxZ > 16) {
                        maxZ = 16;
                    }

                    boolean hasHitWater = false;
                    int genX;
                    int genZ;

                    for (genX = minX; !hasHitWater && genX < maxX; ++genX) {
                        for (genZ = minZ; !hasHitWater && genZ < maxZ; ++genZ) {
                            for (int genY = minY + 1; !hasHitWater && genY >= maxY - 1; --genY) {
                                int waterIndex = (genX * 16 + genZ) * 128 + genY;

                                if (genY >= 0 && genY < 128) {
                                    if (isOceanBlock(blockStorage, waterIndex, genX, genY, genZ, centerX, centerZ)) {
                                        hasHitWater = true;
                                    }

                                    if (genY != maxY - 1 && genX != minX
                                            && genX != maxX - 1
                                            && genZ != minZ
                                            && genZ != maxZ - 1) {
                                        genY = maxY;
                                    }
                                }
                            }
                        }
                    }

                    if (!hasHitWater) {
                        for (genX = minX; genX < maxX; ++genX) {
                            double var59 = ((double) (genX + centerX * 16) + 0.5D - randX) / sizeVar;

                            for (genZ = minZ; genZ < maxZ; ++genZ) {
                                double var46 = ((double) (genZ + centerZ * 16) + 0.5D - randZ) / sizeVar;
                                int caveIndex = (genX * 16 + genZ) * TFWorld.CHUNKHEIGHT + minY;
                                boolean hitGrass = false;

                                if (var59 * var59 + var46 * var46 < 1.0D) {
                                    for (int caveY = minY - 1; caveY >= maxY; --caveY) {
                                        double var51 = ((double) caveY + 0.5D - randY) / scaledSize;

                                        if (var51 > -0.7D && var59 * var59 + var51 * var51 + var46 * var46 < 20.0D) {
                                            Block blockAt = blockStorage[caveIndex];

                                            if (blockAt == Blocks.grass) {
                                                hitGrass = true;
                                            }

                                            if (blockAt != null
                                                    && (blockAt == Blocks.stone || blockAt == TFBlocks.trollSteinn
                                                            || blockAt.getMaterial() == Material.ground
                                                            || blockAt.getMaterial() == Material.grass)) {
                                                if (var59 * var59 + var51 * var51 + var46 * var46 < 0.85D) {
                                                    blockStorage[caveIndex] = caveY < 10 ? Blocks.water : Blocks.air;
                                                } else {
                                                    Block localBlock = isHighlands
                                                            ? (mossRNG.nextInt(6) == 0 ? TFBlocks.trollSteinn
                                                                    : Blocks.stone)
                                                            : Blocks.dirt;
                                                    blockStorage[caveIndex] = hitGrass ? Blocks.grass : localBlock;
                                                    hitGrass = false;
                                                }

                                                if (hitGrass && blockStorage[caveIndex - 1] == Blocks.dirt) {
                                                    blockStorage[caveIndex - 1] = this.worldObj.getBiomeGenForCoords(
                                                            genX + centerX * 16,
                                                            genZ + centerZ * 16).topBlock;
                                                }
                                            }
                                        }

                                        --caveIndex;
                                    }
                                }
                            }
                        }

                        if (shouldStop) {
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Recursively called by generate() (generate) and optionally by itself.
     */
    protected void func_151538_a(World par1World, int genX, int genZ, int centerX, int centerZ, Block[] blockStorage) {
        int numberOfCaves = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(40) + 1) + 1);
        boolean isHighlands = par1World.getBiomeGenForCoords(genX * 16, genZ * 16) instanceof TFBiomeHighlands;

        if (this.rand.nextInt(15) != 0) {
            numberOfCaves = 0;
        }

        for (int i = 0; i < numberOfCaves; ++i) {
            double randX = genX * 16 + this.rand.nextInt(16);
            double randY = this.rand.nextInt(this.rand.nextInt(120) + 8);
            double randZ = genZ * 16 + this.rand.nextInt(16);
            int numberOfNormalNodes = 1;

            if (this.rand.nextInt(4) == 0) {
                this.generateLargeCaveNode(
                        this.rand.nextLong(),
                        centerX,
                        centerZ,
                        blockStorage,
                        randX,
                        randY,
                        randZ,
                        isHighlands);
                numberOfNormalNodes += this.rand.nextInt(4);
            }

            for (int j = 0; j < numberOfNormalNodes; ++j) {
                float randPi = this.rand.nextFloat() * (float) Math.PI * 2.0F;
                float randEight = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
                float caveSize = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();

                if (this.rand.nextInt(10) == 0) {
                    caveSize *= this.rand.nextFloat() * this.rand.nextFloat() * 3.0F + 1.0F;
                }

                this.generateCaveNode(
                        this.rand.nextLong(),
                        centerX,
                        centerZ,
                        blockStorage,
                        randX,
                        randY,
                        randZ,
                        caveSize,
                        randPi,
                        randEight,
                        0,
                        0,
                        1.0D,
                        isHighlands);
            }
        }
    }

    protected boolean isOceanBlock(Block[] data, int index, int x, int y, int z, int chunkX, int chunkZ) {
        return data[index] == Blocks.flowing_water || data[index] == Blocks.water;
    }
}
