package clover.common.util;

import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

public class Config
{
	public static Configuration config;
	public static int chance;

	public static void init(File file)
	{
		if (config == null)
		{
			config = new Configuration(file);
			load();
		}
	}

	public static void load()
	{
		Registry.bannedMods.clear();
		Registry.bannedItemIDs.clear();
		Registry.rareItemIDs.clear();
		Registry.items.clear();

		Property chanceToDrop = config.get(Configuration.CATEGORY_GENERAL, "Chance to drop from grass", 6);
		chanceToDrop.comment = "Chance to drop clover from grass (10 is vanilla seeds chance)";
		chance = chanceToDrop.getInt();

		Property bannedMods = config.get(Configuration.CATEGORY_GENERAL, "Banned Mods", new String[] { });
		bannedMods.comment = "Items from these mods will not drop from clover. Entries on this list are mod ids. You can find them in Mods tab (main menu). You can treat \"minecraft\" as a mod here.";
		if (bannedMods.isList())
		{
			String[] mods = bannedMods.getStringList();

			for (String s : mods)
			{
				System.out.println(s);
				Registry.registerBannedMod(s);
			}
		}

		Property rare = config.get(Configuration.CATEGORY_GENERAL, "Rare Items", Registry.defaultRare);
		rare.comment = "These items will rarely drop from clover. Supports item names (like minecraft:nether_star) and numeric IDs!";
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
						Registry.registerRareItem(Item.getItemById(i));
					}
				} catch (NumberFormatException e)
				{
					Registry.registerRareItem(Item.itemRegistry.getObject(s));
				}
			}
		}

		Property banned = config.get(Configuration.CATEGORY_GENERAL, "Banned Items", Registry.defaultBanned);
		banned.comment = "These items will not drop from clover. Supports item names (like minecraft:stone) and numeric IDs!";
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
						Registry.registerBannedItem(i);
					}
				} catch (NumberFormatException e)
				{
					Registry.registerBannedItem(Item.itemRegistry.getObject(s));
				}
			}
		}

		Property whitelist = config.get(Configuration.CATEGORY_GENERAL, "Item Whitelist", new String[] { });
		whitelist.comment = "If not empty, these items will be only ones that drop from clover. Formatted like other lists in this config. Supports item names (like minecraft:dirt) and numeric IDs!";
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
						Registry.addItem(i);
					}
				} catch (NumberFormatException e)
				{
					Registry.addItem(Item.itemRegistry.getObject(s));
				}
			}
		}

		if (config.hasChanged())
		{
			config.save();
		}
	}
}