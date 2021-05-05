package de.buun.haven.scroreboard;

import de.buun.haven.version.VersionManager;
import de.buun.haven.version.v1_16.v1_16.SidebarScoreboard116;
import de.buun.haven.version.v1_8.scoreboard.SidebarScoreboard18;
import org.bukkit.entity.Player;

public class SidebarScoreboard implements AbstractScoreboard{

    private final AbstractScoreboard core;

    public SidebarScoreboard(){
        this.core = VersionManager.newInstance(AbstractScoreboard.class, SidebarScoreboard18.class, SidebarScoreboard116.class);
    }

    @Override
    public void send(Player player) {

    }

    @Override
    public void setValue(int index, String value) {

    }

    @Override
    public String getValue(int index) {
        return null;
    }
}
