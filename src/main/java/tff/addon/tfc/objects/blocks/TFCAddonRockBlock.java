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
import net.dries007.tfc.api.types.Rock;
import tff.addon.tfc.objects.te.TFCAddonTileEntity;

public class TFCAddonRockBlock extends Block implements ITileEntityProvider, IItemSize
{
    private static final Map<Rock, TFCAddonRockBlock> MAP = new HashMap<>();

    public Rock rock;

    public TFCAddonRockBlock(Rock rock)
    {
        super(Material.ROCK, MapColor.AIR);
        if (MAP.put(rock, this) != null) throw new IllegalStateException("There can only be one.");
        this.rock = rock;
        setSoundType(SoundType.STONE);
        setHarvestLevel("pickaxe", 0);
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