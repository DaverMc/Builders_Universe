package de.buun.haven.version;

import de.buun.haven.BuildersUniverseException;
import de.buun.haven.Loggers;
import de.buun.haven.util.Reflections;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class VersionManager {

    @Getter
    private static Version version;

    private VersionManager(){}

    /**
     * This method setsUp the version the API is running on.
     * If it is already set up by another plugin with this API it does nothing
     *
     * if the server version is incompatible with the versions in the Api it logs an Exception
     * and disables the plugin
     * @param plugin needs a plugin where it is based on
     */
    public static void setUp(Plugin plugin){
        if(version != null){
            Loggers.logInfo("VersionManager is already setUp!");
            return;
        }

        String sVersion = Bukkit.getServer().getClass().getName().split("\\.")[3];
        version = Version.getVersion(sVersion);
        if(version == null){
            new BuildersUniverseException("This plugin isn't available for the version your server is running!").log();
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
    }


    /**
     * This method creates an Instance of a class, based on the version that is set up
     * @param superior The class of the new instance
     * @param v8 the class of an instance for the version 1.8
     * @param v16 the class of an instance for the verion 1.16
     * @param values the parameters of the constructor
     * @param <T> the type of the new instance
     * @return an new instance of the superior class
     */
    public static <T> T newInstance(Class<? extends T> superior, Class<?> v8, Class<?> v16, Object...values){
        try {
            Object obj = createObject(v8,v16,values);
            if(superior.isInstance(obj)){
                return superior.cast(obj);
            }else{
                new BuildersUniverseException("The type of the object doesn't match the superior").log();
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method creates an object of an class
     * @param v8 if the version of the manager is the 1.8
     * @param v16 if the version of the manager is the 1.16
     * @param values the values for the constructor
     * @return an object based on the version classes
     * @throws IllegalAccessException is thrown by an reflective action
     * @throws InvocationTargetException is thrown by an reflective action
     * @throws InstantiationException is thrown by an reflective action
     */
    private static Object createObject(Class<?> v8, Class<?> v16, Object...values) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?>[] classes = new Class[values.length];
        //TODO System.arrayCopy instead of for loop
        for(int i = 0; i < classes.length; i++) classes[i] = values[i].getClass();
        Constructor<?> con8 = Reflections.getConstructor(v8, classes);
        Constructor<?> con16 = Reflections.getConstructor(v16, classes);

        if(con8 == null || con16 == null){
            new BuildersUniverseException("A version based Constructor is null!").log();
            return null;
        }

        switch (version){
            case V1_8:
                return con8.newInstance(values);
            case V1_16:
                return con16.newInstance(values);
            default:
                Loggers.logError(new IllegalArgumentException("This version is not supported by the API-version!"));
                return null;
        }
    }

    public static void sendVersionErrorMessage(Object obj, Player player){
        if(obj != null) return;
        player.sendMessage("§cThis feature is not compatible with your server version!");
    }

}
