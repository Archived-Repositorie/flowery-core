package io.github.justfoxx.structure

import net.minecraft.structure.*
import net.minecraft.structure.pool.StructurePoolBasedGenerator
import net.minecraft.structure.pool.StructurePoolElement
import net.minecraft.util.BlockRotation
import net.minecraft.util.math.BlockBox
import net.minecraft.util.math.BlockPos
import net.minecraft.world.Heightmap
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.feature.StructureFeature
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig
import java.util.*

class LandStructure : StructureFeature<StructurePoolFeatureConfig>(StructurePoolFeatureConfig.CODEC,
    StructureGeneratorFactory { context: StructureGeneratorFactory.Context<StructurePoolFeatureConfig> ->
        createPiecesGenerator(
            context
        )
    }, PostPlacementProcessor.EMPTY
) {
    override fun getGenerationStep(): GenerationStep.Feature {
        return GenerationStep.Feature.SURFACE_STRUCTURES
    }

    companion object {
        private fun isFeatureChunk(context: StructureGeneratorFactory.Context<StructurePoolFeatureConfig>): Boolean {
            // Grabs the chunk position we are at
            val chunkpos = context.chunkPos()
            val centerOfChunk = BlockPos(chunkpos.x * 16, 0, chunkpos.z * 16)
            val landHeight = context.chunkGenerator()
                .getHeightInGround(centerOfChunk.x, centerOfChunk.z, Heightmap.Type.WORLD_SURFACE_WG, context.world())
            val columnOfBlocks =
                context.chunkGenerator().getColumnSample(centerOfChunk.x, centerOfChunk.z, context.world())
            val topBlock = columnOfBlocks.getState(centerOfChunk.up(landHeight).y)


            // Checks to make sure our structure does not spawn within 10 chunks of an Ocean Monument
            // to demonstrate how this method is good for checking extra conditions for spawning
            return (!context.chunkGenerator()
                .method_41053(StructureSetKeys.OCEAN_MONUMENTS, context.seed(), chunkpos.x, chunkpos.z, 10)
                    &&
                    topBlock.fluidState.isEmpty)
        }

        fun createPiecesGenerator(context: StructureGeneratorFactory.Context<StructurePoolFeatureConfig>): Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> {

            // Check if the spot is valid for our structure. This is just as another method for cleanness.
            // Returning an empty optional tells the game to skip this spot as it will not generate the structure.
            if (!isFeatureChunk(context)) {
                return Optional.empty()
            }

            // Turns the chunk coordinates into actual coordinates we can use. (Gets center of that chunk)
            var blockpos = context.chunkPos().getCenterAtY(0)

            // Find the top Y value of the land and then offset our structure to 60 blocks above that.
            // WORLD_SURFACE_WG will stop at top water so we don't accidentally put our structure into the ocean if it is a super deep ocean.
            val topLandY = context.chunkGenerator().getHeightOnGround(
                blockpos.x,
                blockpos.z,
                Heightmap.Type.WORLD_SURFACE_WG,
                context.world()
            )
            blockpos = blockpos.up(topLandY)

            // Return the pieces generator that is now set up so that the game runs it when it needs to create the layout of structure pieces.
            return StructurePoolBasedGenerator.generate(
                context,
                StructurePoolBasedGenerator.PieceFactory { structureManager: StructureManager, poolElement: StructurePoolElement, pos: BlockPos, groundLevelDelta: Int, rotation: BlockRotation, boundingBox: BlockBox ->
                    PoolStructurePiece(
                        structureManager,
                        poolElement,
                        pos,
                        groundLevelDelta,
                        rotation,
                        boundingBox
                    )
                },  // Needed in order to create a list of jigsaw pieces when making the structure's layout.
                blockpos,  // Position of the structure. Y value is ignored if last parameter is set to true.
                false,  // Special boundary adjustments for villages. It's... hard to explain. Keep this false and make your pieces not be partially intersecting.
                // Either not intersecting or fully contained will make children pieces spawn just fine. It's easier that way.
                false // Place at heightmap (top land). Set this to false for structure to be place at the passed in blockpos's Y value instead.
                // Definitely keep this false when placing structures in the nether as otherwise, heightmap placing will put the structure on the Bedrock roof.
            )
        }
    }
}