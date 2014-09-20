package clover.common.util;

import clover.common.core.CommonProxy;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Property;

import java.io.File;

public class Configuration
{
	public static int chance;

	public static void init(File file)
	{
		net.minecraftforge.common.config.Configuration config = new net.minecraftforge.common.config.Configuration(file);

		try
		{
			config.load();

			Property rare = config.get(net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL, "Rare Items", Registry.defaultRare);
			rare.comment = "Supports item names and numeric IDs (support for numeric IDs will be removed in near future)!";
			if (rare.isList())
			{
				String[] items = rare.getStringList();

				for (String s : items)
				{
					try
					{
						int i = Integer.parseInt(s);
						if (Item.getItemById(i) != null)
						{
							Registry.registerRareItem(Item.itemRegistry.getNameForObject(Item.getItemById(i)));
						}
					} catch (NumberFormatException e)
					{
						Registry.registerRareItem(s);
					}
				}
			}

			Property banned = config.get(net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL, "Banned Items", Registry.defaultBanned);
			banned.comment = "Supports item names and numeric IDs (support for numeric IDs will be removed in near future)!";
			if (banned.isList())
			{
				String[] items = banned.getStringList();

				for (String s : items)
				{
					try
					{
						int i = Integer.parseInt(s);
						if (Item.getItemById(i) != null)
						{
							Registry.registerBannedItem(Item.itemRegistry.getNameForObject(Item.getItemById(i)));
						}
					} catch (NumberFormatException e)
					{
						Registry.registerBannedItem(s);
					}
				}
			}

			Property whitelist = config.get(net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL, "Item whitelist", new String[] { });
			whitelist.comment = "If not empty, these items will be only ones that drop from clover. Formatted like other lists in this config. Supports item names and numeric IDs (support for numeric IDs will be removed in near future)!";
			if (whitelist.isList())
			{
				String[] items = whitelist.getStringList();

				for (String s : items)
				{
					try
					{
						int i = Integer.parseInt(s);
						if (Item.getItemById(i) != null)
						{
							Registry.addItem(Item.itemRegistry.getNameForObject(Item.getItemById(i)));
						}
					} catch (NumberFormatException e)
					{
						Registry.addItem(s);
					}
				}
			}

			Property cloverID = config.get(net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL, "Magic Clover", 26400);
			cloverID.comment = "Clover item ID";
			CommonProxy.cloverID = cloverID.getInt();

			Property chanceToDrop = config.get(net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL, "Chance to drop from grass", 8);
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