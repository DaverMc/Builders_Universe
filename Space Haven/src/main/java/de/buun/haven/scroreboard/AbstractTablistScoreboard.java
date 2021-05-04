package de.buun.haven.scroreboard;

import org.bukkit.entity.Player;

public interface AbstractTablistScoreboard {

    void unregisterTeam(Player player);

    void registerTeam(Player player, String prefix, String suffix, int weight);

    void sendUpdate(Player player, Player updated);

    default String getJson(String text){
        return "{\"text\":\"" + text + "\"}";
    }
}
