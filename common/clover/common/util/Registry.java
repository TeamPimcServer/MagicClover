package clover.common.util;

import clover.MagicClover;
import com.google.common.collect.Lists;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.common.registry.GameData;

import java.util.List;

public class Registry
{
	public static List<String> bannedMods = Lists.newArrayList();
	public static List<String> bannedItemIDs = Lists.newArrayList();
	public static List<String> rareItemIDs = Lists.newArrayList();
	public static List<String> items = Lists.newArrayList();

	public static final String[] defaultBanned = { "minecraft:flowing_lava", "minecraft:web", "minecraft:powered_comparator", "minecraft:water", "minecraft:flowing_water", "minecraft:lava", "minecraft:tripwire", "minecraft:farmland", "minecraft:unpowered_comparator", "minecraft:vine", "minecraft:standing_sign", "minecraft:bed", "minecraft:end_portal_frame", "minecraft:piston_extension", "minecraft:cauldron", "minecraft:pumpkin_stem", "minecraft:potatoes", "minecraft:unpowered_repeater", "minecraft:brewing_stand", "minecraft:melon_stem", "minecraft:end_portal", "minecraft:flower_pot", "minecraft:reeds", "minecraft:command_block", "minecraft:bedrock", "minecraft:piston_head", "minecraft:redstone_wire", "minecraft:wall_sign", "minecraft:nether_wart", "minecraft:spawn_egg", "minecraft:portal", "minecraft:iron_door", "minecraft:wheat", "minecraft:wooden_door", "minecraft:deadbush", "minecraft:mob_spawner", "minecraft:carrots", "minecraft:cocoa", "minecraft:powered_repeater", "minecraft:enchanted_book", "minecraft:tripwire_hook", "minecraft:netherrack", "minecraft:tallgrass", "minecraft:command_block_minecart", "minecraft:farmland", "minecraft:lit_furnace", "minecraft:barrier", "minecraft:written_book", "minecraft:firework_charge", "minecraft:fireworks" };
	public static final String[] defaultRare = { "minecraft:dragon_egg", "minecraft:beacon", "minecraft:nether_star", "minecraft:diamond_block", "minecraft:sponge", "minecraft:emerald_block", "minecraft:enchanting_table", "minecraft:end_portal_frame", "minecraft:ender_chest", "minecraft:jukebox", "minecraft:saddle", "minecraft:golden_apple", "minecraft:diamond_shovel", "minecraft:diamond_pickaxe", "minecraft:diamond_axe", "minecraft:diamond_sword", "minecraft:diamond", "minecraft:emerald", "minecraft:ender_eye", "minecraft:diamond_helmet", "minecraft:diamond_chestplate", "minecraft:diamond_leggings", "minecraft:diamond_boots", "minecraft:diamond_hoe", "minecraft:gold_block", "minecraft:iron_block" };

	public static void registerBannedMod(String mod)
	{
		if (!bannedMods.contains(mod))
		{
			bannedMods.add(mod);
		}
	}

	public static void registerBannedItem(String item)
	{
		if (item != null && !bannedItemIDs.contains(item))
		{
			bannedItemIDs.add(item.replace("*", "\\w*"));
		}
	}

	public static void registerRareItem(String item)
	{
		if (item != null && !rareItemIDs.contains(item) && !bannedItemIDs.contains(item))
		{
			rareItemIDs.add(item.replace("*", "\\w*"));
		}
	}

	public static void addItem(String item)
	{
		if (item != null && !items.contains(item) && !bannedItemIDs.contains(item))
		{
			items.add(item);
		}
	}

	public static void load()
	{
		Registry.items.clear();

		if (items.isEmpty())
		{
			for (Object obj : GameData.getItemRegistry())
			{
				Item item = (Item) obj;
				ResourceLocation name = (ResourceLocation) GameData.getItemRegistry().getNameForObject(item);

				if (!isModBanned(name.getResourceDomain()) && !isBanned(name.toString()) && StatCollector.canTranslate(item.getUnlocalizedName() + ".name"))
				{
					items.add(name.toString());
				}
			}

			int size = 0;
			for (String entry : items)
			{
				try
				{
					size += entry.getBytes("utf8").length;
				} catch (Exception ignored)
				{
				}
			}

			System.out.println(items.size() + " entries");
			System.out.println(Math.round(size / 1024) + " kb");
		}
	}

	public static Item getRandomItem()
	{
		if (Registry.items.size() > 0)
		{
			int randomID = MagicClover.rand.nextInt(Registry.items.size());
			int rare = MagicClover.rand.nextInt(5);

			if (!isRare(Registry.items.get(randomID)))
			{
				return GameData.getItemRegistry().getObject(Registry.items.get(randomID));
			} else
			{
				if (rare == 1)
				{
					return GameData.getItemRegistry().getObject(Registry.items.get(randomID));
				} else
				{
					return getRandomItem();
				}
			}
		} else
		{
			return ItemBlock.getItemFromBlock(Blocks.stone);
		}
	}

	public static boolean isBanned(String item)
	{
		for (String entry : bannedItemIDs)
		{
			if (entry.matches(item))
			{
				return true;
			}
		}

		return false;
	}

	public static boolean isRare(String item)
	{
		for (String entry : rareItemIDs)
		{
			if (item.matches(entry))
			{
				return true;
			}
		}

		return false;
	}

	private static boolean isModBanned(String mod)
	{
		for (String m : bannedMods)
		{
			if (m.equalsIgnoreCase(mod))
			{
				return true;
			}
		}

		return false;
	}
}