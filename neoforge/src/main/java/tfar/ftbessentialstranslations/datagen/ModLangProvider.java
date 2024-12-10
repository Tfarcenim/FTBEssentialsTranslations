package tfar.ftbessentialstranslations.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.neoforged.neoforge.common.data.LanguageProvider;
import tfar.ftbessentialstranslations.FTBEssentialsTranslations;
import tfar.ftbessentialstranslations.TranslationKeys;

public class ModLangProvider extends LanguageProvider {
    public ModLangProvider(PackOutput output) {
        super(output, FTBEssentialsTranslations.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addTextComponent(TranslationKeys.COMMAND_NICKNAME_TOO_LONG,"Nickname too long!");
        addTextComponent(TranslationKeys.COMMAND_NICKNAME_RESET,"Nickname reset!");
        addTextComponent(TranslationKeys.COMMAND_NICKNAME_CHANGE,"Nickname changed to '" + "%s" + "'");
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
