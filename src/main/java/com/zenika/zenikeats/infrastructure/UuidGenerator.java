package com.zenika.zenikeats.infrastructure;

import com.zenika.zenikeats.domain.IdGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidGenerator implements IdGenerator {
    @Override
    public String getId() {
        return UUID.randomUUID().toString();
    }
}
