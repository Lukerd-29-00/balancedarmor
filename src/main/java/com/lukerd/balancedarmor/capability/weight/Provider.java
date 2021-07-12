package com.lukerd.balancedarmor.capability.weight;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.NonNullSupplier;

public class Provider implements ICapabilityProvider {

    @CapabilityInject(IWeight.class)
    public static Capability<IWeight> WEIGHT_CAPABILITY = null;

    NonNullSupplier<IWeight> supplier = new Supplier();

    private IWeight instance = WEIGHT_CAPABILITY.getDefaultInstance();

    private LazyOptional<IWeight> lazy = LazyOptional.of(supplier);


    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side){
        if(cap == WEIGHT_CAPABILITY){
            return lazy.cast();
        }
        return LazyOptional.empty();
    }

}
