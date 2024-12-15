package tfar.ftbessentialstranslations;

import com.mojang.authlib.GameProfile;
import dev.ftb.mods.ftbessentials.kit.KitManager;
import dev.ftb.mods.ftbessentials.util.FTBEPlayerData;
import dev.ftb.mods.ftbessentials.util.TeleportPos;
import dev.ftb.mods.ftblibrary.util.TimeUtils;
import joptsimple.internal.Strings;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.*;
import net.minecraft.world.item.ItemStack;

public class CommandHooks {
    public static int listHomes(CommandSourceStack source, GameProfile of) {
        return FTBEPlayerData.getOrCreate(source.getServer(), of.getId()).map((data) -> {
            if (data.homeManager().getNames().isEmpty()) {
                source.sendSuccess(() -> TranslationKeys.NONE, false);//modified
            } else {
                source.sendSuccess(() -> TranslationKeys.homesFor(of.getName()).withStyle(ChatFormatting.GOLD), false);//modified
                TeleportPos origin = new TeleportPos(source.getLevel().dimension(), BlockPos.containing(source.getPosition()));
                data.homeManager().destinations().forEach((entry) -> source.sendSuccess(() -> {
                    MutableComponent literal = Component.empty().append(Component.literal(entry.name()).withStyle(ChatFormatting.AQUA).withStyle(ChatFormatting.BOLD)).append(Component.literal(": " + entry.destination().distanceString(origin) + " away"));
                    if (source.hasPermission(2)) {
                        literal.withStyle(Style.EMPTY.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp @s " + entry.destination().posAsString()))
                                .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TranslationKeys.CLICK_TO_TELEPORT)));//modified
                    }

                    return literal;
                }, false));
            }
            return 1;
        }).orElse(0);
    }

    public static int showKit(CommandSourceStack source, String kitName) {
        KitManager.getInstance().get(kitName).ifPresentOrElse((kit) -> {
            source.sendSuccess(() -> Component.literal(Strings.repeat('-', 40)).withStyle(ChatFormatting.GREEN), false);
            source.sendSuccess(() -> TranslationKeys.KIT_NAME.copy().withStyle(ChatFormatting.AQUA).append(Component.literal(kit.getKitName()).withStyle(ChatFormatting.YELLOW)), false);
            if (kit.getCooldown() > 0L) {
                source.sendSuccess(() -> TranslationKeys.KIT_COOLDOWN.copy().withStyle(ChatFormatting.AQUA).append(Component.literal(TimeUtils.prettyTimeString(kit.getCooldown())).withStyle(ChatFormatting.YELLOW)), false);
            } else if (kit.getCooldown() == 0L) {
                source.sendSuccess(() -> TranslationKeys.NO_KIT_COOLDOWN.copy().withStyle(ChatFormatting.AQUA), false);
            } else {
                source.sendSuccess(() -> TranslationKeys.KIT_ONE_TIME_USE.copy().withStyle(ChatFormatting.AQUA), false);
            }

            if (kit.isAutoGrant()) {
                source.sendSuccess(() -> TranslationKeys.KIT_AUTO_GRANT.copy().withStyle(ChatFormatting.AQUA), false);
            }

            source.sendSuccess(() -> Component.literal("  Items:").withStyle(ChatFormatting.AQUA), false);

            for (ItemStack stack : kit.getItems()) {
                source.sendSuccess(() -> Component.literal("  • ").withStyle(ChatFormatting.YELLOW).append(Component.literal(stack.getCount() + " x ").withStyle(ChatFormatting.WHITE)).append(stack.getDisplayName()), false);
            }

        }, () -> source.sendFailure(TranslationKeys.noKit(kitName).withStyle(ChatFormatting.RED)));
        return 1;
    }

}
