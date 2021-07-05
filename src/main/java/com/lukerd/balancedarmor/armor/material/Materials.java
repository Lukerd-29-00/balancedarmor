package com.lukerd.balancedarmor.armor.material;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum Materials implements IBalancedArmorMaterial {

    MELTED_GOLD("balancedarmor:meltedgold",1,new int[][] {{1,0,0,0},{1,0,0,0},{0,0,0,0}},0, SoundEvents.ARMOR_EQUIP_GOLD,0.0F,0.0F,() -> {
        return Ingredient.of(new IItemProvider[]{Items.GOLD_INGOT});
    }),
    LEATHER("leather", 5, new int[][]{{1, 2, 3, 1},{1,2,3,1},{1,2,3,1}}, 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
        return Ingredient.of(new IItemProvider[]{Items.LEATHER});
    }),
    CHAIN("chainmail", 15, new int[][]{{1, 4, 5, 2},{0,1,1,0},{1,2,2,1}}, 12, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> {
        return Ingredient.of(new IItemProvider[]{Items.IRON_INGOT});
    }),
    IRON("iron", 15, new int[][]{{2, 5, 6, 2},{4,10,12,4},{1,2,3,1}}, 9, SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.05F, () -> {
        return Ingredient.of(new IItemProvider[]{Items.IRON_INGOT});
    }),
    DIAMOND("diamond", 33, new int[][]{{3, 6, 8, 3},{3,6,8,3},{3,6,8,3}}, 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> {
        return Ingredient.of(new IItemProvider[]{Items.DIAMOND});
    }),
    TURTLE("turtle", 25, new int[][]{{2, 5, 6, 2},{2,5,6,2},{2,5,6,2}}, 9, SoundEvents.ARMOR_EQUIP_TURTLE, 0.0F, 0.0F, () -> {
        return Ingredient.of(new IItemProvider[]{Items.SCUTE});
    }),
    NETHERITE("netherite", 37, new int[][]{{3, 6, 8, 3},{3,6,8,3},{6,12,16,6}}, 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> {
        return Ingredient.of(new IItemProvider[]{Items.NETHERITE_INGOT});
    });

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairIngredient;
    private int[][] realProtections;


    private Materials(String name, int durabilityMultiplier, int[][] realProtections, int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient){
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = new int[]{0,0,0,0};
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyValue(repairIngredient);
        this.realProtections = realProtections.clone();
    }

    public int getDurabilityForSlot(EquipmentSlotType p_200896_1_) {
        return HEALTH_PER_SLOT[p_200896_1_.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlotType p_200902_1_) {
        return 0;
    }

    public int getMeleeDefenseForSlot(EquipmentSlotType slot){
        return this.realProtections[0][slot.getIndex()];
    }

    public int getArrowDefenseForSlot(EquipmentSlotType slot){
        return this.realProtections[1][slot.getIndex()];
    }

    public int getFireDefenseForSlot(EquipmentSlotType slot){
        return this.realProtections[2][slot.getIndex()];
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return (Ingredient)this.repairIngredient.get();
    }

    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
