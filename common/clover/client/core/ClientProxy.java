package clover.client.core;

import net.minecraftforge.client.MinecraftForgeClient;
import clover.client.model.ModelClover;
import clover.client.render.RenderCloverItem;
import clover.common.core.CommonProxy;

public class ClientProxy extends CommonProxy
{
	@Override
	public void initClient()
	{
		ModelClover model = new ModelClover();
		MinecraftForgeClient.registerItemRenderer(CommonProxy.cloverID + 256, new RenderCloverItem(model));
	}
}
