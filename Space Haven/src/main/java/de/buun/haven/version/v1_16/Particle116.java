package de.buun.haven.version.v1_16;

import de.buun.haven.entity.particle.AbstractParticle;
import net.minecraft.server.v1_16_R3.PacketPlayOutWorldParticles;
import org.bukkit.Location;

public class Particle116 implements AbstractParticle {

    public Particle116(){
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles();
    }

    @Override
    public void spawn(Location location) {

    }
}
