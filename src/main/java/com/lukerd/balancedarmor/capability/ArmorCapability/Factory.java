package com.lukerd.balancedarmor.capability.ArmorCapability;

import java.util.concurrent.Callable;

public class Factory implements Callable<IArmorCapability> {
    public IArmorCapability call(){
        return new ArmorCapability();
    }
}
