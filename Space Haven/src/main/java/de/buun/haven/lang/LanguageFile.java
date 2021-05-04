package de.buun.haven.lang;

import de.buun.haven.io.config.PropertiesConfiguration;
import lombok.Getter;

import java.io.File;

public class LanguageFile extends PropertiesConfiguration {

    @Getter
    private final String language;

    public LanguageFile(File dir, String language) {
        super(dir, "lang-" + language);
        this.language = language;
    }

    public String processMessage(String path, Object...values){
        String message = this.getValues().get(path);
        if(message == null) return path;

        for(int i = 0; i < values.length; i++){
            message = message.replace("<arg" + i + ">", values[i].toString());
        }
        return message;
    }
}
