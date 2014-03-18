package clover.common.dispenser;

import clover.common.util.Registry;
import cpw.mods.fml.common.registry.GameData;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BehaviorDispenseClover extends BehaviorDefaultDispenseItem
{
	@Override
	protected ItemStack dispenseStack(IBlockSource source, ItemStack item)
	{
		EnumFacing enumfacing = BlockDispenser.func_149937_b(source.getBlockMetadata());
		IPosition iposition = BlockDispenser.func_149939_a(source);
		World world = source.getWorld();

		if (!world.isRemote)
		{
			List<Integer> list = new ArrayList<Integer>();

			for (int i = 0; i < GameData.itemRegistry.getKeys().toArray().length; i++)
			{
				if (GameData.itemRegistry.getKeys().toArray()[i] != null)
				{
					list.add(Item.getIdFromItem(GameData.itemRegistry.get((String) GameData.itemRegistry.getKeys().toArray()[i])));
				}
			}

			list.removeAll(Arrays.asList(0, null));
			list.removeAll(Arrays.asList(Registry.bannedItemIDs));

			Random rand = new Random();
			int randomID = rand.nextInt(list.size());
			int creeper = rand.nextInt(35);
			int rare = rand.nextInt(3);

			if (creeper == 0)
			{
				--item.stackSize;
				ItemMonsterPlacer.spawnCreature(world, EntityList.getEntityID(new EntityCreeper(world)), iposition.getX(), iposition.getY(), iposition.getZ());
			} else
			{
				if (!isRareItem(list.get(randomID)))
				{
					if (isBannedItem(list.get(randomID)) == false)
					{
						--item.stackSize;
						doDispense(world, new ItemStack(Item.getItemById(list.get(randomID)), 1, 0), 0, enumfacing, iposition);
					} else
					{
						this.dispenseStack(source, item);
					}
				} else
				{
					if (rare == 1)
					{
						--item.stackSize;
						doDispense(world, new ItemStack(Item.getItemById(list.get(randomID)), 1, 0), 0, enumfacing, iposition);
					} else
					{
						this.dispenseStack(source, item);
					}
				}
			}
		} else
		{
			this.dispenseStack(source, item);
		}

		return item;
	}

	public boolean isBannedItem(int id)
	{
		return Registry.bannedItemIDs.contains(id);
	}

	public boolean isRareItem(int id)
	{
		return Registry.rareItemIDs.contains(id);
	}
}
