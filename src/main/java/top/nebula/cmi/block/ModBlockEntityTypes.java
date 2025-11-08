package top.nebula.cmi.block;

import top.nebula.cmi.CMI;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import top.nebula.cmi.block.entity.MercuryGeothermalVentBlockEntity;
import top.nebula.cmi.block.entity.MoonGeothermalVentBlockEntity;
import top.nebula.cmi.block.entity.TestGravelBlockEntity;
import top.nebula.cmi.block.entity.WaterPumpBlockEntity;

public class ModBlockEntityTypes {
	private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES;

	public static final RegistryObject<BlockEntityType<WaterPumpBlockEntity>> WATER_PUMP;
	public static final RegistryObject<BlockEntityType<MoonGeothermalVentBlockEntity>> MOON_GEO;
	public static final RegistryObject<BlockEntityType<MercuryGeothermalVentBlockEntity>> MERCURY_GEO;
	public static final RegistryObject<BlockEntityType<TestGravelBlockEntity>> TEST_GRAVEL;

	public static void register(IEventBus eventBus) {
		BLOCK_ENTITY_TYPES.register(eventBus);
	}

	static {
		BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CMI.MODID);

		TEST_GRAVEL = BLOCK_ENTITY_TYPES.register("test_gravel", () -> {
			return BlockEntityType.Builder.of(TestGravelBlockEntity::new, ModBlocks.TEST_GRAVEL.get())
					.build(null);
		});

		MOON_GEO = BLOCK_ENTITY_TYPES.register("moon_geothermal_vent", () -> {
			return BlockEntityType.Builder.of(MoonGeothermalVentBlockEntity::new, ModBlocks.MOON_GEO.get())
					.build(null);
		});

		MERCURY_GEO = BLOCK_ENTITY_TYPES.register("mercury_geothermal_vent", () -> {
			return BlockEntityType.Builder.of(MercuryGeothermalVentBlockEntity::new, ModBlocks.MERCURY_GEO.get())
					.build(null);
		});

		WATER_PUMP = BLOCK_ENTITY_TYPES.register("water_pump", () -> {
			return BlockEntityType.Builder.of(WaterPumpBlockEntity::new, ModBlocks.WATER_PUMP.get())
					.build(null);
		});
	}
}