package com.lukerd.balancedarmor.armor.material;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;

public interface IBalancedArmorMaterial extends IArmorMaterial {
    public int getMeleeDefenseForSlot(EquipmentSlotType slot);

    public int getArrowDefenseForSlot(EquipmentSlotType slot);

    public int getFireDefenseForSlot(EquipmentSlotType slot);

    public int getWeightForSlot(EquipmentSlotType slot);
}
