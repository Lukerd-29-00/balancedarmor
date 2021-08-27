package com.lukerd.balancedarmor.capability.ArmorCapability;

import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.NonNullSupplier;

public class Provider implements ICapabilityProvider {
    @CapabilityInject(IArmorCapability.class)
    public static Capability<IArmorCapability> ARMOR_CAPABILITY = null;


    private IArmorCapability instance = ARMOR_CAPABILITY.getDefaultInstance();


    private NonNullSupplier<IArmorCapability> supplier = new Supplier();

    private LazyOptional<IArmorCapability> lazy = LazyOptional.of(supplier);


    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side){
        if(cap == ARMOR_CAPABILITY){
            return lazy.cast();
        }
        return LazyOptional.empty();
    }
}
