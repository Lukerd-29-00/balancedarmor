package com.lukerd.balancedarmor.capability;

import java.util.concurrent.Callable;

public class Factory implements Callable<IArmorCapability> {
    public IArmorCapability call(){
        return new ArmorCapability();
    }
}
