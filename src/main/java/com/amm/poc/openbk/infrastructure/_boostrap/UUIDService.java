package com.amm.poc.openbk.infrastructure._boostrap;

import java.util.UUID;

public class UUIDService {
    public UUID randomUUID() {
        return UUID.randomUUID();
    }

    public UUID fromString(String uuid) {
        return UUID.fromString(uuid);
    }
}
