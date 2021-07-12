package com.lukerd.balancedarmor.damage;

import com.lukerd.balancedarmor.BalancedArmor;
import com.lukerd.balancedarmor.armor.BalancedArmorItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AirItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;



@Mod.EventBusSubscriber(modid= BalancedArmor.MODID)
public class DamageManager {
    //needs to deal with enchantments and resistance effects!

    private static double max(double a, double b){
        if(a > b){
            return a;
        }
        return b;
    }

    float toughness = 0;

    private static float calcDamage(LivingEntity entity, DamageSource damageSource, float initialDamage){
        EquipmentSlotType[] slots = new EquipmentSlotType[]{EquipmentSlotType.HEAD,EquipmentSlotType.CHEST,EquipmentSlotType.LEGS,EquipmentSlotType.FEET};
        if(damageSource.isBypassArmor()){
            return initialDamage;
        }
        int[] defenses = new int[4];
        Item tmp;
        float toughness = 0;
        for(EquipmentSlotType slot: slots){
            tmp = entity.getItemBySlot(slot).getItem();
            if(tmp instanceof AirItem){
                defenses[slot.getIndex()] = 0;
            }
            if(tmp instanceof BalancedArmorItem) {
                if (damageSource.isProjectile()) {
                    defenses[slot.getIndex()] = ((BalancedArmorItem) tmp).getArrowDefense();
                    toughness += ((BalancedArmorItem) tmp).getToughness();
                } else if (damageSource.isFire()) {
                    defenses[slot.getIndex()] = ((BalancedArmorItem) tmp).getFireDefense();
                    toughness += ((BalancedArmorItem) tmp).getToughness();
                } else {
                    defenses[slot.getIndex()] = ((BalancedArmorItem) tmp).getMeleeDefense();
                    toughness += ((BalancedArmorItem) tmp).getToughness();
                }
            }
        }
        int defensePoints = 0;
        for(int d : defenses){
            defensePoints += d;
        }

       return (float)(initialDamage * (1 - ((max(defensePoints/5,defensePoints - (initialDamage/(2 + (toughness/4)))))/25)));
    }

    @SubscribeEvent
    public static void onDamage(LivingDamageEvent ouch){
        if(ouch.getEntityLiving().level.isClientSide){
            return;
        }
        ouch.setAmount(calcDamage(ouch.getEntityLiving(),ouch.getSource(),ouch.getAmount()));
    }

}
