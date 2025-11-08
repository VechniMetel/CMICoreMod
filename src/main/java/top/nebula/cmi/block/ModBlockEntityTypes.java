package top.nebula.cmi.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import top.nebula.cmi.CMI;
import top.nebula.cmi.block.entity.*;

import java.util.function.Supplier;

public class ModBlockEntityTypes {
	private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES;
	public static final Supplier<BlockEntityType<TestGravelBlockEntity>> TEST_GRAVEL;
	public static final Supplier<BlockEntityType<MoonGeothermalVentBlockEntity>> MOON_GEO;
	public static final Supplier<BlockEntityType<MercuryGeothermalVentBlockEntity>> MERCURY_GEO;
	public static final Supplier<BlockEntityType<WaterPumpBlockEntity>> WATER_PUMP;

	static {
		BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CMI.MODID);

		TEST_GRAVEL = registerBlockEntity("test_gravel", TestGravelBlockEntity::new, ModBlocks.TEST_GRAVEL);

		MOON_GEO = registerBlockEntity("moon_geothermal_vent", MoonGeothermalVentBlockEntity::new, ModBlocks.MOON_GEO);
		MERCURY_GEO = registerBlockEntity("mercury_geothermal_vent", MercuryGeothermalVentBlockEntity::new, ModBlocks.MERCURY_GEO);

		WATER_PUMP = registerBlockEntity("water_pump", WaterPumpBlockEntity::new, ModBlocks.WATER_PUMP);
	}

	private static <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntity(
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