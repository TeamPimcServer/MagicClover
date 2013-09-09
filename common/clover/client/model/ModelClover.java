package clover.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelClover extends ModelBase
{
	ModelRenderer Stalk;
	ModelRenderer Leaf1;
	ModelRenderer Leaf2;
	ModelRenderer Leaf3;
	ModelRenderer Leaf4;
	ModelRenderer Leaf5;
	ModelRenderer Leaf6;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape9;
	ModelRenderer Shape10;
	ModelRenderer Shape11;
	ModelRenderer Shape12;
	ModelRenderer Shape13;
	ModelRenderer Shape14;
	ModelRenderer Shape15;

	public ModelClover()
	{
		textureWidth = 64;
		textureHeight = 32;

		Stalk = new ModelRenderer(this, 0, 0);
		Stalk.addBox(0F, 0F, 0F, 1, 5, 1);
		Stalk.setRotationPoint(0F, 19F, 0F);
		Stalk.setTextureSize(64, 32);
		Stalk.mirror = true;
		setRotation(Stalk, 0F, 0F, 0F);
		Leaf1 = new ModelRenderer(this, 21, 0);
		Leaf1.addBox(0F, 0F, 0F, 7, 1, 1);
		Leaf1.setRotationPoint(-6F, 18F, 0F);
		Leaf1.setTextureSize(64, 32);
		Leaf1.mirror = true;
		setRotation(Leaf1, 0F, 0F, 0F);
		Leaf2 = new ModelRenderer(this, 21, 2);
		Leaf2.addBox(0F, 0F, 0F, 1, 1, 5);
		Leaf2.setRotationPoint(0F, 18F, -5F);
		Leaf2.setTextureSize(64, 32);
		Leaf2.mirror = true;
		setRotation(Leaf2, 0F, 0F, 0F);
		Leaf3 = new ModelRenderer(this, 37, 0);
		Leaf3.addBox(0F, 0F, 0F, 1, 1, 7);
		Leaf3.setRotationPoint(0F, 18F, 1F);
		Leaf3.setTextureSize(64, 32);
		Leaf3.mirror = true;
		setRotation(Leaf3, 0F, 0F, 0F);
		Leaf4 = new ModelRenderer(this, 21, 8);
		Leaf4.addBox(0F, 0F, 0F, 5, 1, 1);
		Leaf4.setRotationPoint(1F, 18F, 0F);
		Leaf4.setTextureSize(64, 32);
		Leaf4.mirror = true;
		setRotation(Leaf4, 0F, 0F, 0F);
		Leaf5 = new ModelRenderer(this, 0, 12);
		Leaf5.addBox(0F, 0F, 0F, 4, 1, 6);
		Leaf5.setRotationPoint(-4F, 18F, -6F);
		Leaf5.setTextureSize(64, 32);
		Leaf5.mirror = true;
		setRotation(Leaf5, 0F, 0F, 0F);
		Leaf6 = new ModelRenderer(this, 10, 9);
		Leaf6.addBox(0F, 0F, 0F, 3, 1, 1);
		Leaf6.setRotationPoint(-3F, 18F, -7F);
		Leaf6.setTextureSize(64, 32);
		Leaf6.mirror = true;
		setRotation(Leaf6, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 23, 12);
		Shape2.addBox(0F, 0F, 0F, 1, 1, 1);
		Shape2.setRotationPoint(-2F, 18F, -8F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 31, 10);
		Shape3.addBox(0F, 0F, 0F, 1, 1, 4);
		Shape3.setRotationPoint(-5F, 18F, -5F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 2, 8);
		Shape4.addBox(0F, 0F, 0F, 1, 1, 2);
		Shape4.setRotationPoint(-6F, 18F, -4F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 0, 19);
		Shape5.addBox(0F, 0F, 0F, 6, 1, 4);
		Shape5.setRotationPoint(-6F, 18F, 1F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 0, 24);
		Shape6.addBox(0F, 0F, 0F, 1, 1, 2);
		Shape6.setRotationPoint(-7F, 18F, 1F);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 21, 10);
		Shape7.addBox(0F, 0F, 0F, 3, 1, 1);
		Shape7.setRotationPoint(-5F, 18F, 5F);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0F);
		Shape8 = new ModelRenderer(this, 6, 24);
		Shape8.addBox(0F, 0F, 0F, 1, 1, 1);
		Shape8.setRotationPoint(-4F, 18F, 6F);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0F, 0F, 0F);
		Shape9 = new ModelRenderer(this, 21, 15);
		Shape9.addBox(0F, 0F, 0F, 5, 1, 4);
		Shape9.setRotationPoint(1F, 18F, -4F);
		Shape9.setTextureSize(64, 32);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0F);
		Shape10 = new ModelRenderer(this, 53, 0);
		Shape10.addBox(0F, 0F, 0F, 4, 1, 1);
		Shape10.setRotationPoint(1F, 18F, -5F);
		Shape10.setTextureSize(64, 32);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0F, 0F);
		Shape11 = new ModelRenderer(this, 41, 10);
		Shape11.addBox(0F, 0F, 0F, 2, 1, 1);
		Shape11.setRotationPoint(2F, 18F, -6F);
		Shape11.setTextureSize(64, 32);
		Shape11.mirror = true;
		setRotation(Shape11, 0F, 0F, 0F);
		Shape12 = new ModelRenderer(this, 21, 20);
		Shape12.addBox(0F, 0F, 0F, 4, 1, 5);
		Shape12.setRotationPoint(1F, 18F, 1F);
		Shape12.setTextureSize(64, 32);
		Shape12.mirror = true;
		setRotation(Shape12, 0F, 0F, 0F);
		Shape13 = new ModelRenderer(this, 13, 0);
		Shape13.addBox(0F, 0F, 0F, 1, 1, 2);
		Shape13.setRotationPoint(5F, 18F, 3F);
		Shape13.setTextureSize(64, 32);
		Shape13.mirror = true;
		setRotation(Shape13, 0F, 0F, 0F);
		Shape14 = new ModelRenderer(this, 11, 4);
		Shape14.addBox(0F, 0F, 0F, 3, 1, 1);
		Shape14.setRotationPoint(1F, 18F, 6F);
		Shape14.setTextureSize(64, 32);
		Shape14.mirror = true;
		setRotation(Shape14, 0F, 0F, 0F);
		Shape15 = new ModelRenderer(this, 5, 0);
		Shape15.addBox(0F, 0F, 0F, 2, 1, 1);
		Shape15.setRotationPoint(1F, 18F, 7F);
		Shape15.setTextureSize(64, 32);
		Shape15.mirror = true;
		setRotation(Shape15, 0F, 0F, 0F);
	}

	public void render(float f, float f1, float f2, float f3, float f4, float f5)
	{
		setRotationAngles(f, f1, f2, f3, f4, f5);
		Stalk.render(f5);
		Leaf1.render(f5);
		Leaf2.render(f5);
		Leaf3.render(f5);
		Leaf4.render(f5);
		Leaf5.render(f5);
		Leaf6.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
		Shape9.render(f5);
		Shape10.render(f5);
		Shape11.render(f5);
		Shape12.render(f5);
		Shape13.render(f5);
		Shape14.render(f5);
		Shape15.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
	}
}
