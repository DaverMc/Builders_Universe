package de.daver.antientity;

import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class ConfigurationFile {

    @Getter
    protected YamlConfiguration yaml;
    private final File file;

    protected ConfigurationFile(File dir, String name){
        this.file = new File(dir, name + ".yml");
        if(file.exists()){
            reload();
            return;
        }
        if(!createFile(this.file)) return;
        reload();
        onCreation();
    }

    public void reload(){
        this.yaml = YamlConfiguration.loadConfiguration(this.file);
    }

    public void save(){
        try {
            this.yaml.save(this.file);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private boolean createFile(File file){
        if(file.exists()) return true;
        if(file.isDirectory()){
            return (file.mkdirs());
        }
        try {
            if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) return false;
            return (file.createNewFile());
        } catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    protected abstract void onCreation();
}
