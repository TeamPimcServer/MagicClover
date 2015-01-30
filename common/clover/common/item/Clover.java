package clover.common.item;

import clover.MagicClover;
import clover.common.dispenser.BehaviorDispenseClover;
import clover.common.util.Registry;
import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Clover extends Item
{
	private static final IBehaviorDispenseItem behavior = new BehaviorDispenseClover();

	public Clover()
	{
		GameRegistry.registerItem(this, "clover");
		setUnlocalizedName("clover");
		setCreativeTab(CreativeTabs.tabFood);

		BlockDispenser.dispenseBehaviorRegistry.putObject(this, behavior);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		if (!world.isRemote)
		{
			int creeper = MagicClover.rand.nextInt(40);

			if (creeper == 0)
			{
				ItemMonsterPlacer.spawnCreature(world, EntityList.getEntityID(new EntityCreeper(world)), player.posX, player.posY, player.posZ);
			} else
			{
				Item randomItem = Registry.getRandomItem();

				player.dropPlayerItemWithRandomChoice(new ItemStack(randomItem, 1, 0), true);
				player.inventory.consumeInventoryItem(this);
			}
		}

		return item;
	}
}