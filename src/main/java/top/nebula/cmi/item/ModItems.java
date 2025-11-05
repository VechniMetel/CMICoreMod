package top.nebula.cmi.item;

import top.nebula.cmi.CMI;
import top.nebula.cmi.block.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
	private static final DeferredRegister<Item> ITEMS;

	public static final RegistryObject<Item> NUCLEAR_MECHANISM;
	public static final RegistryObject<Item> WATER_PUMP;
	public static final RegistryObject<Item> MOON_GEO;
	public static final RegistryObject<Item> MERCURY_GEO;

	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}

	static {
		ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CMI.MODID);

		NUCLEAR_MECHANISM = ITEMS.register("nuclear_mechanism", NuclearMechanism::new);
		MOON_GEO = ITEMS.register("moon_geothermal_vent", () -> {
			return new BlockItem(ModBlocks.MOON_GEO.get(), new Item.Properties());
		});
		MERCURY_GEO = ITEMS.register("mercury_geothermal_vent", () -> {
			return new BlockItem(ModBlocks.MERCURY_GEO.get(), new Item.Properties());
		});
		WATER_PUMP = ITEMS.register("water_pump", () -> {
			return new BlockItem(ModBlocks.WATER_PUMP.get(), new Item.Properties());
		});
	}
}