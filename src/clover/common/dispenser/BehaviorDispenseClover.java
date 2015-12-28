package clover.common.dispenser;

import clover.common.util.Config;
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
		EnumFacing enumfacing = BlockDispenser.func_149937_b(source.getBlockMetadata());
		IPosition iposition = BlockDispenser.func_149939_a(source);
		World world = source.getWorld();

		if (!world.isRemote)
		{
			if (world.difficultySetting.getDifficultyId() > 0 && Math.random() * 100 < Config.creeperChance)
			{
				ItemMonsterPlacer.spawnCreature(world, EntityList.getEntityID(new EntityCreeper(world)), iposition.getX(), iposition.getY(), iposition.getZ());
			} else
			{
				ItemStack stack = Registry.getRandomItem();
				if (stack.getItem() != null)
					doDispense(world, stack, 0, enumfacing, iposition);
			}

			--item.stackSize;
		}

		return item;
	}
}