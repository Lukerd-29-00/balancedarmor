package com.lukerd.balancedarmor.capability.PreviousDamageCapability;

public class PreviousDamageCapability implements IPreviousDamageCapability{
    private short damage;
    @Override
    public short getDamage() {
        return this.damage;
    }

    @Override
    public void setDamage(short damage) {
        this.damage = damage;
    }
}
