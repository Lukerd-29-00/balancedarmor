package com.lukerd.balancedarmor.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class Storage implements Capability.IStorage<IArmorCapability> {

    public INBT writeNBT(Capability<IArmorCapability> cap, IArmorCapability instance, Direction side){
        CompoundNBT output = new CompoundNBT();
        for(ArmorValue value: ArmorValue.getPossibleValues()){
            output.putShort(value.getLabel(),instance.getValue(value));
        }
        return output;
    }

    public void readNBT(Capability<IArmorCapability> cap, IArmorCapability instance, Direction side, INBT nbt){
        CompoundNBT castNBT = (CompoundNBT) nbt;
        for(ArmorValue value: ArmorValue.getPossibleValues()){
            instance.setValue(castNBT.getShort(value.getLabel()),value);
        }
    }
}
