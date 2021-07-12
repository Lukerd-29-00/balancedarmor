package com.lukerd.balancedarmor.armor.manager;

import com.lukerd.balancedarmor.items.ItemList;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
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
        ListNBT enchantments = target.getEnchantmentTags();
        int j;
        for(j = 0; j < enchantments.size() && !((CompoundNBT)enchantments.get(j)).getString("id").equals("minecraft:unbreaking"); j++);
        double r = Math.random();
        double denom = j < enchantments.size() ? (double)(((CompoundNBT)target.getEnchantmentTags().get(j)).getInt("lvl")) + 1.0: 1.0;
        if(r <= 1.0/denom){
            target.setDamageValue(target.getDamageValue() + dmg);
        }
        else{
            try {
                target.setDamageValue(target.getTag().getInt("prevdmg"));
            } catch(NullPointerException e){
                target.setDamageValue(target.getDamageValue());
            }
        }
        CompoundNBT newtag = target.getTag();
        newtag.putInt("prevdmg",target.getDamageValue());
        target.setTag(newtag);
    }


}
