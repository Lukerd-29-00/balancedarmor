package com.lukerd.balancedarmor.armor.manager;

import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

public abstract class AbstractArmorManager {

    protected static boolean[] hasArmor(LivingEntity entity, Item[] armor) {
        return new boolean[] {
                isItem(entity.getItemBySlot(EquipmentSlotType.HEAD),armor[0]),
                isItem(entity.getItemBySlot(EquipmentSlotType.CHEST), armor[1]),
                isItem(entity.getItemBySlot(EquipmentSlotType.LEGS), armor[2]),
                isItem(entity.getItemBySlot(EquipmentSlotType.FEET),armor[3])

        };
    }

    protected static boolean isArmor(EquipmentSlotType slot){
        return slot.equals(EquipmentSlotType.HEAD) || slot.equals(EquipmentSlotType.CHEST) || slot.equals(EquipmentSlotType.LEGS) || slot.equals(EquipmentSlotType.FEET);
    }

    protected static boolean isItem(ItemStack stack, Item cmp){
        return stack.getItem().getRegistryName().equals(cmp.getRegistryName());
    }

    protected static void damageItem(ItemStack target,int dmg){

        CompoundNBT newtag = target.getTag();
        int previousDamage = newtag.getInt("prevdmg");
        if(previousDamage != target.getDamageValue()){
            target.setDamageValue(target.getDamageValue() + dmg);
            newtag.putInt("prevdmg",target.getDamageValue());
            target.setTag(newtag);
        }

    }


}
