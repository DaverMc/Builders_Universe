package de.daver.antientity;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.vehicle.VehicleCreateEvent;

public class EntitySpawnListener implements Listener {

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event){
        if(isWhitelisted(event.getEntity())) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onVehicleSpawn(VehicleCreateEvent event){
        if(isWhitelisted(event.getVehicle())) return;
        event.setCancelled(true);
    }

    private boolean isWhitelisted(Entity entity){
        return AntiEntity.getWhitelist().getWhitelisted().contains(entity.getType());
    }
}
