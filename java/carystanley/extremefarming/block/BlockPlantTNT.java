package carystanley.extremefarming.block;

import carystanley.extremefarming.common.ExtremeFarming;
import carystanley.extremefarming.entity.EntityPlantTNTPrimed;
import net.minecraft.world.World;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;

public class BlockPlantTNT extends BlockTNT
{
    public int type;
	
    public BlockPlantTNT(int type)
    {
        super();
        this.type = type;
    }

    @Override
    public void func_150114_a(World p_150114_1_, int p_150114_2_, int p_150114_3_, int p_150114_4_, int p_150114_5_, EntityLivingBase p_150114_6_)
    {
        if (!p_150114_1_.isRemote)
        {
            if ((p_150114_5_ & 1) == 1)
            {
            	EntityPlantTNTPrimed entitytntprimed;
                entitytntprimed = new EntityPlantTNTPrimed(p_150114_1_, (double)((float)p_150114_2_ + 0.5F), (double)((float)p_150114_3_ + 0.5F), (double)((float)p_150114_4_ + 0.5F), p_150114_6_);  
                entitytntprimed.type = this.type;
                p_150114_1_.spawnEntityInWorld(entitytntprimed);
                p_150114_1_.playSoundAtEntity(entitytntprimed, "game.tnt.primed", 1.0F, 1.0F);
            }
        }
    }
}