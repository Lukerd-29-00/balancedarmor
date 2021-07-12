package com.lukerd.balancedarmor.armor.manager;

import com.lukerd.balancedarmor.BalancedArmor;
import com.lukerd.balancedarmor.armor.BalancedArmorItem;
import com.lukerd.balancedarmor.items.ItemList;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid=BalancedArmor.MODID)
public class DiamondArmorManager extends AbstractArmorManager{

    public DiamondArmorManager(){return;}

    private static void setDurability(ItemStack armor){
        double dmg = (double)armor.getDamageValue();
        double maxdmg = (double)armor.getMaxDamage();
        int tmp = (int)Math.floor((1+((dmg*3)/maxdmg)));
        AbstractArmorManager.damageItem(armor,tmp);
    }

    @SubscribeEvent
    public static void onDamage(LivingDamageEvent ouch){
        LivingEntity entity = ouch.getEntityLiving();
        if(entity.level.isClientSide){
            return;
        }
        boolean[] bools = AbstractArmorManager.hasArmor(ouch.getEntityLiving(), new Item[]{ItemList.diamond_helmet,ItemList.diamond_chestplate,ItemList.diamond_leggings,ItemList.diamond_boots});
        EquipmentSlotType[] slots = new EquipmentSlotType[]{EquipmentSlotType.HEAD,EquipmentSlotType.CHEST,EquipmentSlotType.LEGS,EquipmentSlotType.FEET};
        for(int i = 0;i < 4; i++){
            if(bools[i]){
                setDurability(entity.getItemBySlot(slots[i]));
            }
        }
    }

}
