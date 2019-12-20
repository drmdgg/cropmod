package drmdgg.cropmod;

import org.apache.logging.log4j.LogManager;

import drmdgg.cropmod.init.blocks.plants.MPlant;
import drmdgg.cropmod.lists.BlockList;
import drmdgg.cropmod.lists.ItemList;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("cropmod")
public class CropMod 
{
	public static CropMod instance;
	public static final String modid = "cropmod";
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(modid);
	
	public static final ModItemGroup cropitems = new ModItemGroup("cropitems", ()->(ItemList.mseed));
	
	public CropMod() 
	{
		instance = this;
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		
		MinecraftForge.EVENT_BUS.register(this);		
	}
	
	private void setup(final FMLCommonSetupEvent event)
	{
		logger.info("Setup method registered.");
	}
	
	private void clientRegistries(final FMLClientSetupEvent event)
	{
		logger.info("clientRegistries method registered.");
	}
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event)
		{
			event.getRegistry().registerAll
			(
			new BlockNamedItem(BlockList.mplant, (new Item.Properties()).group(cropitems)).setRegistryName(location("mseed"))
          
			
			);
			logger.info("Items registered");
		}
		
		 @SubscribeEvent
	     public static void registerBlocks(final RegistryEvent.Register<Block> event) 
	        {
	            event.getRegistry().registerAll
	        (

	        		new MPlant(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().sound(SoundType.CROP)).setRegistryName(location("mplant")));
	               
	                logger.info("Blocks registered.");
	        }
		 

		 
		private static ResourceLocation location(String name)
		{
			return new ResourceLocation(CropMod.modid, name);
		}
		
		
	}
}

