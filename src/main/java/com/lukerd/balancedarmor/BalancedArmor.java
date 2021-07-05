package com.lukerd.balancedarmor;

import com.lukerd.balancedarmor.armor.BalancedArmorItem;
import com.lukerd.balancedarmor.armor.material.Materials;
import com.lukerd.balancedarmor.slots.ArmorSlot;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.system.CallbackI;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("balancedarmor")
public class BalancedArmor
{
    public static final String MODID = "balancedarmor";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public BalancedArmor() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("balancedarmor", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts

    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {

            final BalancedArmorItem leather_helmet = new BalancedArmorItem(Materials.LEATHER,ArmorSlot.HEAD.getValue(),new Item.Properties().tab(ItemGroup.TAB_COMBAT));
            final BalancedArmorItem leather_chestplate = new BalancedArmorItem(Materials.LEATHER,ArmorSlot.CHEST.getValue(),new Item.Properties().tab(ItemGroup.TAB_COMBAT));
            final BalancedArmorItem leather_leggings = new BalancedArmorItem(Materials.LEATHER,ArmorSlot.LEGS.getValue(),new Item.Properties().tab(ItemGroup.TAB_COMBAT));
            final BalancedArmorItem leather_boots = new BalancedArmorItem(Materials.LEATHER,ArmorSlot.FEET.getValue(),new Item.Properties().tab(ItemGroup.TAB_COMBAT));

            final BalancedArmorItem chain_helmet = new BalancedArmorItem(Materials.CHAIN,ArmorSlot.HEAD.getValue(),new Item.Properties().tab(ItemGroup.TAB_COMBAT));
            final BalancedArmorItem chain_chestplate = new BalancedArmorItem(Materials.CHAIN,ArmorSlot.CHEST.getValue(),new Item.Properties().tab(ItemGroup.TAB_COMBAT));
            final BalancedArmorItem chain_leggings = new BalancedArmorItem(Materials.CHAIN,ArmorSlot.LEGS.getValue(),new Item.Properties().tab(ItemGroup.TAB_COMBAT));
            final BalancedArmorItem chain_boots = new BalancedArmorItem(Materials.CHAIN,ArmorSlot.FEET.getValue(),new Item.Properties().tab(ItemGroup.TAB_COMBAT));

            final BalancedArmorItem iron_helmet = new BalancedArmorItem(Materials.IRON, ArmorSlot.HEAD.getValue(),new Item.Properties().tab(ItemGroup.TAB_COMBAT));
            final BalancedArmorItem iron_chestplate = new BalancedArmorItem(Materials.IRON,ArmorSlot.CHEST.getValue(),new Item.Properties().tab(ItemGroup.TAB_COMBAT));
            final BalancedArmorItem iron_leggings = new BalancedArmorItem(Materials.IRON,ArmorSlot.LEGS.getValue(), new Item.Properties().tab(ItemGroup.TAB_COMBAT));
            final BalancedArmorItem iron_boots = new BalancedArmorItem(Materials.IRON,ArmorSlot.FEET.getValue(),new Item.Properties().tab(ItemGroup.TAB_COMBAT));

            final ArmorItem gold_helmet = new ArmorItem(ArmorMaterial.GOLD,ArmorSlot.HEAD.getValue(),new Item.Properties().tab(ItemGroup.TAB_COMBAT));
            final ArmorItem gold_chestplate = new ArmorItem(ArmorMaterial.GOLD,ArmorSlot.CHEST.getValue(),new Item.Properties().tab(ItemGroup.TAB_COMBAT));
            final ArmorItem gold_leggings = new ArmorItem(ArmorMaterial.GOLD,ArmorSlot.LEGS.getValue(),new Item.Properties().tab(ItemGroup.TAB_COMBAT));
            final ArmorItem gold_boots = new ArmorItem(ArmorMaterial.GOLD,ArmorSlot.FEET.getValue(),new Item.Properties().tab(ItemGroup.TAB_COMBAT));

            final BalancedArmorItem diamond_helmet = new BalancedArmorItem(Materials.DIAMOND,ArmorSlot.HEAD.getValue(),new Item.Properties().tab(ItemGroup.TAB_COMBAT));
            final BalancedArmorItem diamond_chestplate = new BalancedArmorItem(Materials.DIAMOND,ArmorSlot.CHEST.getValue(),new Item.Properties().tab(ItemGroup.TAB_COMBAT));
            final BalancedArmorItem diamond_leggings = new BalancedArmorItem(Materials.DIAMOND,ArmorSlot.LEGS.getValue(),new Item.Properties().tab(ItemGroup.TAB_COMBAT));
            final BalancedArmorItem diamond_boots = new BalancedArmorItem(Materials.DIAMOND,ArmorSlot.FEET.getValue(),new Item.Properties().tab(ItemGroup.TAB_COMBAT));

            final BalancedArmorItem netherite_helmet = new BalancedArmorItem(Materials.NETHERITE,ArmorSlot.HEAD.getValue(),new Item.Properties().tab(ItemGroup.TAB_COMBAT).fireResistant());
            final BalancedArmorItem netherite_chestplate = new BalancedArmorItem(Materials.NETHERITE,ArmorSlot.CHEST.getValue(),new Item.Properties().tab(ItemGroup.TAB_COMBAT).fireResistant());
            final BalancedArmorItem netherite_leggings = new BalancedArmorItem(Materials.NETHERITE,ArmorSlot.LEGS.getValue(),new Item.Properties().tab(ItemGroup.TAB_COMBAT).fireResistant());
            final BalancedArmorItem netherite_boots = new BalancedArmorItem(Materials.NETHERITE,ArmorSlot.FEET.getValue(),new Item.Properties().tab(ItemGroup.TAB_COMBAT).fireResistant());

            final ArmorItem melted_gold_helmet = new ArmorItem(Materials.MELTED_GOLD, EquipmentSlotType.HEAD,new Item.Properties().setNoRepair());

            leather_helmet.setRegistryName("leather_helmet");
            leather_chestplate.setRegistryName("leather_chestplate");
            leather_leggings.setRegistryName("leather_leggings");
            leather_boots.setRegistryName("leather_boots");

            chain_helmet.setRegistryName("chainmail_helmet");
            chain_chestplate.setRegistryName("chainmail_chestplate");
            chain_leggings.setRegistryName("chainmail_leggings");
            chain_boots.setRegistryName("chainmail_boots");

            iron_helmet.setRegistryName("iron_helmet");
            iron_chestplate.setRegistryName("iron_chestplate");
            iron_leggings.setRegistryName("iron_leggings");
            iron_boots.setRegistryName("iron_boots");

            gold_helmet.setRegistryName("golden_helmet");
            gold_chestplate.setRegistryName("golden_chestplate");
            gold_leggings.setRegistryName("golden_leggings");
            gold_boots.setRegistryName("golden_boots");

            diamond_helmet.setRegistryName("diamond_helmet");
            diamond_chestplate.setRegistryName("diamond_chestplate");
            diamond_leggings.setRegistryName("diamond_leggings");
            diamond_boots.setRegistryName("diamond_boots");

            netherite_helmet.setRegistryName("netherite_helmet");
            netherite_chestplate.setRegistryName("netherite_chestplate");
            netherite_leggings.setRegistryName("netherite_leggings");
            netherite_boots.setRegistryName("netherite_boots");

            melted_gold_helmet.setRegistryName("melted_golden_helmet");

            Item[] items = {
                    leather_helmet,
                    leather_chestplate,
                    leather_leggings,
                    leather_boots,

                    chain_helmet,
                    chain_chestplate,
                    chain_leggings,
                    chain_boots,

                    iron_helmet,
                    iron_chestplate,
                    iron_leggings,
                    iron_boots,

                    gold_helmet,
                    gold_chestplate,
                    gold_leggings,
                    gold_boots,

                    diamond_helmet,
                    diamond_chestplate,
                    diamond_leggings,
                    diamond_boots,

                    netherite_helmet,
                    netherite_chestplate,
                    netherite_leggings,
                    netherite_boots,

                    melted_gold_helmet
            };
            itemRegistryEvent.getRegistry().registerAll(items);

            //CapabilityManager.INSTANCE.register(IArrowResist.class, new Storage(),new Factory());
        }




    }
}
