package com.lukerd.balancedarmor.armor;

import com.lukerd.balancedarmor.armor.material.IBalancedArmorMaterial;
import com.lukerd.balancedarmor.armor.material.Material;
import com.lukerd.balancedarmor.capability.ArmorCapability.ArmorValue;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

public class BalancedArmorItem extends ArmorItem {
    private final int arrowDefense;
    private final int meleeDefense;
    private final int fireDefense;
    private final int weight;

    public BalancedArmorItem(IBalancedArmorMaterial material, EquipmentSlotType slot, Properties properties){
        super(material,slot,properties);

        weight = material.getWeightForSlot(slot);
        arrowDefense = material.getArrowDefenseForSlot(slot);
        meleeDefense = material.getMeleeDefenseForSlot(slot);
        fireDefense = material.getFireDefenseForSlot(slot);
    }

    public int getArrowDefense(){
        return this.arrowDefense;
    }

    public int getMeleeDefense(){
        return this.meleeDefense;
    }

    public int getFireDefense(){
        return this.fireDefense;
    }

    public int getWeight() { return this.weight; }

    @Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer) {
        return material == Material.GOLD || super.makesPiglinsNeutral(stack,wearer) ? true : false;
    }

    public int getProperty(ArmorValue field) throws IllegalArgumentException{
        switch(field){
            case WEIGHT:
                return this.weight;
            case FIRE_DEFENSE:
                return this.fireDefense;
            case ARROW_DEFENSE:
                return this.arrowDefense;
            case MELEE_DEFENSE:
                return this.meleeDefense;
        }
        throw new IllegalArgumentException(String.format("Error: cannot get property of %s from armor item",field.getLabel()));
    }
}
