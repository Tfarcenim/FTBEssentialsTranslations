package tfar.ftbessentialstranslations.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.ftb.mods.ftbessentials.commands.impl.chat.MuteCommand;
import dev.ftb.mods.ftbessentials.util.DurationInfo;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import tfar.ftbessentialstranslations.TranslationKeys;

@Mixin(MuteCommand.class)
public class MuteCommandMixin {
    @ModifyArg(method = "lambda$mute$4",at= @At(value = "INVOKE",
            target = "Ldev/ftb/mods/ftbessentials/commands/impl/chat/MuteCommand;notifyMuting(Lnet/minecraft/commands/CommandSourceStack;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/network/chat/Component;)V"))
    private Component notifyMuting(Component o, @Local(argsOnly = true) ServerPlayer player, @Local(argsOnly = true) CommandSourceStack source, @Local DurationInfo info) {
        return TranslationKeys.notifyMuting(player.getDisplayName(),source.getDisplayName(),info.desc());
    }

    @ModifyArg(method = "lambda$unmute$5",at = @At(value = "INVOKE", target = "Ldev/ftb/mods/ftbessentials/commands/impl/chat/MuteCommand;notifyMuting(Lnet/minecraft/commands/CommandSourceStack;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/network/chat/Component;)V"))
    private Component notifyUnmuting(Component o, @Local(argsOnly = true) ServerPlayer player, @Local(argsOnly = true) CommandSourceStack source) {
        return TranslationKeys.notifyunMuting(player.getDisplayName(),source.getDisplayName());
    }
}
