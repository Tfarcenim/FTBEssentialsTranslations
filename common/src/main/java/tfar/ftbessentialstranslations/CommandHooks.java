package tfar.ftbessentialstranslations;

import com.mojang.authlib.GameProfile;
import dev.ftb.mods.ftbessentials.util.FTBEPlayerData;
import dev.ftb.mods.ftbessentials.util.TeleportPos;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.*;

public class CommandHooks {
    public static int listHomes(CommandSourceStack source, GameProfile of) {
        return FTBEPlayerData.getOrCreate(source.getServer(), of.getId()).map((data) -> {
            if (data.homeManager().getNames().isEmpty()) {
                source.sendSuccess(() -> TranslationKeys.NONE, false);//modified
            } else {
                source.sendSuccess(() -> TranslationKeys.homesFor(of.getName()).withStyle(ChatFormatting.GOLD), false);//modified
                TeleportPos origin = new TeleportPos(source.getLevel().dimension(), BlockPos.containing(source.getPosition()));
                data.homeManager().destinations().forEach((entry) -> {
                    source.sendSuccess(() -> {
                        MutableComponent literal = Component.empty().append(Component.literal(entry.name()).withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.BOLD)).append(Component.literal(": " + entry.destination().distanceString(origin) + " away"));
                        if (source.hasPermission(2)) {
                            literal.withStyle(Style.EMPTY.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp @s " + entry.destination().posAsString()))
                                    .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TranslationKeys.CLICK_TO_TELEPORT)));//modified
                        }

                        return literal;
                    }, false);
                });
            }
            return 1;
        }).orElse(0);
    }
}
