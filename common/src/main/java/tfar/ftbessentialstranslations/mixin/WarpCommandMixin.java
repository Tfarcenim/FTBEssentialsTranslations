package tfar.ftbessentialstranslations.mixin;

import dev.ftb.mods.ftbessentials.commands.impl.teleporting.WarpCommand;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import tfar.ftbessentialstranslations.TranslationKeys;

import java.util.function.Supplier;

@Mixin(WarpCommand.class)
public class WarpCommandMixin {

    @ModifyArg(method = "setWarp",at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V"))
    private Component set(Component o) {
        return TranslationKeys.COMMAND_WARP_SET;
    }

    @ModifyArg(method = "deleteWarp",at = @At(value = "INVOKE",
            target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V",ordinal = 0))
    private Component delete(Component o) {
        return TranslationKeys.COMMAND_WARP_SET;
    }

    @ModifyArg(method = "deleteWarp",at = @At(value = "INVOKE",
            target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V",ordinal = 1))
    private Component notFound(Component o) {
        return TranslationKeys.COMMAND_WARP_NOT_FOUND;
    }

    @ModifyArg(method = "listWarps",at = @At(value = "INVOKE",
            target = "Lnet/minecraft/commands/CommandSourceStack;sendSuccess(Ljava/util/function/Supplier;Z)V"))
    private Supplier<Component> none(Supplier<Component> o) {
        return () -> TranslationKeys.NONE;
    }
}
