package com.mj.core.springboot.model;

import java.util.UUID;

public class UniqueIdentifier {

    private final UUID uuid;

    private UniqueIdentifier(UniqueIdentifierBuilder uniqueIdentifierBuilder) {
        this.uuid = uniqueIdentifierBuilder.uuid;
    }

    public static UniqueIdentifier of(String uuid) {
        return new UniqueIdentifier(new UniqueIdentifierBuilder(uuid));
    }

    public static UniqueIdentifier generateUUID() {
        return new UniqueIdentifierBuilder(UUID.randomUUID()).build();
    }

    public static UniqueIdentifierBuilder builder(String uuid) {
        return new UniqueIdentifierBuilder(uuid);
    }

    public UUID getUniqueIdentifier() {
        return uuid;
    }

    @Override
    public String toString() {
        return uuid.toString();
    }

    private static class UniqueIdentifierBuilder {
        private final UUID uuid;

        private UniqueIdentifierBuilder(UUID uuid) {
            this.uuid = uuid;
        }

        /**
         * @param uuid as string
         */
        private UniqueIdentifierBuilder(String uuid) {
            this.uuid = UUID.fromString(uuid);
        }

        public UniqueIdentifier build() {
            return new UniqueIdentifier(this);
        }
    }
}
