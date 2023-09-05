package com.alinesno.infra.ops.container.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class YamlUtils {

    private static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    public static <T> T loadFromYaml(File file, Class<T> valueType) throws IOException {
        return mapper.readValue(file, valueType);
    }

    public static void saveToYaml(Object obj, File file) throws IOException {
        mapper.writeValue(file, obj);
    }

    public static void writeToYaml(File tempFile, String yamlContent) {
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(yamlContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
