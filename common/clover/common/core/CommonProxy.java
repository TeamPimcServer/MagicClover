package clover.common.core;

import clover.common.items.Clover;
import net.minecraft.item.Item;

public class CommonProxy
{
	public static Item clover;
	public static int cloverID;
	
	public void init()
	{
		clover = new Clover(cloverID);
	}
	
	public void initClient()
	{
		
	}
}
