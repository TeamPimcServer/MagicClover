package clover.common;

import clover.client.render.RenderCloverItem;
import clover.common.item.Clover;
import clover.common.util.Config;
import clover.common.util.References;
import clover.common.util.Registry;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatBasic;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

@Mod(modid = References.MOD_ID, name = References.MOD_NAME, version = "@VERSION@", useMetadata = true, guiFactory = References.GUI_FACTORY)
public class MagicClover
{
	@Mod.Instance(References.MOD_ID)
	public static MagicClover instance;

	public static StatBase cloversUsed = new StatBasic("stat.cloversUsed", new ChatComponentText("Clovers Used")).registerStat();
	public static Item clover;
	public static Random rand = new Random();
	public static Logger logger = LogManager.getLogger(References.MOD_ID);

	@Mod.EventHandler
	public static void preInit(FMLPreInitializationEvent event)
	{
		Config.init(event.getSuggestedConfigurationFile());
		clover = new Clover();

		if (event.getSide() == Side.CLIENT)
			MinecraftForgeClient.registerItemRenderer(clover, new RenderCloverItem());
	}

	@Mod.EventHandler
	public static void init(FMLInitializationEvent event)
	{
		FMLCommonHandler.instance().bus().register(instance);
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
		if (event.modID.equals(References.MOD_ID))
		{
			Registry.clear();
			Config.load();
			Registry.load();
		}
	}
}