package top.nebula.cmi.item;

import net.minecraft.world.item.BrushItem;
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
	public static final RegistryObject<Item> TEST_BRUSH;
	public static final RegistryObject<Item> TEST_GRAVEL;

	public static void register(IEventBus event) {
		ITEMS.register(event);
	}

	static {
		ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CMI.MODID);

		TEST_BRUSH = ITEMS.register("test_brush", () -> {
			return new BrushItem(new Item.Properties()
					.durability(128));
		});

		TEST_GRAVEL = ITEMS.register("test_gravel", () -> {
			return new BlockItem(ModBlocks.TEST_GRAVEL.get(), new Item.Properties());
		});

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