package de.buun.haven;

import de.buun.haven.command.CommandRegistration;
import de.buun.haven.events.EventRegistration;
import de.buun.haven.gui.GuiRegistration;
import lombok.Getter;

public class Framework {

    @Getter
    private final EventRegistration eventRegistration;
    @Getter
    private final GuiRegistration guiRegistration;
    @Getter
    private final CommandRegistration commandRegistration;

    public Framework(SpacePlugin plugin){
        this.eventRegistration = new EventRegistration();
        this.guiRegistration = new GuiRegistration();
        this.commandRegistration = new CommandRegistration();
    }
}
