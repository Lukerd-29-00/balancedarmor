package com.lukerd.balancedarmor.armor.manager;

import com.lukerd.balancedarmor.BalancedArmor;
import com.lukerd.balancedarmor.armor.BalancedArmorItem;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.util.text.TextFormatting;
import java.util.List;

@Mod.EventBusSubscriber(modid= BalancedArmor.MODID,bus=Mod.EventBusSubscriber.Bus.FORGE)
public class ToolTipManager {
    private static Style blueText = Style.EMPTY.withColor(TextFormatting.BLUE);
    private static Style redText = Style.EMPTY.withColor(TextFormatting.RED);

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onTooltipEvent(ItemTooltipEvent event){
        if(event.getItemStack().getItem() instanceof BalancedArmorItem) {
            BalancedArmorItem armorPiece = (BalancedArmorItem)event.getItemStack().getItem();
            List<ITextComponent> tip = event.getToolTip();
            String[] tmp;
            String teststr = "itemmodifiers";
            int i = 0;
            for (; i < tip.size(); i++) {
                if (tip.get(i) instanceof TranslationTextComponent) {

                    tmp = ((TranslationTextComponent) tip.get(i)).getKey().split("\\.");
                    if (tmp.length >= 2 && (tmp[0] + tmp[1]).equals(teststr)) {
                        i++;
                        break;
                    }
                }
            }
            tip.add(i++,new TranslationTextComponent("attribute.modifier.plus.0",String.valueOf(armorPiece.getDefense() + armorPiece.getArrowDefense()),new TranslationTextComponent("attribute.name.projectile_resistance")).setStyle(blueText));
            tip.add(i++,new TranslationTextComponent("attribute.modifier.plus.0",String.valueOf(armorPiece.getDefense() + armorPiece.getFireDefense()),new TranslationTextComponent("attribute.name.fire_resistance")).setStyle(blueText));
            tip.add(i++,new TranslationTextComponent("attribute.modifier.plus.0",String.valueOf(armorPiece.getDefense() + armorPiece.getMeleeDefense()),new TranslationTextComponent("attribute.name.melee_resistance")).setStyle(blueText));
            for(; i < tip.size(); i++){
                try{
                    tmp = ((TranslationTextComponent)tip.get(i)).getKey().split("\\.");
                    if(tmp.length >= 2 && !((tmp[0] + tmp[1]).equals("attributemodifier"))){
                        break;
                    }
                } catch(ClassCastException e){
                    break;
                }
            }
            tip.add(i,new TranslationTextComponent("attribute.modifier.plus.0",String.valueOf(armorPiece.getWeight()),new TranslationTextComponent("attribute.name.weight")).setStyle(redText));
        }
    }
}
