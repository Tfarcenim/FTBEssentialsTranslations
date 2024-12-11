package tfar.ftbessentialstranslations.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.ftb.mods.ftbessentials.commands.impl.admin.NicknameForCommand;
import dev.ftb.mods.ftbessentials.util.FTBEPlayerData;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import tfar.ftbessentialstranslations.FTBEssentialsTranslations;
import tfar.ftbessentialstranslations.TranslationKeys;

import java.util.function.Supplier;

@Mixin(NicknameForCommand.class)
public class NicknameForCommandMixin {
    @ModifyArg(method = "nicknameFor", at = @At(value = "INVOKE", target = FTBEssentialsTranslations.DISPLAY))
    private Component tooLong(Component c) {
        return TranslationKeys.COMMAND_NICKNAME_TOO_LONG;
    }

    @ModifyArg(method = "lambda$nicknameFor$6", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/commands/CommandSourceStack;sendSuccess(Ljava/util/function/Supplier;Z)V",ordinal = 0))
    private static Supplier<Component> reset(Supplier<Component> o) {
        return () -> TranslationKeys.COMMAND_NICKNAME_RESET;
    }

    @ModifyArg(method = "lambda$nicknameFor$6", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/commands/CommandSourceStack;sendSuccess(Ljava/util/function/Supplier;Z)V",ordinal = 1))
    private static Supplier<Component> change(Supplier<Component> o, @Local(argsOnly = true) FTBEPlayerData data) {
        return () -> TranslationKeys.nickNameChange(data.getNick());
    }

}
