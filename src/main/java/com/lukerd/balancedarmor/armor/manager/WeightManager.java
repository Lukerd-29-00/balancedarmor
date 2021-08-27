package com.lukerd.balancedarmor.armor.manager;

import com.lukerd.balancedarmor.BalancedArmor;
import com.lukerd.balancedarmor.capability.ArmorCapability.ArmorValue;
import com.lukerd.balancedarmor.capability.ArmorCapability.Provider;
import com.lukerd.balancedarmor.capability.ArmorCapability.Supplier;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid= BalancedArmor.MODID,bus= Mod.EventBusSubscriber.Bus.FORGE)
public class WeightManager {
    @SubscribeEvent
    public static void onLivingFallEvent(LivingFallEvent fall){
        LivingEntity entity = fall.getEntityLiving();
        if(entity.level.isClientSide || fall.getDamageMultiplier() == 0 || fall.getDistance() < 4){
            return;
        }
        short weight = entity.getCapability(Provider.ARMOR_CAPABILITY).orElseGet(new Supplier()).getValue(ArmorValue.WEIGHT);
        fall.setDamageMultiplier(fall.getDamageMultiplier() + (((float)(weight*10)) / 100F));
    }
}
