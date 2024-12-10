package tfar.ftbessentialstranslations.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import tfar.ftbessentialstranslations.FTBEssentialsTranslations;

public class ModLangProvider extends LanguageProvider {
    public ModLangProvider(PackOutput output) {
        super(output, FTBEssentialsTranslations.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("ftbessentialsadditions.command.nickname.too_long","Nickname too long!");
    }
}
