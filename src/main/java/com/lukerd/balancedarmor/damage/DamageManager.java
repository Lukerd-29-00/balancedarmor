package com.lukerd.balancedarmor.damage;

import com.lukerd.balancedarmor.BalancedArmor;
import com.lukerd.balancedarmor.capability.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;



@Mod.EventBusSubscriber(modid= BalancedArmor.MODID)
public class DamageManager {
    private static float calcDamage(LivingEntity entity, DamageSource damageSource, float initialDamage){
        if(damageSource.isBypassArmor()){
            return initialDamage;
        }
        double toughness = entity.getAttribute(Attributes.ARMOR_TOUGHNESS).getValue();
        double defensePoints = 0.0;
        IArmorCapability armor = entity.getCapability(Provider.ARMOR_CAPABILITY).orElseGet(new Supplier());
        if(damageSource.isProjectile()){
            defensePoints += armor.getValue(ArmorValue.ARROW_DEFENSE);
        }
        else if(damageSource.isFire()){
            defensePoints += armor.getValue(ArmorValue.FIRE_DEFENSE);
        }
        else{
            defensePoints += armor.getValue(ArmorValue.MELEE_DEFENSE);
        }
       return (float)(initialDamage * (1 - ((Math.max(defensePoints/5,defensePoints - (initialDamage/(2 + (toughness/4)))))/25)));
    }

    @SubscribeEvent
    public static void onDamage(LivingDamageEvent ouch){
        if(ouch.getEntityLiving().level.isClientSide){
            return;
        }
        ouch.setAmount(calcDamage(ouch.getEntityLiving(),ouch.getSource(),ouch.getAmount()));
    }

}
