package org.spoutcraft.mod.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class SpoutCraftPlayerRenderer extends RenderPlayer{
    private ModelBiped modelArmorChestplate;
    private ModelBiped modelArmor;
    
    public SpoutCraftPlayerRenderer(){
        this.modelArmorChestplate = new ModelBiped(1.0F);
        this.modelArmor = new ModelBiped(0.5F);
    }
    
    @SuppressWarnings ("deprecation")
    @Override
    protected int setArmorModel(AbstractClientPlayer abstractClientPlayer, int layer, float unusedfloat)
    {
        ItemStack stack = abstractClientPlayer.inventory.armorItemInSlot(3 - layer);

        if (stack != null)
        {
            Item item = stack.getItem();

            if (item instanceof ItemArmor)
            {
                ItemArmor armor = (ItemArmor)item;
                if(armor.getUnlocalizedName().contains("spoutcraft")){
                    this.bindTexture(getArmorResourceLocation(armor, layer));
                }else{
                    this.bindTexture(RenderBiped.func_110857_a(armor, layer));
                }
                ModelBiped model = layer == 2 ? this.modelArmor : this.modelArmorChestplate;
                model.bipedHead.showModel = layer == 0;
                model.bipedHeadwear.showModel = layer == 0;
                model.bipedBody.showModel = layer == 1 || layer == 2;
                model.bipedRightArm.showModel = layer == 1;
                model.bipedLeftArm.showModel = layer == 1;
                model.bipedRightLeg.showModel = layer == 2 || layer == 3;
                model.bipedLeftLeg.showModel = layer == 2 || layer == 3;
                this.setRenderPassModel(model);
                model.onGround = this.mainModel.onGround;
                model.isRiding = this.mainModel.isRiding;
                model.isChild = this.mainModel.isChild;
                float factor = 1.0F;

                if (armor.getArmorMaterial() == EnumArmorMaterial.CLOTH)
                {
                    int color = armor.getColor(stack);
                    float r = (color >> 16 & 255) / 255.0F;
                    float g = (color >> 8 & 255) / 255.0F;
                    float b = (color & 255) / 255.0F;
                    GL11.glColor3f(factor * r, factor * g, factor * b);

                    if (stack.isItemEnchanted())
                    {
                        return 31;
                    }

                    return 16;
                }

                GL11.glColor3f(factor, factor, factor);

                if (stack.isItemEnchanted())
                {
                    return 15;
                }

                return 1;
            }
        }

        return -1;
    }
    
    private ResourceLocation getArmorResourceLocation(ItemArmor itemArmor, int layer)
    {
        String identifier = itemArmor.getUnlocalizedName().replace("item.spoutcraft:", "");
        switch(itemArmor.armorType){
            case 0:
                identifier = identifier.replace("_helmet", "");
                break;
            case 1:
                identifier = identifier.replace("_chestplate", "");
                break;
            case 2:
                identifier = identifier.replace("_leggings", "");
                break;
            case 3:
                identifier = identifier.replace("_boots", "");
                break;
            default:
                identifier = identifier.replace("_helmet", "");
                break;   
        }
        
        return new ResourceLocation("spoutcraft", String.format("textures/items/armor/" + identifier + "_layer_%d.png", Integer.valueOf(layer == 2 ? 2 : 1)));
    }
}
