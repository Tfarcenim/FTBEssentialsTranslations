package tfar.ftbessentialstranslations.mixin;

import dev.ftb.mods.ftbessentials.commands.groups.CheatCommands;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import tfar.ftbessentialstranslations.TranslationKeys;

@Mixin(CheatCommands.class)
public class CheatCommandsMixin {

    @ModifyArg(method = "enderChest",at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;sendFailure(Lnet/minecraft/network/chat/Component;)V"))
    private static Component enderChestFail(Component o) {
        return TranslationKeys.COMMAND_CHEAT_NO_ENDER_CHEST;
    }

    @ModifyArg(method = "lambda$fly$6",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V",ordinal = 0))
    private static Component flightEnable(Component o) {
        return TranslationKeys.COMMAND_FLY_DISABLED;
    }

    @ModifyArg(method = "lambda$fly$6",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V",ordinal = 1))
    private static Component flightDisable(Component o) {
        return TranslationKeys.COMMAND_FLY_ENABLED;
    }

    @ModifyArg(method = "lambda$god$7",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V",ordinal = 0))
    private static Component godEnable(Component o) {
        return TranslationKeys.COMMAND_GOD_DISABLED;
    }

    @ModifyArg(method = "lambda$god$7",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V",ordinal = 1))
    private static Component godDisable(Component o) {
        return TranslationKeys.COMMAND_GOD_ENABLED;
    }

}
