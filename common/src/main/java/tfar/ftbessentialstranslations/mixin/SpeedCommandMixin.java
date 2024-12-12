package tfar.ftbessentialstranslations.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.ftb.mods.ftbessentials.commands.impl.cheat.SpeedCommand;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import tfar.ftbessentialstranslations.TranslationKeys;

@Mixin(SpeedCommand.class)
public class SpeedCommandMixin {


    @Shadow @Final private static ResourceLocation ESSENTIALS_SPEED_ID;

    @ModifyVariable(method = "showSpeed",at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;sendSuccess(Ljava/util/function/Supplier;Z)V"))
    private static Component showSpeed(Component o, @Local(argsOnly = true) AttributeInstance instance, @Local (argsOnly = true)ServerPlayer target) {
        boolean show = instance.hasModifier(ESSENTIALS_SPEED_ID);

        AttributeModifier modifier = instance.getModifier(ESSENTIALS_SPEED_ID);

        int boostPct = 0;

        if (modifier != null) {
            double speedMult = modifier.amount();
            boostPct = (int) (speedMult * 100.0);
        }

        return show ? TranslationKeys.speedBoost(target.getDisplayName(),Component.translatable(instance.getAttribute().value().getDescriptionId()),boostPct) :
                TranslationKeys.noSpeedBoost(target.getDisplayName());
    }
}
