package de.buun.haven.entity.particle;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.server.v1_16_R3.*;

@RequiredArgsConstructor
public enum ParticleType {

    ASH(0, ""),
    AMBIENT_ENTITY_EFFECT(0, ""),
    ANGRY_VILLAGER(20, "angryVillager"),
    BARRIER(35, "barrier"),
    BLOCK(3, ""),
    BUBBLE(4, "bubble"),
    CLOUD(29, "cloud"),
    CRIT(9, "crit"),
    DAMAGE_INDICATOR(7, ""),
    DRAGON_BREATH(8, ""),
    DRIPPING_LAVA(19, "dripLava"),
    FALLING_LAVA(0, ""),
    LANDING_LAVA(0, ""),
    DRIPPING_WATER(18, "dripWater"),
    FALLING_WATER(39, "droplet"),
    DUST(30, "reddust"),
    EFFECT(0, ""),
    ELDER_GUARDIAN(0, ""),
    ENCHANTED_HIT(10, "critMagic"),
    ENCHANT(25, "enchantmenttable"),
    END_ROD(0, ""),
    ENTITY_EFFECT(0, ""),
    EXPLOSION_EMITTER(1, "explode"),
    EXPLOSION(0, "largeexplode"),
    FALLING_DUST(38, "blockdust_"),
    FIREWORK(3, "fireworkSpark"),
    FISHING(0, ""),
    FLAME(26, "flame"),
    SOUL_FIRE_FLAME(26, "flame"),
    SOUL(0, ""),
    FLASH(0, ""),
    HAPPY_VILLAGER(21, "happyVillager"),
    COMPOSTER(0, ""),
    HEART(34, "heart"),
    INSTANT_EFFECT(0, ""),
    ITEM(0, ""),
    ITEM_SLIME(33, "slime"),
    ITEM_SNOWBALL(31, "snowballpoof"),
    LARGE_SMOKE(12, "largesmoke"),
    LAVA(27, "lava"),
    MYCELIUM(0, ""),
    NOTE(23, "note"),
    POOF(0, ""),
    PORTAL(24, "portal"),
    RAIN(0, ""),
    SMOKE(11, "smoke"),
    SNEEZE(0, ""),
    SPIT(0, ""),
    SQUID_INK(0, ""),
    SWEEP_ATTACK(0, ""),
    TOTEM_OF_UNDYING(0, ""),
    UNDERWATER(0, ""),
    SPLASH(5, "splash"),
    WITCH(0, ""),
    BUBBLE_POP(0, ""),
    CURRENT_DOWN(0, ""),
    BUBBLE_COLUMN_UP(0, ""),
    NAUTILUS(0, ""),
    DOLPHIN(0, ""),
    CAMPFIRE_COSY_SMOKE(0, ""),
    CAMPFIRE_SIGNAL_SMOKE(0, ""),
    DRIPPING_HONEY(0, ""),
    FALLING_HONEY(0, ""),
    LANDING_HONEY(0, ""),
    FALLING_NECTAR(0, ""),
    CRIMSON_SPORE(0, ""),
    WARPED_SPORE(0, ""),
    DRIPPING_OBSIDIAN_TEAR(0, ""),
    FALLING_OBSIDIAN_TEAR(0, ""),
    LANDING_OBSIDIAN_TEAR(0, ""),
    REVERSE_PORTAL(0, ""),
    WHITE_ASH(0, "");

    @Getter
    private final int id;

    private final String name;

