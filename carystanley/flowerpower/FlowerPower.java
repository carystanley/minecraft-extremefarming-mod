package carystanley.flowerpower;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;

//import carystanley.flowerpower.entity.EntityGigantopithecus;
//import carystanley.flowerpower.proxy.CommonProxy;
//import carystanley.flowerpower.proxy.ClientProxy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;

@Mod(modid = "carystanley_flowerpower", name = "Flower Power", version = "1.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class FlowerPower {

    @Instance("carystanley_flowerpower")
    public static FlowerPower instance;

    //@SidedProxy(clientSide = "carystanley.flowerpower.proxy.ClientProxy", serverSide = "carystanley.flowerpower.proxy.CommonProxy")
    //public static CommonProxy proxy;

	@Init
	public void load(FMLInitializationEvent event) {
		//proxy.registerEntityRenderHandler();
		
		loadRecipes();
		loadLang();
		loadMobs();
	}
	
	public void loadRecipes() {

	}

	public void loadMobs() {

	}
	
	public void loadLang() {

	}

}
