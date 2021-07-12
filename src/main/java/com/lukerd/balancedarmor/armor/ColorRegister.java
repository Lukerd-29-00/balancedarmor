package com.lukerd.balancedarmor.armor;

import com.lukerd.balancedarmor.BalancedArmor;
import com.lukerd.balancedarmor.items.ItemList;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid= BalancedArmor.MODID,bus = Mod.EventBusSubscriber.Bus.MOD,value=Dist.CLIENT)
public class ColorRegister {

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void HandleColors(ColorHandlerEvent.Item event){
        event.getItemColors().register((stack,tintLayer) ->{
            if(tintLayer == 1){
                return 0xFFFFFFFF;
            }
            CompoundNBT display = stack.getOrCreateTag().getCompound("display");
            return display.equals(new CompoundNBT()) ? 10511680 : display.getInt("color");
        }, ItemList.leather_helmet, ItemList.leather_chestplate, ItemList.leather_leggings, ItemList.leather_boots);
    }
}
