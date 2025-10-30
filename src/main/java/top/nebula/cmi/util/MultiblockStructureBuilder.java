package top.nebula.cmi.util;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.level.block.state.properties.Property;
import vazkii.patchouli.api.IMultiblock;
import vazkii.patchouli.api.PatchouliAPI;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.patchouli.api.IStateMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MultiblockStructureBuilder {
	private final String[][] structure;
	private final List<Object> matchers = new ArrayList<>();

	public static class BlockStateMatcher {

		// 用于累积需要匹配的属性键值对（建造者模式核心）
		private final ImmutableMap.Builder<Property<?>, Comparable<?>> propertyMapBuilder;

		// 私有构造方法，通过静态create()方法创建实例
		private BlockStateMatcher() {
			this.propertyMapBuilder = ImmutableMap.builder();
		}

		/**
		 * 创建一个新的BlockStateMatcher实例，开始链式调用
		 */
		public static BlockStateMatcher create() {
			return new BlockStateMatcher();
		}

		/**
		 * 判断目标BlockState是否匹配所有已添加的属性条件
		 *
		 * @param state 待判断的方块状态
		 * @return 若所有属性都匹配则返回true，否则返回false
		 */
		public boolean matches(BlockState state) {
			// 构建不可变映射（此时确定所有需要匹配的属性）
			ImmutableMap<Property<?>, Comparable<?>> requiredProperties = propertyMapBuilder.build();

			// 遍历所有需要匹配的属性
			for (Map.Entry<Property<?>, Comparable<?>> entry : requiredProperties.entrySet()) {
				Property<?> property = entry.getKey();
				Comparable<?> expectedValue = entry.getValue();

				// 1. 检查BlockState是否包含该属性（避免空指针）
				if (!state.hasProperty(property)) {
					return false;
				}

				// 2. 获取BlockState中该属性的实际值
				// 由于with()方法的泛型约束，这里的类型一定匹配，无需额外转换
				Comparable<?> actualValue = state.getValue(property);

				// 3. 检查值是否匹配
				if (!expectedValue.equals(actualValue)) {
					return false;
				}
			}

			// 所有属性均匹配
			return true;
		}

		public BlockStateMatcher with(Property<?> property, Comparable<?> value) {
			// 校验参数非空（可选，根据需求添加）
			if (property == null || value == null) {
				throw new IllegalArgumentException("Property和value不能为null");
			}
			propertyMapBuilder.put(property, value);
			return this; // 返回自身，实现链式调用
		}
	}

	/**
	 * 定义结构时必须有一个"0"的位置作为整个结构的中心位置
	 *
	 * @param structure 定义结构
	 */
	public MultiblockStructureBuilder(String[][] structure) {
		this.structure = structure;
	}

	/**
	 * 添加方块匹配规则
	 *
	 * @param pos   结构中的字符标识
	 * @param block 支持的匹配类型:
	 *              - Block: 具体方块
	 *              - BlockState: 具体方块状态
	 *              - TagKey<Block>: 方块标签
	 *              - IStateMatcher: 状态匹配器
	 * @return 构建器自身
	 */
	public MultiblockStructureBuilder where(char pos, Block block) {
		matchers.add(pos);
		matchers.add(block);
		return this;
	}

	public MultiblockStructureBuilder where(char pos, TagKey<Block> tag) {
		matchers.add(pos);
		matchers.add(tag);
		return this;
	}

	public MultiblockStructureBuilder where(char pos, IStateMatcher stateMatcher) {
		matchers.add(pos);
		matchers.add(stateMatcher);
		return this;
	}

	public MultiblockStructureBuilder where(char pos, Block block, Predicate<BlockState> predicate) {
		matchers.add(pos);
		matchers.add(PatchouliAPI.get().predicateMatcher(block, predicate));
		return this;
	}

	public MultiblockStructureBuilder where(char pos, Block block, ImmutableMap<Property<?>, ? extends Comparable<?>> stateMatcher) {
		matchers.add(pos);
		BlockStateMatcher blockStateMatcher = BlockStateMatcher.create();
		for (var entry : stateMatcher.entrySet()) {
			blockStateMatcher.with(entry.getKey(), entry.getValue());
		}
		matchers.add(PatchouliAPI.get().predicateMatcher(block, blockStateMatcher::matches));
		return this;
	}

	public IMultiblock build() {
		return PatchouliAPI.get().makeMultiblock(structure, matchers.toArray());
	}
}