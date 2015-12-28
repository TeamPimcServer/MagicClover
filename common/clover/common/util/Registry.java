package clover.common.util;

import clover.common.MagicClover;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import cpw.mods.fml.common.registry.GameData;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.Level;

import java.util.List;
import java.util.regex.Pattern;

public class Registry
{
	private static List<String> bannedItems = Lists.newArrayList();
	private static List<String> whitelistedItems = Lists.newArrayList();
	private static List<String> rareItems = Lists.newArrayList();
	private static List<String> items = Lists.newArrayList();

	private static final Pattern wildcard = Pattern.compile("(?<!\\w)\\*");
	private static final String regexWildcard = "[a-zA-Z0-9_]+";

	public static void registerBannedItem(String item)
	{
		if (item != null)
		{
			String[] parts = item.split(":");
			if (parts.length == 2)
				item += ":[0-9]+";

			bannedItems.add(wildcard.matcher(item).replaceAll(regexWildcard));
		}
	}

	public static void registerWhitelistItem(String item)
	{
		if (item != null)
		{
			String[] parts = item.split(":");
			if (parts.length == 2)
				item += ":[0-9]+";

			whitelistedItems.add(wildcard.matcher(item).replaceAll(regexWildcard));
		}
	}

	public static void registerRareItem(String item)
	{
		if (item != null)
		{
			String[] parts = item.split(":");
			if (parts.length == 2)
				item += ":[0-9]+";

			rareItems.add(wildcard.matcher(item).replaceAll(regexWildcard));
		}
	}

	public static void clear()
	{
		bannedItems.clear();
		whitelistedItems.clear();
		rareItems.clear();
		items.clear();
	}

	public static void load()
	{
		if (whitelistedItems.isEmpty())
		{
			forEachItem(new Predicate<Pair<Item, Integer>>()
			{
				@Override
				public boolean apply(Pair<Item, Integer> item)
				{
					String unlocalizedName = item.getLeft().getUnlocalizedName(new ItemStack(item.getLeft(), 1, item.getRight()));
					String name = GameData.getItemRegistry().getNameForObject(item.getLeft());
					String id = name + ":" + item.getRight();
					boolean canTranslate = unlocalizedName != null && StatCollector.canTranslate(unlocalizedName + ".name");

					if (!isBanned(id) && canTranslate)
						items.add(id);

					return false;
				}
			});
		} else
		{
			forEachItem(new Predicate<Pair<Item, Integer>>()
			{
				@Override
				public boolean apply(Pair<Item, Integer> item)
				{
					String name = GameData.getItemRegistry().getNameForObject(item.getLeft());
					String id = name + ":" + item.getRight();

					if (isWhitelisted(id) && !isBanned(id))
						items.add(id);

					return false;
				}
			});
		}

		MagicClover.logger.log(Level.INFO, items.size() + " entries");
	}

	public static ItemStack getRandomItem()
	{
		if (Registry.items.size() > 0)
		{
			int randomID = MagicClover.rand.nextInt(Registry.items.size());
			String itemName = Registry.items.get(randomID);
			String[] parts = itemName.split(":");
			Item item = GameData.getItemRegistry().getObject(parts[0] + ":" + parts[1]);
			int meta = Integer.parseInt(parts[2]);

			if (!isRare(itemName))
				return new ItemStack(item, 1, meta);
			else if (MagicClover.rand.nextInt(5) == 1)
				return new ItemStack(item, 1, meta);
			else
				return getRandomItem();

		} else
		{
			return new ItemStack(Blocks.stone);
		}
	}

	// Very hacky item dump here, don't look
	private static void forEachItem(Predicate<Pair<Item, Integer>> predicate)
	{
		for (Object obj : GameData.getItemRegistry())
		{
			Item item = (Item) obj;
			int meta = 0;

			if (item.getHasSubtypes())
			{
				List<String> names = Lists.newArrayList();

				while (meta < 15)
				{
					String current = item.getUnlocalizedName(new ItemStack(item, 1, meta));
					String next = item.getUnlocalizedName(new ItemStack(item, 1, meta + 1));

					if (current != null && next != null && !names.contains(next) && !item.getUnlocalizedName(new ItemStack(item, 1, meta)).equals(item.getUnlocalizedName(new ItemStack(item, 1, meta + 1))))
					{
						names.add(current);
						meta++;
					} else
					{
						break;
					}
				}
			}

			for (int i = 0; i <= meta; i++)
				predicate.apply(new ImmutablePair<Item, Integer>(item, i));
		}
	}

	private static boolean isBanned(String item)
	{
		for (String entry : bannedItems)
			if (item.matches(entry))
				return true;

		return false;
	}

	private static boolean isWhitelisted(String item)
	{
		for (String entry : whitelistedItems)
			if (item.matches(entry))
				return true;

		return false;
	}

	private static boolean isRare(String item)
	{
		for (String entry : rareItems)
			if (item.matches(entry))
				return true;

		return false;
	}
}