    public static ParticleParam get116(ParticleType type){
        return switch (type){
            case ASH -> Particles.ASH;
            case AMBIENT_ENTITY_EFFECT -> Particles.AMBIENT_ENTITY_EFFECT;
            case ANGRY_VILLAGER -> Particles.ANGRY_VILLAGER;
            case BARRIER -> Particles.BARRIER;
            case BUBBLE -> Particles.BUBBLE;
            case BUBBLE_COLUMN_UP -> Particles.BUBBLE_COLUMN_UP;
            case BUBBLE_POP -> Particles.BUBBLE_POP;
            case CAMPFIRE_COSY_SMOKE -> Particles.CAMPFIRE_COSY_SMOKE;
            case CAMPFIRE_SIGNAL_SMOKE -> Particles.CAMPFIRE_SIGNAL_SMOKE;
            case CLOUD -> Particles.CLOUD;
            case COMPOSTER -> Particles.COMPOSTER;
            case CRIMSON_SPORE -> Particles.CRIMSON_SPORE;
            case CRIT -> Particles.CRIT;
            case CURRENT_DOWN -> Particles.CURRENT_DOWN;
            case DAMAGE_INDICATOR -> Particles.DAMAGE_INDICATOR;
            case DOLPHIN -> Particles.DOLPHIN;
            case DRAGON_BREATH -> Particles.DRAGON_BREATH;
            case DRIPPING_HONEY -> Particles.DRIPPING_HONEY;
            case DRIPPING_LAVA -> Particles.DRIPPING_LAVA;
            case DRIPPING_OBSIDIAN_TEAR -> Particles.DRIPPING_OBSIDIAN_TEAR;
            case DRIPPING_WATER -> Particles.DRIPPING_WATER;
            case EFFECT -> Particles.EFFECT;
            case ELDER_GUARDIAN -> Particles.ELDER_GUARDIAN;
            case ENCHANT -> Particles.ENCHANT;
            case ENCHANTED_HIT -> Particles.ENCHANTED_HIT;
            case END_ROD -> Particles.END_ROD;
            case ENTITY_EFFECT -> Particles.ENTITY_EFFECT;
            case EXPLOSION -> Particles.EXPLOSION;
            case EXPLOSION_EMITTER -> Particles.EXPLOSION_EMITTER;
            case FALLING_HONEY -> Particles.FALLING_HONEY;
            case FALLING_LAVA -> Particles.FALLING_LAVA;
            case FALLING_NECTAR -> Particles.FALLING_NECTAR;
            case FALLING_OBSIDIAN_TEAR -> Particles.FALLING_OBSIDIAN_TEAR;
            case FALLING_WATER -> Particles.FALLING_WATER;
            case FIREWORK -> Particles.FIREWORK;
            case FISHING -> Particles.FISHING;
            case FLAME -> Particles.FLAME;
            case FLASH -> Particles.FLASH;
            case HAPPY_VILLAGER -> Particles.HAPPY_VILLAGER;
            case HEART -> Particles.HEART;
            case INSTANT_EFFECT -> Particles.INSTANT_EFFECT;
            case ITEM_SLIME -> Particles.ITEM_SLIME;
            case ITEM_SNOWBALL -> Particles.ITEM_SNOWBALL;
            case LANDING_HONEY -> Particles.LANDING_HONEY;
            case LANDING_LAVA -> Particles.LANDING_LAVA;
            case LANDING_OBSIDIAN_TEAR -> Particles.LANDING_OBSIDIAN_TEAR;
            case LARGE_SMOKE -> Particles.LARGE_SMOKE;
            case LAVA -> Particles.LAVA;
            case MYCELIUM -> Particles.MYCELIUM;
            case NAUTILUS -> Particles.NAUTILUS;
            case NOTE -> Particles.NOTE;
            case POOF -> Particles.POOF;
            case PORTAL -> Particles.PORTAL;
            case RAIN -> Particles.RAIN;
            case REVERSE_PORTAL -> Particles.REVERSE_PORTAL;
            case SMOKE -> Particles.SMOKE;
            case SNEEZE -> Particles.SNEEZE;
            case SOUL -> Particles.SOUL;
            case SOUL_FIRE_FLAME -> Particles.SOUL_FIRE_FLAME;
            case SPIT -> Particles.SPIT;
            case SPLASH -> Particles.SPLASH;
            case SQUID_INK -> Particles.SQUID_INK;
            case SWEEP_ATTACK -> Particles.SWEEP_ATTACK;
            case TOTEM_OF_UNDYING -> Particles.TOTEM_OF_UNDYING;
            case UNDERWATER -> Particles.UNDERWATER;
            case WARPED_SPORE -> Particles.WARPED_SPORE;
            case WHITE_ASH -> Particles.WHITE_ASH;
            case WITCH -> Particles.WITCH;
            default -> Particles.WARPED_SPORE;
        };
    }

}
