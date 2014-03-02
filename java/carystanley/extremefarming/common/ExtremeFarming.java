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

    public static final int FLOWER_TNT = 0;
    public static final int WHEAT_TNT = 1;
    public static final int CARROT_TNT = 2;
    public static final int POTATO_TNT = 3;
    public static final int TREE_TNT = 4;
    public static final int WART_TNT = 5;
    public static final int MUSHROOM_TNT = 6;
    public static final int MELON_TNT = 7;
    public static final int PUMPKIN_TNT = 8;
    public static final int DIRT_TNT = 9;
    
    public static Block flowerTnt = (new BlockPlantTNT(FLOWER_TNT)).setHardness(0.0F).setBlockName("extremefarming:flowerTNT").setBlockTextureName("extremefarming:tnt_flower");
    public static Block wheatTnt = (new BlockPlantTNT(WHEAT_TNT)).setHardness(0.0F).setBlockName("extremefarming:wheatTNT").setBlockTextureName("extremefarming:tnt_wheat");
    public static Block carrotTnt = (new BlockPlantTNT(CARROT_TNT)).setHardness(0.0F).setBlockName("extremefarming:carrotTNT").setBlockTextureName("extremefarming:tnt_carrot");
    public static Block potatoTnt = (new BlockPlantTNT(POTATO_TNT)).setHardness(0.0F).setBlockName("extremefarming:potatoTNT").setBlockTextureName("extremefarming:tnt_potato");
    public static Block treeTnt = (new BlockPlantTNT(TREE_TNT)).setHardness(0.0F).setBlockName("extremefarming:treeTNT").setBlockTextureName("extremefarming:tnt_tree");
    public static Block wartTnt = (new BlockPlantTNT(WART_TNT)).setHardness(0.0F).setBlockName("extremefarming:wartTNT").setBlockTextureName("extremefarming:tnt_wart");
    public static Block mushroomTnt = (new BlockPlantTNT(MUSHROOM_TNT)).setHardness(0.0F).setBlockName("extremefarming:mushroomTNT").setBlockTextureName("extremefarming:tnt_brown_mushroom");
    public static Block melonTnt = (new BlockPlantTNT(MELON_TNT)).setHardness(0.0F).setBlockName("extremefarming:melonTNT").setBlockTextureName("extremefarming:tnt_melon");
    public static Block pumpkinTnt = (new BlockPlantTNT(PUMPKIN_TNT)).setHardness(0.0F).setBlockName("extremefarming:pumpkinTNT").setBlockTextureName("extremefarming:tnt_pumpkin");
    public static Block dirtTnt = (new BlockPlantTNT(DIRT_TNT)).setHardness(0.0F).setBlockName("extremefarming:dirtTNT").setBlockTextureName("extremefarming:tnt_dirt");

    public static final Object[][] plantTntConfig = new Object[][] {
        {flowerTnt, Blocks.red_flower, 10, 0},
        {wheatTnt, Blocks.wheat, 8, 0},
        {carrotTnt, Blocks.carrots, 8, 0},
        {potatoTnt, Blocks.potatoes, 8, 0},
        {treeTnt, Blocks.sapling, 6, 0},
        {wartTnt, Blocks.nether_wart, 8, 0},
        {mushroomTnt, Blocks.brown_mushroom, 1, 0},
        {melonTnt, Blocks.melon_block, 1, 0},
        {pumpkinTnt, Blocks.pumpkin, 1, 0},
        {dirtTnt, Blocks.farmland, 1, 1},
    };

    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        GameRegistry.registerBlock(flowerTnt, "extremefarming:flowerTNT");
        GameRegistry.registerBlock(wheatTnt, "extremefarming:wheatTNT");
        GameRegistry.registerBlock(carrotTnt, "extremefarming:carrotTNT");
        GameRegistry.registerBlock(potatoTnt, "extremefarming:potatoTNT");
        GameRegistry.registerBlock(treeTnt, "extremefarming:treeTNT");
        GameRegistry.registerBlock(wartTnt, "extremefarming:wartTNT");
        GameRegistry.registerBlock(mushroomTnt, "extremefarming:mushroomTNT");
        GameRegistry.registerBlock(pumpkinTnt, "extremefarming:pumpkinTNT");
        GameRegistry.registerBlock(melonTnt, "extremefarming:melonTNT");
        GameRegistry.registerBlock(dirtTnt, "extremefarming:dirtTNT");
        // cocoa?
	}

    @EventHandler
	public void init(FMLInitializationEvent event) {
    	loadRecipes();
    	loadRenderers();
	}
	
	public void loadRecipes() {
		GameRegistry.addRecipe(new ItemStack(flowerTnt, 1), new Object[] {"X#X", "#X#", "X#X", 'X', Items.gunpowder, '#', Blocks.red_flower});
		GameRegistry.addRecipe(new ItemStack(flowerTnt, 1), new Object[] {"X#X", "#X#", "X#X", 'X', Items.gunpowder, '#', Blocks.yellow_flower});
		GameRegistry.addRecipe(new ItemStack(wheatTnt, 1), new Object[] {"X#X", "#X#", "X#X", 'X', Items.gunpowder, '#', Items.wheat});
		GameRegistry.addRecipe(new ItemStack(carrotTnt, 1), new Object[] {"X#X", "#X#", "X#X", 'X', Items.gunpowder, '#', Items.carrot});
		GameRegistry.addRecipe(new ItemStack(potatoTnt, 1), new Object[] {"X#X", "#X#", "X#X", 'X', Items.gunpowder, '#', Items.potato});		
		GameRegistry.addRecipe(new ItemStack(treeTnt, 1), new Object[] {"X#X", "#X#", "X#X", 'X', Items.gunpowder, '#', Blocks.sapling});		
		GameRegistry.addRecipe(new ItemStack(wartTnt, 1), new Object[] {"X#X", "#X#", "X#X", 'X', Items.gunpowder, '#', Items.nether_wart});		
		GameRegistry.addRecipe(new ItemStack(mushroomTnt, 1), new Object[] {"X#X", "#X#", "X#X", 'X', Items.gunpowder, '#', Blocks.brown_mushroom});		
		GameRegistry.addRecipe(new ItemStack(mushroomTnt, 1), new Object[] {"X#X", "#X#", "X#X", 'X', Items.gunpowder, '#', Blocks.red_mushroom});		
		GameRegistry.addRecipe(new ItemStack(melonTnt, 1), new Object[] {"X#X", "#X#", "X#X", 'X', Items.gunpowder, '#', Blocks.melon_block});		
		GameRegistry.addRecipe(new ItemStack(pumpkinTnt, 1), new Object[] {"X#X", "#X#", "X#X", 'X', Items.gunpowder, '#', Blocks.pumpkin});		
		GameRegistry.addRecipe(new ItemStack(dirtTnt, 1), new Object[] {"X#X", "#X#", "X#X", 'X', Items.gunpowder, '#', Blocks.dirt});		
	}
	
	public void loadRenderers() {
    	EntityRegistry.registerModEntity(EntityPlantTNTPrimed.class, "plantTntPrimed", 1, this, 80, 3, true);
        if (FMLCommonHandler.instance().getSide().isClient()) {
			RenderingRegistry.registerEntityRenderingHandler(EntityPlantTNTPrimed.class, new RenderPlantTNTPrimed());
        }
	}

}
