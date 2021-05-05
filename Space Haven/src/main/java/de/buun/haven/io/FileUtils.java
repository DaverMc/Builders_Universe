package de.buun.haven.io;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    private FileUtils(){}

    //Returns null if failed to create
    public static File createFile(File file){
        if(file.exists()) return file;
        if(file.isDirectory()) return (file.mkdirs())? file : null;
        file.getParentFile().mkdirs();
        try {
            return (file.createNewFile())? file : null;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static File createFile(File dir, String name){
        return createFile(new File(dir, name));
    }

    public static File createFile(String path){
        return createFile(new File(path));
    }

}
