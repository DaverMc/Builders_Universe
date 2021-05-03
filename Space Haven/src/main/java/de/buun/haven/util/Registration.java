package de.buun.haven.util;

public interface Registration<T> {

    void register(T value);

    void unregister(T value);

}
