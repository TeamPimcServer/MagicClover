package clover.common.item;

import clover.MagicClover;
import clover.common.dispenser.BehaviorDispenseClover;
import clover.common.util.Config;
import clover.common.util.Registry;
import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.IBehaviorDispenseItem;
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
			if (world.getDifficulty().getDifficultyId() > 0 && Math.random() * 100 < Config.creeperChance)
			{
				ItemMonsterPlacer.spawnCreature(world, "Creeper", player.posX, player.posY, player.posZ);
			} else
			{
				ItemStack stack = Registry.getRandomItem();
				if (stack.getItem() != null)
					player.dropPlayerItemWithRandomChoice(stack, true);
			}

			player.inventory.consumeInventoryItem(this);
			player.addStat(MagicClover.cloversUsed, 1);
		}

		return item;
	}
}