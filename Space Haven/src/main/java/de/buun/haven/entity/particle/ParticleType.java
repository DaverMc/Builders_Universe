package de.buun.haven.entity.particle;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ParticleType {

    TEST(0, "test");


    private final int id;

    private final String name;


}
