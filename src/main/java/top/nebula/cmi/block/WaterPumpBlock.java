package top.nebula.cmi.block;

import com.simibubi.create.foundation.block.IBE;
import net.minecraft.world.level.block.Blocks;
import top.nebula.cmi.block.entity.ModBlockEntityTypes;
import top.nebula.cmi.block.entity.WaterPumpBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class WaterPumpBlock extends Block implements IBE<WaterPumpBlockEntity> {
	public WaterPumpBlock() {
		super(Properties.copy(Blocks.OAK_PLANKS));
	}

	@Override
	public Class<WaterPumpBlockEntity> getBlockEntityClass() {
		return WaterPumpBlockEntity.class;
	}

	@Override
	public BlockEntityType<? extends WaterPumpBlockEntity> getBlockEntityType() {
		return ModBlockEntityTypes.WATER_PUMP.get();
	}
}