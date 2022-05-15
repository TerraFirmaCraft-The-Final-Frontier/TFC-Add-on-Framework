package tff.addon.tfc.objects.blocks;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import net.dries007.tfc.api.capability.size.IItemSize;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.types.Tree;
import tff.addon.tfc.objects.te.TFCAddonTileEntity;

public class TFCAddonWoodBlock extends Block implements ITileEntityProvider, IItemSize
{
    private static final Map<Tree, TFCAddonWoodBlock> MAP = new HashMap<>();

    public Tree wood;

    public TFCAddonWoodBlock(Tree wood)
    {
        super(Material.WOOD, MapColor.AIR);
        if (MAP.put(wood, this) != null) throw new IllegalStateException("There can only be one.");
        this.wood = wood;
        setSoundType(SoundType.WOOD);
        setHarvestLevel("axe", 0);
    }

    @Nonnull
    @Override
    public Size getSize(@Nonnull ItemStack stack)
    {
        return Size.NORMAL;
    }

    @Nonnull
    @Override
    public Weight getWeight(@Nonnull ItemStack stack)
    {
        return Weight.MEDIUM;
    }

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TFCAddonTileEntity();
    }
}