package com.lukerd.balancedarmor.armor.manager;

import com.lukerd.balancedarmor.BalancedArmor;
import com.lukerd.balancedarmor.armor.manager.AbstractArmorManager;
import com.lukerd.balancedarmor.items.ItemList;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Iterator;

@Mod.EventBusSubscriber(modid= BalancedArmor.MODID)
public class MeltedGoldHelmetManager extends AbstractArmorManager {

    @SubscribeEvent
    public static void onTickEvent(TickEvent.PlayerTickEvent tick){
        if(tick.player.level.isClientSide){
            return;
        }
        Iterator<ItemStack> slots = tick.player.getArmorSlots().iterator();
        ItemStack helmet = null;
        while(slots.hasNext()){
            helmet = slots.next();
        }
        if(helmet.getItem().equals(ItemList.melted_golden_helmet)){
            tick.player.hurt(DamageSource.CRAMMING,1.0F);
        }
    }

    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event){
        LivingEntity entity = event.getEntityLiving();
        if(entity.level.isClientSide){
            return;
        }
        ItemStack helmet = entity.getItemBySlot(EquipmentSlotType.HEAD);
        if(helmet.getItem() == ItemList.melted_golden_helmet){
            helmet.setCount(0);
        }
    }

}
