package clover.common.util;

import clover.MagicClover;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.util.regex.Pattern;

public class Config
{
	public static Configuration config;
	public static int dropChance;
	public static int creeperChance;

	private static final String[] defaultBanned = { "minecraft:flowing_lava", "minecraft:web", "minecraft:powered_comparator", "minecraft:water", "minecraft:flowing_water", "minecraft:lava", "minecraft:tripwire", "minecraft:farmland", "minecraft:unpowered_comparator", "minecraft:vine", "minecraft:standing_sign", "minecraft:bed", "minecraft:end_portal_frame", "minecraft:piston_extension", "minecraft:cauldron", "minecraft:pumpkin_stem", "minecraft:potatoes", "minecraft:unpowered_repeater", "minecraft:brewing_stand", "minecraft:melon_stem", "minecraft:end_portal", "minecraft:flower_pot", "minecraft:reeds", "minecraft:command_block", "minecraft:bedrock", "minecraft:piston_head", "minecraft:redstone_wire", "minecraft:wall_sign", "minecraft:nether_wart", "minecraft:spawn_egg", "minecraft:portal", "minecraft:iron_door", "minecraft:wheat", "minecraft:wooden_door", "minecraft:deadbush", "minecraft:mob_spawner", "minecraft:carrots", "minecraft:cocoa", "minecraft:powered_repeater", "minecraft:enchanted_book", "minecraft:tripwire_hook", "minecraft:netherrack", "minecraft:tallgrass", "minecraft:command_block_minecart", "minecraft:farmland", "minecraft:lit_furnace", "minecraft:barrier", "minecraft:written_book", "minecraft:firework_charge", "minecraft:fireworks", "minecraft:log:[4-5]", "minecraft:log2:[0-3]", "minecraft:stone_slab:2", "minecraft:cooked_fish:[2-3]", "minecraft:leaves:[4-5]", "minecraft:leaves2:[0-3]", "minecraft:map" };
	private static final String[] defaultWhitelisted = { "minecraft:*", "magicclover:clover" };
	private static final String[] defaultRare = { "minecraft:dragon_egg", "minecraft:beacon", "minecraft:nether_star", "minecraft:diamond_block", "minecraft:sponge", "minecraft:emerald_block", "minecraft:enchanting_table", "minecraft:end_portal_frame", "minecraft:ender_chest", "minecraft:jukebox", "minecraft:saddle", "minecraft:golden_apple", "minecraft:diamond_shovel", "minecraft:diamond_pickaxe", "minecraft:diamond_axe", "minecraft:diamond_sword", "minecraft:diamond", "minecraft:emerald", "minecraft:ender_eye", "minecraft:diamond_helmet", "minecraft:diamond_chestplate", "minecraft:diamond_leggings", "minecraft:diamond_boots", "minecraft:diamond_hoe", "minecraft:gold_block", "minecraft:iron_block" };
	private static final Pattern digitsOnly = Pattern.compile("[0-9]+");

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
		Property chanceToDrop = config.get(Configuration.CATEGORY_GENERAL, "Chance to drop from grass", 6);
		chanceToDrop.comment = "Chance to drop clover from grass (10 is vanilla seeds chance).\nCan be set to 0 for no drop.";
		dropChance = chanceToDrop.getInt();

		Property chanceForCreeper = config.get(Configuration.CATEGORY_GENERAL, "Chance to spawn creeper", 2);
		chanceForCreeper.comment = "Chance to spawn creeper from clover (in range 0-100)";
		creeperChance = chanceForCreeper.getInt();

		Property rare = config.get(Configuration.CATEGORY_GENERAL, "Rare Items", defaultRare);
		rare.comment = "These items will rarely drop from clover.\nAccepts item names only (like minecraft:nether_star).\nSupports wildcards (*).";
		if (rare.isList())
		{
			String[] items = rare.getStringList();

			for (String s : items)
			{
				if (digitsOnly.matcher(s).matches())
				{
					int id = Integer.parseInt(s);
					MagicClover.logger.log(Level.WARN, String.format("MagicClover: Item ids are not supported (%s).", id));
				} else
				{
					Registry.registerRareItem(s);
				}
			}
		}

		Property banned = config.get(Configuration.CATEGORY_GENERAL, "Banned Items", defaultBanned);
		banned.comment = "These items will not drop from clover.\nAccepts item names only (like minecraft:stone or thaumcraft:amber).\nSupports wildcards (*).";
		if (banned.isList())
		{
			String[] items = banned.getStringList();

			for (String s : items)
			{
				if (digitsOnly.matcher(s).matches())
				{
					int id = Integer.parseInt(s);
					MagicClover.logger.log(Level.WARN, String.format("MagicClover: Item ids are not supported (%s)!", id));
				} else
				{
					Registry.registerBannedItem(s);
				}
			}
		}

		Property whitelist = config.get(Configuration.CATEGORY_GENERAL, "Item Whitelist", defaultWhitelisted);
		whitelist.comment = "If not empty, these items will be the only ones that drop from clover.\nFormatted like other lists in this config.\nAccepts item names only (like minecraft:dirt or buildcraft:pipeWire).\nSupports wildcards (*).";
		if (whitelist.isList())
		{
			String[] items = whitelist.getStringList();

			for (String s : items)
			{
				if (digitsOnly.matcher(s).matches())
				{
					int id = Integer.parseInt(s);
					MagicClover.logger.log(Level.WARN, String.format("MagicClover: Item ids are not supported (%s)!", id));
				} else
				{
					Registry.registerWhitelistItem(s);
				}
			}
		}

		if (config.hasChanged())
			config.save();
	}
}