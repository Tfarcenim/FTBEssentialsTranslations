package tfar.ftbessentialstranslations;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class TranslationKeys {

    public static final MutableComponent COMMAND_NICKNAME_TOO_LONG = Component.translatable("ftbessentials.command.nickname.too_long");
    public static final MutableComponent COMMAND_NICKNAME_RESET = Component.translatable("ftbessentials.command.nickname.reset");

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

}
