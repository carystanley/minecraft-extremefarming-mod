package carystanley.extremefarming.entity;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import carystanley.extremefarming.common.ExtremeFarming;
import carystanley.extremefarming.world.PlantExplosion;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityPlantTNTPrimed extends EntityTNTPrimed implements IEntityAdditionalSpawnData
{
	public int type;

    public EntityPlantTNTPrimed(World par1World)
    {
        super(par1World);
    }

    public EntityPlantTNTPrimed(World par1World, double par2, double par4, double par6, EntityLivingBase par8EntityLivingBase)
    {
        super(par1World, par2, par4, par6, par8EntityLivingBase);
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

        PlantExplosion explosion = new PlantExplosion(this.type, this.worldObj, this, this.posX, this.posY, this.posZ, f);
        explosion.doExplosionA();
        explosion.doExplosionB(false);
    }

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		this.fuse = additionalData.readInt();
		this.type = additionalData.readInt();
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		buffer.writeInt(this.fuse);
		buffer.writeInt(this.type);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) 
	{
		super.writeEntityToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setInteger("Type", this.type);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) 
	{
		super.readEntityFromNBT(par1nbtTagCompound);
		this.type = par1nbtTagCompound.getInteger("Type");
	}
}