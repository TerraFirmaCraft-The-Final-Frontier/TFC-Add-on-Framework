package tff.addon.tfc.registry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.objects.CreativeTabsTFC;
import net.dries007.tfc.objects.items.itemblock.ItemBlockTFC;
import tff.addon.tfc.TFCAddon;
import tff.addon.tfc.objects.blocks.TFCAddonMetalBlock;
import tff.addon.tfc.objects.blocks.TFCAddonRockBlock;
import tff.addon.tfc.objects.blocks.TFCAddonWoodBlock;
import tff.addon.tfc.objects.te.TFCAddonTileEntity;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = TFCAddon.MOD_ID)
@GameRegistry.ObjectHolder(TFCAddon.MOD_ID)
public final class BlockRegistry
{
    private static ImmutableList<ItemBlock> allNormalItemBlocks;
    private static ImmutableList<TFCAddonWoodBlock> allWoodBlocks;
    private static ImmutableList<TFCAddonMetalBlock> allMetalBlocks;
    private static ImmutableList<TFCAddonRockBlock> allRockBlocks;

    public static ImmutableList<ItemBlock> getAllNormalItemBlocks()
    {
        return allNormalItemBlocks;
    }

    public static ImmutableList<TFCAddonWoodBlock> getAllWoodBlocks()
    {
        return allWoodBlocks;
    }

    public static ImmutableList<TFCAddonMetalBlock> getAllMetalBlocks()
    {
        return allMetalBlocks;
    }

    public static ImmutableList<TFCAddonRockBlock> getAllRockBlocks()
    {
        return allRockBlocks;
    }

    @SubscribeEvent
    @SuppressWarnings("ConstantConditions")
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        IForgeRegistry<Block> blockRegistry = event.getRegistry();

        Builder<ItemBlock> normalItemBlocks = ImmutableList.builder();
        Builder<TFCAddonWoodBlock> addonWoodBlocks = ImmutableList.builder();
        Builder<TFCAddonMetalBlock> addonMetalBlocks = ImmutableList.builder();
        Builder<TFCAddonRockBlock> addonRockBlocks = ImmutableList.builder();

        for (Tree wood : TFCRegistries.TREES.getValuesCollection())
        {
            addonWoodBlocks.add(register(blockRegistry, "wood/thing/" + wood.getRegistryName().getPath(), new TFCAddonWoodBlock(wood), CreativeTabsTFC.CT_WOOD));
        }

        for (Metal metal : TFCRegistries.METALS.getValuesCollection())
        {
            addonMetalBlocks.add(register(blockRegistry, "metal/thing/" + metal.getRegistryName().getPath(), new TFCAddonMetalBlock(metal), CreativeTabsTFC.CT_METAL));
        }

        for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
        {
            addonRockBlocks.add(register(blockRegistry, "rock/thing/" + rock.getRegistryName().getPath(), new TFCAddonRockBlock(rock), CreativeTabsTFC.CT_ROCK_BLOCKS));
        }

        allWoodBlocks = addonWoodBlocks.build();
        allWoodBlocks.forEach(x -> normalItemBlocks.add(new ItemBlockTFC(x)));

        allMetalBlocks = addonMetalBlocks.build();
        allMetalBlocks.forEach(x -> normalItemBlocks.add(new ItemBlockTFC(x)));

        allRockBlocks = addonRockBlocks.build();
        allRockBlocks.forEach(x -> normalItemBlocks.add(new ItemBlockTFC(x)));

        allNormalItemBlocks = normalItemBlocks.build();

        register(TFCAddonTileEntity.class, TFCAddonTileEntity.NAME);
    }

    private static <T extends Block> T register(IForgeRegistry<Block> r, String name, T block, CreativeTabs ct)
    {
        block.setCreativeTab(ct);
        return register(r, name, block);
    }

    private static <T extends Block> T register(IForgeRegistry<Block> r, String name, T block)
    {
        block.setRegistryName(TFCAddon.MOD_ID, name);
        block.setTranslationKey(TFCAddon.MOD_ID + "." + name.replace('/', '.'));
        r.register(block);
        TFCAddon.LOGGER.debug("Block registered: " + name);
        return block;
    }

    private static <T extends TileEntity> void register(Class<T> te, String name)
    {
        TileEntity.register(TFCAddon.MOD_ID + ":" + name, te);
        TFCAddon.LOGGER.debug("Tile Entity registered: " + name);
    }
}