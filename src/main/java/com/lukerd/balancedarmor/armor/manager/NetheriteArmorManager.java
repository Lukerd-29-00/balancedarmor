package com.lukerd.balancedarmor.armor.manager;

import com.lukerd.balancedarmor.BalancedArmor;
import com.lukerd.balancedarmor.items.ItemList;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid= BalancedArmor.MODID)
public class NetheriteArmorManager extends AbstractArmorManager {
    @SubscribeEvent
    public static void onFireDamage(LivingDamageEvent ouch) {
        if (ouch.getEntityLiving().level.isClientSide) {
            return;
        }
        final boolean[] armor = hasArmor(ouch.getEntityLiving(), new Item[]{ItemList.netherite_helmet, ItemList.netherite_chestplate, ItemList.netherite_leggings, ItemList.netherite_boots});
        final EquipmentSlotType[] slots = {EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET};
        ItemStack target = null;
        for (int i = 0; i < 4; i++) {
            if (armor[i]) {
                target = ouch.getEntityLiving().getItemBySlot(slots[i]);
                target.setDamageValue(target.getDamageValue() - 3);
            }
        }
    }
}
