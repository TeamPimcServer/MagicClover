package clover.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

public class RenderCloverItem implements IItemRenderer
{
	public ResourceLocation texture = new ResourceLocation("magicclover", "model/clover.png");
	public IModelCustom model;

	public RenderCloverItem()
	{
		model = AdvancedModelLoader.loadModel("/assets/magicclover/model/clover.tcn");
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		if (type == ItemRenderType.ENTITY && !item.isOnItemFrame() || type == ItemRenderType.EQUIPPED)
		{
			return true;
		} else
		{
			return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		GL11.glPushMatrix();
		
		switch (type)
		{
		case ENTITY:
			GL11.glTranslatef(0F, -0.1F, 0F);
			GL11.glScalef(-0.07F, -0.07F, 0.07F);
			break;
		case EQUIPPED:
			GL11.glTranslatef(0.7F, 0.5F, 0.7F);
			GL11.glScalef(-0.07F, -0.07F, 0.07F);
			GL11.glRotatef(60, 0.7f, 0, 1);
			break;
		default:
		}
		
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		model.renderAll();
		GL11.glPopMatrix();
	}
}
