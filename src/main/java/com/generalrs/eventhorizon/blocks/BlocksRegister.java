package com.generalrs.eventhorizon.blocks;

import com.generalrs.eventhorizon.EventHorizon;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlocksRegister {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EventHorizon.MODID);

    public static RegistryObject<Block> TITANIUM_ORE=BLOCKS.register("titanium_ore",()->{
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND).strength(50.0F, 1200.0F).requiresCorrectToolForDrops();
        BlockBase blockBase = new BlockBase(properties);
        return blockBase;
    });;

    public void register(IEventBus bus){
        BLOCKS.register(bus);
    }

}
