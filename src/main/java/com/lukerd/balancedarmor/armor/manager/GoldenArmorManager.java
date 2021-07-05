package com.lukerd.balancedarmor.armor.manager;

import com.lukerd.balancedarmor.BalancedArmor;
import com.lukerd.balancedarmor.armor.manager.AbstractArmorManager;
import com.lukerd.balancedarmor.items.ItemList;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.ShortNBT;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;
@Mod.EventBusSubscriber(modid=BalancedArmor.MODID)
public class GoldenArmorManager extends AbstractArmorManager {

    public GoldenArmorManager(){return;}

    @SubscribeEvent
    public static void onFireDamage(LivingDamageEvent ouch){
        if(ouch.getEntityLiving().level.isClientSide){
            return;
        }
        final boolean[] armor = hasArmor(ouch.getEntityLiving(),new Item[]{ItemList.golden_helmet,ItemList.golden_chestplate,ItemList.golden_leggings,ItemList.golden_boots});
        final EquipmentSlotType[] slots = {EquipmentSlotType.HEAD,EquipmentSlotType.CHEST,EquipmentSlotType.LEGS,EquipmentSlotType.FEET};
        ItemStack target = null;
        for(int i = 0;i < 4;i++){
            if(armor[i]){
                target = ouch.getEntityLiving().getItemBySlot(slots[i]);
                target.setDamageValue(target.getDamageValue() + 5);
                if(i == 0 && target.getDamageValue() >= target.getMaxDamage()){
                    ItemStack melt = new ItemStack(ItemList.melted_golden_helmet);
                    ShortNBT hide = ShortNBT.valueOf((short)3);
                    melt.enchant(Enchantments.BINDING_CURSE,1);
                    melt.addTagElement("HideFlags",hide);
                    ouch.getEntityLiving().setItemSlot(EquipmentSlotType.HEAD,melt);
                }
            }
        }


    }
}
