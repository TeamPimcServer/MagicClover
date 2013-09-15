package clover.common.util;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Property;
import clover.common.core.CommonProxy;
import cpw.mods.fml.common.FMLLog;

public class Configuration
{
	public static boolean allowGarbage; 
	public static int chance;
	
	public static void init(File file)
	{
		net.minecraftforge.common.Configuration config = new net.minecraftforge.common.Configuration(file);

		try
		{
			config.load();
			
			CommonProxy.cloverID = config.get(net.minecraftforge.common.Configuration.CATEGORY_ITEM, "Magic Clover", 26400).getInt();
			
			Property chanceToDrop = config.get(net.minecraftforge.common.Configuration.CATEGORY_GENERAL, "Chance to drop from grass", 15);
			chanceToDrop.comment = "Chance to drop clover from grass (10 is vanilla wheat seed chance)";
			chance = chanceToDrop.getInt();
		} catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "MagicClover Mod has had a problem loading it's configuration file");
		} finally
		{
			config.save();
		}
	}
}
