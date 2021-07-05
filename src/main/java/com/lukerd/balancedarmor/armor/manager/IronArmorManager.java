package com.lukerd.balancedarmor.armor.manager;

import com.lukerd.balancedarmor.BalancedArmor;
import com.lukerd.balancedarmor.armor.manager.AbstractArmorManager;
import com.lukerd.balancedarmor.items.ItemList;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

import static net.minecraft.util.math.MathHelper.ceil;

@Mod.EventBusSubscriber(modid=BalancedArmor.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class IronArmorManager extends AbstractArmorManager {

    private static final double helmetSpeed = -0.05;
    private static final double chestSpeed = -0.1;
    private static final double legsSpeed = -0.1;
    private static final double feetSpeed = -0.05;
    private static final AttributeModifier helmetSpeedModifier = new AttributeModifier(
            UUID.fromString("2368215c-84cd-4772-9e6b-7345ee06ccc4"),
            "Iron helmet speed modifier",
            helmetSpeed,
            AttributeModifier.Operation.MULTIPLY_BASE
    );
    private static final AttributeModifier chestSpeedModifier = new AttributeModifier(
            UUID.fromString("f150d58a-86cd-4064-b4a4-4297b89f110b"),
            "Iron chestplate speed modifier",
            chestSpeed,
            AttributeModifier.Operation.MULTIPLY_BASE
    );
    private static final AttributeModifier legSpeedModifier = new AttributeModifier(
            UUID.fromString("9bb2a0bd-0dd1-4e56-9de2-8826ac0216a7"),
            "Iron leggings speed modifier",
            legsSpeed,
            AttributeModifier.Operation.MULTIPLY_BASE
    );
    private static final AttributeModifier feetSpeedModifier = new AttributeModifier(
            UUID.fromString("abc14da8-2db3-450b-89e4-9906c572fc8d"),
            "Iron boots speed modifier",
            feetSpeed,
            AttributeModifier.Operation.MULTIPLY_BASE
    );


    public IronArmorManager(){
        return;
    }


    protected static boolean isHelmet(ItemStack stack) {
        return AbstractArmorManager.isItem(stack,ItemList.iron_helmet);
    }

    protected static boolean isChestplate(ItemStack stack) {
        return AbstractArmorManager.isItem(stack,ItemList.iron_chestplate);
    }

    protected static boolean isLeggings(ItemStack stack) {
        return AbstractArmorManager.isItem(stack,ItemList.iron_leggings);
    }

    protected static boolean isBoots(ItemStack stack) {
        return AbstractArmorManager.isItem(stack,ItemList.iron_boots);
    }


    protected boolean[] hasIronArmor(LivingEntity entity) {
        return super.hasArmor(entity, new Item[]{ItemList.iron_helmet,ItemList.iron_chestplate,ItemList.iron_leggings,ItemList.iron_boots});
    }


    @SubscribeEvent
    public static void onEquipmentChange(LivingEquipmentChangeEvent event){
        if(event.getEntityLiving().level.isClientSide || !isArmor(event.getSlot())){
            return;
        }
        LivingEntity entity = event.getEntityLiving();
        ModifiableAttributeInstance target = entity.getAttribute(Attributes.MOVEMENT_SPEED);
        ItemStack stack = entity.getItemBySlot(event.getSlot());
        if(!AbstractArmorManager.isItem(event.getTo(), Items.AIR)){
            if(isHelmet(stack)) {
                if (target.getModifiers().contains(helmetSpeedModifier)) {
                    return;
                }
                target.addPermanentModifier(helmetSpeedModifier);
            }
            else if(isChestplate(stack)){
                if (target.getModifiers().contains(chestSpeedModifier)) {
                    return;
                }
                target.addPermanentModifier(chestSpeedModifier);
            }
            else if(isLeggings(stack)){
                if (target.getModifiers().contains(legSpeedModifier)) {
                    return;
                }
                target.addPermanentModifier(legSpeedModifier);
            }
            else if(isBoots(stack)){
                if (target.getModifiers().contains(feetSpeedModifier)) {
                    return;
                }
                target.addPermanentModifier(feetSpeedModifier);
            }
        }
        else if(!AbstractArmorManager.isItem(event.getFrom(),Items.AIR)){
            switch(event.getSlot()){
                case HEAD:
                    target.removePermanentModifier(helmetSpeedModifier.getId());
                case CHEST:
                    target.removePermanentModifier(chestSpeedModifier.getId());
                case LEGS:
                    target.removePermanentModifier(legSpeedModifier.getId());
                case FEET:
                    target.removePermanentModifier(feetSpeedModifier.getId());
            }
        }
    }


    @SubscribeEvent
    public static void onFall(LivingFallEvent fall){
        if(fall.getEntityLiving().level.isClientSide){
            return;
        }
        for(boolean bool : AbstractArmorManager.hasArmor(fall.getEntityLiving(),new Item[]{ItemList.iron_helmet,ItemList.iron_chestplate,ItemList.iron_leggings,ItemList.iron_boots})) {
            if (!bool || fall.getDistance() <= 2) {
                continue;
            }
            fall.setDistance(ceil(fall.getDistance()*1.1));
        }

    }

}
