package tfar.ftbessentialstranslations.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.data.LanguageProvider;
import tfar.ftbessentialstranslations.FTBEssentialsTranslations;
import tfar.ftbessentialstranslations.TranslationKeys;

import java.util.UUID;

public class ModLangProvider extends LanguageProvider {
    public ModLangProvider(PackOutput output) {
        super(output, FTBEssentialsTranslations.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addTextComponent(TranslationKeys.COMMAND_NICKNAME_TOO_LONG,"Nickname too long!");
        addTextComponent(TranslationKeys.COMMAND_NICKNAME_RESET,"Nickname reset!");
        addTextComponent(TranslationKeys.nickNameChange(""),"Nickname changed to '" + "%s" + "'");

        addTextComponent(TranslationKeys.notifyMuting(Component.empty(),Component.empty(),""),"%s has been muted by %s, %s");
        addTextComponent(TranslationKeys.notifyunMuting(Component.empty(),Component.empty()),"%s has been unmuted by %s");

        addTextComponent(TranslationKeys.speedBoost(Component.empty(),Component.empty(),0),"Speed boost for %s (%s) = %s%");
        addTextComponent(TranslationKeys.noSpeedBoost(Component.empty()),"No speed boost for %s (%s) = %s%");

        addTextComponent(TranslationKeys.leaderboardTitle(""), "== Leaderboard [%s] ==");
        addTextComponent(TranslationKeys.COMMAND_LEADERBOARD_NODATA,"No data!");
        addTextComponent(TranslationKeys.COMMAND_HOME_SET,"Home set!");
        addTextComponent(TranslationKeys.COMMAND_HOME_TOO_MANY,"Can't add any more homes!");
        addTextComponent(TranslationKeys.COMMAND_HOME_DELETED,"Home deleted!");
        addTextComponent(TranslationKeys.COMMAND_HOME_NOT_FOUND,"Home not found!");
        addTextComponent(TranslationKeys.homesFor(""), "Homes for %s\n---");
        addTextComponent(TranslationKeys.CLICK_TO_TELEPORT,"Click to teleport");
        addTextComponent(TranslationKeys.unknownPlayer(""),"Unknown player: %s");

        addTextComponent(TranslationKeys.COMMAND_TP_OFFLINE_PLAYER_ONLINE,"Player is online! Use regular /tp command instead");
        addTextComponent(TranslationKeys.offlineMove(UUID.randomUUID(), Vec3.ZERO, ResourceLocation.parse("s")),
                "Offline player %s moved to [%s,%s,%s] in %s");
        addTextComponent(TranslationKeys.cantUpdate(""),"Can't update dat file: %s");
        addTextComponent(TranslationKeys.REQUEST_ALREADY_SENT,"Request already sent!");
        addTextComponent(TranslationKeys.REQUEST_SENT,"Request sent!");
        addTextComponent(TranslationKeys.REQUEST_INVALID,"Invalid request!");
        addTextComponent(TranslationKeys.PLAYER_OFFLINE,"Player has gone offline!");
        addTextComponent(TranslationKeys.REQUEST_DENIED,"Request denied!");
        addTextComponent(TranslationKeys.tpaTitle(Component.empty(),Component.empty()),("TPA request! [ %s ➡ %s ]"));
        addTextComponent(TranslationKeys.CLICK_ONE,"Click one of these: ");
        addTextComponent(TranslationKeys.ACCEPT,"Accept ✔");
        addTextComponent(TranslationKeys.DENY,"Deny ❌");
        addTextComponent(TranslationKeys.CLICK_ACCEPT,"Click to Accept");
        addTextComponent(TranslationKeys.CLICK_DENY,"Click to Deny");

        addTextComponent(TranslationKeys.COMMAND_WARP_SET,"Warp set!");
        addTextComponent(TranslationKeys.COMMAND_WARP_NOT_FOUND,"Warp deleted!");
        addTextComponent(TranslationKeys.COMMAND_WARP_DELETED,"Warp not found!");
        addTextComponent(TranslationKeys.addKitToBlock(""), "Added item(s) from kit '%s' to focused inventory");
        addTextComponent(TranslationKeys.cantStore(""),"Can't store kit in inventory: %s");
        addTextComponent(TranslationKeys.cantCreateKit(""),"Can't create kit: %s");
        addTextComponent(TranslationKeys.createKit(""), "Kit '%s' created");
        addTextComponent(TranslationKeys.cantGiveKit(""),"Can't give kit to player: %s");
        addTextComponent(TranslationKeys.giveKitMultiple("",0), "Kit '%s' given to %s player(s)");
        addTextComponent(TranslationKeys.listKits(0),"%s kit(s)");

        addTextComponent(TranslationKeys.noKit(""),"No such kit: %s");
        addTextComponent(TranslationKeys.unknownKit(""),"Unknown kit: %s");
        addTextComponent(TranslationKeys.KIT_NAME,"Kit name: ");
        addTextComponent(TranslationKeys.KIT_COOLDOWN,"Cooldown: ");
        addTextComponent(TranslationKeys.NO_KIT_COOLDOWN,"No Cooldown: ");
        addTextComponent(TranslationKeys.KIT_ONE_TIME_USE,"One-Time Use");//had 2 spaces
        addTextComponent(TranslationKeys.KIT_AUTO_GRANT,"  Autogranted on player login");
        addTextComponent(TranslationKeys.kitDeleted(""), "Kit '%s' deleted");
        addTextComponent(TranslationKeys.cantDelete("",""), "Can't delete kit '%s': %s");
        addTextComponent(TranslationKeys.autoGrantModify("",false), "Kit '%s' autogrant modified: %s");
        addTextComponent(TranslationKeys.cooldownModify("",Component.empty()), "Kit '%s' cooldown modified: %s");
        addTextComponent(TranslationKeys.resetCooldownAll(""), "Cooldown for '%s' reset for all players");
        addTextComponent(TranslationKeys.unknownPlayerID(UUID.randomUUID()),"Unknown player ID: %s");
        addTextComponent(TranslationKeys.resetCooldownPlayer("",UUID.randomUUID()), "Cooldown for '%s' reset for UUID %s");
        addTextComponent(TranslationKeys.COMMAND_CHEAT_NO_ENDER_CHEST,"Unable to open enderchest inventory!");
        addTextComponent(TranslationKeys.COMMAND_FLY_ENABLED,"Flight enabled");
        addTextComponent(TranslationKeys.COMMAND_FLY_DISABLED,"Flight disabled");
        addTextComponent(TranslationKeys.COMMAND_GOD_ENABLED,"God mode enabled");
        addTextComponent(TranslationKeys.COMMAND_GOD_DISABLED,"God mode disabled");
    }

    protected void addTextComponent(MutableComponent component, String text) {
        ComponentContents contents = component.getContents();
        if (contents instanceof TranslatableContents translatableContents) {
            add(translatableContents.getKey(),text);
        } else {
            throw new UnsupportedOperationException(component +" is not translatable");
        }
    }
}
