package top.nebula.cmi.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import top.nebula.cmi.CMI;
import top.nebula.cmi.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public class ModItemModelProvider extends ItemModelProvider {
	public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, CMI.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		simpleItem(ModItems.NUCLEAR_MECHANISM);
	}

	private ItemModelBuilder simpleItem(Supplier<Item> item) {
		String getItemKey = BuiltInRegistries.ITEM.getKey(item.get()).toString();
		String getItemPath = BuiltInRegistries.ITEM.getKey(item.get()).getPath();
		ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(CMI.MODID, "item/" + getItemPath);
		ResourceLocation parent = ResourceLocation.fromNamespaceAndPath("minecraft", "item/generated");

		return withExistingParent(getItemKey, parent)
				.texture("layer0", texture);
	}
}