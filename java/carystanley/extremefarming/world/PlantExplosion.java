package carystanley.extremefarming.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import carystanley.extremefarming.common.ExtremeFarming;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class PlantExplosion extends Explosion
{
	private World worldObj;
	public int type;
	protected Random explosionRNG = new Random();

    public PlantExplosion(int type, World par1World, Entity par2Entity, double par3, double par5, double par7, float par9)
    {
        super(par1World, par2Entity, par3, par5, par7, par9);
        this.type = type;
        this.worldObj = par1World;
    }

    @Override
    public void doExplosionB(boolean par1)
    {
        Iterator iterator;
        ChunkPosition chunkposition;
        int i;
        int j;
        int k;
        Block block;

        iterator = this.affectedBlockPositions.iterator();

        while (iterator.hasNext())
        {
            chunkposition = (ChunkPosition)iterator.next();
            i = chunkposition.chunkPosX;
            j = chunkposition.chunkPosY;
            k = chunkposition.chunkPosZ;
            block = this.worldObj.getBlock(i, j, k);
            Block block1 = this.worldObj.getBlock(i, j - 1, k);

            if (block.getMaterial() == Material.air && (block1.getMaterial() == Material.ground || block1.getMaterial() == Material.grass || block1.getMaterial() == Material.sand) && this.explosionRNG.nextInt(3) != 0)
            {
            	if ((Integer)ExtremeFarming.plantTntConfig[this.type][3] == 1) {
                    this.worldObj.setBlock(i, j-1, k, (Block)ExtremeFarming.plantTntConfig[this.type][1], this.explosionRNG.nextInt((Integer)ExtremeFarming.plantTntConfig[this.type][2]), 3);            		
            	} else {
                    this.worldObj.setBlock(i, j, k, (Block)ExtremeFarming.plantTntConfig[this.type][1], this.explosionRNG.nextInt((Integer)ExtremeFarming.plantTntConfig[this.type][2]), 3);
            	}
            }
        }
    }

}