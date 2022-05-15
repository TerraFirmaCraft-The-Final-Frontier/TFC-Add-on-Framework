package tff.addon.tfc.objects.te;

import javax.annotation.Nonnull;

import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import net.dries007.tfc.objects.te.TEInventory;

public class TFCAddonTileEntity extends TEInventory implements ITickable
{
    public static final String NAME = "tfc_addon_tile_entity";

    public TFCAddonTileEntity()
    {
        super(42);
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TextComponentString(I18n.format(NAME));
    }

    @Nonnull
    @Override
    public NBTTagCompound getTileData()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return nbt;
    }

    @Override
    public void update()
    {

    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        return super.writeToNBT(nbt);
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        int metadata = getBlockMetadata();
        return new SPacketUpdateTileEntity(this.pos, metadata, nbt);
    }

    @Override
    public NBTTagCompound getUpdateTag()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return nbt;
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
    {
        this.readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public void handleUpdateTag(NBTTagCompound tag)
    {
        this.readFromNBT(tag);
    }
}