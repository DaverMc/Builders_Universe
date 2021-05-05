package de.buun.haven.version.v1_8;

import de.buun.haven.entity.AbstractArmorstand;
import de.buun.haven.entity.armorstand.ArmorstandPart;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

public class Armorstand18 implements AbstractArmorstand {

    private ArmorStand bukkitArmorstand;

    public Armorstand18(Location location){
        this((ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND));
    }

    public Armorstand18(ArmorStand bukkitArmorstand){
        this.bukkitArmorstand = bukkitArmorstand;
        this.bukkitArmorstand.setGravity(false);
        this.bukkitArmorstand.setArms(true);
        this.bukkitArmorstand.setBasePlate(false);
    }

    @Override
    public ItemStack getItem(ArmorstandPart part) {
        return switch (part) {
            case HEAD -> this.bukkitArmorstand.getHelmet();
            case BODY -> this.bukkitArmorstand.getChestplate();
            case LEFT_ARM -> this.bukkitArmorstand.getItemInHand();
            case RIGHT_LEG, LEFT_LEG -> this.bukkitArmorstand.getLeggings();
            case BOOTS -> this.bukkitArmorstand.getBoots();
            default -> null;
        };
    }

    @Override
    public void setItem(ArmorstandPart part, ItemStack stack) {
        switch (part) {
            case HEAD -> this.bukkitArmorstand.setHelmet(stack);
            case BODY -> this.bukkitArmorstand.setChestplate(stack);
            case LEFT_ARM -> this.bukkitArmorstand.setItemInHand(stack);
            case RIGHT_LEG, LEFT_LEG -> this.bukkitArmorstand.setLeggings(stack);
            case BOOTS -> this.bukkitArmorstand.setBoots(stack);
        }
    }

    @Override
    public void move(ArmorstandPart part, double x, double y, double z) {
        switch (part) {
            case HEAD:
                this.bukkitArmorstand.setHeadPose(this.bukkitArmorstand.getHeadPose().add(x, y, z));
                break;
            case BODY:
                this.bukkitArmorstand.setBodyPose(this.bukkitArmorstand.getBodyPose().add(x, y, z));
                break;
            case LEFT_ARM:
                this.bukkitArmorstand.setLeftArmPose(this.bukkitArmorstand.getLeftArmPose().add(x, y, z));
                break;
            case RIGHT_ARM:
                this.bukkitArmorstand.setRightArmPose(this.bukkitArmorstand.getRightArmPose().add(x, y, z));
                break;
            case LEFT_LEG:
                this.bukkitArmorstand.setLeftLegPose(this.bukkitArmorstand.getLeftLegPose().add(x, y, z));
                break;
            case RIGHT_LEG:
                this.bukkitArmorstand.setRightLegPose(this.bukkitArmorstand.getRightLegPose().add(x, y, z));
                break;
            case FULL:
                this.teleport(x,y,z);
                break;
            default:
                break;
        }
    }

    private void teleport(double x ,double y, double z){
        Location oldLocation = this.bukkitArmorstand.getLocation();
        oldLocation.add(x,y,z);
        this.bukkitArmorstand.teleport(oldLocation);
    }

    @Override
    public void reset() {
        bukkitArmorstand.setHeadPose(new EulerAngle(0,0,0));
        bukkitArmorstand.setBodyPose(new EulerAngle(0,0,0));
        bukkitArmorstand.setLeftLegPose(new EulerAngle(0,0,0));
        bukkitArmorstand.setRightLegPose(new EulerAngle(0,0,0));
        bukkitArmorstand.setRightArmPose(new EulerAngle(0,0,0));
        bukkitArmorstand.setLeftArmPose(new EulerAngle(0,0,0));
    }

    @Override
    public void delete() {
        this.bukkitArmorstand.remove();
    }

    @Override
    public void clone(Location location) {
        ArmorStand newAs = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);

        newAs.setHeadPose(bukkitArmorstand.getHeadPose());
        newAs.setBodyPose(bukkitArmorstand.getBodyPose());
        newAs.setLeftArmPose(bukkitArmorstand.getLeftArmPose());
        newAs.setRightArmPose(bukkitArmorstand.getRightArmPose());
        newAs.setLeftLegPose(bukkitArmorstand.getLeftLegPose());
        newAs.setRightLegPose(bukkitArmorstand.getRightLegPose());
        location.setYaw(bukkitArmorstand.getLocation().getYaw());
        location.setPitch(bukkitArmorstand.getLocation().getPitch());
        newAs.teleport(location);

        EntityEquipment newEquip = newAs.getEquipment();
        EntityEquipment oldEquip = bukkitArmorstand.getEquipment();
        if(newEquip == null || oldEquip == null) return;
        newEquip.setHelmet(oldEquip.getHelmet());
        newEquip.setChestplate(oldEquip.getChestplate());
        newEquip.setLeggings(oldEquip.getLeggings());
        newEquip.setBoots(oldEquip.getBoots());
        newEquip.setItemInHand(oldEquip.getItemInHand());

        newAs.setVisible(bukkitArmorstand.isVisible());
        newAs.setSmall(bukkitArmorstand.isSmall());
        newAs.setBasePlate(bukkitArmorstand.hasBasePlate());
        newAs.setGravity(false);
        newAs.setArms(true);
        newAs.setCustomName(bukkitArmorstand.getCustomName());
        newAs.setCustomNameVisible(bukkitArmorstand.isCustomNameVisible());
    }

    @Override
    public boolean setVisibility(boolean visible) {
        boolean old = this.bukkitArmorstand.isVisible();
        this.bukkitArmorstand.setVisible(visible);
        return old;
    }

    @Override
    public boolean setGravity(boolean gravitation) {
        boolean old = this.bukkitArmorstand.isVisible();
        this.bukkitArmorstand.setGravity(gravitation);
        return old;}

    @Override
    public boolean setBasePlate(boolean on) {
        boolean old = this.bukkitArmorstand.isVisible();
        this.bukkitArmorstand.setBasePlate(on);
        return old;
    }

    @Override
    public boolean setSize(boolean small) {
        boolean old = this.bukkitArmorstand.isVisible();
        this.bukkitArmorstand.setSmall(small);
        return old;
    }

    @Override
    public boolean showArms(boolean on) {
        boolean old = this.bukkitArmorstand.isVisible();
        this.bukkitArmorstand.setArms(on);
        return old;
    }
}
