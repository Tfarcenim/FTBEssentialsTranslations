package tfar.ftbessentialstranslations.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.ftb.mods.ftbessentials.commands.impl.chat.NicknameCommand;
import dev.ftb.mods.ftbessentials.util.FTBEPlayerData;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import tfar.ftbessentialstranslations.FTBEssentialsTranslations;
import tfar.ftbessentialstranslations.TranslationKeys;

@Mixin(NicknameCommand.class)
@Debug(export = true)
public class NicknameCommandMixin {

    @ModifyArg(method = "nickname",at = @At(value = "INVOKE", target = FTBEssentialsTranslations.DISPLAY))
    private Component tooLong(Component original) {
        return TranslationKeys.COMMAND_NICKNAME_TOO_LONG;
    }


    @ModifyArg(method = "lambda$nickname$2",at = @At(value = "INVOKE", target = FTBEssentialsTranslations.DISPLAY,ordinal = 0))
    private static Component reset(Component original) {
        return TranslationKeys.COMMAND_NICKNAME_RESET;
    }

    @ModifyArg(method = "lambda$nickname$2",at = @At(value = "INVOKE", target = FTBEssentialsTranslations.DISPLAY,ordinal = 1))
    private static Component change(Component original, @Local(argsOnly = true) FTBEPlayerData data) {
        return TranslationKeys.nickNameChange(data.getNick());
    }

}
