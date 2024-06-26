// Date: 3/3/2012 11:56:45 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package twilightforest.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class ModelTFPenguin extends ModelBase {

    // fields
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer head;
    ModelRenderer beak;

    public ModelTFPenguin() {
        textureWidth = 64;
        textureHeight = 32;

        body = new ModelRenderer(this, 32, 0);
        body.addBox(-4F, 0F, -4F, 8, 9, 8);
        body.setRotationPoint(0F, 14F, 0F);

        rightarm = new ModelRenderer(this, 34, 18);
        rightarm.addBox(-1F, -1F, -2F, 1, 8, 4);
        rightarm.setRotationPoint(-4F, 15F, 0F);

        leftarm = new ModelRenderer(this, 24, 18);
        leftarm.addBox(0F, -1F, -2F, 1, 8, 4);
        leftarm.setRotationPoint(4F, 15F, 0F);

        leftarm.mirror = true;

        rightleg = new ModelRenderer(this, 0, 16);
        rightleg.addBox(-2F, 0F, -5F, 4, 1, 8);
        rightleg.setRotationPoint(-2F, 23F, 0F);
        rightleg.setTextureSize(64, 32);

        leftleg = new ModelRenderer(this, 0, 16);
        leftleg.addBox(-2F, 0F, -5F, 4, 1, 8);
        leftleg.setRotationPoint(2F, 23F, 0F);

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-3.5F, -4F, -3.5F, 7, 5, 7);
        head.setRotationPoint(0F, 13F, 0F);

        beak = new ModelRenderer(this, 0, 13);
        beak.addBox(-1F, 0F, -1F, 2, 1, 2);
        beak.setRotationPoint(0F, -1F, -4F);

        head.addChild(beak);

    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        setRotationAngles(par2, par3, par4, par5, par6, par7);

        if (isChild) {
            float f = 2.0F;
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 5F * par7, 0.75F * par7);
            head.render(par7);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / f, 1.0F / f, 1.0F / f);
            GL11.glTranslatef(0.0F, 24F * par7, 0.0F);
            body.render(par7);
            rightleg.render(par7);
            leftleg.render(par7);
            rightarm.render(par7);
            leftarm.render(par7);
            GL11.glPopMatrix();
        } else {
            head.render(par7);
            body.render(par7);
            rightleg.render(par7);
            leftleg.render(par7);
            rightarm.render(par7);
            leftarm.render(par7);
        }
    }

    /**
     * Sets the models various rotation angles.
     */
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6) {
        head.rotateAngleX = par5 / (180F / (float) Math.PI);
        head.rotateAngleY = par4 / (180F / (float) Math.PI);

        rightleg.rotateAngleX = MathHelper.cos(par1) * 0.7F * par2;
        leftleg.rotateAngleX = MathHelper.cos(par1 + (float) Math.PI) * 0.7F * par2;

        rightarm.rotateAngleZ = par3;
        leftarm.rotateAngleZ = -par3;
    }

}
