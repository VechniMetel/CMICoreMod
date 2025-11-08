package top.nebula.cmi.datagen;

import top.nebula.cmi.CMI;
import top.nebula.cmi.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlockStateProvider extends BlockStateProvider {
	public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
		super(output, CMI.MODID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		saplingBlock(ModBlocks.GOLD_SAPLING);
		simpleBlockWithItem(ModBlocks.WATER_PUMP.get(), new ModelFile.UncheckedModelFile(modLoc("block/water_pump")));
	}

	private void saplingBlock(Supplier<Block> blockRegistryObject) {
		simpleBlock(blockRegistryObject.get(), models()
				.cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
						blockTexture(blockRegistryObject.get()))
				.renderType("cutout"));
	}
}