package de.buun.haven.version.v1_8;

import de.buun.haven.entity.particle.AbstractParticle;
import de.buun.haven.entity.particle.ParticleType;
import de.buun.haven.util.PacketSender;
import de.buun.haven.util.Reflections;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Particle18 implements AbstractParticle {

    private PacketPlayOutWorldParticles packet;

    public Particle18(ParticleType type){
        packet = new PacketPlayOutWorldParticles();
        Reflections.setFieldValue(packet, "j", true);
        Reflections.setFieldValue(packet, "a", EnumParticle.a(type.getId()));
    }

    public void setAmount(int amount){
        Reflections.setFieldValue(packet, "i", amount);
    }

    public void setSpeed(float speed){
        Reflections.setFieldValue(packet, "h", speed);
    }

    public void setOffset(double x, double y, double z){
        Reflections.setFieldValue(packet, "e", (float) x);
        Reflections.setFieldValue(packet, "f", (float) y);
        Reflections.setFieldValue(packet, "g", (float) z);
    }

    public void setLocation(Location location){
        Reflections.setFieldValue(packet, "b", (float) location.getX());
        Reflections.setFieldValue(packet, "c", (float) location.getY());
        Reflections.setFieldValue(packet, "d", (float) location.getZ());
    }

    @Override
    public void spawn(Player player) {
        PacketSender.send(player, packet);
    }
}
