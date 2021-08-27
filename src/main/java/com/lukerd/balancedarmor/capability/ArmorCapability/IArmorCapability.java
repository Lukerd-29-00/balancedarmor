package com.lukerd.balancedarmor.capability.ArmorCapability;

import com.lukerd.balancedarmor.capability.ArmorCapability.ArmorValue;

public interface IArmorCapability {
    public short getValue(ArmorValue desired) throws IllegalArgumentException;
    public void setValue(short value,ArmorValue field);
}
