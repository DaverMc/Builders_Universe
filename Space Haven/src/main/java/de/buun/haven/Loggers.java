package de.buun.haven;

import de.buun.haven.io.config.Configuration;
import org.bukkit.Bukkit;

public class Loggers {

    private Loggers(){}

    public static void logInfo(String message){
        Bukkit.getLogger().info(message);
    }

    public static void logWarning(String message){
        Bukkit.getLogger().warning(message);
    }

    public static void logError(Exception e){
        Bukkit.getLogger().severe(e.getMessage());
    }

    public static void logConfig(Configuration config) {
        var logMessage = String.format("The Configuration %s was successfully loaded!", config.name());
        Bukkit.getLogger().config(logMessage);
    }

}
