package clover.common.core;

import net.minecraft.item.Item;
import clover.common.items.Clover;
import clover.common.util.Registry;

public class CommonProxy
{
	public static Item clover;
	public static int cloverID;
	
	public void init()
	{
		clover = new Clover(cloverID, 6, 2, false);
		
		Registry.init();
	}
	
	public void initClient()
	{
		
	}
}
