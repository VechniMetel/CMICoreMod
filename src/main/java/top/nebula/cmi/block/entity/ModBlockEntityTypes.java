package top.nebula.cmi.block.entity;

import top.nebula.cmi.CMI;
import top.nebula.cmi.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntityTypes {
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES;

    public static final RegistryObject<BlockEntityType<WaterPumpBlockEntity>> WATER_PUMP;

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITY_TYPES.register(eventBus);
    }

    static {
        BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CMI.MODID);

        WATER_PUMP = BLOCK_ENTITY_TYPES.register("water_pump", () -> BlockEntityType.Builder.of(WaterPumpBlockEntity::new, ModBlocks.WATER_PUMP.get()).build(null));
    }
}