package com.lukerd.balancedarmor.capability;

public interface IArmorCapability {
    public short getValue(ArmorValue desired) throws IllegalArgumentException;
    public void setValue(short value,ArmorValue field);
}
