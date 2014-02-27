package carystanley.extremefarming.common;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod.EventHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;

import carystanley.extremefarming.client.renderer.entity.RenderPlantTNTPrimed;
import carystanley.extremefarming.block.BlockPlantTNT;
import carystanley.extremefarming.entity.EntityPlantTNTPrimed;

@Mod(modid = "carystanley_extremefarming", name = "Extreme Farming", version = "1.0")

public class ExtremeFarming {

    @Instance("carystanley_extremefarming")
    public static ExtremeFarming instance;

    // examples
    // https://code.google.com/p/gun-powder-mod/source/browse/trunk/gun-powder-mod/Minecraft/modSrc/gunPowderMod/?r=40
    // https://github.com/NexusTools/ExpandedTNT/blob/master/src/net/nexustools/expandedtnt/DynamicTNT/RenderDynamicTNTPrimed.java

    public static Block flowerTnt = (new BlockPlantTNT(Blocks.red_flower)).setHardness(0.0F).setBlockName("extremefarming:flowerTNT").setBlockTextureName("tnt");
    public static Block wheatTnt = (new BlockPlantTNT(Blocks.wheat)).setHardness(0.0F).setBlockName("extremefarming:wheatTNT").setBlockTextureName("extremefarming:tnt_wheat");
    public static Block carrotTnt = (new BlockPlantTNT(Blocks.carrots)).setHardness(0.0F).setBlockName("extremefarming:carrotTNT").setBlockTextureName("tnt");
    public static Block potatoTnt = (new BlockPlantTNT(Blocks.potatoes)).setHardness(0.0F).setBlockName("extremefarming:potatoTNT").setBlockTextureName("tnt");
    public static Block treeTnt = (new BlockPlantTNT(Blocks.sapling)).setHardness(0.0F).setBlockName("extremefarming:treeTNT").setBlockTextureName("tnt");
    public static Block wartTnt = (new BlockPlantTNT(Blocks.nether_wart)).setHardness(0.0F).setBlockName("extremefarming:wartTNT").setBlockTextureName("tnt");
    public static Block mushroomTnt = (new BlockPlantTNT(Blocks.brown_mushroom)).setHardness(0.0F).setBlockName("extremefarming:mushroomTNT").setBlockTextureName("tnt");

    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        GameRegistry.registerBlock(flowerTnt, "extremefarming:flowerTNT");
        GameRegistry.registerBlock(wheatTnt, "extremefarming:wheatTNT");
        GameRegistry.registerBlock(carrotTnt, "extremefarming:carrotTNT");
        GameRegistry.registerBlock(potatoTnt, "extremefarming:potatoTNT");
        GameRegistry.registerBlock(treeTnt, "extremefarming:treeTNT");
        GameRegistry.registerBlock(wartTnt, "extremefarming:wartTNT");
        GameRegistry.registerBlock(mushroomTnt, "extremefarming:mushroomTNT");
        // farmland
        // punkkin or Mellon?
        // cocoa
	}

    @EventHandler
	public void init(FMLInitializationEvent event) {
    	loadRecipes();
    	loadRenderers();
	}
	
	public void loadRecipes() {
		GameRegistry.addRecipe(new ItemStack(flowerTnt, 1), new Object[] {"X#X", "#X#", "X#X", 'X', Items.gunpowder, '#', Blocks.red_flower});
		GameRegistry.addRecipe(new ItemStack(wheatTnt, 1), new Object[] {"X#X", "#X#", "X#X", 'X', Items.gunpowder, '#', Items.wheat});
		GameRegistry.addRecipe(new ItemStack(carrotTnt, 1), new Object[] {"X#X", "#X#", "X#X", 'X', Items.gunpowder, '#', Items.carrot});
		GameRegistry.addRecipe(new ItemStack(potatoTnt, 1), new Object[] {"X#X", "#X#", "X#X", 'X', Items.gunpowder, '#', Items.potato});		
		GameRegistry.addRecipe(new ItemStack(treeTnt, 1), new Object[] {"X#X", "#X#", "X#X", 'X', Items.gunpowder, '#', Blocks.sapling});		
		GameRegistry.addRecipe(new ItemStack(wartTnt, 1), new Object[] {"X#X", "#X#", "X#X", 'X', Items.gunpowder, '#', Items.nether_wart});		
		GameRegistry.addRecipe(new ItemStack(mushroomTnt, 1), new Object[] {"X#X", "#X#", "X#X", 'X', Items.gunpowder, '#', Blocks.brown_mushroom});		
	}
	
	public void loadRenderers() {
        if (FMLCommonHandler.instance().getSide().isClient()) {
        	EntityRegistry.registerModEntity(EntityPlantTNTPrimed.class, "plantTntPrimed", 1, this, 80, 3, true);
			RenderingRegistry.registerEntityRenderingHandler(EntityPlantTNTPrimed.class, new RenderPlantTNTPrimed(wheatTnt));
        }
	}

}
