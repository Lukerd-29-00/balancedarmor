package com.lukerd.balancedarmor.armor;

import com.lukerd.balancedarmor.armor.material.IBalancedArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class DyeableBalancedArmorItem extends BalancedArmorItem implements IDyeableArmorItem {

    public DyeableBalancedArmorItem(IBalancedArmorMaterial material, EquipmentSlotType slot, Properties properties){
        super(material,slot,properties);
    }

    public static int getItemColor(ItemStack stack, int tintIndex){
        if(tintIndex == 0) {
            try {
                return ((CompoundNBT) stack.getOrCreateTag().get("display")).getInt("color");
            }
            catch(NullPointerException e){
                return 10511680;
            }
        }
        else{
            return 0xFFFFFFFF;
        }
    }

}
