package de.buun.haven.command;

import lombok.Getter;

public abstract class AbstractSubCommand {

    @Getter
    private String name;

    public abstract void run();

}
