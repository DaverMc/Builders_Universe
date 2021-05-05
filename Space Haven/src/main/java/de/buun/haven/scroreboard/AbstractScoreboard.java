package de.buun.haven.scroreboard;

import org.bukkit.entity.Player;

public interface AbstractScoreboard {

    void send(Player player);

    void setValue(int index, String value);

    String getValue(int index);

}
