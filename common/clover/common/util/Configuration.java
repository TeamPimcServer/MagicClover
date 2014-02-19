package clover.common.util;

import clover.common.core.CommonProxy;
import cpw.mods.fml.common.FMLLog;
import net.minecraftforge.common.config.Property;

import java.io.File;
import java.util.logging.Level;

public class Configuration
{
	public static int chance;

	public static void init(File file)
	{
		net.minecraftforge.common.config.Configuration config = new net.minecraftforge.common.config.Configuration(file);

		try
		{
			config.load();

			CommonProxy.cloverID = config.get(net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL, "Magic Clover", 26400).getInt();

			Property chanceToDrop = config.get(net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL, "Chance to drop from grass", 11);
			chanceToDrop.comment = "Chance to drop clover from grass (10 is vanilla wheat seed chance)";
			chance = chanceToDrop.getInt();
		} catch (Exception e)
		{

		} finally
		{
			config.save();
		}
	}
}
