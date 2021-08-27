package com.lukerd.balancedarmor.capability.PreviousDamageCapability;

import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.NonNullSupplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Provider implements ICapabilityProvider {
    @CapabilityInject(IPreviousDamageCapability.class)
    public static Capability<IPreviousDamageCapability> PREVIOUS_DAMAGE_CAPABILITY = null;

    private IPreviousDamageCapability instance = PREVIOUS_DAMAGE_CAPABILITY.getDefaultInstance();

    private NonNullSupplier<IPreviousDamageCapability> supplier = new Supplier();

    private LazyOptional<IPreviousDamageCapability> lazy = LazyOptional.of(supplier);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == PREVIOUS_DAMAGE_CAPABILITY){
            return lazy.cast();
        }
        return LazyOptional.empty();
    }
}
