package de.buun.haven.web;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Dashboard {

    public static void main(String[] args) {
        File file = new File("D:\\Programmieren\\02_IntelliJ\\Builders Universe\\Space Haven\\src\\main\\resources\\web\\main.html");
        try {
            Desktop.getDesktop().browse(file.toURI());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
