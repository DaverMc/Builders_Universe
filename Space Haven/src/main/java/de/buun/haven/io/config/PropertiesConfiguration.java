package de.buun.haven.io.config;

import de.buun.haven.io.FileUtils;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PropertiesConfiguration implements Configuration{

    private final String name;
    private final File file;
    @Getter
    private Map<String, String> values;

    public PropertiesConfiguration(File dir, String name) {
        this.name = name + ".properties";
        this.file = FileUtils.createFile(dir, this.name);
        this.values = readValues();
    }

    private Map<String, String> readValues(){
        Map<String, String> map = new HashMap<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(this.file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(": ");
                if (parts.length == 0) continue;
                map.put(parts[0], processValue(parts));
            }
        }catch (IOException e){
            e.printStackTrace();
            return map;
        }
        return map;
    }

    private String processValue(String[] parts){
        if (parts.length == 1) {
            return parts[0];
        } else if (parts.length == 2) {
            return parts[1];
        } else {
            String value = "";
            for (int i = 1; i < parts.length; i++) {
                value = value + ": " + parts[i];
            }
            return value.substring(2);
        }
    }

    public void reload(){
        this.values = readValues();
    }

    @Override
    public String name() {
        return this.name;
    }
}
