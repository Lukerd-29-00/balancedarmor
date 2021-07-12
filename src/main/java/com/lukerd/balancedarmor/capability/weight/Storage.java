package com.lukerd.balancedarmor.capability.weight;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class Storage implements Capability.IStorage<IWeight>{
    public INBT writeNBT(Capability<IWeight> capability, IWeight instance, Direction side){
        CompoundNBT weight = new CompoundNBT();
        weight.putShort("Weight",instance.getWeight());
        return weight;
    }

    public void readNBT(Capability<IWeight> capability, IWeight instance, Direction side, INBT nbt){
        instance.setWeight(((CompoundNBT)nbt).getShort("Weight"));
    }
}
