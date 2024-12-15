package tfar.ftbessentialstranslations.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.ftb.mods.ftbessentials.commands.impl.KitCommand;
import dev.ftb.mods.ftbessentials.kit.Kit;
import dev.ftb.mods.ftbessentials.kit.KitManager;
import dev.ftb.mods.ftblibrary.util.TimeUtils;
import joptsimple.internal.Strings;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import tfar.ftbessentialstranslations.CommandHooks;
import tfar.ftbessentialstranslations.TranslationKeys;

import java.util.Collection;
import java.util.Iterator;
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

}
