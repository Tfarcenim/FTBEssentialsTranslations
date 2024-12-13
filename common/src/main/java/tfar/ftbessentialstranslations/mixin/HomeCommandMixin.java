package tfar.ftbessentialstranslations.mixin;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.ftb.mods.ftbessentials.commands.impl.teleporting.HomeCommand;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.*;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import tfar.ftbessentialstranslations.CommandHooks;
import tfar.ftbessentialstranslations.TranslationKeys;

import java.util.List;

@Mixin(HomeCommand.class)
@Debug(export = true)
public abstract class HomeCommandMixin {

    @Shadow public abstract List<LiteralArgumentBuilder<CommandSourceStack>> register();

    @ModifyArg(method = "lambda$setHome$13",at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V",ordinal = 0))
    private static Component setHome(Component o) {
        return TranslationKeys.COMMAND_HOME_SET;
    }

    @ModifyArg(method = "lambda$setHome$13",at = @At(value = "INVOKE",
            target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V",ordinal = 1))
    private static Component tooMany(Component o) {
        return TranslationKeys.COMMAND_HOME_TOO_MANY;
    }

    @ModifyArg(method = "lambda$delHome$14",at = @At(value = "INVOKE",
            target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V",ordinal = 0))
    private static Component deleted(Component o) {
        return TranslationKeys.COMMAND_HOME_DELETED;
    }

    @ModifyArg(method = "lambda$delHome$14",at = @At(value = "INVOKE",
            target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V",ordinal = 1))
    private static Component notFound(Component o) {
        return TranslationKeys.COMMAND_HOME_NOT_FOUND;
    }

    /*@ModifyArg(method = "lambda$listHomes$19",at = @At(value = "INVOKE",
            target = "Lnet/minecraft/commands/CommandSourceStack;sendSuccess(Ljava/util/function/Supplier;Z)V",ordinal = 0))
    private static Supplier<Component> none(Supplier<Component> o) {
        return () -> TranslationKeys.NONE;
    }*/

    /*@ModifyArg(method = "lambda$listHomes$19",at = @At(value = "INVOKE",
            target = "Lnet/minecraft/commands/CommandSourceStack;sendSuccess(Ljava/util/function/Supplier;Z)V",ordinal = 1))
    private static Supplier<Component> list(Supplier<Component> o, @Local(argsOnly = true) GameProfile profile) {
        return () -> TranslationKeys.homesFor(profile.getName()).withStyle(ChatFormatting.GOLD);
    }*/

    /*@ModifyArg(method = "*",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/HoverEvent;<init>(Lnet/minecraft/network/chat/HoverEvent$Action;Ljava/lang/Object;)V")) private Object clickTeleport(Object o) {
        return TranslationKeys.CLICK_TO_TELEPORT;
    }*/

    //easier than trying to make a modifyarg
    @Overwrite
    public static int listHomes(CommandSourceStack source, GameProfile of) {
        return CommandHooks.listHomes(source, of);
    }

}
