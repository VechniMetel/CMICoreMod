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

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    static {
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CMI.MODID);

        NUCLEAR_MECHANISM = ITEMS.register("nuclear_mechanism", NuclearMechanism::new);
        WATER_PUMP = ITEMS.register("water_pump", () -> new BlockItem(ModBlocks.WATER_PUMP.get(), new Item.Properties()));
    }
}