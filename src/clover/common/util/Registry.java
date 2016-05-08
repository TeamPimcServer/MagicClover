package clover.common.util;

import clover.MagicClover;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.Level;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

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
		forEachItem(new Predicate<Pair<Item, Integer>>()
		{
			@Override
			public boolean apply(Pair<Item, Integer> item)
			{
				String unlocalizedName = item.getLeft().getUnlocalizedName(new ItemStack(item.getLeft(), 1, item.getRight()));
				String name = Item.REGISTRY.getNameForObject(item.getLeft()).toString();
				String id = name + ":" + item.getRight();

				boolean canTranslate = unlocalizedName != null && I18n.canTranslate(unlocalizedName + ".name");

				if ((whitelistedItems.isEmpty() || isWhitelisted(id)) && !isBanned(id) && canTranslate)
					items.add(id);

				return false;
			}
		});

		MagicClover.logger.log(Level.INFO, items.size() + " entries");
	}

	public static ItemStack getRandomItem()
	{
		if (Registry.items.size() > 0)
		{
			int randomID = MagicClover.rand.nextInt(Registry.items.size());
			String itemName = Registry.items.get(randomID);
			String[] parts = itemName.split(":");
			Item item = Item.REGISTRY.getObject(new ResourceLocation(parts[0] + ":" + parts[1]));
			int meta = Integer.parseInt(parts[2]);

			if (!isRare(itemName))
				return new ItemStack(item, 1, meta);
			else if (MagicClover.rand.nextInt(5) == 1)
				return new ItemStack(item, 1, meta);
			else
				return getRandomItem();

		} else
		{
			return new ItemStack(Blocks.STONE);
		}
	}

	// Very hacky item search code here, don't look
	private static void forEachItem(Predicate<Pair<Item, Integer>> predicate)
	{
		for (Object obj : Item.REGISTRY)
		{
			Item item = (Item) obj;
			int meta = 0;

			if (item.getHasSubtypes())
			{
				List<String> names = Lists.newArrayList();

				while (meta < 15)
				{
					String current;
					String next;

					// Apparently there are mods that don't do safety checks in getUnlocalizedName :/
					try
					{
						current = item.getUnlocalizedName(new ItemStack(item, 1, meta));
					} catch (Exception e)
					{
						meta--;
						break;
					}

					try
					{
						next = item.getUnlocalizedName(new ItemStack(item, 1, meta + 1));
					} catch (Exception e)
					{
						break;
					}

					if (current != null && next != null && !names.contains(next) && !current.equals(next))
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
			try
			{
				if (item.matches(entry))
					return true;
			} catch (PatternSyntaxException e)
			{
				MagicClover.logger.log(Level.WARN, "Item " + item + " has bad characters in it's internal name, silently omitting it.");
				return true;
			}

		return false;
	}

	private static boolean isWhitelisted(String item)
	{
		for (String entry : whitelistedItems)
			try
			{
				if (item.matches(entry))
					return true;
			} catch (PatternSyntaxException e)
			{
				MagicClover.logger.log(Level.WARN, "Item " + item + " has bad characters in it's internal name, silently omitting it.");
			}

		return false;
	}

	private static boolean isRare(String item)
	{
		for (String entry : rareItems)
			try
			{
				if (item.matches(entry))
					return true;
			} catch (PatternSyntaxException e)
			{
				MagicClover.logger.log(Level.WARN, "Item " + item + " has bad characters in it's internal name, silently omitting it.");
			}

		return false;
	}
}