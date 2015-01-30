package clover.common.dispenser;

import clover.MagicClover;
import clover.common.util.Registry;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BehaviorDispenseClover extends BehaviorDefaultDispenseItem
{
	@Override
	protected ItemStack dispenseStack(IBlockSource source, ItemStack item)
	{
		EnumFacing enumfacing = BlockDispenser.getFacing(source.getBlockMetadata());
		IPosition iposition = BlockDispenser.getDispensePosition(source);
		World world = source.getWorld();

		if (!world.isRemote)
		{
			int creeper = MagicClover.rand.nextInt(45);

			if (creeper == 0)
			{
				ItemMonsterPlacer.spawnCreature(world, EntityList.getEntityID(new EntityCreeper(world)), iposition.getX(), iposition.getY(), iposition.getZ());
			} else
			{
				doDispense(world, new ItemStack(Registry.getRandomItem(), 1, 0), 0, enumfacing, iposition);
				--item.stackSize;
			}
		} else
		{
			this.dispenseStack(source, item);
		}

		return item;
	}
}