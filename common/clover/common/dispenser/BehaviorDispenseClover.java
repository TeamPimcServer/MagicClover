package clover.common.dispenser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import clover.common.util.Registry;

public class BehaviorDispenseClover extends BehaviorDefaultDispenseItem
{
	@Override
	protected ItemStack dispenseStack(IBlockSource source, ItemStack item)
	{
		EnumFacing enumfacing = BlockDispenser.getFacing(source.getBlockMetadata());
		IPosition iposition = BlockDispenser.getIPositionFromBlockSource(source);
		World world = source.getWorld();

		if (!world.isRemote)
		{
			List<Integer> list = new ArrayList<Integer>();

			for (int i = 0; i < Item.itemsList.length; i++)
			{
				if (Item.itemsList[i] != null)
				{
					list.add(Item.itemsList[i].itemID);
				}
			}

			list.removeAll(Arrays.asList(0, null));
			list.removeAll(Arrays.asList(Registry.bannedItemIDs));

			Random rand = new Random();
			int randomID = rand.nextInt(list.size());
			int rare = rand.nextInt(3);

			if (!isRareItem(list.get(randomID)))
			{
				if (isBannedItem(list.get(randomID)) == false)
				{
					--item.stackSize;
					doDispense(world, new ItemStack(list.get(randomID), 1, 0), 0, enumfacing, iposition);
				}
			} else
			{
				if (rare == 1)
				{
					--item.stackSize;
					doDispense(world, new ItemStack(list.get(randomID), 1, 0), 0, enumfacing, iposition);
				} else
				{
					--item.stackSize;
					ItemMonsterPlacer.spawnCreature(world, EntityList.getEntityID(new EntityCreeper(world)), iposition.getX(), iposition.getY(), iposition.getZ());
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
		for (int i = 0; i < Registry.bannedItemIDs.size(); i++)
		{
			if (Registry.bannedItemIDs.get(i) == id)
			{
				return true;
			}
		}

		return false;
	}

	public boolean isRareItem(int id)
	{
		for (int i = 0; i < Registry.rareItemIDs.size(); i++)
		{
			if (Registry.rareItemIDs.get(i) == id)
			{
				return true;
			}
		}

		return false;
	}
}
