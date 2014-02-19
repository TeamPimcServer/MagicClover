package clover.common.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import clover.common.items.Clover;
import clover.common.util.Registry;

public class CommonProxy
{
	public static Item clover;
	public static int cloverID;
	
	public void init()
	{
		clover = new Clover(6, 2, false);

		GameRegistry.registerItem(clover, "magic_clover");
		
		Registry.init();
	}
	
	public void initClient()
	{
		
	}
}
