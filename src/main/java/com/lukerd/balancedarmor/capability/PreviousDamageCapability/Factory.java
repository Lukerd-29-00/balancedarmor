package com.lukerd.balancedarmor.capability.PreviousDamageCapability;

import java.util.concurrent.Callable;

public class Factory implements Callable<IPreviousDamageCapability> {
    public IPreviousDamageCapability call(){
        return new PreviousDamageCapability();
    }
}
