package clover.common.core;

import clover.common.util.Configuration;
import clover.common.util.References;
import clover.common.util.Registry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

import java.util.Random;

@Mod(modid = References.MOD_ID, name = References.MOD_NAME, version = References.MOD_VERSION, useMetadata = true)
public class MagicClover
{
	@SidedProxy(clientSide = References.CLIENT_PROXY, serverSide = References.COMMON_PROXY)
	public static CommonProxy proxy;

	@Instance(References.MOD_ID)
	public static MagicClover instance;

	public static Random rand = new Random();

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new clover.common.handlers.EventHandler());
		Configuration.init(event.getSuggestedConfigurationFile());
		proxy.init();
		proxy.initClient();
	}

	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		Registry.load();
		MinecraftForge.addGrassSeed(new ItemStack(CommonProxy.clover, 1, 0), Configuration.chance);
	}
}