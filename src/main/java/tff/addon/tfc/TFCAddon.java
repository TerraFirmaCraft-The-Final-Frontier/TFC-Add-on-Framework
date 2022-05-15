package tff.addon.tfc;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.client.resource.VanillaResourceType;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

import tff.addon.tfc.registry.DataGenerator;

@Mod(modid = TFCAddon.MOD_ID, name = TFCAddon.NAME, version = TFCAddon.VERSION, dependencies = TFCAddon.DEPENDENCIES)
public class TFCAddon
{
    public static final String MOD_ID = "tfc_addon";
    public static final String NAME = "TFCAddon";
    public static final String VERSION = "1.0.0";
    public static final String DEPENDENCIES = "required-after:tfc;required-after:resourceloader";
    public static final Logger LOGGER = LogManager.getLogger("TFCAddon");

    @EventHandler
    public void init(FMLInitializationEvent event) throws IOException
    {
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
        {
            DataGenerator.genForgeBlockStates(MOD_ID, "Thing");
            FMLClientHandler.instance().refreshResources(VanillaResourceType.MODELS);

            DataGenerator.genLangFile(MOD_ID, "Thing");
            FMLClientHandler.instance().refreshResources(VanillaResourceType.LANGUAGES);
        }
    }
}