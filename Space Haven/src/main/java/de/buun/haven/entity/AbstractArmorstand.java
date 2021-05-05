package de.buun.haven.entity;

import de.buun.haven.entity.armorstand.ArmorstandPart;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public interface AbstractArmorstand {

    ItemStack getItem(ArmorstandPart part);

    void setItem(ArmorstandPart part, ItemStack stack);

    void move(ArmorstandPart part, double x, double y, double z);

    void reset();

    void delete();

    void clone(Location location);

    boolean setVisibility(boolean visible);

    boolean setGravity(boolean gravitation);

    boolean setBasePlate(boolean on);

    boolean setSize(boolean small);

    boolean showArms(boolean on);

}
