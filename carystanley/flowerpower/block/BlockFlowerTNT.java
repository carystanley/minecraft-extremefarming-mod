package carystanley.flowerpower.block;

import net.minecraft.block.BlockTNT;

import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.entity.EntityLivingBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;




import java.util.Random;
import net.minecraft.block.material.Material;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;

import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;


public class BlockFlowerTNT extends BlockTNT
{
    @SideOnly(Side.CLIENT)
    private Icon side_a;
    @SideOnly(Side.CLIENT)
    private Icon side_b;

    public BlockFlowerTNT(int par1)
    {
        super(par1);
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        return par1 == 0 ? this.side_b : (par1 == 1 ? this.side_a : this.blockIcon);
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World par1World, int par2, int par3, int par4, Explosion par5Explosion)
    {
        if (!par1World.isRemote)
        {
            EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(par1World, (double)((float)par2 + 0.5F), (double)((float)par3 + 0.5F), (double)((float)par4 + 0.5F), par5Explosion.getExplosivePlacedBy());
            entitytntprimed.fuse = par1World.rand.nextInt(entitytntprimed.fuse / 4) + entitytntprimed.fuse / 8;
            par1World.spawnEntityInWorld(entitytntprimed);
        }
    }

    /**
     * spawns the primed tnt and plays the fuse sound.
     */
    public void primeTnt(World par1World, int par2, int par3, int par4, int par5, EntityLivingBase par6EntityLivingBase)
    {
        if (!par1World.isRemote)
        {
            if ((par5 & 1) == 1)
            {
                EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(par1World, (double)((float)par2 + 0.5F), (double)((float)par3 + 0.5F), (double)((float)par4 + 0.5F), par6EntityLivingBase);
                par1World.spawnEntityInWorld(entitytntprimed);
                par1World.playSoundAtEntity(entitytntprimed, "random.fuse", 1.0F, 1.0F);
            }
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(this.getTextureName() + "_side");
        this.side_a = par1IconRegister.registerIcon(this.getTextureName() + "_top");
        this.side_b = par1IconRegister.registerIcon(this.getTextureName() + "_bottom");
    }
}
