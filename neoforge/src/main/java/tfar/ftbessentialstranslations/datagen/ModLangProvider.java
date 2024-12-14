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
