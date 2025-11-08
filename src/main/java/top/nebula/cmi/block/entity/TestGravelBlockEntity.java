package top.nebula.cmi.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import top.nebula.cmi.CMI;
import top.nebula.cmi.block.custom.TestGravelBlock;
import top.nebula.cmi.item.ModItems;

public class TestGravelBlockEntity extends BrushableBlockEntity {
	public TestGravelBlockEntity(BlockPos pos, BlockState state) {
		super(pos, state);
	}

	@Override
	public boolean brush(long gameTime, @NotNull Player player, @NotNull Direction direction) {
		if (level == null || level.isClientSide()) {
			return false;
		}

		BlockState state = this.getBlockState();
		int current = state.getValue(TestGravelBlock.DUSTED);

		if (current < 3) {
			level.setBlock(worldPosition, state.setValue(TestGravelBlock.DUSTED, current + 1), 3);
		}

		// 设置战利品表
		if (player.getMainHandItem().is(ModItems.TEST_BRUSH.get())) {
			this.setLootTable(ResourceLocation.fromNamespaceAndPath(CMI.MODID, "archaeology/custom_brush_loot"), gameTime);
		} else {
			this.setLootTable(ResourceLocation.withDefaultNamespace("archaeology/desert_pyramid"), gameTime);
		}
		return super.brush(gameTime, player, direction);
	}
}