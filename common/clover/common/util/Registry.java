package clover.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Registry
{
	public static List<Integer> bannedItemIDs = new ArrayList<Integer>();
	public static List<Integer> rareItemIDs = new ArrayList<Integer>();

	public static void registerBannedItem(int id)
	{
		bannedItemIDs.add(id);
	}
	
	public static void registerRareItem(int id)
	{
		rareItemIDs.add(id);
	}
	
	public static void init()
	{
		bannedItemIDs.addAll(Arrays.asList(10, 30, 150, 9, 8, 11, 132, 60, 149, 106, 63, 26, 120, 36, 118, 104, 142, 95, 93, 117, 105, 119, 140, 83, 137, 7, 34, 55, 68, 115, 383, 90, 71, 59, 64, 32, 52, 141, 127, 94, 403, 131, 87, 31));
		rareItemIDs.addAll(Arrays.asList(122, 138, 399, 57, 19, 133, 116, 120, 130, 84, 329, 322, 277, 278, 279, 276, 264, 388, 381, 310, 311, 312, 313, 293));
	}
}
