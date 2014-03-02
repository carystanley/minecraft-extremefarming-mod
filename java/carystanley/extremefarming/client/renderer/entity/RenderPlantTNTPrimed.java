package carystanley.extremefarming.client.renderer.entity;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderTNTPrimed;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import carystanley.extremefarming.common.ExtremeFarming;
import carystanley.extremefarming.entity.EntityPlantTNTPrimed;

public class RenderPlantTNTPrimed extends RenderTNTPrimed
{
    private RenderBlocks blockRenderer = new RenderBlocks();

    public RenderPlantTNTPrimed()
    {
    	super();
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    //@Override
    public void doRender(EntityPlantTNTPrimed par1EntityTNTPrimed, double par2, double par4, double par6, float par8, float par9)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        float f2;
        Block renderBlock = (Block)ExtremeFarming.plantTntConfig[par1EntityTNTPrimed.type][0];

        if ((float)par1EntityTNTPrimed.fuse - par9 + 1.0F < 10.0F)
        {
            f2 = 1.0F - ((float)par1EntityTNTPrimed.fuse - par9 + 1.0F) / 10.0F;

            if (f2 < 0.0F)
            {
                f2 = 0.0F;
            }

            if (f2 > 1.0F)
            {
                f2 = 1.0F;
            }

            f2 *= f2;
            f2 *= f2;
            float f3 = 1.0F + f2 * 0.3F;
            GL11.glScalef(f3, f3, f3);
        }

        f2 = (1.0F - ((float)par1EntityTNTPrimed.fuse - par9 + 1.0F) / 100.0F) * 0.8F;
        this.bindEntityTexture(par1EntityTNTPrimed);
        this.blockRenderer.renderBlockAsItem(renderBlock, 0, par1EntityTNTPrimed.getBrightness(par9));

        if (par1EntityTNTPrimed.fuse / 5 % 2 == 0)
        {
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_DST_ALPHA);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, f2);
            this.blockRenderer.renderBlockAsItem(renderBlock, 0, 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
        }

        GL11.glPopMatrix();
    }

/*
    protected ResourceLocation getEntityTexture(EntityTNTPrimed par1EntityTNTPrimed)
    {
        return TextureMap.locationBlocksTexture;
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getEntityTexture((EntityTNTPrimed)par1Entity);
    }
  */
    
    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.doRender((EntityPlantTNTPrimed)par1Entity, par2, par4, par6, par8, par9);
    }

}
