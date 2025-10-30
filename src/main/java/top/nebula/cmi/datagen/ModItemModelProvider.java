package top.nebula.cmi.datagen;

import top.nebula.cmi.CMI;
import top.nebula.cmi.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
	public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, CMI.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		simpleItem(ModItems.NUCLEAR_MECHANISM);
	}

	private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
		return withExistingParent(item.getId().getPath(),
				ResourceLocation.fromNamespaceAndPath("minecraft", "item/generated")).texture("layer0",
				ResourceLocation.fromNamespaceAndPath(CMI.MODID, "item/" + item.getId().getPath()));
	}
}