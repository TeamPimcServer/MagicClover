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
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class Clover extends Item
{
	private static final IBehaviorDispenseItem behavior = new BehaviorDispenseClover();

	public static final String UNLOCALIZED_NAME = "clover";
	public static final String REGISTRY_NAME = "clover";

	public Clover()
	{

		setUnlocalizedName(Clover.UNLOCALIZED_NAME);
		setRegistryName(Clover.REGISTRY_NAME);

		setCreativeTab(CreativeTabs.MATERIALS);

		GameRegistry.register(this);
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, behavior);

	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack item, World world, EntityPlayer player, EnumHand hand)
	{
		// TODO temporary refuse right click with offhand.
		if(hand != EnumHand.MAIN_HAND) {
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, item);
		}
		if (!world.isRemote)
		{
			if (world.getDifficulty().getDifficultyId() > 0 && Math.random() * 100 < Config.creeperChance)
			{
				ItemMonsterPlacer.spawnCreature(world, "Creeper", player.posX, player.posY, player.posZ);
			} else
			{
				ItemStack stack = Registry.getRandomItem();
				if (stack.getItem() != null)
					player.dropItem(stack, true);
			}

			final EnumFacing mainInventoryFacing = EnumFacing.UP;

			if (player.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, mainInventoryFacing))
			{
				// TODO: This code currently only supports main inventory, does not handle offhand inventory. Do we need offhand one?

				final IItemHandler mainInventory = player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
													mainInventoryFacing);
				consumeFrom(mainInventory);
			}


			player.addStat(MagicClover.cloversUsed, 1);
		}

		return new ActionResult<ItemStack>(EnumActionResult.PASS, item);
	}

	/**
	 * Consumes the clover from the specified inventory
	 * @param inventory The inventory where we want to consume the item
     */
	private void consumeFrom(IItemHandler inventory)
	{

		for(int slot = 0; slot < inventory.getSlots(); slot++)
        {
            final ItemStack stack = inventory.getStackInSlot(slot);

            if(stack.getItem().equals(this))
            {
                inventory.extractItem(slot, 1, true);
                break;
            }
        }
	}
}