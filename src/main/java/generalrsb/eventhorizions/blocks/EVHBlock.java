package generalrsb.eventhorizions.blocks;

import generalrsb.eventhorizions.items.EVHToolType;
import net.minecraft.core.Direction;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;

public class EVHBlock extends Block
{
    private boolean dropsSelf = false;
    public EVHToolType toolType = EVHToolType.NONE;
    private TagKey<Block> minimumTierToMine = null;
    private boolean rotational = false;


    public EVHBlock() {
        super(BlockBehaviour.Properties.of());
    }
    public EVHBlock(BlockBehaviour.Properties state,boolean dropsSelf) {
        super(state);
        this.dropsSelf = dropsSelf;
    }

    public boolean isDropsSelf() {
        return dropsSelf;
    }

    public EVHBlock pickaxeable(){
        toolType = EVHToolType.PICKAXE;
        return this;
    }
    public EVHBlock minimumTier(TagKey<Block> tier){
        this.minimumTierToMine = tier;
        return this;
    }

    public TagKey<Block> getMinimumTierToMine() {
        return minimumTierToMine;
    }
    public EVHBlock rotationable(){
        rotational = true;
        return this;
    }
    public EVHBlock dropsSelf(){
        dropsSelf = true;
        return this;
    }
    public boolean isRotationable() {
        return rotational;
    }
    public DirectionProperty getAxis(){
        return null;
    }

}
