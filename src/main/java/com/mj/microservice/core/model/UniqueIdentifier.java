package com.mj.microservice.core.model;

import java.util.UUID;

public final class UniqueIdentifier {

    private final UUID uuid;

    private UniqueIdentifier(String uuid) {
        this.uuid = UUID.fromString(uuid);
    }

    private UniqueIdentifier(UUID uuid) {
        this.uuid = uuid;
    }

    private UniqueIdentifier(UniqueIdentifier uniqueIdentifier) {
        this.uuid = uniqueIdentifier.uuid;
    }

    public static UniqueIdentifier parse(String uuid) {
        return new UniqueIdentifier(uuid);
    }

    public static UniqueIdentifier generateUUID() {
        return new UniqueIdentifier(UUID.randomUUID());
    }

    public UUID getUniqueIdentifier() {
        return uuid;
    }

    @Override
    public String toString() {
        return uuid.toString();
    }
}
