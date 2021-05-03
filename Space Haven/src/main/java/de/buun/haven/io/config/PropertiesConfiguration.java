package de.buun.haven.io.config;

import java.io.File;

public class PropertiesConfiguration implements Configuration{

    private String name;

    public PropertiesConfiguration(File dir, String name){

    }

    @Override
    public String name() {
        return this.name;
    }
}
