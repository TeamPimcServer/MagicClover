package clover.common.util;

import clover.common.core.CommonProxy;
import clover.common.core.MagicClover;
import cpw.mods.fml.common.FMLLog;
import net.minecraftforge.common.MinecraftForge;
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

			Property rareItems = config.get(net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL, "Rare Items", Registry.defaultRare);
			rareItems.comment = "Rare item IDs";
			if(rareItems.isIntList())
			{
				int[] rItems = rareItems.getIntList();

				for (int i : rItems)
				{
					Registry.registerRareItem(i);
				}
			}

			Property bannedItems = config.get(net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL, "Banned Items", Registry.defaultBanned);
			bannedItems.comment = "Banned item IDs";
			if(bannedItems.isIntList())
			{
				int[] bItems = bannedItems.getIntList();

				for (int i : bItems)
				{
					Registry.registerBannedItem(i);
				}
			}

			Property cloverID = config.get(net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL, "Magic Clover", 26400);
			cloverID.comment = "Clover item ID";
			CommonProxy.cloverID = cloverID.getInt();

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
