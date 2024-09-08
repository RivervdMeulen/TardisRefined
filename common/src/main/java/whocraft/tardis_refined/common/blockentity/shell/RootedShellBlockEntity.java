package whocraft.tardis_refined.common.blockentity.shell;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.common.capability.TardisLevelOperator;
import whocraft.tardis_refined.common.dimension.DimensionHandler;
import whocraft.tardis_refined.common.tardis.TardisDesktops;
import whocraft.tardis_refined.common.tardis.themes.DesktopTheme;
import whocraft.tardis_refined.common.util.Platform;
import whocraft.tardis_refined.registry.TRBlockEntityRegistry;

import java.util.UUID;

public class RootedShellBlockEntity extends ShellBaseBlockEntity{
    public RootedShellBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(TRBlockEntityRegistry.ROOT_SHELL.get(), blockPos, blockState);
    }

    private boolean runSetUpOnNextTick = false;

    @Override
    public DesktopTheme getAssociatedTheme() {
        return TardisDesktops.DEFAULT_OVERGROWN_THEME;
    }

    @Override
    public void playDoorCloseSound(boolean closeDoor) {
        //Leave blank
    }

    @Override
    public void playDoorLockedSound(boolean lockDoor) {
        //Leave blank
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, ShellBaseBlockEntity blockEntity) {
        if (runSetUpOnNextTick) {
            this.runSetUpOnNextTick = false;
            setUpTardis(blockState, level, blockPos);
        }
    }

    public void setUpTardisOnNextTick() {
        runSetUpOnNextTick = true;
    }

    /** Generate the dimension and open the Root Shell */
    private void setUpTardis(BlockState blockState, Level level, BlockPos blockPos) {
        if (level instanceof ServerLevel serverLevel) {
            if (this.shouldSetup()) {
                //Create a Level Key with a randomised UUID
                ResourceKey<Level> generatedLevelKey = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(TardisRefined.MODID, UUID.randomUUID().toString()));

                //Create the Level on demand which will create our capability
                ServerLevel interior = DimensionHandler.getOrCreateInterior(serverLevel, generatedLevelKey.location());

                // Set the UUID on the block entity.
                this.setTardisId(generatedLevelKey);

                TardisLevelOperator.get(interior).ifPresent(tardisLevelOperator -> {
                    if (!tardisLevelOperator.hasInitiallyGenerated()) {
                        tardisLevelOperator.setupInitialCave(serverLevel, blockState, blockPos);
                    }
                    //After we setup the data and desktop, open the doors.
                    tardisLevelOperator.setDoorClosed(false);
                    serverLevel.playSound(null, blockPos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1, 1);
                });
            } else {
                ServerLevel tardisLevel = Platform.getServer().getLevel(getTardisId());
                TardisLevelOperator.get(tardisLevel).ifPresent(tardisLevelOperator -> {
                    //Always open the root shell doors when this method is called to ensure that the player isn't softlocked by the early return of InteractionResult that occurs if the player isn't using shears.
                    tardisLevelOperator.setDoorClosed(false);
                });
            }
        }
    }
}
