package com.lukerd.balancedarmor.armor.manager;

import com.lukerd.balancedarmor.BalancedArmor;
import com.lukerd.balancedarmor.capability.weight.IWeight;
import com.lukerd.balancedarmor.capability.weight.Provider;
import com.lukerd.balancedarmor.capability.weight.Supplier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid= BalancedArmor.MODID,bus= Mod.EventBusSubscriber.Bus.FORGE)
public class WeightManager {
    @SubscribeEvent
    public static void playerLogin(LivingEquipmentChangeEvent event){
        if(event.getEntityLiving() instanceof PlayerEntity){
            LazyOptional<IWeight> weight = event.getEntityLiving().getCapability(Provider.WEIGHT_CAPABILITY);
            IWeight tmp = weight.orElseGet(new Supplier());
            tmp.setWeight((short)2);

            System.out.println(event.getEntityLiving().getCapability(Provider.WEIGHT_CAPABILITY,null).orElseGet(new Supplier()).getWeight());
        }


    }
}
