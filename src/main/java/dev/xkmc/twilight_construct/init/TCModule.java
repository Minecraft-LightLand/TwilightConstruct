package dev.xkmc.twilight_construct.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import slimeknights.mantle.item.BlockTooltipItem;
import slimeknights.mantle.item.TooltipItem;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.deferred.SynchronizedDeferredRegister;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.common.registration.BlockDeferredRegisterExtension;
import slimeknights.tconstruct.common.registration.EnumDeferredRegister;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class TCModule {

	protected static final BlockDeferredRegisterExtension BLOCKS = new BlockDeferredRegisterExtension(TwilightConstruct.MODID);
	protected static final ItemDeferredRegisterExtension ITEMS = new ItemDeferredRegisterExtension(TwilightConstruct.MODID);
	protected static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(TwilightConstruct.MODID);
	protected static final EnumDeferredRegister<MobEffect> MOB_EFFECTS = new EnumDeferredRegister<>(Registries.MOB_EFFECT, TwilightConstruct.MODID);
	protected static final SynchronizedDeferredRegister<CreativeModeTab> CREATIVE_TABS = SynchronizedDeferredRegister.create(Registries.CREATIVE_MODE_TAB, TwilightConstruct.MODID);

	protected static final Item.Properties ITEM_PROPS = new Item.Properties();
	protected static final Item.Properties UNSTACKABLE_PROPS = (new Item.Properties()).stacksTo(1);
	protected static final Function<Block, ? extends BlockItem> BLOCK_ITEM = (b) -> new BlockItem(b, ITEM_PROPS);
	protected static final Function<Block, ? extends BlockItem> TOOLTIP_BLOCK_ITEM = (b) -> new BlockTooltipItem(b, ITEM_PROPS);
	protected static final Function<Block, ? extends BlockItem> UNSTACKABLE_BLOCK_ITEM = (b) -> new BlockTooltipItem(b, UNSTACKABLE_PROPS);
	protected static final Supplier<Item> TOOLTIP_ITEM = () -> new TooltipItem(ITEM_PROPS);

	protected TCModule() {
	}

	public static void initRegisters() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		BLOCKS.register(bus);
		ITEMS.register(bus);
		FLUIDS.register(bus);
		MOB_EFFECTS.register(bus);
		CREATIVE_TABS.register(bus);
	}

	protected static BlockBehaviour.Properties builder(SoundType soundType) {
		return Properties.of().sound(soundType);
	}

	protected static BlockBehaviour.Properties builder(MapColor color, SoundType soundType) {
		return builder(soundType).mapColor(color);
	}

	protected static BlockBehaviour.Properties metalBuilder(MapColor color) {
		return builder(color, SoundType.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5.0F);
	}

	protected static BlockBehaviour.Properties woodBuilder(MapColor color) {
		return builder(color, SoundType.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F, 7.0F).ignitedByLava();
	}

	protected static <T> ResourceKey<T> key(ResourceKey<? extends Registry<T>> registry, String name) {
		return ResourceKey.create(registry, TConstruct.getResource(name));
	}


}
