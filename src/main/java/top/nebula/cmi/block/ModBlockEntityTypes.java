package top.nebula.cmi.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import top.nebula.cmi.CMI;
import top.nebula.cmi.block.entity.*;

public class ModBlockEntityTypes {
	private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
			DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CMI.MODID);
	public static final RegistryObject<BlockEntityType<TestGravelBlockEntity>> TEST_GRAVEL =
			register("test_gravel", TestGravelBlockEntity::new, ModBlocks.TEST_GRAVEL.get());
	public static final RegistryObject<BlockEntityType<MoonGeothermalVentBlockEntity>> MOON_GEO =
			register("moon_geothermal_vent", MoonGeothermalVentBlockEntity::new, ModBlocks.MOON_GEO.get());
	public static final RegistryObject<BlockEntityType<MercuryGeothermalVentBlockEntity>> MERCURY_GEO =
			register("mercury_geothermal_vent", MercuryGeothermalVentBlockEntity::new, ModBlocks.MERCURY_GEO.get());
	public static final RegistryObject<BlockEntityType<WaterPumpBlockEntity>> WATER_PUMP =
			register("water_pump", WaterPumpBlockEntity::new, ModBlocks.WATER_PUMP.get());

	/**
	 *
	 * @param name    注册名
	 * @param factory BlockEntity 构造方法引用 (例如 CustomBlockEntity::new)
	 * @param blocks  绑定的方块实例
	 */
	private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> factory, Block... blocks) {
		return BLOCK_ENTITIES.register(name, () -> {
			return BlockEntityType.Builder.of(factory, blocks)
					.build(null);
		});
	}

	public static void register(IEventBus event) {
		BLOCK_ENTITIES.register(event);
	}
}