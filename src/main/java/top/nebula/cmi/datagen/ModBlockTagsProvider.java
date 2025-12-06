package top.nebula.cmi.datagen;

import com.simibubi.create.AllTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.nebula.cmi.CMI;
import top.nebula.cmi.common.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
	public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper helper) {
		super(output, provider, CMI.MODID, helper);
	}

	@Override
	public void addTags(HolderLookup.@NotNull Provider provider) {
		tag(BlockTags.MINEABLE_WITH_AXE)
				.add(ModBlocks.WATER_PUMP.get());
		tag(BlockTags.MINEABLE_WITH_PICKAXE)
				.add(ModBlocks.WATER_PUMP.get());
		tag(AllTags.AllBlockTags.WRENCH_PICKUP.tag)
				.add(ModBlocks.WATER_PUMP.get());
	}
}