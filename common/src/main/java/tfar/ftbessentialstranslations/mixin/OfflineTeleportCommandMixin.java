package tfar.ftbessentialstranslations.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.ftb.mods.ftbessentials.commands.impl.teleporting.OfflineTeleportCommand;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.coordinates.Coordinates;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import tfar.ftbessentialstranslations.TranslationKeys;

import java.util.UUID;
import java.util.function.Supplier;

@Mixin(OfflineTeleportCommand.class)
public abstract class OfflineTeleportCommandMixin {
    @Shadow protected abstract int tpOffline(CommandSourceStack source, UUID playerId, ServerLevel level, Coordinates dest);

    /**
     * @author
     * @reason
     */
    @Overwrite
    private int tpOffline(CommandSourceStack source, String playerName, ServerLevel level, Coordinates dest) {
        source.getServer().getProfileCache().getAsync(playerName).whenComplete((profileOpt, throwable) -> {
            source.getServer().executeIfPossible(
                    () -> profileOpt.ifPresentOrElse(profile -> this.tpOffline(source, profile.getId(), level, dest),
                    () -> source.sendFailure(TranslationKeys.unknownPlayer(playerName))));//change
        });
        return 1;
    }

    @ModifyArg(method = "tpOffline(Lnet/minecraft/commands/CommandSourceStack;Ljava/util/UUID;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/commands/arguments/coordinates/Coordinates;)I"
            ,at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;sendFailure(Lnet/minecraft/network/chat/Component;)V",ordinal = 0)
    )
    private Component playerOnline(Component o) {
        return TranslationKeys.COMMAND_TP_OFFLINE_PLAYER_ONLINE;
    }

    @ModifyArg(method = "tpOffline(Lnet/minecraft/commands/CommandSourceStack;Ljava/util/UUID;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/commands/arguments/coordinates/Coordinates;)I"
            ,at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;sendSuccess(Ljava/util/function/Supplier;Z)V")
    )
    private Supplier<Component> tp(Supplier<Component> s, @Local(argsOnly = true) UUID uuid, @Local Vec3 pos,@Local(argsOnly = true) CommandSourceStack source) {
        return () -> TranslationKeys.offlineMove(uuid,pos,source.getLevel().dimension().location());
    }

    @ModifyArg(method = "tpOffline(Lnet/minecraft/commands/CommandSourceStack;Ljava/util/UUID;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/commands/arguments/coordinates/Coordinates;)I"
            ,at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;sendFailure(Lnet/minecraft/network/chat/Component;)V",ordinal = 1)
    )
    private Component error(Component o,@Local Exception e) {
        return TranslationKeys.cantUpdate(e.getMessage());
    }

}
