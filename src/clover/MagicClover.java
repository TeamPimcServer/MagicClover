package clover;

import clover.common.item.Clover;
import clover.common.util.Config;
import clover.common.util.References;
import clover.common.util.Registry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
//import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatBasic;
//import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

@Mod(modid = References.MOD_ID, name = References.MOD_NAME, version = "@VERSION@", useMetadata = true, guiFactory = References.GUI_FACTORY, acceptedMinecraftVersions="[1.9,]")
public class MagicClover
{
	@Mod.Instance(References.MOD_ID)
	public static MagicClover instance;

	public static StatBase cloversUsed = new StatBasic("stat.cloversUsed", new TextComponentString("Clovers Used")).registerStat();
	public static Item clover;
	public static Random rand = new Random();
	public static Logger logger = LogManager.getLogger(References.MOD_ID);

	@Mod.EventHandler
	public static void preInit(FMLPreInitializationEvent event)
	{
		Config.init(event.getSuggestedConfigurationFile());
		clover = new Clover();
		if(event.getSide() == Side.CLIENT)
			ModelLoader.setCustomModelResourceLocation(clover, 0, new ModelResourceLocation("magicclover:clover", "inventory"));
	}

	@Mod.EventHandler
	public static void init(FMLInitializationEvent event)
	{


		MinecraftForge.EVENT_BUS.register(instance);
		MinecraftForge.addGrassSeed(new ItemStack(clover), Config.dropChance);
	}

	@Mod.EventHandler
	public static void postInit(FMLPostInitializationEvent event)
	{
		Registry.load();
	}

	@SubscribeEvent
	public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if(event.getModID().equals(References.MOD_ID))
		{
			Registry.clear();
			Config.load();
			Registry.load();
		}
	}
}