package top.nebula.cmi.block;

import top.nebula.cmi.CMI;
import top.nebula.cmi.block.custom.MercuryGeothermalVentBlock;
import top.nebula.cmi.block.custom.MarsGeothermalVentBlock;
import top.nebula.cmi.block.custom.TestGravelBlock;
import top.nebula.cmi.block.custom.WaterPumpBlock;
import top.nebula.cmi.worldgen.ModConfiguredFeatures;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ModBlocks {
	private static final DeferredRegister<Block> BLOCKS;

	public static final Supplier<Block> GOLD_SAPLING;
	public static final Supplier<Block> WATER_PUMP;
	public static final Supplier<Block> MARS_GEO;
	public static final Supplier<Block> MERCURY_GEO;
	public static final Supplier<Block> TEST_GRAVEL;

	public static void register(IEventBus event) {
		BLOCKS.register(event);
	}

	static {
		BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CMI.MODID);

		TEST_GRAVEL = BLOCKS.register("test_gravel", TestGravelBlock::new);

		GOLD_SAPLING = BLOCKS.register("gold_sapling", () -> {
			return new SaplingBlock(new AbstractTreeGrower() {
				@Override
				protected @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(@NotNull RandomSource randomSource, boolean b) {
					return ModConfiguredFeatures.GOLDEN_TREE;
				}
			}, BlockBehaviour.Properties.of()
					.mapColor(MapColor.PLANT)
					.noCollission()
					.randomTicks()
					.instabreak()
					.sound(SoundType.GRASS)
					.pushReaction(PushReaction.DESTROY)
			);
		});
		WATER_PUMP = BLOCKS.register("water_pump", WaterPumpBlock::new);
		MARS_GEO = BLOCKS.register("mars_geothermal_vent", MarsGeothermalVentBlock::new);
		MERCURY_GEO = BLOCKS.register("mercury_geothermal_vent", MercuryGeothermalVentBlock::new);
	}
}