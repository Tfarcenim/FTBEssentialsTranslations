package tfar.ftbessentialstranslations.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.ftb.mods.ftbessentials.commands.impl.misc.LeaderboardCommand;
import dev.ftb.mods.ftbessentials.util.Leaderboard;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import tfar.ftbessentialstranslations.TranslationKeys;

import java.util.function.Supplier;

@Mixin(LeaderboardCommand.class)
public class LeaderboardCommandMixin {

    @ModifyArg(method = "leaderboard",at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;sendSuccess(Ljava/util/function/Supplier;Z)V",ordinal = 0))
    private static Supplier<Component> leaderboardTitle(Supplier<Component> o, @Local(argsOnly = true) Leaderboard<?> leaderboard) {
        return () -> TranslationKeys.leaderboardTitle(leaderboard.formattedName()).withStyle(ChatFormatting.DARK_GREEN);
    }

    @ModifyArg(method = "leaderboard",at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;sendSuccess(Ljava/util/function/Supplier;Z)V",
            ordinal = 1))
    private static Supplier<Component> noData(Supplier<Component> o, @Local(argsOnly = true) Leaderboard<?> leaderboard) {
        return () -> TranslationKeys.COMMAND_LEADERBOARD_NODATA.copy().withStyle(ChatFormatting.GRAY);
    }
}
