package com.lukerd.balancedarmor.armor;

import com.lukerd.balancedarmor.armor.material.IBalancedArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;

public class BalancedArmorItem extends ArmorItem {
    private final int arrowDefense;
    private final int meleeDefense;
    private final int fireDefense;

    public BalancedArmorItem(IBalancedArmorMaterial material, EquipmentSlotType slot, Properties properties){
        super(material,slot,properties);

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
}
