package com.lukerd.balancedarmor.capability;

public class ArmorCapability implements IArmorCapability {
    private short weight;
    private short fireDefense;
    private short arrowDefense;
    private short meleeDefense;

    public short getValue(ArmorValue desired) throws IllegalArgumentException{
        switch(desired){
            case WEIGHT:
                return this.weight;
            case FIRE_DEFENSE:
                return this.fireDefense;
            case ARROW_DEFENSE:
                return this.arrowDefense;
            case MELEE_DEFENSE:
                return this.meleeDefense;
        }
        throw new IllegalArgumentException("Invalid enum value passed to getValue!");
    }

    public void setValue(short value, ArmorValue field){
        switch(field){
            case WEIGHT:
                this.weight = value;
                break;
            case FIRE_DEFENSE:
                this.fireDefense = value;
                break;
            case ARROW_DEFENSE:
                this.arrowDefense = value;
                break;
            case MELEE_DEFENSE:
                this.meleeDefense = value;
                break;
        }
    }
}
