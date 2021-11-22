package com.sehon.enhancemod.block;

import com.sehon.enhancemod.EnhanceMod;
import com.sehon.enhancemod.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = //Same as item register, just for blocks
            DeferredRegister.create(ForgeRegistries.BLOCKS, EnhanceMod.MOD_ID);

    public static final RegistryObject<Block> TITANIUM_slab = registerBlock("titanium_slab",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(14f)));

    // how this works:
    // when creating a new block, you need two things: the block itself and the block item. these two methods create both and serve as useful shortcuts.
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus); //This method tells forge to add DeferredRegister Blocks into the game
    }
}
