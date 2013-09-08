package clover.common.creativetab;

import clover.common.core.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabClover extends CreativeTabs
{
	public CreativeTabClover(int par1, String par2Str)
	{
		super(par1, par2Str);
	}
	
	@Override
	public Item getTabIconItem()
	{
		return CommonProxy.clover;
	}
}
