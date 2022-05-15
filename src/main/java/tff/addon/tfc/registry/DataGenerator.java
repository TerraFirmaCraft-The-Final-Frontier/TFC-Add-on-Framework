package tff.addon.tfc.registry;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.stream.JsonWriter;
import org.apache.commons.lang3.text.WordUtils;
import net.minecraft.launchwrapper.Launch;

import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.api.types.Tree;
import tff.addon.tfc.TFCAddon;

public class DataGenerator
{
    public static File rootFolder = Launch.minecraftHome == null ? new File(".") : Launch.minecraftHome;

    public static void genForgeBlockStates(String object) throws IOException
    {
        File foldersWood = new File(rootFolder + "/resources/" + TFCAddon.MOD_ID + "/blockstates/wood/" + object.replace(" ", "_").toLowerCase() + "/");
        foldersWood.mkdirs();

        File foldersMetal = new File(rootFolder + "/resources/" + TFCAddon.MOD_ID + "/blockstates/metal/" + object.replace(" ", "_").toLowerCase() + "/");
        foldersMetal.mkdirs();

        File foldersRock = new File(rootFolder + "/resources/" + TFCAddon.MOD_ID + "/blockstates/rock/" + object.replace(" ", "_").toLowerCase() + "/");
        foldersRock.mkdirs();

        for (Tree wood : TFCRegistries.TREES.getValuesCollection())
        {
            File json = new File(rootFolder + "/resources/" + TFCAddon.MOD_ID + "/blockstates/wood/" + object.replace(" ", "_").toLowerCase() + "/" + wood + ".json");

            JsonWriter jsonWriter = new JsonWriter(new FileWriter(json));
            jsonWriter.setIndent("    ");
            jsonWriter.setLenient(false);
            jsonWriter.beginObject();
            jsonWriter.name("forge_marker").value(1);
            jsonWriter.name("defaults").beginObject();
            jsonWriter.name("model").value(TFCAddon.MOD_ID + ":block_model");
            jsonWriter.name("textures").beginObject();
            jsonWriter.name("all").value(TerraFirmaCraft.MOD_ID + ":blocks/wood/planks/" + wood);
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
            File json = new File(rootFolder + "/resources/" + TFCAddon.MOD_ID + "/blockstates/metal/" + object.replace(" ", "_").toLowerCase() + "/" + metal + ".json");

            JsonWriter jsonWriter = new JsonWriter(new FileWriter(json));
            jsonWriter.setIndent("    ");
            jsonWriter.setLenient(false);
            jsonWriter.beginObject();
            jsonWriter.name("forge_marker").value(1);
            jsonWriter.name("defaults").beginObject();
            jsonWriter.name("model").value(TFCAddon.MOD_ID + ":block_model");
            jsonWriter.name("textures").beginObject();
            jsonWriter.name("all").value(TerraFirmaCraft.MOD_ID + ":blocks/metal/" + metal);
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
            File json = new File(rootFolder + "/resources/" + TFCAddon.MOD_ID + "/blockstates/rock/" + object.replace(" ", "_").toLowerCase() + "/" + rock + ".json");

            JsonWriter jsonWriter = new JsonWriter(new FileWriter(json));
            jsonWriter.setIndent("    ");
            jsonWriter.setLenient(false);
            jsonWriter.beginObject();
            jsonWriter.name("forge_marker").value(1);
            jsonWriter.name("defaults").beginObject();
            jsonWriter.name("model").value(TFCAddon.MOD_ID + ":block_model");
            jsonWriter.name("textures").beginObject();
            jsonWriter.name("all").value(TerraFirmaCraft.MOD_ID + ":blocks/stonetypes/raw/" + rock);
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
    public static void genLangFile(String object) throws IOException
    {
        File folders = new File(rootFolder + "/resources/" + TFCAddon.MOD_ID + "/lang/");
        folders.mkdirs();

        File lang = new File(rootFolder + "/resources/" + TFCAddon.MOD_ID + "/lang/" + "en_us.lang");

        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(lang));

        fileWriter.write("# " + object + " (Wood)");
        for (Tree wood : TFCRegistries.TREES.getValuesCollection())
        {
            String woodKey = WordUtils.capitalize(wood.getRegistryName().getPath().replace("_", " "));
            fileWriter.newLine();
            fileWriter.write("tile." + TFCAddon.MOD_ID + ".wood." + object.toLowerCase() + "." + wood + ".name=" + woodKey + " " + object);
        }

        fileWriter.newLine();

        fileWriter.write("# " + object + " (Metal)");
        for (Metal metal : TFCRegistries.METALS.getValuesCollection())
        {
            String metalKey = WordUtils.capitalize(metal.getRegistryName().getPath().replace("_", " "));
            fileWriter.newLine();
            fileWriter.write("tile." + TFCAddon.MOD_ID + ".metal." + object.toLowerCase() + "." + metal + ".name=" + metalKey + " " + object);
        }

        fileWriter.newLine();

        fileWriter.write("# " + object + " (Stone)");
        for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
        {
            String metalKey = WordUtils.capitalize(rock.getRegistryName().getPath().replace("_", " "));
            fileWriter.newLine();
            fileWriter.write("tile." + TFCAddon.MOD_ID + ".rock." + object.toLowerCase() + "." + metalKey + " " + object);
        }

        fileWriter.close();
    }
}