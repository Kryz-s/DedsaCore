package me.kryz.dedsacore.config;

import com.google.gson.*;
import com.mojang.datafixers.util.Pair;
import me.kryz.dedsacore.utils.ColorUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class JsonConfigFile {

    private final File file;
    private final Gson gson;
    private final Map<String, JsonElement> cache;

    /**
     * Constructor para manejar un archivo JSON desde los recursos del plugin.
     *
     * @param plugin   Instancia del plugin.
     * @param resource Nombre del archivo JSON en los recursos.
     */
    public JsonConfigFile(final Plugin plugin, final String resource) {
        this.file = new File(plugin.getDataFolder(), resource);
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.cache = new HashMap<>();

        if (!file.exists()) {
            try (final InputStream in = plugin.getResource(resource)) {
                if (in == null) {
                    throw new RuntimeException("No se encontró el recurso: " + resource);
                }

                if (!plugin.getDataFolder().exists() && !plugin.getDataFolder().mkdirs()) {
                    throw new RuntimeException("No se pudo crear el directorio del plugin.");
                }

                Files.copy(in, file.toPath());
            } catch (IOException e) {
                throw new RuntimeException("Error al copiar el archivo JSON desde los recursos.", e);
            }
        }
        loadAndCacheJson();
    }

    /**
     * Carga el contenido del archivo JSON y lo almacena en la memoria.
     */
    private void loadAndCacheJson() {
        try (final FileReader reader = new FileReader(file)) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            cacheJsonIterative(jsonObject);
        } catch (JsonSyntaxException e) {
            throw new JsonSyntaxException("El archivo JSON está mal formado.", e);
        } catch (JsonIOException e) {
            throw new JsonIOException("Error al acceder al archivo JSON.", e);
        } catch (IOException e) {
            throw new JsonParseException("Error al leer el archivo JSON.", e);
        }
    }

    /**
     * Carga las claves y valores en el caché usando un enfoque iterativo.
     *
     * @param rootJsonObject Objeto JSON raíz a procesar.
     */
    private void cacheJsonIterative(final JsonObject rootJsonObject) {
        // Usamos una pila para emular el comportamiento recursivo
        final Deque<Map.Entry<String, JsonElement>> stack = new ArrayDeque<>();
        stack.push(new AbstractMap.SimpleEntry<>("", rootJsonObject));

        while (!stack.isEmpty()) {
            final Map.Entry<String, JsonElement> current = stack.pop();
            final String currentPath = current.getKey();
            final JsonElement jsonElement = current.getValue();

            if (jsonElement.isJsonObject()) {
                final JsonObject jsonObject = jsonElement.getAsJsonObject();
                for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                    final String newPath = currentPath.isEmpty() ? entry.getKey() : currentPath + "/" + entry.getKey();
                    stack.push(new AbstractMap.SimpleEntry<>(newPath, entry.getValue()));
                }
            } else if (jsonElement.isJsonArray() || jsonElement.isJsonPrimitive()) {
                cache.put(currentPath, jsonElement);
            }
        }
    }

    /**
     * Recarga todas las claves disponibles desde el archivo JSON.
     *
     */
    public void reloadKeys() {
        cache.clear(); // Limpiar el caché actual
        loadAndCacheJson(); // Volver a cargar y almacenar las claves
    }

    /**
     * Obtiene un valor en forma de cadena desde un path en el archivo JSON.
     *
     * @param path Ruta del valor en formato "key1/key2/key3".
     * @return Valor como String o null si no existe.
     */
    public String getString(final String path) {
        final JsonElement element = cache.get(path);
        return element != null && element.isJsonPrimitive() ? element.getAsString() : path;
    }

    /**
     * Obtiene un valor en forma de número desde un path en el archivo JSON.
     *
     * @param path Ruta del valor en formato "key1/key2/key3".
     * @return Valor como Number o null si no existe.
     */
    public Number getNumber(final String path) {
        final JsonElement element = cache.get(path);
        return element != null && element.isJsonPrimitive() ? element.getAsNumber() : 0;
    }

    /**
     * Obtiene una lista de cadenas desde un path en el archivo JSON.
     *
     * @param path Ruta del valor en formato "key1/key2/key3".
     * @return Lista de cadenas o null si no existe.
     */
    @NotNull
    public List<String> getStringList(final String path) {
        final JsonElement element = cache.get(path);
        if (element != null && element.isJsonArray()) {
            final List<String> list = new ArrayList<>();
            element.getAsJsonArray().forEach(e -> {
                if (e.isJsonPrimitive() && e.getAsJsonPrimitive().isString()) {
                    list.add(e.getAsString());
                }
            });
            return list;
        }
        return List.of();
    }

    /**
     * Obtiene una lista de cadenas desde un path en el archivo JSON.
     *
     * @param path Ruta del valor en formato "key1/key2/key3".
     * @return Lista de cadenas o null si no existe.
     */
    @NotNull
    public List<String> getItemLoreComponent(final String path) {
        final JsonElement element = cache.get(path);
        if (element != null && element.isJsonArray()) {
            final List<String> list = new ArrayList<>();
            element.getAsJsonArray().forEach(e -> {
                if (e.isJsonPrimitive() && e.getAsJsonPrimitive().isString()) {
                    list.add(ColorUtils.legacyForItemFormat(e.getAsString()));
                }
            });
            return list;
        }
        return List.of();
    }

    /**
     * Verifica si el valor en un path es una lista.
     *
     * @param path Ruta del valor en formato "key1/key2/key3".
     * @return True si el valor es una lista, false en caso contrario.
     */
    public boolean isList(final String path) {
        final JsonElement element = cache.get(path);
        return element != null && element.isJsonArray();
    }
}
