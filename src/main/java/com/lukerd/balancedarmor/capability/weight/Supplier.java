package com.lukerd.balancedarmor.capability.weight;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.util.NonNullSupplier;

public class Supplier implements NonNullSupplier<IWeight> {
    @CapabilityInject(IWeight.class)
    public static Capability<IWeight> WEIGHT_CAPABILITY = null;

    public IWeight get(){
        return WEIGHT_CAPABILITY.getDefaultInstance();
    }
}
