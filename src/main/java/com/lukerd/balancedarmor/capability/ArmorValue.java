package com.lukerd.balancedarmor.capability;

public enum ArmorValue {
    WEIGHT,
    FIRE_DEFENSE,
    ARROW_DEFENSE,
    MELEE_DEFENSE;

    public String getLabel() throws IllegalArgumentException{
        switch(this){
            case WEIGHT:
                return "weight";
            case FIRE_DEFENSE:
                return "fireDefense";
            case ARROW_DEFENSE:
                return "arrowDefense";
            case MELEE_DEFENSE:
                return "meleeDefense";
        }
        throw new IllegalArgumentException("Cannot get label of invalid ArmorValue!");
    }

    public static ArmorValue[] getPossibleValues(){
        return new ArmorValue[]{ WEIGHT, FIRE_DEFENSE, ARROW_DEFENSE, MELEE_DEFENSE };
    }
}
