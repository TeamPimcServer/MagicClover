package clover.common.util;

import clover.common.core.MagicClover;
import com.google.common.collect.Lists;
import net.minecraft.item.Item;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Registry
{
	public static List<String> bannedItemIDs = Lists.newArrayList();
	public static List<String> rareItemIDs = Lists.newArrayList();
	public static List<String> items = Lists.newArrayList();

	public static String[] defaultBanned = { "minecraft:flowing_lava", "minecraft:web", "minecraft:powered_comparator", "minecraft:water", "minecraft:flowing_water", "minecraft:lava", "minecraft:tripwire", "minecraft:farmland", "minecraft:unpowered_comparator", "minecraft:vine", "minecraft:standing_sign", "minecraft:bed", "minecraft:end_portal_frame", "minecraft:piston_extension", "minecraft:cauldron", "minecraft:pumpkin_stem", "minecraft:potatoes", "minecraft:unpowered_repeater", "minecraft:brewing_stand", "minecraft:melon_stem", "minecraft:end_portal", "minecraft:flower_pot", "minecraft:reeds", "minecraft:command_block", "minecraft:bedrock", "minecraft:piston_head", "minecraft:redstone_wire", "minecraft:wall_sign", "minecraft:nether_wart", "minecraft:spawn_egg", "minecraft:portal", "minecraft:iron_door", "minecraft:wheat", "minecraft:wooden_door", "minecraft:deadbush", "minecraft:mob_spawner", "minecraft:carrots", "minecraft:cocoa", "minecraft:powered_repeater", "minecraft:enchanted_book", "minecraft:tripwire_hook", "minecraft:netherrack", "minecraft:tallgrass", "minecraft:command_block_minecart" };
	public static String[] defaultRare = { "minecraft:dragon_egg", "minecraft:beacon", "minecraft:nether_star", "minecraft:diamond_block", "minecraft:sponge", "minecraft:emerald_block", "minecraft:enchanting_table", "minecraft:end_portal_frame", "minecraft:ender_chest", "minecraft:jukebox", "minecraft:saddle", "minecraft:golden_apple", "minecraft:diamond_shovel", "minecraft:diamond_pickaxe", "minecraft:diamond_axe", "minecraft:diamond_sword", "minecraft:diamond", "minecraft:emerald", "minecraft:ender_eye", "minecraft:diamond_helmet", "minecraft:diamond_chestplate", "minecraft:diamond_leggings", "minecraft:diamond_boots", "minecraft:diamond_hoe", "minecraft:gold_block", "minecraft:iron_block" };

	public static void registerBannedItem(String id)
	{
		if (Item.itemRegistry.containsKey(id) && !bannedItemIDs.contains(id))
		{
			bannedItemIDs.add(id);
		}
	}

	public static void registerRareItem(String id)
	{
		if (Item.itemRegistry.containsKey(id) && !rareItemIDs.contains(id) && !bannedItemIDs.contains(id))
		{
			rareItemIDs.add(id);
		}
	}

	public static void addItem(String id)
	{
		if (Item.itemRegistry.containsKey(id) && !items.contains(id) && !bannedItemIDs.contains(id))
		{
			items.add(id);
		}
	}

	public static void load()
	{
		if (items.isEmpty())
		{
			Set mcItems = Item.itemRegistry.getKeys();
			Iterator<String> itemsIter = mcItems.iterator();

			while (itemsIter.hasNext())
			{
				String s = itemsIter.next();
				if (s != null && !s.isEmpty() && Item.itemRegistry.getObject(s) != null && !bannedItemIDs.contains(s))
				{
					items.add(s);
				}
			}
		}
	}

	public static Item getRandomItem()
	{
		int randomID = MagicClover.rand.nextInt(Registry.items.size());
		int rare = MagicClover.rand.nextInt(4);

		if (!isRareItem(Registry.items.get(randomID)))
		{
			return (Item) Item.itemRegistry.getObject(Registry.items.get(randomID));
		} else
		{
			if (rare == 1)
			{
				return (Item) Item.itemRegistry.getObject(Registry.items.get(randomID));
			} else
			{
				return getRandomItem();
			}
		}
	}

	public static boolean isRareItem(String id)
	{
		return rareItemIDs.contains(id);
	}
}