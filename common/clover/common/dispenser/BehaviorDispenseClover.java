package clover.common.dispenser;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BehaviorDispenseClover extends BehaviorDefaultDispenseItem
{
	Integer[] bannedItemIDs = { 10, 30, 150, 9, 8, 11, 132, 60, 149, 106, 63, 26, 120, 36, 118, 104, 142, 95, 93, 117, 105, 119, 140, 83, 137, 7, 34, 55, 68, 115, 383, 90, 71, 59, 64, 32, 52, 141, 127, 94, 403, 131, 87, 1, 3, 4, 12, 13, 31 };
	Integer[] rareItemIDs = { 122, 138, 399, 57, 19, 133, 116, 120, 130, 84, 329, 322, 277, 278, 279, 276, 264, 388, 381, 310, 311, 312, 313 };

	@Override
	protected ItemStack dispenseStack(IBlockSource source, ItemStack item)
	{
		EnumFacing enumfacing = BlockDispenser.getFacing(source.getBlockMetadata());
		World world = source.getWorld();
		int x = source.getXInt() + enumfacing.getFrontOffsetX();
		int y = source.getYInt() + enumfacing.getFrontOffsetY();
		int z = source.getZInt() + enumfacing.getFrontOffsetZ();

		if (!world.isRemote)
		{
			int randomID = world.rand.nextInt(400);
			int rare = world.rand.nextInt(5);
			if (isValidItem(randomID))
			{
				if (!isRareItem(randomID))
				{
					if (isBannedItem(randomID) == false)
					{
						--item.stackSize;
						EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(randomID, 1, 0));
						world.spawnEntityInWorld(entityItem);
					}
				} else
				{
					if (rare == 1)
					{
						--item.stackSize;
						EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(randomID, 1, 0));
						world.spawnEntityInWorld(entityItem);
					}
				}
			}
		}else
		{
			this.dispenseStack(source, item);
		}

		return item;
	}

	public boolean isBannedItem(int id)
	{
		for (int i = 0; i < bannedItemIDs.length; i++)
		{
			if (bannedItemIDs[i] == id)
			{
				return true;
			}
		}

		return false;
	}

	public boolean isRareItem(int id)
	{
		for (int i = 0; i < rareItemIDs.length; i++)
		{
			if (rareItemIDs[i] == id)
			{
				return true;
			}
		}

		return false;
	}

	public boolean isValidItem(int id)
	{
		if (Item.itemsList[id] == null)
		{
			return false;
		} else
		{
			return true;
		}
	}
}
