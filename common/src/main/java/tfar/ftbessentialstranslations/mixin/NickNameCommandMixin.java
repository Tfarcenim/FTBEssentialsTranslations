package tfar.ftbessentialstranslations.mixin;

import dev.ftb.mods.ftbessentials.commands.impl.chat.NicknameCommand;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(NicknameCommand.class)
@Debug(export = true)
public class NickNameCommandMixin {

    @ModifyArg(method = "nickname",at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V"))
    private Component customMessage(Component original) {
        return Component.literal("this is a test");
    }

}
