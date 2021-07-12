package com.lukerd.balancedarmor.capability.weight;

import java.util.concurrent.Callable;

public class Factory implements Callable<IWeight> {
    public IWeight call(){
        return new Weight();
    }
}
