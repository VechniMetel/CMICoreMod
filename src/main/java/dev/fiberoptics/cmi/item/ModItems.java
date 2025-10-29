package dev.fiberoptics.cmi.item;

import dev.fiberoptics.cmi.CMI;
import dev.fiberoptics.cmi.block.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
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
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,CMI.MODID);

        NUCLEAR_MECHANISM = ITEMS.register("nuclear_mechanism", NuclearMechanism::new);
        WATER_PUMP = ITEMS.register("water_pump",() -> new BlockItem(ModBlocks.WATER_PUMP.get(),new Item.Properties()));
    }
}
