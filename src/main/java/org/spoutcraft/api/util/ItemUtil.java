package org.spoutcraft.api.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

public class ItemUtil {

    public static String getItemName(Item item)
    {
        return getItemName(item.getUnlocalizedName());
    }

    public static String getItemName(String identifier)
    {
        return identifier.replace("item.spoutcraft:", "");
    }

    public static String getArmorName(ItemArmor itemArmor)
    {
        String identifier = getItemName(itemArmor);        
        return getArmorName(identifier, itemArmor.armorType);
    }

    public static String getArmorName(String identifier, int armorType)
    {
        if(identifier.contains("spoutcraft")){
            identifier = getItemName(identifier);
        }
        switch(armorType){
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
        
        return identifier;
    }
}