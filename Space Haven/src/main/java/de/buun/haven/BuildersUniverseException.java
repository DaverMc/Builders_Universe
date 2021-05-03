package de.buun.haven;

public class BuildersUniverseException extends Exception{

    public BuildersUniverseException(String message){
        super(message);
    }

    public void log(){
        Loggers.logError(this);
    }

}
