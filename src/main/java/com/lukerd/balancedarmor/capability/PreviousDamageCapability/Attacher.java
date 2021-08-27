package com.lukerd.balancedarmor.capability.PreviousDamageCapability;

import com.lukerd.balancedarmor.BalancedArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BalancedArmor.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Attacher {
    @SubscribeEvent
    public static void onAttachCapability(AttachCapabilitiesEvent<ItemStack> event){
        event.addCapability(new ResourceLocation(BalancedArmor.MODID,"capability.previous_damage"),new Provider());
    }
}
