package clover.client.core;

import net.minecraftforge.client.MinecraftForgeClient;
import clover.client.render.RenderCloverItem;
import clover.common.core.CommonProxy;

public class ClientProxy extends CommonProxy
{
	@Override
	public void initClient()
	{
		MinecraftForgeClient.registerItemRenderer(CommonProxy.clover, new RenderCloverItem());
	}
}