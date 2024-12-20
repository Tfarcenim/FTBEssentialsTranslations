package tfar.ftbessentialstranslations;

import net.minecraft.core.BlockPos;
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

    public static final MutableComponent CLICK_ONE = Component.translatable("ftbessentials.commannd.tpa.click_one");
    public static final MutableComponent ACCEPT = Component.translatable("ftbessentials.accept");
    public static final MutableComponent DENY = Component.translatable("ftbessentials.deny");

    public static final MutableComponent CLICK_ACCEPT = Component.translatable("ftbessentials.click_accept");
    public static final MutableComponent CLICK_DENY = Component.translatable("ftbessentials.click_deny");

    public static final MutableComponent COMMAND_WARP_SET =  Component.translatable("ftbessentials.command.warp.set");
    public static final MutableComponent COMMAND_WARP_DELETED =  Component.translatable("ftbessentials.command.warp.deleted");
    public static final MutableComponent COMMAND_WARP_NOT_FOUND =  Component.translatable("ftbessentials.command.warp.not_found");

    public static final MutableComponent KIT_NAME = Component.translatable("ftbessentials.command.kit.name");
    public static final MutableComponent KIT_COOLDOWN = Component.translatable("ftbessentials.command.kit.cooldown");
    public static final MutableComponent NO_KIT_COOLDOWN = Component.translatable("ftbessentials.command.kit.no_cooldown");
    public static final MutableComponent KIT_ONE_TIME_USE = Component.translatable("ftbessentials.command.kit.one_time_use");
    public static final MutableComponent KIT_AUTO_GRANT = Component.translatable("ftbessentials.command.kit.auto_grant");

    public static final MutableComponent COMMAND_CHEAT_NO_ENDER_CHEST = Component.translatable("ftbessentials.command.cheat.no_ender_chest");
    public static final MutableComponent COMMAND_FLY_ENABLED = Component.translatable("ftbessentials.command.cheat.fly.enabled");
    public static final MutableComponent COMMAND_FLY_DISABLED = Component.translatable("ftbessentials.command.cheat.fly.disabled");

    public static final MutableComponent COMMAND_GOD_ENABLED = Component.translatable("ftbessentials.command.cheat.god.enabled");
    public static final MutableComponent COMMAND_GOD_DISABLED = Component.translatable("ftbessentials.command.cheat.god.disabled");
    public static final MutableComponent COMMAND_TELEPORT_HISTORY = Component.translatable("ftbessentials.command.teleport.history");
    public static final MutableComponent COMMAND_RTP_DENIED = Component.translatable("ftbessentials.command.rtp.denied");
    public static final MutableComponent COMMAND_RTP_LOOKING = Component.translatable("ftbessentials.command.rtp.looking");
    public static final MutableComponent COMMAND_RTP_COULDNT_FIND = Component.translatable("ftbessentials.command.rtp.couldnt_find");

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

    public static MutableComponent tpaTitle(Component name,Component target) {
        return Component.translatable("ftbessentials.command.tpa.title",name,target);
    }

    public static MutableComponent addKitToBlock(String name) {
        return Component.translatable("ftbessentials.command.kit.add_to_block",name);
    }

    public static MutableComponent cantStore(String message) {
        return Component.translatable("ftbessentials.command.kit.cant_store",message);
    }

    public static MutableComponent cantCreateKit(String message) {
        return Component.translatable("ftbessentials.command.kit.cant_create",message);
    }

    public static MutableComponent createKit(String message) {
        return Component.translatable("ftbessentials.command.kit.create",message);
    }

    public static MutableComponent cantGiveKit(String message) {
        return Component.translatable("ftbessentials.command.kit.cant_give",message);
    }

    public static MutableComponent giveKitMultiple(String name,int players) {
        return Component.translatable("ftbessentials.command.kit.give_kit_multiple",name,players);
    }


    public static MutableComponent listKits(int kits) {
        return Component.translatable("ftbessentials.command.kit.list_kits",kits);
    }

    public static MutableComponent noKit(String name) {
        return Component.translatable("ftbessentials.command.kit.no_kit",name);
    }

    public static MutableComponent unknownKit(String name) {
        return Component.translatable("ftbessentials.command.kit.unknown_kit",name);
    }

    public static MutableComponent kitDeleted(String name) {
        return Component.translatable("ftbessentials.command.kit.deleted",name);
    }

    public static MutableComponent cantDelete(String name,String message) {
        return Component.translatable("ftbessentials.command.kit.cant_delete",name,message);
    }

    public static MutableComponent autoGrantModify(String name,boolean auto) {
        return Component.translatable("ftbessentials.command.kit.auto_grant_modify",name,auto);
    }

    public static MutableComponent cooldownModify(String name,Component time) {
        return Component.translatable("ftbessentials.command.kit.cooldown_modify",name,time);
    }

    public static MutableComponent resetCooldownAll(String name) {
        return Component.translatable("ftbessentials.command.kit.reset_cooldown_all",name);
    }

    public static MutableComponent unknownPlayerID(UUID uuid) {
        return Component.translatable("ftbessentials.command.kit.unknown_player_id",uuid.toString());
    }

    public static MutableComponent resetCooldownPlayer(String kitName,UUID playerID) {
        return Component.translatable("ftbessentials.command.kit.reset_cooldown_player",kitName,playerID.toString());
    }

    //String.format("Found good location after %d " + (attempt == 1 ? "attempt" : "attempts") + " @ [x %d, z %d]", attempt, goodPos.getX(), goodPos.getZ()))
    public static MutableComponent foundGoodLocation(int attempts, BlockPos goodPos) {
        return attempts > 1 ? foundGoodLocationMany(attempts,goodPos) : foundGoodLocation1(goodPos);
    }

    public static MutableComponent foundGoodLocation1(BlockPos goodPos) {
        return Component.translatable("ftbessentials.command.rtp.found_location_one_attempt",goodPos.getX(),goodPos.getZ());
    }

    public static MutableComponent foundGoodLocationMany(int attempts, BlockPos goodPos) {
        return Component.translatable("ftbessentials.command.rtp.found_location_many_attempts",attempts,goodPos.getX(),goodPos.getZ());
    }

    public static MutableComponent cantJump(String message) {
        return Component.translatable("ftbessentials.command.rtp.cant_jump",message);
    }

}
