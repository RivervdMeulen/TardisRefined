package whocraft.tardis_refined.mixin.forge;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.client.extensions.IBlockEntityRendererExtension;
import org.spongepowered.asm.mixin.Mixin;
import whocraft.tardis_refined.common.blockentity.console.GlobalConsoleBlockEntity;
import whocraft.tardis_refined.common.blockentity.device.ConsoleConfigurationBlockEntity;
import whocraft.tardis_refined.common.blockentity.door.BulkHeadDoorBlockEntity;
import whocraft.tardis_refined.common.blockentity.door.RootShellDoorBlockEntity;
import whocraft.tardis_refined.common.blockentity.life.EyeBlockEntity;
import whocraft.tardis_refined.common.blockentity.shell.GlobalShellBlockEntity;


@Mixin({EyeBlockEntity.class, ConsoleConfigurationBlockEntity.class, GlobalShellBlockEntity.class, RootShellDoorBlockEntity.class, GlobalShellBlockEntity.class, GlobalConsoleBlockEntity.class, BulkHeadDoorBlockEntity.class})
public class BlockEntityRendererMixin extends BlockEntity implements IBlockEntityRendererExtension {

    public BlockEntityRendererMixin(BlockEntityType<?> arg, BlockPos arg2, BlockState arg3) {
        super(arg, arg2, arg3);
    }

    @Override
    public AABB getRenderBoundingBox(BlockEntity blockEntity) {
        return INFINITE_EXTENT_AABB;
    }

}
