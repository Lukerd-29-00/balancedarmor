package com.lukerd.balancedarmor.capability.ArmorCapability;

import com.lukerd.balancedarmor.BalancedArmor;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid= BalancedArmor.MODID,bus= Mod.EventBusSubscriber.Bus.FORGE)
public class Attacher {
    @SubscribeEvent
    public static void onAttachCapability(AttachCapabilitiesEvent<Entity> event){
        event.addCapability(new ResourceLocation(BalancedArmor.MODID,"capability.armor"),new Provider());
    }
}
