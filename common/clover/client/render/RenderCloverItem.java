package clover.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import clover.client.model.ModelClover;

public class RenderCloverItem implements IItemRenderer
{
	private ResourceLocation texture = new ResourceLocation("magicclover", "model/clover.png");
	
	private ModelClover model;

	public RenderCloverItem(ModelClover model)
	{
		this.model = model;
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return type == ItemRenderType.ENTITY;
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
			GL11.glTranslatef(0F, 1.5F, 0F);
			GL11.glScalef(-1F, -1F, 1F);
			break;
		default:
		}

		Minecraft.getMinecraft().renderEngine.bindTexture(texture);

		model.render(0, 0, 0, 0, 0, 0.0625F);

		GL11.glPopMatrix();
	}
}
