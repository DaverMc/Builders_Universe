package de.buun.haven.lang.json;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MessageType {

    CHAT((byte) 0),
    SYSTEM_MESSAGE((byte) 1),
    HOTBAR((byte) 2);

    @Getter
    private final byte position;

}
