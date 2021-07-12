package com.lukerd.balancedarmor.armor;

import com.lukerd.balancedarmor.armor.material.Materials;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import com.lukerd.balancedarmor.items.ItemList;

public class ArmorLookup {
    private static final Item[][] vanillasets = {
            {Items.LEATHER_BOOTS,Items.LEATHER_LEGGINGS,Items.LEATHER_CHESTPLATE,Items.LEATHER_HELMET},
            {Items.GOLDEN_BOOTS,Items.GOLDEN_LEGGINGS,Items.GOLDEN_LEGGINGS,Items.GOLDEN_BOOTS},
            {Items.CHAINMAIL_BOOTS,Items.CHAINMAIL_LEGGINGS,Items.CHAINMAIL_CHESTPLATE,Items.CHAINMAIL_HELMET},
            {Items.IRON_BOOTS,Items.IRON_LEGGINGS,Items.IRON_CHESTPLATE,Items.IRON_HELMET},
            {Items.DIAMOND_BOOTS,Items.DIAMOND_LEGGINGS,Items.DIAMOND_CHESTPLATE,Items.DIAMOND_HELMET},
            {null,null,null,Items.TURTLE_HELMET},
            {Items.NETHERITE_BOOTS,Items.NETHERITE_LEGGINGS,Items.NETHERITE_CHESTPLATE,Items.NETHERITE_HELMET}
    };
    private static final Item[][] balancedsets = {
            {null,null,null,ItemList.melted_golden_helmet},
            {ItemList.leather_boots,ItemList.leather_leggings,ItemList.leather_chestplate,ItemList.leather_helmet},
            {ItemList.golden_boots,ItemList.golden_leggings,ItemList.golden_chestplate,ItemList.golden_helmet},
            {ItemList.chain_boots,ItemList.chain_leggings,ItemList.chain_chestplate,ItemList.chain_helmet},
            {ItemList.iron_boots,ItemList.iron_leggings,ItemList.iron_chestplate,ItemList.iron_helmet},
            {ItemList.diamond_boots,ItemList.diamond_leggings,ItemList.diamond_chestplate,ItemList.diamond_helmet},
            {ItemList.netherite_boots,ItemList.netherite_leggings,ItemList.netherite_chestplate,ItemList.netherite_helmet}
    };

    private static Item[] getVanillaSetByMaterial(ArmorMaterial material) throws IllegalArgumentException{
        switch(material){
            case LEATHER:
                return vanillasets[0];
            case GOLD:
                return vanillasets[1];
            case CHAIN:
                return vanillasets[2];
            case IRON:
                return vanillasets[3];
            case DIAMOND:
                return vanillasets[4];
            case TURTLE:
                return vanillasets[5];
            case NETHERITE:
                return vanillasets[6];
        }
        throw new IllegalArgumentException("ERROR: Invalid material detected!");
    }

    private static Item[] getBalancedSetByMaterial(Materials material) throws IllegalArgumentException{
        switch(material){
            case MELTED_GOLD:
                return balancedsets[0];
            case LEATHER:
                return balancedsets[1];
            case GOLD:
                return balancedsets[2];
            case CHAIN:
                return balancedsets[3];
            case IRON:
                return balancedsets[4];
            case DIAMOND:
                return balancedsets[5];
            case NETHERITE:
                return balancedsets[6];
        }
        throw new IllegalArgumentException("ERROR: Invalid material detected!");
    }

    public static ArmorItem getArmor(IArmorMaterial material, EquipmentSlotType slot){
        return material instanceof ArmorMaterial ? (ArmorItem)getVanillaSetByMaterial((ArmorMaterial)material)[slot.getIndex()] : (ArmorItem)getBalancedSetByMaterial((Materials)material)[slot.getIndex()];
    }
}
