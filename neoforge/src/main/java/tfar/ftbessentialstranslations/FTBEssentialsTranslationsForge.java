package tfar.ftbessentialstranslations;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import tfar.ftbessentialstranslations.datagen.ModDatagen;

@Mod(FTBEssentialsTranslations.MOD_ID)
public class FTBEssentialsTranslationsForge {

    public FTBEssentialsTranslationsForge(IEventBus eventBus) {
        eventBus.addListener(ModDatagen::gather);
        // This method is invoked by the NeoForge mod loader when it is ready
        // to load your mod. You can access NeoForge and Common code in this
        // project.

        // Use NeoForge to bootstrap the Common mod.
        FTBEssentialsTranslations.LOG.info("Hello NeoForge world!");
        FTBEssentialsTranslations.init();

    }
}