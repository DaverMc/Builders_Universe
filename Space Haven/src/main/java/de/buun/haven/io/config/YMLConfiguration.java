package de.buun.haven.io.config;

import de.buun.haven.io.FileUtils;
import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class YMLConfiguration implements Configuration{

    private final String name;
    private final File file;
    @Getter
    private YamlConfiguration yml;

    public YMLConfiguration(File dir, String name){
        this.name = name + ".yml";
        this.file = FileUtils.createFile(dir, name);
        reload();
    }

    public void save(){
        try {
            this.yml.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload(){
        this.yml = YamlConfiguration.loadConfiguration(this.file);
    }

    @Override
    public String name() {
        return this.name;
    }
}
