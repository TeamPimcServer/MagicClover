package clover.common.items;

import clover.common.dispenser.BehaviorDispenseClover;
import clover.common.util.Configuration;
import clover.common.util.Registry;
import cpw.mods.fml.common.registry.GameData;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Clover extends ItemFood
{
	private static final IBehaviorDispenseItem behavior = new BehaviorDispenseClover();

	public Clover(int healAmount, int saturation, boolean isWolfsFavoriteMeat)
	{
		super(healAmount, saturation, isWolfsFavoriteMeat);
		setUnlocalizedName("clover");
		setTextureName("magicclover:clover");
		setCreativeTab(CreativeTabs.tabFood);
		BlockDispenser.dispenseBehaviorRegistry.putObject(this, behavior);
	}

	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		if (!world.isRemote)
		{
			if (player.isSneaking())
			{
				List<Integer> list = new ArrayList<Integer>();

				for (int i = 0; i < GameData.itemRegistry.getKeys().size(); i++)
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
					ItemMonsterPlacer.spawnCreature(world, EntityList.getEntityID(new EntityCreeper(world)), player.posX, player.posY, player.posZ);
				} else
				{
					if (!isRareItem(list.get(randomID)))
					{
						if (isBannedItem(list.get(randomID)) == false)
						{
							player.dropPlayerItemWithRandomChoice(new ItemStack(Item.getItemById(list.get(randomID)), 1, 0), true);
							player.inventory.consumeInventoryItem(this);
						} else
						{
							this.onItemRightClick(item, world, player);
						}
					} else
					{
						if (rare == 1)
						{
							player.dropPlayerItemWithRandomChoice(new ItemStack(Item.getItemById(list.get(randomID)), 1, 0), true);
							player.inventory.consumeInventoryItem(this);
						} else
						{
							this.onItemRightClick(item, world, player);
						}
					}
				}
			} else
			{
				if (player.canEat(false))
				{
					player.setItemInUse(item, this.getMaxItemUseDuration(item));
				}
			}
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