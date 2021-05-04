package de.buun.haven.util;

import de.buun.haven.version.Version;
import de.buun.haven.version.VersionManager;
import net.minecraft.server.v1_16_R3.Packet;
import net.minecraft.server.v1_16_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PacketSender {

    private PacketSender(){}

    public static void send(Player player, Object packet){
        if (VersionManager.getVersion() == Version.V1_8) {
            send18(packet, player);
        } else if (VersionManager.getVersion() == Version.V1_16) {
            send116(packet, player);
        }
    }

    public static void sendPackets(Player player, Object...packets){
        for (Object packet : packets){
            send(player, packet);
        }
    }

    public static void broadcast(Object...packets){
        Bukkit.getOnlinePlayers().forEach(player -> send(player, packets));
    }


    private static void send18(Object packet, Player player){
        net.minecraft.server.v1_8_R3.PlayerConnection connection = ((org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer) player).getHandle().playerConnection;
        connection.sendPacket((net.minecraft.server.v1_8_R3.Packet<?>) packet);
    }

    private static void send116(Object packet, Player player){
        PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
        connection.sendPacket((Packet<?>) packet);
    }

}
