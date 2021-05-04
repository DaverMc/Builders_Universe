package de.buun.haven.version.v1_8.scoreboard;

import de.buun.haven.scroreboard.AbstractTablistHeaderFooter;
import de.buun.haven.util.PacketSender;
import de.buun.haven.util.Reflections;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.entity.Player;

public class TablistHeaderFooter18 implements AbstractTablistHeaderFooter {

    private IChatBaseComponent header;
    private IChatBaseComponent footer;

    @Override
    public void send(Player player) {
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
        Reflections.setFieldValue(packet, "a", header);
        Reflections.setFieldValue(packet, "b", footer);
        PacketSender.send(player, packet);
    }

    @Override
    public void setHeader(String[] header) {
        this.header = IChatBaseComponent.ChatSerializer.a(getJson(header));
    }

    @Override
    public void setFooter(String[] footer) {
        this.footer = IChatBaseComponent.ChatSerializer.a(getJson(footer));
    }
}
