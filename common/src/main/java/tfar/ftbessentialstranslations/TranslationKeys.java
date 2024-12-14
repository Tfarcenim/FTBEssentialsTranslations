package tfar.ftbessentialstranslations;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public class TranslationKeys {

    public static final MutableComponent COMMAND_NICKNAME_TOO_LONG = Component.translatable("ftbessentials.command.nickname.too_long");
    public static final MutableComponent COMMAND_NICKNAME_RESET = Component.translatable("ftbessentials.command.nickname.reset");
    public static final MutableComponent COMMAND_LEADERBOARD_NODATA = Component.translatable("ftbessentials.command.leaderboard.nodata");

    public static final MutableComponent COMMAND_HOME_SET = Component.translatable("ftbessentials.command.home.home_set");
    public static final MutableComponent COMMAND_HOME_TOO_MANY = Component.translatable("ftbessentials.command.home.too_many_homes");
    public static final MutableComponent COMMAND_HOME_DELETED = Component.translatable("ftbessentials.command.home.deleted");
    public static final MutableComponent COMMAND_HOME_NOT_FOUND = Component.translatable("ftbessentials.command.home.not_found");

    public static final MutableComponent NONE = Component.translatable("command.none");
    public static final MutableComponent CLICK_TO_TELEPORT = Component.translatable("command.click_to_teleport");
    public static final MutableComponent COMMAND_TP_OFFLINE_PLAYER_ONLINE = Component.translatable("ftbessentials.command.tp_offline.player_online");

    public static final MutableComponent REQUEST_ALREADY_SENT = Component.translatable("ftbessentials.command.tpa.request_already_sent");
    public static final MutableComponent REQUEST_SENT = Component.translatable("ftbessentials.command.tpa.request_sent");
    public static final MutableComponent REQUEST_INVALID = Component.translatable("ftbessentials.command.tpa.request_invalid");
    public static final MutableComponent PLAYER_OFFLINE = Component.translatable("ftbessentials.command.tpa.player_offline");
    public static final MutableComponent REQUEST_DENIED = Component.translatable("ftbessentials.command.tpa.request_denied");

    public static MutableComponent nickNameChange(String nick) {
        return Component.translatable("ftbessentials.command.nickname.change",nick);
    }

    //MutableComponent msg = player.getDisplayName().copy().append(" has been muted by ").append(source.getDisplayName()).append(", ").append(info.desc());
    public static MutableComponent notifyMuting(Component displayName, Component name, String desc) {
        return Component.translatable("ftbessentials.command.mute",displayName,name,desc);
    }

    public static MutableComponent notifyunMuting(Component displayName, Component name) {
        return Component.translatable("ftbessentials.command.unmute",displayName,name);
    }

    //            msg = Component.literal("Speed boost for ").append(target.getDisplayName()).append(" (")
    //            .append(Component.translatable(((Attribute)attrInstance.getAttribute().value()).getDescriptionId())).append(") = " + boostPct + "%");
    public static MutableComponent speedBoost(Component displayName,Component attrId,int boostPct) {
        return Component.translatable("ftbessentials.command.speed",displayName,attrId,boostPct);
    }

    public static MutableComponent noSpeedBoost(Component displayName) {
        return Component.translatable("ftbessentials.command.nospeed",displayName);
    }

    public static MutableComponent leaderboardTitle(String formattedName) {
        return Component.translatable("ftbessentials.command.leaderboard.title",formattedName);
    }

    public static MutableComponent homesFor(String name) {
        return Component.translatable("ftbessentials.command.home.list",name);
    }

    public static MutableComponent unknownPlayer(String name) {
        return Component.translatable("command.unknown_player",name);
    }

    //Component.literal(String.format("Offline player %s moved to [%.2f,%.2f,%.2f] in %s", playerId, vec.x, vec.y, vec.z, source.getLevel().dimension().location()));
    public static MutableComponent offlineMove(UUID uuid, Vec3 pos, ResourceLocation dimension) {
        String x = String.format("%.2f",pos.x);
        String y = String.format("%.2f",pos.y);
        String z = String.format("%.2f",pos.z);
        return Component.translatable("ftbessentials.command.offline_tp.move",uuid.toString(),x,y,z,dimension.toString());
    }

    public static MutableComponent cantUpdate(String message) {
        return Component.translatable("ftbessentials.command.offline_tp.cant_update",message);
    }

}
