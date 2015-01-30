package clover.common.util;

import clover.MagicClover;
import com.google.common.collect.Lists;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import java.util.List;

public class Registry
{
	public static List<String> bannedMods = Lists.newArrayList();
	public static List<Integer> bannedItemIDs = Lists.newArrayList();
	public static List<Integer> rareItemIDs = Lists.newArrayList();
	public static List<Integer> items = Lists.newArrayList();

	public static String[] defaultBanned = { "minecraft:flowing_lava", "minecraft:web", "minecraft:powered_comparator", "minecraft:water", "minecraft:flowing_water", "minecraft:lava", "minecraft:tripwire", "minecraft:farmland", "minecraft:unpowered_comparator", "minecraft:vine", "minecraft:standing_sign", "minecraft:bed", "minecraft:end_portal_frame", "minecraft:piston_extension", "minecraft:cauldron", "minecraft:pumpkin_stem", "minecraft:potatoes", "minecraft:unpowered_repeater", "minecraft:brewing_stand", "minecraft:melon_stem", "minecraft:end_portal", "minecraft:flower_pot", "minecraft:reeds", "minecraft:command_block", "minecraft:bedrock", "minecraft:piston_head", "minecraft:redstone_wire", "minecraft:wall_sign", "minecraft:nether_wart", "minecraft:spawn_egg", "minecraft:portal", "minecraft:iron_door", "minecraft:wheat", "minecraft:wooden_door", "minecraft:deadbush", "minecraft:mob_spawner", "minecraft:carrots", "minecraft:cocoa", "minecraft:powered_repeater", "minecraft:enchanted_book", "minecraft:tripwire_hook", "minecraft:netherrack", "minecraft:tallgrass", "minecraft:command_block_minecart", "minecraft:farmland", "minecraft:lit_furnace", "minecraft:barrier", "minecraft:written_book", "minecraft:firework_charge", "minecraft:fierworks" };
	public static String[] defaultRare = { "minecraft:dragon_egg", "minecraft:beacon", "minecraft:nether_star", "minecraft:diamond_block", "minecraft:sponge", "minecraft:emerald_block", "minecraft:enchanting_table", "minecraft:end_portal_frame", "minecraft:ender_chest", "minecraft:jukebox", "minecraft:saddle", "minecraft:golden_apple", "minecraft:diamond_shovel", "minecraft:diamond_pickaxe", "minecraft:diamond_axe", "minecraft:diamond_sword", "minecraft:diamond", "minecraft:emerald", "minecraft:ender_eye", "minecraft:diamond_helmet", "minecraft:diamond_chestplate", "minecraft:diamond_leggings", "minecraft:diamond_boots", "minecraft:diamond_hoe", "minecraft:gold_block", "minecraft:iron_block" };

	public static void registerBannedMod(String mod)
	{
		System.out.println(mod);
		if (!bannedMods.contains(mod))
		{
			System.out.println(mod);
			bannedMods.add(mod);
		}
	}

	public static void registerBannedItem(Object item)
	{
		if (item != null)
		{
			int id = Item.itemRegistry.getIDForObject(item);

			if (!bannedItemIDs.contains(id))
			{
				bannedItemIDs.add(id);
			}
		}
	}

	public static void registerRareItem(Object item)
	{
		if (item != null)
		{
			int id = Item.itemRegistry.getIDForObject(item);

			if (!rareItemIDs.contains(id) && !bannedItemIDs.contains(id))
			{
				rareItemIDs.add(id);
			}
		}
	}

	public static void addItem(Object item)
	{
		if (item != null)
		{
			int id = Item.itemRegistry.getIDForObject(item);

			if (!items.contains(id) && !bannedItemIDs.contains(id))
			{
				items.add(id);
			}
		}
	}

	public static void load()
	{
		if (items.isEmpty())
		{
			for (Object obj : Item.itemRegistry)
			{
				Item item = (Item) obj;
				ResourceLocation resource = (ResourceLocation) Item.itemRegistry.getNameForObject(item);
				int id = Item.itemRegistry.getIDForObject(item);

				if (!bannedMods.contains(resource.getResourceDomain()) && !bannedItemIDs.contains(id) && StatCollector.canTranslate(item.getUnlocalizedName() + ".name"))
				{
					items.add(id);
				}
			}
		}

		System.out.println(items.size() * 32 / 1024 + " kb");
	}

	public static Item getRandomItem()
	{
		if (Registry.items.size() > 0)
		{
			int randomID = MagicClover.rand.nextInt(Registry.items.size());
			int rare = MagicClover.rand.nextInt(5);

			if (!rareItemIDs.contains(Registry.items.get(randomID)))
			{
				return (Item) Item.itemRegistry.getObjectById(Registry.items.get(randomID));
			} else
			{
				if (rare == 1)
				{
					return (Item) Item.itemRegistry.getObjectById(Registry.items.get(randomID));
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
}