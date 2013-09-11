package clover.common.core;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import clover.common.util.Configuration;
import clover.common.util.References;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = References.MOD_ID, name = References.MOD_NAME, version = References.MOD_VERSION, useMetadata = true)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class MagicClover
{
	@SidedProxy(clientSide = References.CLIENT_PROXY, serverSide = References.COMMON_PROXY)
	public static CommonProxy proxy;

	@Instance(References.MOD_ID)
	public static MagicClover instance;

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
		MinecraftForge.addGrassSeed(new ItemStack(CommonProxy.cloverID + 256, 1, 0), 12);
	}

	@EventHandler
	public static void modLoaded(FMLPostInitializationEvent event)
	{
		
	}
}
