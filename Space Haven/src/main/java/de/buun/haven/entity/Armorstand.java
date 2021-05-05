package de.buun.haven.entity;

import de.buun.haven.entity.armorstand.ArmorstandPart;
import de.buun.haven.item.Item;
import de.buun.haven.item.ItemBuilder;
import de.buun.haven.version.VersionManager;
import de.buun.haven.version.v1_16.Armorstand116;
import de.buun.haven.version.v1_8.Armorstand18;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

public class Armorstand {

    private final AbstractArmorstand core;

    public Armorstand(Location location){
        this.core = VersionManager.newInstance(AbstractArmorstand.class, Armorstand18.class, Armorstand116.class, location);
    }

    public Armorstand(ArmorStand bukkitArmorstand){
        this.core = VersionManager.newInstance(AbstractArmorstand.class, Armorstand18.class, Armorstand116.class, bukkitArmorstand);
    }

    public void setItem(ArmorstandPart part, Item item){
        core.setItem(part, item.toStack());
    }

    public Item getItem(ArmorstandPart part){
        return ItemBuilder.fromItemStack(this.core.getItem(part));
    }

    public void moveX(ArmorstandPart part, double multiplier){
        move(part, multiplier, 0 ,0);
    }

    public void moveY(ArmorstandPart part, double multiplier){
        move(part, 0, multiplier, 0);
    }

    public void moveZ(ArmorstandPart part, double multiplier){
        move(part, 0, 0 , multiplier);
    }

    public void move(ArmorstandPart part, double x, double y, double z){
        this.core.move(part, x, y, z);
    }

    public boolean setMeta(ArmorstandMeta type, boolean value){
        return switch (type){
            case SMALL -> this.core.setSize(value);
            case GRAVITY -> this.core.setGravity(value);
            case ARMS_SHOWN -> this.core.showArms(value);
            case BASE_PLATE -> this.core.setBasePlate(value);
            case VISIBILITY -> this.core.setVisibility(value);
        };
    }

    public void clone(Location location){
        this.core.clone(location);
    }

    public enum ArmorstandMeta{
        BASE_PLATE,
        VISIBILITY,
        GRAVITY,
        ARMS_SHOWN,
        SMALL;
    }
}
