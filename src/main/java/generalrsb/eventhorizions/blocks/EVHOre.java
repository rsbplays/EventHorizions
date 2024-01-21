package generalrsb.eventhorizions.blocks;

import generalrsb.eventhorizions.EventHorizions;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class EVHOre extends EVHBlock {
    int xp;

    public EVHOre(int xp) {
        super();
        this.xp = xp;
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if (willHarvest){
            ExperienceOrb orb = new ExperienceOrb(level,pos.getX(),pos.getY(),pos.getZ(),xp);
            level.addFreshEntity(orb);
        }
        return true;
    }
}
