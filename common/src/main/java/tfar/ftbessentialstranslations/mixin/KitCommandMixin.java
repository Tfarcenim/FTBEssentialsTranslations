package tfar.ftbessentialstranslations.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.ftb.mods.ftbessentials.commands.impl.KitCommand;
import dev.ftb.mods.ftbessentials.kit.Kit;
import dev.ftb.mods.ftbessentials.kit.KitManager;
import dev.ftb.mods.ftbessentials.util.DurationInfo;
import dev.ftb.mods.ftblibrary.util.TimeUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import tfar.ftbessentialstranslations.CommandHooks;
import tfar.ftbessentialstranslations.TranslationKeys;

import java.util.Collection;
import java.util.UUID;
import java.util.function.Supplier;

@Mixin(KitCommand.class)
public class KitCommandMixin {
    @ModifyArg(method = "putKitInBlockInv",at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;sendSuccess(Ljava/util/function/Supplier;Z)V"))
    private static Supplier<Component> inBlock(Supplier<Component> o, @Local(argsOnly = true) String kitName) {
        return ()-> TranslationKeys.addKitToBlock(kitName).withStyle(ChatFormatting.YELLOW);
    }

    @ModifyArg(method = "putKitInBlockInv",at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;sendFailure(Lnet/minecraft/network/chat/Component;)V"))
    private static Component cantStore(Component o, @Local Exception e) {
        return TranslationKeys.cantStore(e.getMessage());
    }

    @ModifyArg(method = "createKitFromPlayer",at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;sendFailure(Lnet/minecraft/network/chat/Component;)V"))
    private static Component failPlayer(Component o, @Local Exception e) {
        return TranslationKeys.cantCreateKit(e.getMessage());
    }

    @ModifyArg(method = "createKitFromPlayer",at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;sendSuccess(Ljava/util/function/Supplier;Z)V"))
    private static Supplier<Component> createPlayer(Supplier<Component> o, @Local(argsOnly = true,ordinal = 0) String name) {
        return ()-> TranslationKeys.createKit(name).withStyle(ChatFormatting.YELLOW);
    }

    @ModifyArg(method = "createKitFromBlock",at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;sendSuccess(Ljava/util/function/Supplier;Z)V"))
    private static Supplier<Component> createBlock(Supplier<Component> o, @Local(argsOnly = true,ordinal = 0) String name) {
        return ()-> TranslationKeys.createKit(name).withStyle(ChatFormatting.YELLOW);
    }

    @ModifyArg(method = "createKitFromBlock",at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;sendFailure(Lnet/minecraft/network/chat/Component;)V"))
    private static Component failBlock(Component o, @Local Exception e) {
        return TranslationKeys.cantCreateKit(e.getMessage());
    }

    @ModifyArg(method = "giveKit",at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;sendFailure(Lnet/minecraft/network/chat/Component;)V"))
    private static Component cantGive(Component o, @Local Exception e) {
        return TranslationKeys.cantGiveKit(e.getMessage());
    }

    @ModifyArg(method = "giveKit",at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;sendSuccess(Ljava/util/function/Supplier;Z)V"))
    private static Supplier<Component> giveKit(Supplier<Component> o, @Local(argsOnly = true,ordinal = 0) String name, @Local(argsOnly = true) Collection<ServerPlayer> players) {
        return ()-> TranslationKeys.giveKitMultiple(name,players.size()).withStyle(ChatFormatting.YELLOW);
    }


    @ModifyArg(method = "listKits",at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;sendSuccess(Ljava/util/function/Supplier;Z)V",ordinal = 0))
    private static Supplier<Component> listKit(Supplier<Component> o, @Local Collection<Kit> kits) {
        return ()-> TranslationKeys.listKits(kits.size()).withStyle(ChatFormatting.AQUA);
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    private static int showKit(CommandSourceStack source, String kitName) {
        return CommandHooks.showKit(source, kitName);
    }

    @ModifyArg(method = "deleteKit",at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;sendSuccess(Ljava/util/function/Supplier;Z)V"))
    private static Supplier<Component> deleteKit(Supplier<Component> o,@Local(argsOnly = true) String kitName) {
        return () -> TranslationKeys.kitDeleted(kitName).withStyle(ChatFormatting.YELLOW);
    }

    @ModifyArg(method = "deleteKit",at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;sendFailure(Lnet/minecraft/network/chat/Component;)V"))
    private static Component cantDelete(Component o, @Local(argsOnly = true) String kitName, @Local Exception e) {
        return TranslationKeys.cantDelete(kitName,e.getMessage());
    }

    @ModifyArg(method = "lambda$modifyAutogrant$50",at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;sendSuccess(Ljava/util/function/Supplier;Z)V"))
    private static Supplier<Component> autoGrantModify(Supplier<Component> o, @Local(argsOnly = true) String kitName,@Local(argsOnly = true) boolean grant) {
        return () -> TranslationKeys.autoGrantModify(kitName,grant);
    }

    @ModifyArg(method = "modifyAutogrant",at = @At(value = "INVOKE", target = "Ljava/util/Optional;ifPresentOrElse(Ljava/util/function/Consumer;Ljava/lang/Runnable;)V"))
    private static Runnable noKitAutoGrant(Runnable r, @Local(argsOnly = true) CommandSourceStack source, @Local(argsOnly = true) String kitName) {
        return () -> source.sendFailure(TranslationKeys.noKit(kitName).withStyle(ChatFormatting.RED));
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    private static int modifyCooldown(CommandSourceStack source, String kitName, String cooldown) {
        KitManager.getInstance().get(kitName).ifPresentOrElse((kit) -> {
            long secs = DurationInfo.getSeconds(cooldown);
            KitManager.getInstance().addKit(kit.withCooldown(secs), true);
            Component newTime = secs < 0L ? TranslationKeys.KIT_ONE_TIME_USE : Component.literal(TimeUtils.prettyTimeString(secs));
            source.sendSuccess(() -> TranslationKeys.cooldownModify(kitName,newTime).withStyle(ChatFormatting.YELLOW), false);
        }, () -> source.sendFailure(TranslationKeys.noKit(kitName).withStyle(ChatFormatting.RED)));
        return 1;
    }

    @ModifyArg(method = "resetCooldowns(Lnet/minecraft/commands/CommandSourceStack;Ljava/lang/String;)I",at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;sendSuccess(Ljava/util/function/Supplier;Z)V"))
    private static Supplier<Component> resetAll(Supplier<Component> o,@Local(argsOnly = true) String kitName) {
        return () -> TranslationKeys.resetCooldownAll(kitName).withStyle(ChatFormatting.YELLOW);
    }

    @ModifyArg(method = "resetCooldowns(Lnet/minecraft/commands/CommandSourceStack;Ljava/lang/String;)I",at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;sendFailure(Lnet/minecraft/network/chat/Component;)V"))
    private static Component unknownAll(Component o, @Local(argsOnly = true) String kitName) {
        return TranslationKeys.unknownKit(kitName);
    }

    @ModifyArg(method = "resetCooldowns(Lnet/minecraft/commands/CommandSourceStack;Ljava/lang/String;Ljava/util/UUID;)I",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;sendFailure(Lnet/minecraft/network/chat/Component;)V",ordinal = 0))
    private static Component unknown(Component o, @Local(argsOnly = true) String kitName) {
        return TranslationKeys.unknownKit(kitName).withStyle(ChatFormatting.RED);
    }

    @ModifyArg(method = "resetCooldowns(Lnet/minecraft/commands/CommandSourceStack;Ljava/lang/String;Ljava/util/UUID;)I",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;sendFailure(Lnet/minecraft/network/chat/Component;)V",ordinal = 1))
    private static Component unknownPlayer(Component o, @Local(argsOnly = true) UUID playerID) {
        return TranslationKeys.unknownPlayerID(playerID).withStyle(ChatFormatting.RED);
    }

    @ModifyArg(method = "lambda$resetCooldowns$56",at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;sendSuccess(Ljava/util/function/Supplier;Z)V"))
    private static Supplier<Component> reset(Supplier<Component> o,@Local(argsOnly = true) String kitName, @Local(argsOnly = true) UUID playerID) {
        return () -> TranslationKeys.resetCooldownPlayer(kitName,playerID).withStyle(ChatFormatting.YELLOW);
    }



}
