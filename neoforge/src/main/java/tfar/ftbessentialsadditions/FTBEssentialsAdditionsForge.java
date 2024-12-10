package tfar.ftbessentialsadditions;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(FTBEssentialsAdditions.MOD_ID)
public class FTBEssentialsAdditionsForge {

    public FTBEssentialsAdditionsForge(IEventBus eventBus) {

        // This method is invoked by the NeoForge mod loader when it is ready
        // to load your mod. You can access NeoForge and Common code in this
        // project.

        // Use NeoForge to bootstrap the Common mod.
        FTBEssentialsAdditions.LOG.info("Hello NeoForge world!");
        FTBEssentialsAdditions.init();

    }
}