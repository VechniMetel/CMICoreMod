package dev.fiberoptics.cmi;

import blusunrize.immersiveengineering.common.items.HammerItem;
import com.simibubi.create.compat.jei.category.BasinCategory;
import com.simibubi.create.compat.jei.category.MixingCategory;
import com.simibubi.create.content.processing.basin.BasinRecipe;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import dev.fiberoptics.cmi.block.ModBlocks;
import dev.fiberoptics.cmi.block.entity.ModBlockEntityTypes;
import dev.fiberoptics.cmi.item.ModItems;
import dev.fiberoptics.cmi.worldgen.region.ModOverworldRegion;
import dev.fiberoptics.cmi.worldgen.surfacerule.ModSurfaceRuleData;
import net.minecraft.client.gui.screens.inventory.StructureBlockEditScreen;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.features.MiscOverworldFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.StructureBlockEntity;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import dev.fiberoptics.cmi.config.CommonConfig;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CMI.MODID)
public class CMI {
    public static final String MODID = "cmi";

    public CMI(FMLJavaModLoadingContext context) {
        context.registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC, CMI.MODID + "/common.toml");

        ModBlocks.register(context.getModEventBus());
        ModItems.register(context.getModEventBus());
        ModBlockEntityTypes.register(context.getModEventBus());

        context.getModEventBus().addListener(this::commonSetup);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Regions.register(new ModOverworldRegion(5));

            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, CMI.MODID,
                    ModSurfaceRuleData.makeRules()
            );
            SurfaceRuleManager.addToDefaultSurfaceRulesAtStage(SurfaceRuleManager.RuleCategory.OVERWORLD,
                    SurfaceRuleManager.RuleStage.AFTER_BEDROCK, 0, ModSurfaceRuleData.makeInjections()
            );
        });
    }
}
