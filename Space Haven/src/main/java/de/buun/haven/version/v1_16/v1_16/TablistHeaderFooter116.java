package de.buun.haven.version.v1_16.v1_16;

import de.buun.haven.scroreboard.AbstractTablistHeaderFooter;
import de.buun.haven.util.PacketSender;
import de.buun.haven.util.Reflections;
import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.entity.Player;

public class TablistHeaderFooter116 implements AbstractTablistHeaderFooter {

    private IChatBaseComponent header;
    private IChatBaseComponent footer;


    @Override
    public void send(Player player) {
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
        Reflections.setFieldValue(packet, "header", header);
        Reflections.setFieldValue(packet, "footer", footer);
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
