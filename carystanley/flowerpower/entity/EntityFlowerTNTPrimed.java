package carystanley.flowerpower.entity;
import net.minecraft.entity.item.EntityTNTPrimed;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityFlowerTNTPrimed extends EntityTNTPrimed
{
    public EntityFlowerTNTPrimed(World par1World)
    {
        super(par1World);
    }

    public EntityFlowerTNTPrimed(World par1World, double par2, double par4, double par6, EntityLivingBase par8EntityLivingBase)
    {
        super(par1World, par2, par4, par6, par8EntityLivingBase);
    }

    private void explode()
    {
        float f = 4.0F;
        this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, f, true);
    }
}
