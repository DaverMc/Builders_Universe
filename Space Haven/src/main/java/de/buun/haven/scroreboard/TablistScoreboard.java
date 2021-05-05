package de.buun.haven.scroreboard;

import de.buun.haven.version.VersionManager;
import de.buun.haven.version.v1_16.v1_16.TablistScoreboard116;
import de.buun.haven.version.v1_8.scoreboard.TablistScoreboard18;
import org.bukkit.entity.Player;

public class TablistScoreboard implements AbstractTablistScoreboard{

    private final AbstractTablistScoreboard core;

    public TablistScoreboard(){
        this.core = VersionManager.newInstance(AbstractTablistScoreboard.class,
                TablistScoreboard18.class, TablistScoreboard116.class);
    }

    @Override
    public void unregisterTeam(Player player) {
        this.core.unregisterTeam(player);
    }

    @Override
    public void registerTeam(Player player, String prefix, String suffix, int weight) {
        this.core.registerTeam(player,  prefix, suffix, weight);
    }

    @Override
    public void sendUpdate(Player player, Player updated) {
        this.core.sendUpdate(player, updated);
    }
}
