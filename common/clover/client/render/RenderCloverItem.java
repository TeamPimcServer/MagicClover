package clover.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import clover.client.model.ModelClover;

public class RenderCloverItem implements IItemRenderer
{
	ResourceLocation texture = new ResourceLocation("magicclover", "/model/clover.png");

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
//		GL11.glScalef(-1F, -1F, 1F);

		switch (type)
		{
		case INVENTORY:
			GL11.glScalef(-1F, -1F, 1F);
			GL11.glTranslatef(0, -1.1F, 0);
			break;
		case EQUIPPED:
			GL11.glScalef(-1F, -1F, 1F);
			GL11.glTranslatef(-0.8F, -0.2F, 0.7F);
			break;
		case EQUIPPED_FIRST_PERSON:
			GL11.glScalef(-1F, -1F, 1F);
			GL11.glTranslatef(0, -0.7F, 0.7F);
			break;
		case ENTITY:
			GL11.glTranslatef(0F, 1.5F, 0F);
			GL11.glScalef(-1F, -1F, 1F);
			break;
		default:
		}

		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

		model.render(0, 0, 0, 0, 0, 0.0625F);

		GL11.glPopMatrix();
	}
}
