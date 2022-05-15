package tff.addon.tfc.registry;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.stream.JsonWriter;
import org.apache.commons.lang3.text.WordUtils;
import net.minecraft.launchwrapper.Launch;

import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.api.types.Tree;

public class DataGenerator
{
    public static File rootFolder = Launch.minecraftHome == null ? new File(".") : Launch.minecraftHome;

    public static void genForgeBlockStates(String modid) throws IOException
    {
        File foldersWood = new File(rootFolder + "/resources/" + modid + "/blockstates/wood/thing/");
        foldersWood.mkdirs();

        File foldersMetal = new File(rootFolder + "/resources/" + modid + "/blockstates/metal/thing/");
        foldersMetal.mkdirs();

        File foldersRock = new File(rootFolder + "/resources/" + modid + "/blockstates/rock/thing/");
        foldersRock.mkdirs();

        for (Tree wood : TFCRegistries.TREES.getValuesCollection())
        {
            File json = new File(rootFolder + "/resources/" + modid + "/blockstates/wood/thing/" + wood + ".json");

            JsonWriter jsonWriter = new JsonWriter(new FileWriter(json));
            jsonWriter.setIndent("    ");
            jsonWriter.setLenient(false);
            jsonWriter.beginObject();
            jsonWriter.name("forge_marker").value(1);
            jsonWriter.name("defaults").beginObject();
            jsonWriter.name("model").value("tfc_addon:block_model");
            jsonWriter.name("textures").beginObject();
            jsonWriter.name("all").value("tfc:blocks/wood/planks/" + wood);
            jsonWriter.endObject();
            jsonWriter.endObject();
            jsonWriter.name("variants").beginObject();
            jsonWriter.name("normal").beginArray().beginObject().endObject().endArray();
            jsonWriter.endObject();
            jsonWriter.endObject();
            jsonWriter.close();
        }

        for (Metal metal : TFCRegistries.METALS.getValuesCollection())
        {
            File json = new File(rootFolder + "/resources/" + modid + "/blockstates/metal/thing/" + metal + ".json");

            JsonWriter jsonWriter = new JsonWriter(new FileWriter(json));
            jsonWriter.setIndent("    ");
            jsonWriter.setLenient(false);
            jsonWriter.beginObject();
            jsonWriter.name("forge_marker").value(1);
            jsonWriter.name("defaults").beginObject();
            jsonWriter.name("model").value("tfc_addon:block_model");
            jsonWriter.name("textures").beginObject();
            jsonWriter.name("all").value("tfc:blocks/metal/" + metal);
            jsonWriter.endObject();
            jsonWriter.endObject();
            jsonWriter.name("variants").beginObject();
            jsonWriter.name("normal").beginArray().beginObject().endObject().endArray();
            jsonWriter.endObject();
            jsonWriter.endObject();
            jsonWriter.close();
        }

        for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
        {
            File json = new File(rootFolder + "/resources/" + modid + "/blockstates/rock/thing/" + rock + ".json");

            JsonWriter jsonWriter = new JsonWriter(new FileWriter(json));
            jsonWriter.setIndent("    ");
            jsonWriter.setLenient(false);
            jsonWriter.beginObject();
            jsonWriter.name("forge_marker").value(1);
            jsonWriter.name("defaults").beginObject();
            jsonWriter.name("model").value("tfc_addon:block_model");
            jsonWriter.name("textures").beginObject();
            jsonWriter.name("all").value("tfc:blocks/stonetypes/raw/" + rock);
            jsonWriter.endObject();
            jsonWriter.endObject();
            jsonWriter.name("variants").beginObject();
            jsonWriter.name("normal").beginArray().beginObject().endObject().endArray();
            jsonWriter.endObject();
            jsonWriter.endObject();
            jsonWriter.close();
        }
    }

    @SuppressWarnings("ConstantConditions")
    public static void genLangFile(String modid) throws IOException
    {
        File folders = new File(rootFolder + "/resources/" + modid + "/lang/");
        folders.mkdirs();

        File lang = new File(rootFolder + "/resources/" + modid + "/lang/" + "en_us.lang");

        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(lang));

        fileWriter.write("# WOODEN THINGS");
        for (Tree wood : TFCRegistries.TREES.getValuesCollection())
        {
            String woodKey = WordUtils.capitalize(wood.getRegistryName().getPath().replace("_", " "));
            fileWriter.newLine();
            fileWriter.write("tile.tfc_addon.wood.thing." + wood + ".name=" + woodKey + " Thing");
        }

        fileWriter.newLine();

        fileWriter.write("# METAL THINGS");
        for (Metal metal : TFCRegistries.METALS.getValuesCollection())
        {
            String metalKey = WordUtils.capitalize(metal.getRegistryName().getPath().replace("_", " "));
            fileWriter.newLine();
            fileWriter.write("tile.tfc_addon.metal.thing." + metal + ".name=" + metalKey + " Thing");
        }

        fileWriter.newLine();

        fileWriter.write("# STONE THINGS");
        for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
        {
            String metalKey = WordUtils.capitalize(rock.getRegistryName().getPath().replace("_", " "));
            fileWriter.newLine();
            fileWriter.write("tile.tfc_addon.rock.thing." + rock + ".name=" + metalKey + " Thing");
        }

        fileWriter.close();
    }
}