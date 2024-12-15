package tfar.ftbessentialstranslations.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.ftb.mods.ftbessentials.api.records.TPARequest;
import dev.ftb.mods.ftbessentials.commands.impl.teleporting.TPACommand;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
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

    @ModifyArg(method = "tpa",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V",ordinal = 1))
    private Component title(Component o, @Local(argsOnly = true) boolean here,
                            @Local(argsOnly = true,ordinal = 0)ServerPlayer player,@Local(argsOnly = true,ordinal = 1)ServerPlayer target) {
        ServerPlayer p = here ? target : player;
        ServerPlayer t = here ? player : target;

        return TranslationKeys.tpaTitle(p.getDisplayName(),t.getDisplayName());
    }


    @ModifyArg(method = "tpa",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;displayClientMessage(Lnet/minecraft/network/chat/Component;Z)V",ordinal = 2))
    private Component action(Component o, @Local(argsOnly = true) boolean here,
                             @Local(argsOnly = true,ordinal = 0)ServerPlayer player, @Local(argsOnly = true,ordinal = 1)ServerPlayer target, @Local TPARequest request) {

        MutableComponent component2 = TranslationKeys.CLICK_ONE.copy();
        component2.append(TranslationKeys.ACCEPT.copy().setStyle(Style.EMPTY.applyFormat(ChatFormatting.GREEN).withBold(true)
                .withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaccept " + request.id()))
                .withHoverEvent(new HoverEvent(net.minecraft.network.chat.HoverEvent.Action.SHOW_TEXT, TranslationKeys.CLICK_ACCEPT))));
        component2.append(" | ");
        component2.append(TranslationKeys.DENY.copy().setStyle(Style.EMPTY.applyFormat(ChatFormatting.RED).withBold(true)
                .withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpdeny " + request.id()))
                .withHoverEvent(new HoverEvent(net.minecraft.network.chat.HoverEvent.Action.SHOW_TEXT,TranslationKeys.CLICK_DENY))));
        component2.append(" |");


        return component2;
    }


}
