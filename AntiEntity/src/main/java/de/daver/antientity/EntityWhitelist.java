package de.daver.antientity;

import lombok.Getter;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class EntityWhitelist extends ConfigurationFile{

    @Getter
    private List<EntityType> whitelisted;

    protected EntityWhitelist() {
        super(AntiEntity.getInstance().getDataFolder(), "entity-whitelist");
        searchWhitelisted();
    }

    public void searchWhitelisted(){
        whitelisted = new ArrayList<>();
        for (String name : this.yaml.getKeys(false)){
            if(!this.yaml.getBoolean(name)) continue;
            whitelisted.add(EntityType.valueOf(name));
        }
    }

    protected void onCreation() {
        for (EntityType type : EntityType.values()){
            this.yaml.set(type.name(), true);
        }
        save();
    }
}
