package carystanley.extremefarming.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
	public Block block;
	protected Random explosionRNG = new Random();

    public PlantExplosion(Block block, World par1World, Entity par2Entity, double par3, double par5, double par7, float par9)
    {
        super(par1World, par2Entity, par3, par5, par7, par9);
        this.block = block;
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
                this.worldObj.setBlock(i, j, k, this.block, this.explosionRNG.nextInt(8), 3);
            }
        }
    }

}