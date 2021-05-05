package de.buun.haven.version.v1_16;

import de.buun.haven.entity.particle.AbstractParticle;
import de.buun.haven.entity.particle.ParticleType;
import de.buun.haven.util.PacketSender;
import de.buun.haven.util.Reflections;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Particle116 implements AbstractParticle {

    private final PacketPlayOutWorldParticles packet;

    public Particle116(ParticleType type){
        this.packet = new PacketPlayOutWorldParticles();
        ParticleParam particleParam = ParticleType.get116(type);

        Reflections.setFieldValue(packet, "i", true);
        Reflections.setFieldValue(packet, "a", particleParam);
    }

    @Override
    public void spawn(Player player) {
        PacketSender.send(player, this.packet);
    }

    @Override
    public void setLocation(Location location) {
        Reflections.setFieldValue(packet, "a", location.getX());
        Reflections.setFieldValue(packet, "b", location.getY());
        Reflections.setFieldValue(packet, "c", location.getZ());
    }

    @Override
    public void setOffset(double x, double y, double z) {
        Reflections.setFieldValue(packet, "d", (float) x);
        Reflections.setFieldValue(packet, "e", (float) y);
        Reflections.setFieldValue(packet, "f", (float) z);
    }

    @Override
    public void setSpeed(float speed) {
        Reflections.setFieldValue(packet, "g", speed);
    }

    @Override
    public void setAmount(int amount) {
        Reflections.setFieldValue(packet, "h", amount);
    }
}
