package de.buun.haven.scroreboard;

import org.bukkit.entity.Player;

public interface AbstractTablistHeaderFooter {

    void send(Player player);

    void setHeader(String[] header);

    void setFooter(String[] footer);

    default String getJson(String[] array){
        StringBuilder line = new StringBuilder("{\"text\":\"");
        for(String string : array) line.append(string).append("\n");
        return (line.toString().endsWith("\n")) ? line.substring(0, line.length() - 1) : line.toString();
    }

}
