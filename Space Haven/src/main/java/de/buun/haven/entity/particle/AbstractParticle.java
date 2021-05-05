package de.buun.haven.entity.particle;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface AbstractParticle {

    void spawn(Player player);

    void setLocation(Location location);

    void setOffset(double x, double y, double z);

    void setSpeed(float speed);

    void setAmount(int amount);
}
