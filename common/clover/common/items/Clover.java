package clover.common.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import clover.common.dispenser.BehaviorDispenseClover;
import clover.common.util.Registry;

public class Clover extends ItemFood
{
	private static final IBehaviorDispenseItem behavior = new BehaviorDispenseClover();

	public Clover(int id, int healAmount, int saturation, boolean isWolfsFavoriteMeat)
	{
		super(id, healAmount, saturation, isWolfsFavoriteMeat);
		setUnlocalizedName("clover");
		setTextureName("magicclover:clover");
		setCreativeTab(CreativeTabs.tabFood);
		BlockDispenser.dispenseBehaviorRegistry.putObject(this, behavior);
	}

	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		super.onItemRightClick(item, world, player);

		if (!world.isRemote)
		{
			if (player.isSneaking())
			{
				List<Integer> list = new ArrayList<Integer>();

				for (int i = 0; i < itemsList.length; i++)
				{
					if (itemsList[i] != null)
					{
						list.add(itemsList[i].itemID);
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
						player.dropPlayerItem(new ItemStack(list.get(randomID), 1, 0));
						player.inventory.consumeInventoryItem(itemID);
					}
				} else
				{
					if (rare == 1)
					{
						player.dropPlayerItem(new ItemStack(list.get(randomID), 1, 0));
						player.inventory.consumeInventoryItem(itemID);
					} else
					{
						ItemMonsterPlacer.spawnCreature(world, EntityList.getEntityID(new EntityCreeper(world)), player.posX, player.posY, player.posZ);
					}
				}
			}
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
