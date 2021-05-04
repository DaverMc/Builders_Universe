package de.buun.haven.scroreboard;

import de.buun.haven.version.VersionManager;
import de.buun.haven.version.v1_16.v1_16.TablistHeaderFooter116;
import de.buun.haven.version.v1_8.scoreboard.TablistHeaderFooter18;
import org.bukkit.entity.Player;

public class TablistHeaderFooter implements AbstractTablistHeaderFooter{

    private final AbstractTablistHeaderFooter core;

    public TablistHeaderFooter(){
        this.core = VersionManager.newInstance(AbstractTablistHeaderFooter.class,
                TablistHeaderFooter18.class, TablistHeaderFooter116.class);
    }

    @Override
    public void send(Player player) {
        core.send(player);
    }

    @Override
    public void setHeader(String[] header) {
        core.setHeader(header);
    }

    @Override
    public void setFooter(String[] footer) {
        core.setFooter(footer);
    }
}
