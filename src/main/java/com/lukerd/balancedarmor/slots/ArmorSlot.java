package com.lukerd.balancedarmor.slots;

import net.minecraft.inventory.EquipmentSlotType;

public enum ArmorSlot {
    HEAD,
    CHEST,
    LEGS,
    FEET;

    public static EquipmentSlotType byName(String input) {
        return EquipmentSlotType.byName(input);
    }

    public static EquipmentSlotType byTypeAndIndex(EquipmentSlotType.Group group, int index) {
        return EquipmentSlotType.byTypeAndIndex(group,index);
    }

    public EquipmentSlotType getValue(){
        switch(this){
            case HEAD:
                return EquipmentSlotType.HEAD;
            case CHEST:
                return EquipmentSlotType.CHEST;
            case LEGS:
                return EquipmentSlotType.LEGS;
            case FEET:
                return EquipmentSlotType.FEET;
        }
        throw new IllegalArgumentException("Impossible enum value detected!");
    }
}
