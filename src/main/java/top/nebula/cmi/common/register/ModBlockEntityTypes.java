package top.nebula.cmi.common.register;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import top.nebula.cmi.CMI;
import top.nebula.cmi.common.block.entity.*;

import java.util.function.Supplier;

public class ModBlockEntityTypes {
	private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES;
	public static final Supplier<BlockEntityType<TestGravelBlockEntity>> TEST_GRAVEL;
	public static final Supplier<BlockEntityType<MarsGeothermalVentBlockEntity>> MARS_GEO;
	public static final Supplier<BlockEntityType<MercuryGeothermalVentBlockEntity>> MERCURY_GEO;
	public static final Supplier<BlockEntityType<WaterPumpBlockEntity>> WATER_PUMP;

	static {
		BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CMI.MODID);

		TEST_GRAVEL = register("test_gravel", TestGravelBlockEntity::new, ModBlocks.TEST_GRAVEL);

		MARS_GEO = register("mars_geothermal_vent", MarsGeothermalVentBlockEntity::new, ModBlocks.MARS_GEO);
		MERCURY_GEO = register("mercury_geothermal_vent", MercuryGeothermalVentBlockEntity::new, ModBlocks.MERCURY_GEO);

		WATER_PUMP = register("water_pump", WaterPumpBlockEntity::new, ModBlocks.WATER_PUMP);
	}

	/**
	 * 注册方块实体类型
	 *
	 * @param id     注册id
	 * @param entity 方块实体类
	 * @param block  方块
	 */
	private static <T extends BlockEntity> Supplier<BlockEntityType<T>> register(
			String id,
			BlockEntityType.BlockEntitySupplier<T> entity,
			Supplier<? extends Block> block
	) {
		return BLOCK_ENTITIES.register(id, () -> {
			return BlockEntityType.Builder.of(entity, block.get())
					.build(null);
		});
	}

	public static void register(IEventBus event) {
		BLOCK_ENTITIES.register(event);
	}
}