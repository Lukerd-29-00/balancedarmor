package com.lukerd.balancedarmor.capability.PreviousDamageCapability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.util.NonNullSupplier;

public class Supplier implements NonNullSupplier<IPreviousDamageCapability> {
    @CapabilityInject(IPreviousDamageCapability.class)
    public static Capability<IPreviousDamageCapability> PREVIOUS_DAMAGE_CAPABILITY = null;

    public IPreviousDamageCapability get() { return PREVIOUS_DAMAGE_CAPABILITY.getDefaultInstance(); }
}
