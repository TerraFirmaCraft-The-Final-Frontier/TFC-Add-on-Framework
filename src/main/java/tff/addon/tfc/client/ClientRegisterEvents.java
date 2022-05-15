package tff.addon.tfc.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import tff.addon.tfc.TFCAddon;
import tff.addon.tfc.registry.BlockRegistry;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = TFCAddon.MOD_ID)
public final class ClientRegisterEvents
{
    @SubscribeEvent
    @SuppressWarnings("ConstantConditions")
    public static void registerModels(ModelRegistryEvent event)
    {
        for (ItemBlock item : BlockRegistry.getAllNormalItemBlocks())
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "normal"));

        for (Block block : BlockRegistry.getAllWoodBlocks())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().build());

        for (Block block : BlockRegistry.getAllMetalBlocks())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().build());

        for (Block block : BlockRegistry.getAllRockBlocks())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().build());
    }
}