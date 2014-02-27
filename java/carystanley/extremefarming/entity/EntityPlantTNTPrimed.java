package carystanley.extremefarming.entity;

import carystanley.extremefarming.world.PlantExplosion;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class EntityPlantTNTPrimed extends EntityTNTPrimed
{
	public Block block;

    public EntityPlantTNTPrimed(World par1World)
    {
        super(par1World);
        fuse = 80;
        if (par1World.isRemote) {
            System.out.println("SPAWN CLIENT");
        }
    }

    public EntityPlantTNTPrimed(Block block, World par1World)
    {
        super(par1World);
        this.block = block;
    }

    public EntityPlantTNTPrimed(Block block, World par1World, double par2, double par4, double par6, EntityLivingBase par8EntityLivingBase)
    {
        super(par1World, par2, par4, par6, par8EntityLivingBase);
        this.block = block;
    }

    @Override
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY -= 0.03999999910593033D;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
            this.motionY *= -0.5D;
        }

        if (this.fuse-- <= 0)
        {
            this.setDead();

            if (!this.worldObj.isRemote)
            {
                this.myexplode();
            }
        }
        else
        {
            this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
        }
    }

    private void myexplode()
    {
        float f = 4.0F;
        this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, f, false);

        PlantExplosion explosion = new PlantExplosion(this.block, this.worldObj, this, this.posX, this.posY, this.posZ, f);
        explosion.doExplosionA();
        explosion.doExplosionB(false);
    }
}