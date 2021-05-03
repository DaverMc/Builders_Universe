package de.buun.haven.io.config;

import de.buun.haven.Loggers;

public interface Configuration {

    String name();

    public default void log(){
        Loggers.logConfig(this);
    }
}
