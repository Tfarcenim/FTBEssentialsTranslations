package tfar.ftbessentialstranslations.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.ftb.mods.ftbessentials.commands.groups.TeleportingCommands;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import tfar.ftbessentialstranslations.TranslationKeys;
//@Debug(export = true)
@Mixin(TeleportingCommands.class)
public class TeleportingCommandsMixin {
    @ModifyArg(method = "lambda$back$7",at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V"))
    private static Component empty(Component o) {
        return TranslationKeys.COMMAND_TELEPORT_HISTORY.copy().withStyle(ChatFormatting.RED);
    }

    @ModifyArg(method = "rtp",at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V"))
    private static Component denied(Component o) {
        return TranslationKeys.COMMAND_RTP_DENIED.copy().withStyle(ChatFormatting.RED);
    }

    @ModifyArg(method = "lambda$rtp$10",at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V"))
    private static Component looking(Component o) {
        return TranslationKeys.COMMAND_RTP_LOOKING;
    }

    @ModifyArg(method = "findBlockPos",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V",ordinal = 0))
    private static Component foundPos(Component o, @Local(ordinal = 0)int attempts, @Local(ordinal = 1) BlockPos goodPos) {
        return TranslationKeys.foundGoodLocation(attempts,goodPos);
    }

    @ModifyArg(method = "findBlockPos",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V",ordinal = 1))
    private static Component cantFindPos(Component o) {
        return TranslationKeys.COMMAND_RTP_COULDNT_FIND.copy().withStyle(ChatFormatting.RED);
    }

    @ModifyArg(method = "jump",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;sendFailure(Lnet/minecraft/network/chat/Component;)V"))
    private static Component failedJump(Component o,@Local Exception e) {
        return TranslationKeys.cantJump(e.getMessage());
    }




}
