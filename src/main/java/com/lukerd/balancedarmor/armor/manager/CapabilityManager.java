package com.lukerd.balancedarmor.armor.manager;

import com.lukerd.balancedarmor.BalancedArmor;
import com.lukerd.balancedarmor.armor.BalancedArmorItem;
import com.lukerd.balancedarmor.capability.ArmorCapability.ArmorValue;
import com.lukerd.balancedarmor.capability.ArmorCapability.IArmorCapability;
import com.lukerd.balancedarmor.capability.ArmorCapability.Provider;
import com.lukerd.balancedarmor.capability.ArmorCapability.Supplier;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid= BalancedArmor.MODID)
public class CapabilityManager {
    private static String speedModUUID = "0ff2f376-cf54-4894-8cd4-13a4d955dba2";
    @SubscribeEvent
    public static void onArmorSwap(LivingEquipmentChangeEvent event){
        if(event.getSlot().getType() != EquipmentSlotType.Group.ARMOR || event.getEntityLiving().level.isClientSide){
            return;
        }
        IArmorCapability armorCap = event.getEntityLiving().getCapability(Provider.ARMOR_CAPABILITY).orElseGet(new Supplier());
        for(ArmorValue value: ArmorValue.getPossibleValues()) {
            short newVal = armorCap.getValue(value);
            if (event.getFrom().getItem() instanceof BalancedArmorItem) {
                newVal -= ((BalancedArmorItem) event.getFrom().getItem()).getProperty(value);
            } else if (event.getTo().getItem() instanceof BalancedArmorItem) {
                newVal += ((BalancedArmorItem) event.getTo().getItem()).getProperty(value);
            }
            armorCap.setValue(newVal, value);
        }
        float weight = armorCap.getValue(ArmorValue.WEIGHT);
        AttributeModifier speed = event.getEntityLiving().getAttribute(Attributes.MOVEMENT_SPEED).getModifier(UUID.fromString(speedModUUID));
        if(speed != null){
            event.getEntityLiving().getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(speed);

        }
        event.getEntityLiving().getAttribute(Attributes.MOVEMENT_SPEED).addPermanentModifier(new AttributeModifier(UUID.fromString(speedModUUID),"weight_penalty",(1.0F -  (weight/100)) - 1,AttributeModifier.Operation.MULTIPLY_BASE));

    }
}
