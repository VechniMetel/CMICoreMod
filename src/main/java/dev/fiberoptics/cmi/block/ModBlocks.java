package dev.fiberoptics.cmi.block;

import dev.fiberoptics.cmi.CMI;
import dev.fiberoptics.cmi.worldgen.ModConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ModBlocks {
    private static final DeferredRegister<Block> BLOCKS;

    public static final RegistryObject<Block> GOLD_SAPLING;
    public static final RegistryObject<Block> WATER_PUMP;

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    static {
        BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CMI.MODID);

        GOLD_SAPLING = BLOCKS.register("gold_sapling",() -> new SaplingBlock(new AbstractTreeGrower() {
            @Override
            protected @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(@NotNull RandomSource randomSource, boolean b) {
                return ModConfiguredFeatures.GOLDEN_TREE;
            }
        }, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.DESTROY)));
        WATER_PUMP = BLOCKS.register("water_pump", WaterPumpBlock::new);
    }
}
