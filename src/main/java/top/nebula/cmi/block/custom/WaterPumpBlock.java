package top.nebula.cmi.block.custom;

import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.nebula.cmi.CMI;
import top.nebula.cmi.block.ModBlockEntityTypes;
import top.nebula.cmi.block.ModBlocks;
import top.nebula.cmi.block.entity.WaterPumpBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

@SuppressWarnings("ALL")
@Mod.EventBusSubscriber(modid = CMI.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WaterPumpBlock extends Block implements IBE<WaterPumpBlockEntity> {
	public WaterPumpBlock() {
		super(Properties.copy(Blocks.OAK_PLANKS));
	}

	BlockPos pos;
	BlockState state;

	@Override
	public Class<WaterPumpBlockEntity> getBlockEntityClass() {
		return WaterPumpBlockEntity.class;
	}

	@Override
	public BlockEntityType<? extends WaterPumpBlockEntity> getBlockEntityType() {
		return ModBlockEntityTypes.WATER_PUMP.get();
	}

	@SubscribeEvent
	public void rightClickShow(PlayerInteractEvent.RightClickBlock event) {
		Level level = event.getLevel();
		BlockPos pos = event.getPos();
		Player player = event.getEntity();
		InteractionHand hand = event.getHand();
		ItemStack item = player.getItemInHand(hand);
		BlockState state = level.getBlockState(pos);

		WaterPumpBlockEntity entity = (WaterPumpBlockEntity) level.getBlockEntity(pos);

		if (level.isClientSide()) {
			return;
		}

		if (item.is(AllTags.AllItemTags.WRENCH.tag) && hand == InteractionHand.MAIN_HAND && state.is(ModBlocks.WATER_PUMP.get())) {
			player.swing(hand);

			entity.showMultiblock();
		}
	}
}