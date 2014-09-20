package clover.common.items;

import clover.common.core.MagicClover;
import clover.common.dispenser.BehaviorDispenseClover;
import clover.common.util.Registry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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

import java.util.List;

public class Clover extends ItemFood
{
	private static final IBehaviorDispenseItem behavior = new BehaviorDispenseClover();

	public Clover()
	{
		super(6, 2, false);
		setUnlocalizedName("clover");
		setTextureName("magicclover:clover");
		setCreativeTab(CreativeTabs.tabFood);
		BlockDispenser.dispenseBehaviorRegistry.putObject(this, behavior);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
	{
		if (!world.isRemote)
		{
			if (player.isSneaking())
			{
				int creeper = MagicClover.rand.nextInt(40);

				if (creeper == 0)
				{
					ItemMonsterPlacer.spawnCreature(world, EntityList.getEntityID(new EntityCreeper(world)), player.posX, player.posY, player.posZ);
				} else
				{
					player.dropPlayerItemWithRandomChoice(new ItemStack(Registry.getRandomItem(), 1, 0), true);
					player.inventory.consumeInventoryItem(this);
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4)
	{
		list.add("Shift+RightClick to use!");
	}
}