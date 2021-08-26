package com.lukerd.balancedarmor.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.util.NonNullSupplier;

public class Supplier implements NonNullSupplier<IArmorCapability> {
    @CapabilityInject(IArmorCapability.class)
    public static Capability<IArmorCapability> ARMOR_CAPABILITY = null;

    public IArmorCapability get(){
        return ARMOR_CAPABILITY.getDefaultInstance();
    }
}
