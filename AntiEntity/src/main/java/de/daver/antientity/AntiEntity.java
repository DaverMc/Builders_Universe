package de.daver.antientity;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiEntity extends JavaPlugin {

    @Getter
    private static AntiEntity instance;
    @Getter
    private static EntityWhitelist whitelist;

    @Override
    public void onEnable() {
        instance = this;
        whitelist = new EntityWhitelist();
        getCommand("aereload").setExecutor(new WhitelistReloadCommand());
        Bukkit.getPluginManager().registerEvents(new EntitySpawnListener(), this);
        Bukkit.getLogger().info("Entity-Whitelist loaded!");
    }

}
