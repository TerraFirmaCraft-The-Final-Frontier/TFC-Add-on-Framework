package tff.addon.tfc.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import tff.addon.tfc.TFCAddon;

@Config(modid = TFCAddon.MOD_ID, name = "TFCAddon")
@Mod.EventBusSubscriber(modid = TFCAddon.MOD_ID)
public class TFCAddonConfig
{
    @Config.Comment("General Settings")
    public static final GeneralCFG GENERAL = new GeneralCFG();

    @SubscribeEvent
    public static void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(TFCAddon.MOD_ID))
        {
            ConfigManager.sync(TFCAddon.MOD_ID, Config.Type.INSTANCE);
        }
    }

    public static class GeneralCFG
    {
        @Config.Name("Config Boolean")
        @Config.Comment("An example config boolean")
        public boolean configBoolean = true;

        @Config.RequiresWorldRestart
        @Config.Name("Config Integer")
        @Config.Comment("An example config integer")
        public int configInteger = 42;

        @Config.RequiresMcRestart
        @Config.Name("Config String")
        @Config.Comment("An example config string")
        public String configString = "TFC";
    }
}