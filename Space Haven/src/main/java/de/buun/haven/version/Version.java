package de.buun.haven.version;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Version {

    V1_8("v1_8_R3"),
    V1_16("v1_16_R3");

    @Getter
    private final String bukkitName;

    public static Version getVersion(String bukkitVersion){
        for (Version v : Version.values()){
            if(bukkitVersion.equals(v.bukkitName)) return v;
        }
        return null;
    }

}
