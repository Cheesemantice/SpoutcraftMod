package org.spoutcraft.mod.enchantments;

import org.spoutcraft.api.enchantments.EnchantmentPrefab;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

public class CustomEnchantment extends Enchantment {
    private final EnchantmentPrefab prefab;
    
    public CustomEnchantment(int id, EnchantmentPrefab prefab) {
        super(id, prefab.getWeight(), prefab.getEnchantmentType());
        this.prefab = prefab;
        
        this.setName(prefab.getIdentifier());
    }
    
    /**
     * Change what items this enchantment can be applied to using an enchantment table
     */
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack itemStack) {
        return true;
    }
    
    /**
     * Change what items this enchantment can be applied to
     */
    @Override
    public boolean canApply(ItemStack itemStack) {
        return true;
    }
    
    protected EnchantmentPrefab getPrefab() {
        return prefab;
    }
}
