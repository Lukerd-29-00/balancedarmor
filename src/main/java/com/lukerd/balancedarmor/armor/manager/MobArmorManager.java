package com.lukerd.balancedarmor.armor.manager;

import com.lukerd.balancedarmor.BalancedArmor;
import com.lukerd.balancedarmor.armor.ArmorLookup;
import com.lukerd.balancedarmor.armor.material.Materials;
import com.lukerd.balancedarmor.items.ItemList;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid= BalancedArmor.MODID,bus= Mod.EventBusSubscriber.Bus.FORGE)
public class MobArmorManager {

    private static void setArmor(LivingEntity mob, EquipmentSlotType slot){
        if(mob.getItemBySlot(EquipmentSlotType.HEAD).getItem() instanceof ArmorItem) {
            ArmorItem armor = (ArmorItem) mob.getItemBySlot(EquipmentSlotType.HEAD).getItem();
            if (armor.equals(ArmorLookup.getArmor(ArmorMaterial.LEATHER, slot))) {
                mob.setItemSlot(slot, new ItemStack(ArmorLookup.getArmor(Materials.LEATHER, slot)));
            } else if (armor.equals(ArmorLookup.getArmor(ArmorMaterial.GOLD, slot))) {
                mob.setItemSlot(slot, new ItemStack(ArmorLookup.getArmor(Materials.GOLD, slot)));
            } else if (armor.equals(ArmorLookup.getArmor(ArmorMaterial.CHAIN, slot))) {
                mob.setItemSlot(slot, new ItemStack(ArmorLookup.getArmor(Materials.CHAIN, slot)));
            } else if (armor.equals(ArmorLookup.getArmor(ArmorMaterial.IRON, slot))) {
                mob.setItemSlot(slot, new ItemStack(ArmorLookup.getArmor(Materials.IRON, slot)));
            } else if (armor.equals(ArmorLookup.getArmor(ArmorMaterial.DIAMOND, slot))) {
                mob.setItemSlot(slot, new ItemStack(ArmorLookup.getArmor(Materials.DIAMOND, slot)));
            } else if (armor.equals(ArmorLookup.getArmor(ArmorMaterial.NETHERITE, slot))) {
                mob.setItemSlot(slot, new ItemStack(ArmorLookup.getArmor(Materials.NETHERITE, slot)));
            }
        }
    }


    @SubscribeEvent
    public static void onMobSpawn(LivingSpawnEvent event){
        LivingEntity mob = event.getEntityLiving();
        setArmor(mob,EquipmentSlotType.FEET);
        setArmor(mob,EquipmentSlotType.LEGS);
        setArmor(mob,EquipmentSlotType.CHEST);
        setArmor(mob,EquipmentSlotType.HEAD);
    }
}
