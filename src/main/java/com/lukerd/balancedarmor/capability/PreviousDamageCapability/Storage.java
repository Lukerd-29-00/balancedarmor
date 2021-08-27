package com.lukerd.balancedarmor.capability.PreviousDamageCapability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class Storage implements Capability.IStorage<IPreviousDamageCapability>{
    private static String key = "prevdmg";
    @Nullable
    @Override
    public INBT writeNBT(Capability<IPreviousDamageCapability> capability, IPreviousDamageCapability instance, Direction side) {
        CompoundNBT output = new CompoundNBT();
        output.putShort(key, instance.getDamage());
        return output;
    }

    @Override
    public void readNBT(Capability<IPreviousDamageCapability> capability, IPreviousDamageCapability instance, Direction side, INBT nbt) {
        instance.setDamage(((CompoundNBT)nbt).getShort(key));
    }
}
