package com.zenika.zenikeats.infrastructure;

import com.zenika.zenikeats.domain.IdGenerator;

import java.util.UUID;

public class UuidGenerator implements IdGenerator {
    @Override
    public String getId() {
        return UUID.randomUUID().toString();
    }
}
