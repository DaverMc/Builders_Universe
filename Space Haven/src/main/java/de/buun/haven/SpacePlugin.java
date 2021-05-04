package de.buun.haven;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class SpacePlugin extends JavaPlugin {

    @Getter
    private Framework framework;



    protected abstract void onInitialisation();

    protected abstract void onTermination();

}
