package tfar.ftbessentialstranslations.mixin;

import dev.ftb.mods.ftbessentials.commands.impl.teleporting.TPACommand;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import tfar.ftbessentialstranslations.TranslationKeys;

@Mixin(TPACommand.class)
public class TPACommandMixin {
    @ModifyArg(method = "tpa",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V",ordinal = 0))
    private Component alreadySent(Component o) {
        return TranslationKeys.REQUEST_ALREADY_SENT;
    }

    @ModifyArg(method = "tpaccept",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V",ordinal = 0))
    private Component invalid(Component o) {
        return TranslationKeys.REQUEST_INVALID;
    }

    @ModifyArg(method = "tpaccept",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V",ordinal = 1))
    private Component invalid1(Component o) {
        return TranslationKeys.REQUEST_INVALID;
    }

    @ModifyArg(method = "tpaccept",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V",ordinal = 2))
    private Component offline(Component o) {
        return TranslationKeys.PLAYER_OFFLINE;
    }

    @ModifyArg(method = "tpdeny",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V",ordinal = 0))
    private Component invalid2(Component o) {
        return TranslationKeys.REQUEST_INVALID;
    }

    @ModifyArg(method = "tpdeny",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V",ordinal = 1))
    private Component invalid3(Component o) {
        return TranslationKeys.REQUEST_INVALID;
    }

    @ModifyArg(method = "tpdeny",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V",ordinal = 2))
    private Component denied(Component o) {
        return TranslationKeys.REQUEST_DENIED;
    }


    @ModifyArg(method = "tpdeny",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V",ordinal = 3))
    private Component denied1(Component o) {
        return TranslationKeys.REQUEST_DENIED;
    }


}
