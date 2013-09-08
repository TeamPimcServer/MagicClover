package clover.common.util;

import java.io.File;
import java.util.logging.Level;

import clover.common.core.CommonProxy;
import cpw.mods.fml.common.FMLLog;

public class Configuration
{
	public static void init(File file)
	{
		net.minecraftforge.common.Configuration config = new net.minecraftforge.common.Configuration(file);

		try
		{
			config.load();
			CommonProxy.cloverID = config.get(net.minecraftforge.common.Configuration.CATEGORY_ITEM, "Magic Clover", 26400).getInt();
		} catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "MagicClover Mod has had a problem loading it's configuration file");
		} finally
		{
			config.save();
		}
	}
}
