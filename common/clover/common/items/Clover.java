package clover.common.items;

import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import clover.common.dispenser.BehaviorDispenseClover;

public class Clover extends ItemFood
{
	Integer[] bannedItemIDs = { 10, 30, 150, 9, 8, 11, 132, 60, 149, 106, 63, 26, 120, 36, 118, 104, 142, 95, 93, 117, 105, 119, 140, 83, 137, 7, 34, 55, 68, 115, 383, 90, 71, 59, 64, 32, 52, 141, 127, 94, 403, 131, 87, 1, 3, 4, 12, 13, 31 };
	Integer[] rareItemIDs = { 122, 138, 399, 57, 19, 133, 116, 120, 130, 84, 329, 322, 277, 278, 279, 276, 264, 388, 381, 310, 311, 312, 313, 293 };
	private static final IBehaviorDispenseItem behavior = new BehaviorDispenseClover();

	public Clover(int id, int healAmount, int saturation, boolean isWolfsFavoriteMeat)
	{
		super(id, healAmount, saturation, isWolfsFavoriteMeat);
		setUnlocalizedName("clover");
		setTextureName("magicclover:clover");
		setCreativeTab(CreativeTabs.tabFood);
		BlockDispenser.dispenseBehaviorRegistry.putObject(this, behavior);
		setFull3D();
	}

	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		super.onItemRightClick(item, world, player);
		
		if (!world.isRemote)
		{
			if (player.isSneaking())
			{
				int randomID = world.rand.nextInt(400);
				int rare = world.rand.nextInt(6);

				if (isValidItem(randomID))
				{
					if (!isRareItem(randomID))
					{
						if (isBannedItem(randomID) == false)
						{
							player.dropPlayerItem(new ItemStack(randomID, 1, 0));
							player.inventory.consumeInventoryItem(itemID);
						}
					} else
					{
						if (rare == 1)
						{
							player.dropPlayerItem(new ItemStack(randomID, 1, 0));
							player.inventory.consumeInventoryItem(itemID);
						}
					}
				} else
				{
					this.onItemRightClick(item, world, player);
				}
			}
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
