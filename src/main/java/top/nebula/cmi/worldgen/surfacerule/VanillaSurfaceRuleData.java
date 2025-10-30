package top.nebula.cmi.worldgen.surfacerule;

import com.google.common.collect.ImmutableList;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class VanillaSurfaceRuleData {
	private static final SurfaceRules.RuleSource AIR;
	private static final SurfaceRules.RuleSource BEDROCK;
	private static final SurfaceRules.RuleSource WHITE_TERRACOTTA;
	private static final SurfaceRules.RuleSource ORANGE_TERRACOTTA;
	private static final SurfaceRules.RuleSource TERRACOTTA;
	private static final SurfaceRules.RuleSource RED_SAND;
	private static final SurfaceRules.RuleSource RED_SANDSTONE;
	private static final SurfaceRules.RuleSource STONE;
	private static final SurfaceRules.RuleSource DEEPSLATE;
	private static final SurfaceRules.RuleSource DIRT;
	private static final SurfaceRules.RuleSource PODZOL;
	private static final SurfaceRules.RuleSource COARSE_DIRT;
	private static final SurfaceRules.RuleSource MYCELIUM;
	private static final SurfaceRules.RuleSource GRASS_BLOCK;
	private static final SurfaceRules.RuleSource CALCITE;
	private static final SurfaceRules.RuleSource GRAVEL;
	private static final SurfaceRules.RuleSource SAND;
	private static final SurfaceRules.RuleSource SANDSTONE;
	private static final SurfaceRules.RuleSource PACKED_ICE;
	private static final SurfaceRules.RuleSource SNOW_BLOCK;
	private static final SurfaceRules.RuleSource MUD;
	private static final SurfaceRules.RuleSource POWDER_SNOW;
	private static final SurfaceRules.RuleSource ICE;
	private static final SurfaceRules.RuleSource WATER;
	private static final SurfaceRules.RuleSource LAVA;
	private static final SurfaceRules.RuleSource NETHERRACK;
	private static final SurfaceRules.RuleSource SOUL_SAND;
	private static final SurfaceRules.RuleSource SOUL_SOIL;
	private static final SurfaceRules.RuleSource BASALT;
	private static final SurfaceRules.RuleSource BLACKSTONE;
	private static final SurfaceRules.RuleSource WARPED_WART_BLOCK;
	private static final SurfaceRules.RuleSource WARPED_NYLIUM;
	private static final SurfaceRules.RuleSource NETHER_WART_BLOCK;
	private static final SurfaceRules.RuleSource CRIMSON_NYLIUM;
	private static final SurfaceRules.RuleSource ENDSTONE;

	static {
		AIR = makeStateRule(Blocks.AIR);
		BEDROCK = makeStateRule(Blocks.BEDROCK);
		WHITE_TERRACOTTA = makeStateRule(Blocks.WHITE_TERRACOTTA);
		ORANGE_TERRACOTTA = makeStateRule(Blocks.ORANGE_TERRACOTTA);
		TERRACOTTA = makeStateRule(Blocks.TERRACOTTA);
		RED_SAND = makeStateRule(Blocks.RED_SAND);
		RED_SANDSTONE = makeStateRule(Blocks.RED_SANDSTONE);
		STONE = makeStateRule(Blocks.STONE);
		DEEPSLATE = makeStateRule(Blocks.DEEPSLATE);
		DIRT = makeStateRule(Blocks.DIRT);
		PODZOL = makeStateRule(Blocks.PODZOL);
		COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
		MYCELIUM = makeStateRule(Blocks.MYCELIUM);
		GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
		CALCITE = makeStateRule(Blocks.CALCITE);
		GRAVEL = makeStateRule(Blocks.GRAVEL);
		SAND = makeStateRule(Blocks.SAND);
		SANDSTONE = makeStateRule(Blocks.SANDSTONE);
		PACKED_ICE = makeStateRule(Blocks.PACKED_ICE);
		SNOW_BLOCK = makeStateRule(Blocks.SNOW_BLOCK);
		MUD = makeStateRule(Blocks.MUD);
		POWDER_SNOW = makeStateRule(Blocks.POWDER_SNOW);
		ICE = makeStateRule(Blocks.ICE);
		WATER = makeStateRule(Blocks.WATER);
		LAVA = makeStateRule(Blocks.LAVA);
		NETHERRACK = makeStateRule(Blocks.NETHERRACK);
		SOUL_SAND = makeStateRule(Blocks.SOUL_SAND);
		SOUL_SOIL = makeStateRule(Blocks.SOUL_SOIL);
		BASALT = makeStateRule(Blocks.BASALT);
		BLACKSTONE = makeStateRule(Blocks.BLACKSTONE);
		WARPED_WART_BLOCK = makeStateRule(Blocks.WARPED_WART_BLOCK);
		WARPED_NYLIUM = makeStateRule(Blocks.WARPED_NYLIUM);
		NETHER_WART_BLOCK = makeStateRule(Blocks.NETHER_WART_BLOCK);
		CRIMSON_NYLIUM = makeStateRule(Blocks.CRIMSON_NYLIUM);
		ENDSTONE = makeStateRule(Blocks.END_STONE);
	}

	public VanillaSurfaceRuleData() {
	}

	private static SurfaceRules.RuleSource makeStateRule(Block pBlock) {
		return SurfaceRules.state(pBlock.defaultBlockState());
	}

	public static SurfaceRules.RuleSource overworld() {
		return overworldLike(true, false, true);
	}

	public static SurfaceRules.RuleSource overworldLike(boolean p_198381_, boolean pBedrockRoof,
	                                                    boolean pBedrockFloor
	) {
		SurfaceRules.ConditionSource $$3 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(97), 2);
		SurfaceRules.ConditionSource $$4 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(256), 0);
		SurfaceRules.ConditionSource $$5 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(63), -1);
		SurfaceRules.ConditionSource $$6 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(74), 1);
		SurfaceRules.ConditionSource $$7 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(60), 0);
		SurfaceRules.ConditionSource $$8 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0);
		SurfaceRules.ConditionSource $$9 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0);
		SurfaceRules.ConditionSource $$10 = SurfaceRules.waterBlockCheck(-1, 0);
		SurfaceRules.ConditionSource $$11 = SurfaceRules.waterBlockCheck(0, 0);
		SurfaceRules.ConditionSource $$12 = SurfaceRules.waterStartCheck(-6, -1);
		SurfaceRules.ConditionSource $$13 = SurfaceRules.hole();
		SurfaceRules.ConditionSource $$14 = SurfaceRules.isBiome(
				new ResourceKey[]{Biomes.FROZEN_OCEAN, Biomes.DEEP_FROZEN_OCEAN});
		SurfaceRules.ConditionSource $$15 = SurfaceRules.steep();
		SurfaceRules.RuleSource $$16 = SurfaceRules.sequence(
				new SurfaceRules.RuleSource[]{SurfaceRules.ifTrue($$11, GRASS_BLOCK), DIRT});
		SurfaceRules.RuleSource $$17 = SurfaceRules.sequence(
				new SurfaceRules.RuleSource[]{SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, SANDSTONE), SAND});
		SurfaceRules.RuleSource $$18 = SurfaceRules.sequence(
				new SurfaceRules.RuleSource[]{SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, STONE), GRAVEL});
		SurfaceRules.ConditionSource $$19 = SurfaceRules.isBiome(
				new ResourceKey[]{Biomes.WARM_OCEAN, Biomes.BEACH, Biomes.SNOWY_BEACH});
		SurfaceRules.ConditionSource $$20 = SurfaceRules.isBiome(new ResourceKey[]{Biomes.DESERT});
		SurfaceRules.RuleSource $$21 = SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
				SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.STONY_PEAKS}),
						SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
								SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.CALCITE, -0.0125, 0.0125),
										CALCITE
								), STONE
						})
				), SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.STONY_SHORE}),
				SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
						SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.GRAVEL, -0.05, 0.05), $$18), STONE
				})
		), SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.WINDSWEPT_HILLS}),
				SurfaceRules.ifTrue(surfaceNoiseAbove((double) 1.0F), STONE)
		), SurfaceRules.ifTrue($$19, $$17), SurfaceRules.ifTrue($$20, $$17),
				SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.DRIPSTONE_CAVES}), STONE)
		});
		SurfaceRules.RuleSource $$22 = SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.POWDER_SNOW, 0.45, 0.58),
				SurfaceRules.ifTrue($$11, POWDER_SNOW)
		);
		SurfaceRules.RuleSource $$23 = SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.POWDER_SNOW, 0.35, 0.6),
				SurfaceRules.ifTrue($$11, POWDER_SNOW)
		);
		SurfaceRules.RuleSource $$24 = SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
				SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.FROZEN_PEAKS}),
						SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
								SurfaceRules.ifTrue($$15, PACKED_ICE),
								SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.PACKED_ICE, (double) -0.5F, 0.2),
										PACKED_ICE
								),
								SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.ICE, (double) -0.0625F, 0.025),
										ICE
								), SurfaceRules.ifTrue($$11, SNOW_BLOCK)
						})
				), SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.SNOWY_SLOPES}),
				SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
						SurfaceRules.ifTrue($$15, STONE), $$22, SurfaceRules.ifTrue($$11, SNOW_BLOCK)
				})
		), SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.JAGGED_PEAKS}), STONE),
				SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.GROVE}),
						SurfaceRules.sequence(new SurfaceRules.RuleSource[]{$$22, DIRT})
				), $$21, SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.WINDSWEPT_SAVANNA}),
				SurfaceRules.ifTrue(surfaceNoiseAbove((double) 1.75F), STONE)
		), SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.WINDSWEPT_GRAVELLY_HILLS}),
				SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
						SurfaceRules.ifTrue(surfaceNoiseAbove((double) 2.0F), $$18),
						SurfaceRules.ifTrue(surfaceNoiseAbove((double) 1.0F), STONE),
						SurfaceRules.ifTrue(surfaceNoiseAbove((double) -1.0F), DIRT), $$18
				})
		), SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.MANGROVE_SWAMP}), MUD), DIRT
		});
		SurfaceRules.RuleSource $$25 = SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
				SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.FROZEN_PEAKS}),
						SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
								SurfaceRules.ifTrue($$15, PACKED_ICE),
								SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.PACKED_ICE, (double) 0.0F, 0.2),
										PACKED_ICE
								),
								SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.ICE, (double) 0.0F, 0.025), ICE),
								SurfaceRules.ifTrue($$11, SNOW_BLOCK)
						})
				), SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.SNOWY_SLOPES}),
				SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
						SurfaceRules.ifTrue($$15, STONE), $$23, SurfaceRules.ifTrue($$11, SNOW_BLOCK)
				})
		), SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.JAGGED_PEAKS}), SurfaceRules.sequence(
				new SurfaceRules.RuleSource[]{SurfaceRules.ifTrue($$15, STONE), SurfaceRules.ifTrue($$11, SNOW_BLOCK)})
		), SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.GROVE}),
				SurfaceRules.sequence(new SurfaceRules.RuleSource[]{$$23, SurfaceRules.ifTrue($$11, SNOW_BLOCK)})
		), $$21, SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.WINDSWEPT_SAVANNA}),
				SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
						SurfaceRules.ifTrue(surfaceNoiseAbove((double) 1.75F), STONE),
						SurfaceRules.ifTrue(surfaceNoiseAbove((double) -0.5F), COARSE_DIRT)
				})
		), SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.WINDSWEPT_GRAVELLY_HILLS}),
				SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
						SurfaceRules.ifTrue(surfaceNoiseAbove((double) 2.0F), $$18),
						SurfaceRules.ifTrue(surfaceNoiseAbove((double) 1.0F), STONE),
						SurfaceRules.ifTrue(surfaceNoiseAbove((double) -1.0F), $$16), $$18
				})
		), SurfaceRules.ifTrue(
				SurfaceRules.isBiome(new ResourceKey[]{Biomes.OLD_GROWTH_PINE_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA}),
				SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
						SurfaceRules.ifTrue(surfaceNoiseAbove((double) 1.75F), COARSE_DIRT),
						SurfaceRules.ifTrue(surfaceNoiseAbove(-0.95), PODZOL)
				})
		), SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.ICE_SPIKES}),
				SurfaceRules.ifTrue($$11, SNOW_BLOCK)
		), SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.MANGROVE_SWAMP}), MUD),
				SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.MUSHROOM_FIELDS}), MYCELIUM), $$16
		});
		SurfaceRules.ConditionSource $$26 = SurfaceRules.noiseCondition(Noises.SURFACE, -0.909, -0.5454);
		SurfaceRules.ConditionSource $$27 = SurfaceRules.noiseCondition(Noises.SURFACE, -0.1818, 0.1818);
		SurfaceRules.ConditionSource $$28 = SurfaceRules.noiseCondition(Noises.SURFACE, 0.5454, 0.909);
		SurfaceRules.RuleSource $$29 = SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
				SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
								SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.WOODED_BADLANDS}),
										SurfaceRules.ifTrue($$3, SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
														SurfaceRules.ifTrue($$26, COARSE_DIRT),
														SurfaceRules.ifTrue($$27, COARSE_DIRT),
														SurfaceRules.ifTrue($$28, COARSE_DIRT), $$16
												})
										)
								), SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.SWAMP}),
								SurfaceRules.ifTrue($$8, SurfaceRules.ifTrue(SurfaceRules.not($$9),
												SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP,
														(double) 0.0F), WATER)
										)
								)
						), SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.MANGROVE_SWAMP}),
								SurfaceRules.ifTrue($$7, SurfaceRules.ifTrue(SurfaceRules.not($$9),
												SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP,
														(double) 0.0F), WATER)
										)
								)
						)
						})
				), SurfaceRules.ifTrue(SurfaceRules.isBiome(
						new ResourceKey[]{Biomes.BADLANDS, Biomes.ERODED_BADLANDS, Biomes.WOODED_BADLANDS}),
				SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
						SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
										SurfaceRules.ifTrue($$4, ORANGE_TERRACOTTA),
										SurfaceRules.ifTrue($$6, SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
														SurfaceRules.ifTrue($$26, TERRACOTTA),
														SurfaceRules.ifTrue($$27, TERRACOTTA),
														SurfaceRules.ifTrue($$28, TERRACOTTA), SurfaceRules.bandlands()
												})
										), SurfaceRules.ifTrue($$10,
										SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
												SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, RED_SANDSTONE), RED_SAND
										})
								), SurfaceRules.ifTrue(SurfaceRules.not($$13), ORANGE_TERRACOTTA),
										SurfaceRules.ifTrue($$12, WHITE_TERRACOTTA), $$18
								})
						), SurfaceRules.ifTrue($$5, SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
								SurfaceRules.ifTrue($$9, SurfaceRules.ifTrue(SurfaceRules.not($$6), ORANGE_TERRACOTTA)),
								SurfaceRules.bandlands()
						})
				), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.ifTrue($$12, WHITE_TERRACOTTA))
				})
		), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
				SurfaceRules.ifTrue($$10, SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
								SurfaceRules.ifTrue($$14,
										SurfaceRules.ifTrue($$13, SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
														SurfaceRules.ifTrue($$11, AIR),
														SurfaceRules.ifTrue(SurfaceRules.temperature(), ICE), WATER
												})
										)
								), $$25
						})
				)
		), SurfaceRules.ifTrue($$12, SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
						SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.ifTrue($$14, SurfaceRules.ifTrue($$13
								, WATER))),
						SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, $$24),
						SurfaceRules.ifTrue($$19, SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, SANDSTONE)),
						SurfaceRules.ifTrue($$20, SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, SANDSTONE))
				})
		), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
						SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{
										Biomes.FROZEN_PEAKS, Biomes.JAGGED_PEAKS
								}), STONE
						), SurfaceRules.ifTrue(SurfaceRules.isBiome(
						new ResourceKey[]{Biomes.WARM_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN}), $$17
				), $$18
				})
		)
		});
		ImmutableList.Builder<SurfaceRules.RuleSource> $$30 = ImmutableList.builder();
		if (pBedrockRoof) {
			$$30.add(SurfaceRules.ifTrue(SurfaceRules.not(
							SurfaceRules.verticalGradient("bedrock_roof", VerticalAnchor.belowTop(5),
									VerticalAnchor.top())),
					BEDROCK
			));
		}

		if (pBedrockFloor) {
			$$30.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(),
							VerticalAnchor.aboveBottom(5)
					), BEDROCK
			));
		}

		SurfaceRules.RuleSource $$31 = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), $$29);
		$$30.add(p_198381_ ? $$31 : $$29);
		$$30.add(SurfaceRules.ifTrue(
				SurfaceRules.verticalGradient("deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(8)),
				DEEPSLATE
		));
		return SurfaceRules.sequence((SurfaceRules.RuleSource[]) $$30.build()
				.toArray((p_198379_) -> new SurfaceRules.RuleSource[p_198379_]));
	}

	public static SurfaceRules.RuleSource nether() {
		SurfaceRules.ConditionSource $$0 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(31), 0);
		SurfaceRules.ConditionSource $$1 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(32), 0);
		SurfaceRules.ConditionSource $$2 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(30), 0);
		SurfaceRules.ConditionSource $$3 = SurfaceRules.not(SurfaceRules.yStartCheck(VerticalAnchor.absolute(35), 0));
		SurfaceRules.ConditionSource $$4 = SurfaceRules.yBlockCheck(VerticalAnchor.belowTop(5), 0);
		SurfaceRules.ConditionSource $$5 = SurfaceRules.hole();
		SurfaceRules.ConditionSource $$6 = SurfaceRules.noiseCondition(Noises.SOUL_SAND_LAYER, -0.012);
		SurfaceRules.ConditionSource $$7 = SurfaceRules.noiseCondition(Noises.GRAVEL_LAYER, -0.012);
		SurfaceRules.ConditionSource $$8 = SurfaceRules.noiseCondition(Noises.PATCH, -0.012);
		SurfaceRules.ConditionSource $$9 = SurfaceRules.noiseCondition(Noises.NETHERRACK, 0.54);
		SurfaceRules.ConditionSource $$10 = SurfaceRules.noiseCondition(Noises.NETHER_WART, 1.17);
		SurfaceRules.ConditionSource $$11 = SurfaceRules.noiseCondition(Noises.NETHER_STATE_SELECTOR, (double) 0.0F);
		SurfaceRules.RuleSource $$12 = SurfaceRules.ifTrue($$8,
				SurfaceRules.ifTrue($$2, SurfaceRules.ifTrue($$3, GRAVEL))
		);
		return SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
				SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(),
								VerticalAnchor.aboveBottom(5)
						), BEDROCK
				), SurfaceRules.ifTrue(SurfaceRules.not(
						SurfaceRules.verticalGradient("bedrock_roof", VerticalAnchor.belowTop(5),
								VerticalAnchor.top())),
				BEDROCK
		), SurfaceRules.ifTrue($$4, NETHERRACK),
				SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.BASALT_DELTAS}),
						SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
								SurfaceRules.ifTrue(SurfaceRules.UNDER_CEILING, BASALT),
								SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR,
										SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
												$$12, SurfaceRules.ifTrue($$11, BASALT), BLACKSTONE
										})
								)
						})
				), SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.SOUL_SAND_VALLEY}),
				SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
						SurfaceRules.ifTrue(SurfaceRules.UNDER_CEILING, SurfaceRules.sequence(
								new SurfaceRules.RuleSource[]{SurfaceRules.ifTrue($$11, SOUL_SAND), SOUL_SOIL})
						), SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.sequence(
						new SurfaceRules.RuleSource[]{$$12, SurfaceRules.ifTrue($$11, SOUL_SAND), SOUL_SOIL})
				)
				})
		), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
						SurfaceRules.ifTrue(SurfaceRules.not($$1), SurfaceRules.ifTrue($$5, LAVA)),
						SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.WARPED_FOREST}),
								SurfaceRules.ifTrue(SurfaceRules.not($$9),
										SurfaceRules.ifTrue($$0, SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
														SurfaceRules.ifTrue($$10, WARPED_WART_BLOCK), WARPED_NYLIUM
												})
										)
								)
						), SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.CRIMSON_FOREST}),
						SurfaceRules.ifTrue(SurfaceRules.not($$9),
								SurfaceRules.ifTrue($$0, SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
												SurfaceRules.ifTrue($$10, NETHER_WART_BLOCK), CRIMSON_NYLIUM
										})
								)
						)
				)
				})
		), SurfaceRules.ifTrue(SurfaceRules.isBiome(new ResourceKey[]{Biomes.NETHER_WASTES}),
				SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
						SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR,
								SurfaceRules.ifTrue($$6, SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
												SurfaceRules.ifTrue(SurfaceRules.not($$5),
														SurfaceRules.ifTrue($$2, SurfaceRules.ifTrue($$3, SOUL_SAND))
												), NETHERRACK
										})
								)
						), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.ifTrue($$0, SurfaceRules.ifTrue($$3,
										SurfaceRules.ifTrue($$7, SurfaceRules.sequence(new SurfaceRules.RuleSource[]{
														SurfaceRules.ifTrue($$1, GRAVEL),
														SurfaceRules.ifTrue(SurfaceRules.not($$5), GRAVEL)
												})
										)
								)
						)
				)
				})
		), NETHERRACK
		});
	}

	public static SurfaceRules.RuleSource end() {
		return ENDSTONE;
	}

	public static SurfaceRules.RuleSource air() {
		return AIR;
	}

	private static SurfaceRules.ConditionSource surfaceNoiseAbove(double pValue) {
		return SurfaceRules.noiseCondition(Noises.SURFACE, pValue / (double) 8.25F, Double.MAX_VALUE);
	}
}